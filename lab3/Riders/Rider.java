package Riders;
import Elevators.AbstractElevator;
import Elevators.InfiniteElevator;
import EventBarriers.EventBarrier;
import EventBarriers.FloorEventBarrier;
import Main.Building;
import Main.Floor;


public class Rider extends Thread{
	
	private Building myBuilding;
	private EventBarrier myBarrier;
	private int currentLevel;
	private int destinationLevel;
	private InfiniteElevator myElevator;
	private int myId;
	private String myName;
	
	public Rider(int id, Building b, int floor) {
		myBarrier = null;
		currentLevel = floor;
		myBuilding = b;
		myElevator = null;
		myId = id;
		myName = "R" + id;
	}
	
	public void setDestination(int n) {
		destinationLevel = n;
	}
	
	public void run() {
		Floor currentFloor = myBuilding.getFloor(currentLevel);
		myBuilding.requestElevator(this);
		FloorEventBarrier myEventBarrier = 
			(FloorEventBarrier) currentFloor.getEventBarrier(currentLevel - destinationLevel);
		
		printPushButton(currentLevel - destinationLevel);
		
		myEventBarrier.arrive(); // wait for elevator to arrive
		// get in elevator and do shit
		myElevator = myEventBarrier.getElevator();
		//System.out.println("SDFSDFSDF");
		//System.out.println(myElevator.getClass().getName());
		//System.out.println(myElevator.Enter());
		
		if (myElevator.Enter()) {
			int difference = currentLevel - destinationLevel;
			myBuilding.getFloor(currentLevel).decrementWaiter(difference);
			
			//System.out.println("SDFSDFSDF");
			
			printEnterElevator(myElevator, currentFloor);
			
			myElevator.RequestFloor(destinationLevel);
			myEventBarrier.complete();	// signal to event barrier for up/down on the floor
			myElevator.getElevatorWaitingBarrier(destinationLevel).arrive(); // wait inside
			
			printExitElevator(myElevator, myBuilding.getFloor(destinationLevel));

			myElevator.Exit(); // get out
			myElevator.getElevatorWaitingBarrier(destinationLevel).complete(); // signal get out
			currentLevel = destinationLevel; // update riders location
			myElevator = null; // now doesn't have an elevator
			myBarrier = null; // now doesn't have a wait barrier
			
		}
		
		// **** IMPLEMENT THE LOOP FOR FILE READING NOW ****

		
	}
	
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public int getCurrentDestinationLevel() {
		return destinationLevel;
	}
	
	
	// ***** PRINT METHODS *****
	
	
	private void printPushButton(int i) {
		String s = "D";
		if (i < 0) s = "U";
		System.out.println(myName + " pushes " + s + currentLevel);
	}
	
	private void printEnterElevator(InfiniteElevator e, Floor f) {
		System.out.println(myName + " enters " + e.getStringName() + " on " + f.getName());
		System.out.println(myName + " pushes " + e.getStringName() + "B" + destinationLevel);
	}
	
	private void printExitElevator(InfiniteElevator e, Floor f) {
		System.out.println(myName + " exits " + e.getStringName() + " on " + f.getName());
	}
	
	
}

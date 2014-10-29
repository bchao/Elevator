package Riders;
import Elevators.AbstractElevator;
import EventBarriers.EventBarrier;
import EventBarriers.FloorEventBarrier;
import Main.Building;
import Main.Floor;


public class Rider extends Thread{
	
	private Building myBuilding;
	private EventBarrier myBarrier;
	private int currentLevel;
	private int destinationLevel;
	private AbstractElevator myElevator;
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
		System.out.println("SDFSDFSDF");
		System.out.println(myElevator.getClass().getName());
		//System.out.println(myElevator.Enter());
		
		if (myEventBarrier.hasRoom()) {
			System.out.println("SDFSDFSDF");
			
			printEnterElevator(myElevator, currentFloor);
			
			myElevator.RequestFloor(destinationLevel);
			myEventBarrier.complete(); // signal to event barrier for up/down on the floor
			myElevator.getElevatorWaitingBarrier(destinationLevel).arrive(); // wait inside
			
			printExitElevator(myBuilding.getFloor(destinationLevel));

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
	
	private void printEnterElevator(AbstractElevator e, Floor f) {
		System.out.println(myName + " enters " + e.getName() + " on " + f.getName());
		System.out.println(myName + " pushes " + e.getName() + "B" + destinationLevel);
	}
	
	private void printExitElevator(Floor f) {
		System.out.println(myName + " exits " + myElevator.getName() + " on " + f.getName());
	}
	
	
}

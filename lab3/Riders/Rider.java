package Riders;
import java.util.ArrayList;
import java.util.List;

import Buildings.Building;
import Elevators.AbstractElevator;
import Elevators.InfiniteElevator;
import EventBarriers.EventBarrier;
import EventBarriers.FloorEventBarrier;
import Main.Floor;
import Main.Parser;


public class Rider extends Thread{

	private Building myBuilding;
	private EventBarrier myBarrier;
	private int currentLevel;
	private int destinationLevel;
	private InfiniteElevator myElevator;
	private int myId;
	private String myName;
	private int waitNum = 0;
	private List<Integer> myDestinations;

	public Rider(int id, Building b, int floor, List<Integer> d) {
		myBarrier = null;
		currentLevel = floor;
		myBuilding = b;
		myElevator = null;
		myId = id;
		myName = "R" + id;
		myDestinations = d;	
	}

	public Rider(int id, Building b, int floor) {
		myBarrier = null;
		currentLevel = floor;
		myBuilding = b;
		myElevator = null;
		myId = id;
		myName = "R" + id;
		myDestinations = new ArrayList<Integer>();
	}

	public void addDestination(int n) {
		//destinationLevel = n;
		myDestinations.add(n);
	}

	public void run() {
		
		for (int i = 0; i < myDestinations.size(); i++) {
			
			destinationLevel = myDestinations.get(i);
			
			boolean gotOn = false;
			Floor currentFloor = myBuilding.getFloor(currentLevel);
			myBuilding.requestElevator(this);
			
			printPushButton(currentLevel - destinationLevel);
			
			FloorEventBarrier myEventBarrier = 
					(FloorEventBarrier) currentFloor.getEventBarrier(currentLevel - destinationLevel);


			while (!gotOn) {

				myEventBarrier.arrive(); // wait for elevator to arrive
				// get in elevator and do shit
				myElevator = myEventBarrier.getElevator();

				//System.out.println(myEventBarrier.);

				if (myElevator.Enter()) {
					gotOn = true;
					int difference = currentLevel - destinationLevel;
					myBuilding.getFloor(currentLevel).decrementWaiter(difference);

					printEnterElevator(myElevator, currentFloor);

					myElevator.RequestFloor(destinationLevel);

					myEventBarrier.complete();	// signal to event barrier for up/down on the floor

					myElevator.getElevatorWaitingBarrier(destinationLevel).arrive(); // wait inside


					myElevator.Exit(); // get out
					
					printExitElevator(myElevator, myBuilding.getFloor(destinationLevel));

					myElevator.getElevatorWaitingBarrier(destinationLevel).complete(); // signal get out
					currentLevel = destinationLevel; // update riders location

					// MIGHT NEED TO BE SET TO NULL AGAIN
					//myElevator = null; // now doesn't have an elevator
				}

				if (!gotOn) {
					myEventBarrier.decrementNumThread();
					if (waitNum == 0) printDidNotGetOn(myElevator, currentFloor);
					waitNum++;
				}

			}

			waitNum = 0;
			// **** IMPLEMENT THE LOOP FOR FILE READING NOW ****

		}
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
		Parser.writer.println(myName + " pushes " + s + currentLevel);
	}

	private void printEnterElevator(InfiniteElevator e, Floor f) {
		Parser.writer.println(myName + " enters " + e.getStringName() + " on " + f.getName());
		Parser.writer.println(myName + " pushes " + e.getStringName() + "B" + destinationLevel);
	}

	private void printExitElevator(InfiniteElevator e, Floor f) {
		Parser.writer.println(myName + " exits " + e.getStringName() + " on " + f.getName());
	}

	private void printDidNotGetOn(InfiniteElevator e, Floor f) {
		Parser.writer.println(myName + " did not get on " + e.getStringName() + " on " + f.getName());
	}


}

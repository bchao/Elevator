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

	protected Building myBuilding;
	protected EventBarrier myBarrier;
	protected int currentLevel;
	protected int destinationLevel;
	protected InfiniteElevator myElevator;
	protected int myId;
	protected String myName;
	protected int waitNum = 0;
	protected List<Integer> myDestinations;

	public Rider(int id, Building b, int floor, List<Integer> d) {
		myBarrier = null;
		currentLevel = floor;
		myBuilding = b;
		myElevator = null;
		myId = id;
		myName = "R" + (id + 1);
		myDestinations = d;	
	}

	public Rider(int id, Building b, int floor) {
		myBarrier = null;
		currentLevel = floor;
		myBuilding = b;
		myElevator = null;
		myId = id;
		myName = "R" + (id + 1);
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
					printRequestFloor(myElevator, currentFloor);

					myElevator.RequestFloor(destinationLevel);

					myEventBarrier.complete();	// signal to event barrier for up/down on the floor

					myElevator.getElevatorWaitingBarrier(destinationLevel).arrive(); // wait inside


					myElevator.Exit(); // get out					
					
					printExitElevator(myElevator, myBuilding.getFloor(destinationLevel));
					
					myBuilding.tripFinished();

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
		
//		Parser.writer.close();
	}


	public int getCurrentLevel() {
		return currentLevel;
	}

	public int getCurrentDestinationLevel() {
		return destinationLevel;
	}


	// ***** PRINT METHODS *****


	protected void printPushButton(int i) {
		String s = "D";
		if (i < 0) s = "U";
		Parser.writer.println(myName + " pushes " + s + (currentLevel + 1));
	}

	protected void printEnterElevator(InfiniteElevator e, Floor f) {
		Parser.writer.println(myName + " enters " + e.getStringName() + " on " + f.getName());
	}
	
	protected void printRequestFloor(InfiniteElevator e, Floor f) {
		Parser.writer.println(myName + " pushes " + e.getStringName() + "B" + (destinationLevel + 1));
	}

	protected void printExitElevator(InfiniteElevator e, Floor f) {
		Parser.writer.println(myName + " exits " + e.getStringName() + " on " + f.getName());
	}

	protected void printDidNotGetOn(InfiniteElevator e, Floor f) {
		Parser.writer.println(myName + " did not get on " + e.getStringName() + " on " + f.getName());
	}


}

package Riders;

import java.util.List;

import Buildings.Building;
import EventBarriers.FloorEventBarrier;
import Main.Floor;

public class Joker extends Rider {
	
	private boolean noWait;
	private boolean noRequest;
	private boolean noExit;
	
	public Joker(int id, Building b, int floor, String jokerBehavior) {
		super(id, b, floor);
		if (jokerBehavior.equals("NO_WAIT")) {
			noWait = true;
		} else if (jokerBehavior.equals("NO_REQUEST")) {
			noRequest = true;
		} else if (jokerBehavior.equals("NO_EXIT")) {
			noExit = true;
		}
	}

	public Joker(int id, Building b, int floor, List<Integer> d, String jokerBehavior) {
		super(id, b, floor, d);
		if (jokerBehavior.equals("NO_WAIT")) {
			noWait = true;
		} else if (jokerBehavior.equals("NO_REQUEST")) {
			noRequest = true;
		} else if (jokerBehavior.equals("NO_EXIT")) {
			noExit = true;
		}
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
				if (noWait) {
					myElevator.fakeEnter();
					int difference = currentLevel - destinationLevel;
					myBuilding.getFloor(currentLevel).decrementWaiter(difference);
					myEventBarrier.complete();
					myBuilding.tripFinished();
					break;
				}
				//System.out.println(myEventBarrier.);

				if (myElevator.Enter()) {
					gotOn = true;
					int difference = currentLevel - destinationLevel;
					myBuilding.getFloor(currentLevel).decrementWaiter(difference);

					printEnterElevator(myElevator, currentFloor);
					
					if (!noRequest) {
						printRequestFloor(myElevator, currentFloor);
						myElevator.RequestFloor(destinationLevel);
					}

					myEventBarrier.complete();	// signal to event barrier for up/down on the floor
					
					if (!noRequest) {
						myElevator.getElevatorWaitingBarrier(destinationLevel).arrive(); // wait inside
					}

					myElevator.Exit(); // get out	
					if (!noExit && !noRequest) {
						printExitElevator(myElevator, myBuilding.getFloor(destinationLevel));
					}
					
					myBuilding.tripFinished();

					myElevator.getElevatorWaitingBarrier(destinationLevel).complete(); // signal get out
					currentLevel = destinationLevel; // update riders location

					// MIGHT NEED TO BE SET TO NULL AGAIN
					//myElevator = null; // now doesn't have an elevator
				}

				if (!gotOn && !noWait) {
					myEventBarrier.decrementNumThread();
					if (waitNum == 0) printDidNotGetOn(myElevator, currentFloor);
					waitNum++;
				}
			}
			waitNum = 0;
			// **** IMPLEMENT THE LOOP FOR FILE READING NOW ****
		}
		
	}
}

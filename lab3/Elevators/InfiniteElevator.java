package Elevators;

import java.util.HashSet;
import java.util.Set;

import Buildings.Building;
import EventBarriers.FloorEventBarrier;
import Main.Direction;
import Main.Floor;
import Main.Parser;
import Riders.Rider;

/**
 * Class that extends AbstractElevator and implements the methods needed to transport
 * riders. There is NO BOUND on the number of riders it can hold
 *
 */

public class InfiniteElevator extends AbstractElevator {

	protected int currentLevel;
	protected int numOccupants;
	protected Set<Integer> myDestinations;
	protected Building myBuilding;
	protected Direction myDir;

	public InfiniteElevator(int numFloors, int elevatorId, int maxOccupancyThreshold, 
														Building building) {
		
		super(numFloors, elevatorId, maxOccupancyThreshold);
		currentLevel = 0;
		numOccupants = 0;
		myDestinations = new HashSet<Integer>();
		myBuilding = building;
		myDir = Direction.UP;
	}
	
	public void run() {
		while(true) {
			
			waitForRequests();
			
			Floor currentFloor = myBuilding.getFloor(currentLevel);



			if ((myDestinations.contains(currentLevel) || 
					currentFloor.peopleWaiting(myDir)) && elevatorNotAlreadyThere(currentFloor)) {
				VisitFloor(currentLevel);
			}
						
			changeLevel();
			
			// go idle if there is no one requesting a floor in the building

			
		}
	}

	private synchronized boolean elevatorNotAlreadyThere(Floor currentFloor) {
		return currentFloor.getEventBarrier(myDir).getElevator() == null;		
	}

	private synchronized void waitForRequests() {
		//System.out.println(!myBuilding.peopleWaiting());
		notifyAll();
		while (!myBuilding.peopleWaiting()) {
			try {
				//System.out.println(!myBuilding.peopleWaiting());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		notifyAll();
	}


		// was synch
	private void changeLevel() {
		if (currentLevel == myBuilding.getMaxLevel()) myDir = Direction.DOWN;
		if (currentLevel == 0) myDir = Direction.UP;
		
		printMoveToNewLevel();
		
		currentLevel += myDir.getDir();
	}

	public void OpenDoors() {
		
		printDoorsOpen();
		
		myRiderEventBarriers.get(currentLevel).raise(); // let the riders out!
		Floor currentFloor = myBuilding.getFloor(currentLevel);
		if (currentLevel != myBuilding.getMaxLevel() && currentLevel != 0) 
			currentFloor.getEventBarrier(myDir).raise(this); // notify those waiting that 'vator is here
		else currentFloor.getEventBarrier(myDir.getOppositeDir()).raise(this);

	}

	// was not before
	public synchronized void CloseDoors() {
		
		printDoorsClose();
		
		myDestinations.remove(currentLevel);
	}

	public void VisitFloor(int floor) {
		OpenDoors();
		CloseDoors();
	}

	
	public synchronized boolean Enter() {
		if (!isFull()) {
			numOccupants++;
			return true;
		}
		else return isFull();
		
	}

	public synchronized void Exit() {
		numOccupants--;
		myBuilding.tripFinished();
		myBuilding.setGlobalNumPeopleWaiting(-1);
	}
	

	public synchronized void RequestFloor(int floor) {
		myDestinations.add(floor);
	}
	
	public boolean isFull() {
		return false;
	}

	
	// ***** PRINT METHODS ****
	
	private void printDoorsOpen() {
		Parser.writer.println(myName + " on " + myBuilding.getFloor(currentLevel).getName() 
							 + " opens");
	}
	
	private void printDoorsClose() {
		Parser.writer.println(myName + " on " + myBuilding.getFloor(currentLevel).getName() 
							 + " closes");
	}
	
	private void printMoveToNewLevel() {
		Parser.writer.println(myName + " moves " + myDir + " to " + myBuilding.getFloor(currentLevel + myDir.getDir()).getName());
	}
	
	public int getMaxOccupancy() {
		return maxOccupancyThreshold;
	}

	public int getNumOccupants() {
		return numOccupants;
	}
	
	public String getStringName() {
		return myName;
	}

}

package Elevators;

import java.util.HashSet;
import java.util.Set;

import Main.Building;
import Main.Direction;
import Main.Floor;
import Riders.Rider;


public class InfiniteElevator extends AbstractElevator {

	private int currentLevel;
	private int numOccupants;
	private Set<Integer> myDestinations;
	private Building myBuilding;
	private Direction myDir;

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
			Floor currentFloor = myBuilding.getFloor(currentLevel);
			
			if (myDestinations.contains(currentFloor) || 
					currentFloor.peopleWaiting(myDir)) {
			
				VisitFloor(currentLevel);
			}
			
			// NEED TO IMPLEMENT, MUST CHANGE LEVEL GIVEN DIRECTION
			
			changeLevel();
		}
	}


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
		currentFloor.getEventBarrier(myDir).raise(); // notify those waiting that 'vator is here
	}

	public void CloseDoors() {
		
		printDoorsClose();
		
		myDestinations.remove(currentLevel);
	}

	public void VisitFloor(int floor) {
		OpenDoors();
		CloseDoors();
	}

	
	public boolean Enter() {
		numOccupants++;
		return true;
	}

	public void Exit() {
		numOccupants--;
	}
	

	public void RequestFloor(int floor) {
		myDestinations.add(floor);
	}
	
	public boolean isFull() {
		return false;
	}

	
	// ***** PRINT METHODS ****
	
	private void printDoorsOpen() {
		System.out.println(myName + " on " + myBuilding.getFloor(currentLevel).getName() 
							 + " opens");
	}
	
	private void printDoorsClose() {
		System.out.println(myName + " on " + myBuilding.getFloor(currentLevel).getName() 
							 + " closes");
	}
	
	private void printMoveToNewLevel() {
		String s = "";
		switch (myDir) {
		case UP:
			s = " moves up to ";
		case DOWN:
			s = " moves down to ";
		}
		
		System.out.println(myName + s + myBuilding.getFloor(currentLevel + myDir.getDir()));
	}

}

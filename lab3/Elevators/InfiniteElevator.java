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
		}
	}

	public void OpenDoors() {
		Floor currentFloor = myBuilding.getFloor(currentLevel);
		currentFloor.getEventBarrier(myDir).raise();
	}

	public void CloseDoors() {
		myDestinations.remove(currentLevel);
	}

	public void VisitFloor(int floor) {
		OpenDoors();
	}

	public boolean Enter(Rider r) {
		myRiders.add(r);
		numOccupants++;
		return Enter();
	}
	
	public boolean Enter() {
		return true;
	}

	public void Exit() {
		
	}

	public void RequestFloor(int floor) {
		myDestinations.add(floor);
	}
	
	public boolean isFull() {
		return false;
	}


}

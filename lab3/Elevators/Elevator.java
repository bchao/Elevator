package Elevators;
import java.util.*;

import Main.Building;
import Main.Direction;
import Main.Floor;
import Riders.Rider;

public class Elevator extends AbstractElevator{
	
	private int currentLevel;
	private int numOccupants;
	private int maxOccupants;
	private Set<Integer> myDestinations;
	private Building myBuilding;
	private Direction myDir;
	
	

	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold, 
														Building building) {
		
		super(numFloors, elevatorId, maxOccupancyThreshold);
		currentLevel = 0;
		numOccupants = 0;
		maxOccupants = 0;
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
		}
	}

	public void OpenDoors() {
		Floor currentFloor = myBuilding.getFloor(currentLevel);
		currentFloor.getEventBarrier(myDir).raise();
		// get here after everyone enters
		
	}

	public void CloseDoors() {
		myDestinations.remove(currentLevel);
	}

	public void VisitFloor(int floor) {
		OpenDoors();
	}

	public boolean Enter() {
		if (!isFull()) {
			numOccupants++;	
			return true;
		} else {
			return false;
		}
	}
	

	public void Exit() {
		
	}

	public void RequestFloor(int floor) {
		myDestinations.add(floor);
	}

	public boolean isFull() {
		
		return numOccupants == maxOccupants;
	}

	


}

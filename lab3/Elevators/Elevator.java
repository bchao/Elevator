package Elevators;

import Main.Building;

public class Elevator extends InfiniteElevator{
	

	private int maxOccupants;

	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold, 
														Building building) {
		
		super(numFloors, elevatorId, maxOccupancyThreshold, building);
		maxOccupants = maxOccupancyThreshold;

	}
	
	@Override 
	public synchronized boolean isFull() {
		return numOccupants == maxOccupants;
	}

	


}

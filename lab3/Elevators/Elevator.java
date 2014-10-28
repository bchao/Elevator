package Elevators;

import Main.Building;

/**
 * Same as the Infinite Elevator except we've implemented the isFull() method to 
 * account for the fact that this elevator has a maximum number of riders it can hold
 *
 */

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

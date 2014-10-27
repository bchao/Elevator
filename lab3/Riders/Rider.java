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
	
	public Rider(Building b, int floor) {
		myBarrier = null;
		currentLevel = floor;
		myBuilding = b;
	}
	
	public void setDestination(int n) {
		destinationLevel = n;
	}
	
	public void run() {
		Floor currentFloor = myBuilding.getFloor(currentLevel);
		myBuilding.requestElevator(this);
		FloorEventBarrier myEventBarrier = 
					(FloorEventBarrier) currentFloor.getEventBarrier(currentLevel - 
																	destinationLevel);
		myEventBarrier.arrive();
		// get in elevator and do shit
		AbstractElevator myElevator = myEventBarrier.getElevator();
		if (myElevator.Enter(this)) {
			// NEEDS TO BE IMPLEMENTED
		}
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public int getCurrentDestinationLevel() {
		return destinationLevel;
	}
	
	
}

package Buildings;

import java.util.*;

import Elevators.*;
import EventBarriers.EventBarrier;
import Main.Floor;
import Riders.*;

public class Building extends AbstractBuilding {
	
	protected List<Floor> myFloors;
	protected List<AbstractElevator> myElevators;
	protected int numberOfTripsToService;
	protected EventBarrier finalEventBarrier = null;
	
	public Building(int numFloors, int numElevators, int maxOccupancy) {
		super(numFloors, numElevators);
		myFloors = new ArrayList<Floor>();
		myElevators = new ArrayList<AbstractElevator>();
		initializeFloors(numFloors);
	}
	
	public void runElevators() {
		for(int i = 0; i < numElevators; i++) {
			myElevators.get(i).start();
		}
	}

	private void initializeFloors(int numFloors) {
		for (int i = 0; i < numFloors; i++) {
			myFloors.add(new Floor(i));
		}
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {

		
		return null;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		return null;
	}

	public synchronized void requestElevator(Rider rider) {
		setGlobalNumPeopleWaiting(1);
		int difference = rider.getCurrentLevel() - rider.getCurrentDestinationLevel();
		myFloors.get(rider.getCurrentLevel()).incrementWaiter(difference);
	}

	public synchronized Floor getFloor(int currentLevel) {
		return myFloors.get(currentLevel);
	}
	
	public synchronized int getMaxLevel() {
		return myFloors.size() - 1;
	}
	
	public void tripFinished() {
		--numberOfTripsToService;
		if (numberOfTripsToService == 0) finalEventBarrier.raise();
	}
	
	public void setNumberOfTrips(int n) {
		numberOfTripsToService = n;
	}
	
	public void setEvBar(EventBarrier b) {
		finalEventBarrier = b;
	}


}

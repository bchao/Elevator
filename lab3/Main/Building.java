package Main;

import java.util.*;

import Elevators.*;
import Riders.*;

public class Building extends AbstractBuilding {
	
	private List<Floor> myFloors;
	private List<AbstractElevator> myElevators;
	
	public Building(int numFloors, int numElevators, int maxOccupancy) {
		super(numFloors, numElevators);
		myFloors = new ArrayList<Floor>();
		myElevators = new ArrayList<AbstractElevator>();
		initializeFloors(numFloors);
		initializeElevators(numFloors, numElevators, maxOccupancy);
	}
	
	public void runElevators() {
		for(int i = 0; i < numElevators; i++) {
			myElevators.get(i).start();
		}
	}

	private void initializeElevators(int numFloors, int numElevators, int maxOccupancy) {
		for (int i = 0; i < numElevators; i++) {
			myElevators.add(new InfiniteElevator(numFloors, i, maxOccupancy, this));
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


}

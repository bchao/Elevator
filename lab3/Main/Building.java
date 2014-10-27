package Main;

import java.util.*;

import Elevators.*;
import Riders.*;

public class Building extends AbstractBuilding {
	
	private List<Floor> myFloors;
	private List<Elevator> myElevators;
	
	public Building(int numFloors, int numElevators, int maxOccupancy) {
		super(numFloors, numElevators);
		myFloors = new ArrayList<Floor>();
		myElevators = new ArrayList<Elevator>();
		initializeFloors(numFloors);
		initializeElevators(numFloors, numElevators, maxOccupancy);
	}

	private void initializeElevators(int numFloors, int numElevators, int maxOccupancy) {
		for (int i = 0; i < numElevators; i++) {
			myElevators.add(new Elevator(numFloors, i, maxOccupancy, this));
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

	public void requestElevator(Rider rider) {
		int difference = rider.getCurrentLevel() - rider.getCurrentDestinationLevel();
		
		if (difference > 0) {
			CallDown(rider.getCurrentLevel());
		} else {
			CallUp(rider.getCurrentLevel());			
		}
		
		myFloors.get(rider.getCurrentLevel()).incrementWaiter(difference);

		
	}

	public Floor getFloor(int currentLevel) {
		return myFloors.get(currentLevel);
	}

}

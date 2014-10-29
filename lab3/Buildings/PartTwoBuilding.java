package Buildings;

import Elevators.*;

public class PartTwoBuilding extends Building {

	public PartTwoBuilding(int numFloors, int numElevators, int maxOccupancy) {
		super(numFloors, numElevators, maxOccupancy);
		initializeElevators(numFloors, numElevators, maxOccupancy);
	}
	
	private void initializeElevators(int numFloors, int numElevators, int maxOccupancy) {
		for (int i = 0; i < numElevators; i++) {
			myElevators.add(new Elevator(numFloors, i, maxOccupancy, this));
		}
	}

}

package Buildings;

import Elevators.InfiniteElevator;

public class PartOneBuilding extends Building {

	public PartOneBuilding(int numFloors, int numElevators, int maxOccupancy) {
		super(numFloors, numElevators, maxOccupancy);
		initializeElevators(numFloors, numElevators, maxOccupancy);
	}
	
	private void initializeElevators(int numFloors, int numElevators, int maxOccupancy) {
		for (int i = 0; i < numElevators; i++) {
			myElevators.add(new InfiniteElevator(numFloors, i, maxOccupancy, this));
		}
	}

}

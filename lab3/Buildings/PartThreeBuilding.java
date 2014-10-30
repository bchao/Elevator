package Buildings;

import Elevators.*;

public class PartThreeBuilding extends Building {

	public PartThreeBuilding(int numFloors, int numElevators, int maxOccupancy) {
		super(numFloors, numElevators, maxOccupancy);
		initializeElevators(numFloors, numElevators, maxOccupancy);
	}
	
	private void initializeElevators(int numFloors, int numElevators, int maxOccupancy) {
		for (int i = 0; i < numElevators; i++) {
			Elevator e = new Elevator(numFloors, i, maxOccupancy, this);
			if(i <= numFloors)
				e.setCurrentLevel(i);
			myElevators.add(e);
		}
	}

}

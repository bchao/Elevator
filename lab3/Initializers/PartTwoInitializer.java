package Initializers;

import Buildings.PartTwoBuilding;
import EventBarriers.EventBarrier;
import Main.Parser;

public class PartTwoInitializer extends AbstractInitializer {

	@Override
	public void beginSimulation(String filename) {
		p = new Parser();
		
		myBuilding = new PartTwoBuilding(p.getFloors(), p.getElevators(), p.getCapacity());
		
		createRiders(p.getRiderMap());
		startRiders();
		myBuilding.runElevators();
		
		EventBarrier b = new EventBarrier();
		b.arrive();
	}

}

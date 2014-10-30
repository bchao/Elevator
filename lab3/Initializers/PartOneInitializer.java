package Initializers;

import Buildings.PartOneBuilding;
import EventBarriers.EventBarrier;
import Main.Parser;

public class PartOneInitializer extends AbstractInitializer{

	@Override
	public void beginSimulation(String filename) {
		p = new Parser();
		
		myBuilding = new PartOneBuilding(p.getFloors(), p.getElevators(), p.getCapacity());

		createRiders(p.getRiderMap());
		startRiders();
		myBuilding.runElevators();
		
		EventBarrier b = new EventBarrier();
		b.arrive();
	}

}

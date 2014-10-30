package Initializers;

import java.util.ArrayList;

import Buildings.PartThreeBuilding;
import EventBarriers.EventBarrier;
import Main.Parser;
import Riders.Rider;

public class PartThreeInitializer extends AbstractInitializer {

	@Override
	public void beginSimulation() {
		p = new Parser();
		EventBarrier b = new EventBarrier();
		myBuilding = new PartThreeBuilding(p.getFloors(), p.getElevators(), p.getCapacity());
		myBuilding.setEvBar(b);

		createRiders(p.getRiderStarts(), p.getRiderMap());
		startRiders();
		myBuilding.runElevators();
		
		p.writer.println("hi");
		
		b.arrive();
	}

}

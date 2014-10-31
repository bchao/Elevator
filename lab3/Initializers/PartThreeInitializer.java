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

		createRiders(p.getRiderStarts(), p.getRiderMap(), p.getRiderBehaviors());
		startRiders();
		myBuilding.runElevators();
				
		b.arrive();
		Parser.writer.println();
		Parser.writer.println("FINISH SIMULATION");
		Parser.writer.close();
	}

}

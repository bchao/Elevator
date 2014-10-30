package Initializers;

import java.util.ArrayList;

import Buildings.PartOneBuilding;
import EventBarriers.EventBarrier;
import Main.Parser;
import Riders.Rider;

public class PartOneInitializer extends AbstractInitializer{

	@Override
	public void beginSimulation() {
		p = new Parser();
		myBuilding = new PartOneBuilding(p.getFloors(), p.getElevators(), p.getCapacity());
		EventBarrier b = new EventBarrier();
		myBuilding.setEvBar(b);

		createRiders(p.getRiderStarts(), p.getRiderMap());
		startRiders();
		myBuilding.runElevators();
		
		b.arrive();
		Parser.writer.println();
		Parser.writer.println("FINISH SIMULATION");
		Parser.writer.close();
	}

}

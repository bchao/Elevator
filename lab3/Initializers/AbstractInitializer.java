package Initializers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Main.Parser;
import Riders.Rider;
import Buildings.Building;

public abstract class AbstractInitializer {
	Building myBuilding;
	Parser p;
	ArrayList<Rider> myRiders = new ArrayList<Rider>();
	int numTrips = 0;
	
	public AbstractInitializer() {}
		
	// this method should instantiate the parser with the given file name
	public abstract void beginSimulation();
	
	public void createRiders(int[] riderStarts, HashMap<Integer, ArrayList<Integer>> riderMap) {
		for(int i = 0; i < riderStarts.length; i++) {
			if(riderStarts[i] == 0)
				continue;
			
			Rider r = new Rider(i, myBuilding, riderStarts[i]);
			for(int floor : riderMap.get(i)) {
				r.addDestination(floor);
				numTrips++;
			}
			
			myRiders.add(r);
		}
		myBuilding.setNumberOfTrips(numTrips);
	}
	
	public void startRiders() {
		for(Rider r : myRiders)
			r.start();
	}
	
}

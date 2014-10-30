package Initializers;

import java.util.ArrayList;
import java.util.HashMap;

import Main.Parser;
import Riders.Rider;
import Buildings.Building;

public abstract class AbstractInitializer {
	Building myBuilding;
	Parser p;
	ArrayList<Rider> myRiders;
	
	public AbstractInitializer() {}
		
	// this method should instantiate the parser with the given file name
	public abstract void beginSimulation(String filename);

	public void createRiders(HashMap<Integer, ArrayList<int[]>> map) {
		for(int riderKey : map.keySet()) {
			ArrayList<int[]> list = map.get(riderKey);
			Rider r = new Rider(riderKey, myBuilding, list.get(0)[0]);
			for(int[] arr : list) {
				r.addDestination(arr[1]);
			}
			
			myRiders.add(r);
		}
	}
	
	public void startRiders() {
		for(Rider r : myRiders)
			r.start();
	}
	
}

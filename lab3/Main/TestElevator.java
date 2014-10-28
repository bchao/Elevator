package Main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Riders.Rider;

public class TestElevator {
	Building myBuilding;
	@Test
	public void testOneElevator() {
		myBuilding = new Building(10, 1, 10);
		
		Rider Peter = new Rider("Peter", myBuilding, 2);
		Peter.setDestination(9);
		Rider Parker = new Rider("Parker", myBuilding, 5);
		Parker.setDestination(9);
		
		Peter.run();

		myBuilding.runElevators();
				
		assertTrue(myBuilding.getFloor(0).peopleWaiting(Direction.UP));
		assertTrue(myBuilding.getFloor(9).peopleWaiting(Direction.UP));
	}
	

}

package Main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import EventBarriers.EventBarrier;
import Riders.Rider;

public class TestElevator {
	Building myBuilding;
	@Test
	public void testOneElevator() {
		
		myBuilding = new Building(10, 1, 10);
		Rider Peter = new Rider(0, myBuilding, 2);
		Peter.setDestination(9);
		

		Peter.start();
		myBuilding.runElevators();

		EventBarrier b = new EventBarrier();
		b.arrive();

		//sleepThread();	
		assertTrue(myBuilding.getFloor(0).peopleWaiting(Direction.UP));
		assertTrue(myBuilding.getFloor(9).peopleWaiting(Direction.UP));
	}
	

	private void sleepThread() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

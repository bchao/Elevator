package Main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Buildings.Building;
import Buildings.PartOneBuilding;
import EventBarriers.EventBarrier;
import Riders.Rider;

public class TestElevator {
	Building myBuilding;
	@Test
	public void testOneElevator() {
		
		myBuilding = new PartOneBuilding(10, 1, 10);
		Rider Peter = new Rider(0, myBuilding, 2);
		Peter.setDestination(3);
		Rider Parker = new Rider(1, myBuilding, 2);
		Parker.setDestination(5);
		Rider Brett = new Rider(2, myBuilding, 5);
		Brett.setDestination(7);
		Rider Brandon = new Rider(3, myBuilding, 4);
		Brandon.setDestination(7);
		
		Peter.start();
		Brett.start();
		Parker.start();
		Brandon.start();
		
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

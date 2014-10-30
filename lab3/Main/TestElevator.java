package Main;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import Buildings.*;
import EventBarriers.*;
import Riders.Rider;

public class TestElevator {
	Building myBuilding;
	@Test
	public void testOneElevator() {
		// CHOOSE WHICH TYPE
		myBuilding = new PartOneBuilding(10, 1, 10);
		//myBuilding = new PartTwoBuilding(10, 1, 3);
		
		//testPartOne();
		//testPartTwo();
		testPartOneMultipleDestinations();
		
		myBuilding.runElevators();
		
		EventBarrier b = new EventBarrier();
		b.arrive();

		//sleepThread();	
		assertTrue(myBuilding.getFloor(0).peopleWaiting(Direction.UP));
		assertTrue(myBuilding.getFloor(9).peopleWaiting(Direction.UP));
	}

	private void testPartOneMultipleDestinations() {
		List<Integer> d = new ArrayList<Integer>();
		d.add(7);
		d.add(3);
		Rider Peter = new Rider(0, myBuilding, 2, d);
		Peter.start();
	}


	private void testPartOne() {
		Rider Peter = new Rider(0, myBuilding, 2);
		Peter.addDestination(3);
		Rider Parker = new Rider(1, myBuilding, 2);
		Parker.addDestination(5);
		Rider Brett = new Rider(2, myBuilding, 5);
		Brett.addDestination(7);
		Rider Brandon = new Rider(3, myBuilding, 4);
		Brandon.addDestination(7);
		
		Peter.start();
		Brett.start();
		Parker.start();
		Brandon.start();
	}
	
	
	private void testPartTwo() {
		Rider Peter = new Rider(0, myBuilding, 2);
		Peter.addDestination(3);
		Rider Parker = new Rider(1, myBuilding, 2);
		Parker.addDestination(5);
		Rider Brett = new Rider(2, myBuilding, 2);
		Brett.addDestination(7);
		Rider Brandon = new Rider(3, myBuilding, 2);
		Brandon.addDestination(7);
		
		Peter.start();
		Brett.start();
		Parker.start();
		Brandon.start();
	}

	private void sleepThread() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

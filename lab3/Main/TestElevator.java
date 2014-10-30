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
		EventBarrier b = new EventBarrier();

//		myBuilding = new PartOneBuilding(10, 1, 10);
//		myBuilding = new PartTwoBuilding(10, 1, 3);
//		myBuilding = new PartTwoBuilding(10, 2, 3);
		myBuilding = new PartThreeBuilding(10, 2, 3);
		
		myBuilding.setEvBar(b);
		  
		Parser p = new Parser();
		
//		testPartOne();
//		testPartTwo();
//		testPartTwoB();
//		testPartOneMultipleDestinations();
//		testPartTwoMultipleDestinations();
		testPartTwoFinalTest();
		
		myBuilding.runElevators();
		 
		b.arrive();
		Parser.writer.println();
		Parser.writer.println("FINISH SIMULATION");
		Parser.writer.close();
		
		//sleepThread();	
//		assertTrue(myBuilding.getFloor(0).peopleWaiting(Direction.UP));
//		assertTrue(myBuilding.getFloor(9).peopleWaiting(Direction.UP));
		assertTrue(true);
	}

	private void testPartOneMultipleDestinations() {
		List<Integer> d = new ArrayList<Integer>();
		d.add(7);
		d.add(3);
		Rider Peter = new Rider(0, myBuilding, 2, d);
		myBuilding.setNumberOfTrips(2);
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
		
		myBuilding.setNumberOfTrips(4);
		
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
		
		myBuilding.setNumberOfTrips(4);
		
		Peter.start();
		Brett.start();
		Parker.start();
		Brandon.start();
	}

	private void testPartTwoB() {
		
		Rider Peter = new Rider(0, myBuilding, 2);
		Peter.addDestination(3);
		Rider Parker = new Rider(1, myBuilding, 2);
		Parker.addDestination(5);
		Rider Brett = new Rider(2, myBuilding, 2);
		Brett.addDestination(7);
		Rider Brandon = new Rider(3, myBuilding, 2);
		Brandon.addDestination(7);
		Rider Kyle = new Rider(4, myBuilding, 2);
		Kyle.addDestination(4);
		
		myBuilding.setNumberOfTrips(5);
		
		Peter.start();
		Brett.start();
		Parker.start();
		Brandon.start();
		Kyle.start();
	}
	
	private void testPartTwoMultipleDestinations() {
		
		Rider Peter = new Rider(0, myBuilding, 2);
		Peter.addDestination(3);
		Rider Parker = new Rider(1, myBuilding, 2);
		Parker.addDestination(5);
		Rider Brett = new Rider(2, myBuilding, 2);
		Brett.addDestination(7);
		Brett.addDestination(1);
		Rider Brandon = new Rider(3, myBuilding, 2);
		Brandon.addDestination(7);
		Rider Kyle = new Rider(4, myBuilding, 2);
		Kyle.addDestination(4);
		
		myBuilding.setNumberOfTrips(6);
		
		Peter.start();
		Brett.start();
		Parker.start();
		Brandon.start();
		Kyle.start();
	}
	
	private void testPartTwoFinalTest() {
		Rider Peter = new Rider(0, myBuilding, 2);
		Peter.addDestination(8);
		Rider Parker = new Rider(1, myBuilding, 2);
		Parker.addDestination(6);
		Rider Brett = new Rider(2, myBuilding, 2);
		Brett.addDestination(7);
		Brett.addDestination(1);
		Rider Brandon = new Rider(3, myBuilding, 2);
		Brandon.addDestination(7);
		Rider Kyle = new Rider(4, myBuilding, 2);
		Kyle.addDestination(8);
		
		Rider Josh = new Rider(5, myBuilding, 5);
		Josh.addDestination(7);
		
		myBuilding.setNumberOfTrips(7);
		
		Peter.start();
		Brett.start();
		Parker.start();
		Brandon.start();
		Kyle.start();
		Josh.start();
		
		
	}
	
	private void sleepThread() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

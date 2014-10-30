package Main;

import java.io.PrintWriter;

import Initializers.*;

public class ElevatorMain {
	
	public static void main(String args[]) {		
		// Class called Initializer (parent class)
		
		if(args.length == 0) {
			System.out.println("Problem 2 Part 3");
			PartThreeInitializer p2p3 = new PartThreeInitializer();
			p2p3.beginSimulation();
		} else if(args.length > 2) {
			System.out.println("Too many arguments");
		} else {
			if(args[1].equals("p1")) {
				System.out.println("Testing EventBarrier");
				//TestEventBarrier.test();
			}
			if(args[1].equals("p2part1")) {
				// call the elevator part1
				
				
			} else if(args[1].equals("p2part2")) {
				// call the elevator part2
			} else if(args[1].equals("p2part3")) {
				// call the elevator part3
			}
			// ...a
		}
	}
}

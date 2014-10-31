package Main;

import java.io.PrintWriter;
import java.util.Scanner;

import Initializers.*;

public class ElevatorMain {

	public static void main(String args[]) throws Exception {		
		// Class called Initializer (parent class)
//		System.out.print("Enter 'p2part1', 'p2part2', or 'p2part3': ");
//		Scanner sc = new Scanner(System.in);
//		String part = "";
//		if (sc.hasNext()) {
//			part = sc.nextLine();
//		}
		
		if(args.length == 0) {
			System.out.println("Problem 2 Part 3");
			PartThreeInitializer p2p3 = new PartThreeInitializer();
			p2p3.beginSimulation();
		} else if(args.length > 2) {
	        throw new Exception("Too many arguements");
	    } else { // known just one arg
	    	if (args[0].equals("p1")) {
	    	   System.out.println("Problem 1");
	    	   Part1.TestEventBarrier teb = new Part1.TestEventBarrier();
	    	}
	    	if(args[0].equals("p2part1")) {
	    		System.out.println("Problem 2 Part 1");
				PartOneInitializer p2p1 = new PartOneInitializer();
				p2p1.beginSimulation();
	    	} else if(args[0].equals("p2part2")) {
	    		System.out.println("Problem 2 Part 2");
				PartTwoInitializer p2p2 = new PartTwoInitializer();
				p2p2.beginSimulation();
	    	} else if(args[0].equals("p2part3")) {
	    		System.out.println("Problem 2 Part 3");
				PartThreeInitializer p2p3 = new PartThreeInitializer();
				p2p3.beginSimulation();
	    	}
	    }
		
//		if (part.equals("part1")) {
//			System.out.println("Problem 1");
//			Part1.TestEventBarrier teb = new Part1.TestEventBarrier();
//		}
//		else if (part.equals("p2part1")) {
//			System.out.println("Problem 2 Part 1");
//			PartOneInitializer p2p1 = new PartOneInitializer();
//			p2p1.beginSimulation();
//		} else if(part.equals("p2part2")) {
//			System.out.println("Problem 2 Part 2");
//			PartTwoInitializer p2p2 = new PartTwoInitializer();
//			p2p2.beginSimulation();
//		} else if(part.equals("p2part3")) {
//			System.out.println("Problem 2 Part 3");
//			PartThreeInitializer p2p3 = new PartThreeInitializer();
//			p2p3.beginSimulation();
//		}		
	}
}

package Main;

import java.io.PrintWriter;
import java.util.Scanner;

import Initializers.*;

public class ElevatorMain {
	
	public static void main(String args[]) {		
		// Class called Initializer (parent class)
	    System.out.println("Enter p2part1 to run Part 1, p2part2 to run Part 2, or p2part3 to run Part 3.\n");
		Scanner sc = new Scanner(System.in);
		String part = "";
	    if (sc.hasNext()) {
	    	part = sc.nextLine();
	    }
//		if(args.length == 0) {
//			System.out.println("Problem 2 Part 3");
//			PartThreeInitializer p2p3 = new PartThreeInitializer();
//			p2p3.beginSimulation();
//		} else if(args.length > 2) {
//			System.out.println("Too many arguments");
//		} else {
//			if(part.equals("p1")) {
//				System.out.println("Testing EventBarrier");
//				//TestEventBarrier.test();
//			}
			if (part.equals("p2part1")) {
				System.out.println("Problem 2 Part 1");
				PartOneInitializer p2p1 = new PartOneInitializer();
				p2p1.beginSimulation();
			} else if(part.equals("p2part2")) {
				System.out.println("Problem 2 Part 2");
				PartTwoInitializer p2p2 = new PartTwoInitializer();
				p2p2.beginSimulation();
			} else if(part.equals("p2part3")) {
				System.out.println("Problem 2 Part 3");
				PartThreeInitializer p2p3 = new PartThreeInitializer();
				p2p3.beginSimulation();
			}
			// ...a
		}
	//}
}

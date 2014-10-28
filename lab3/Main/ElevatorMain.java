package Main;

public class ElevatorMain {
	
	public static void main(String args[]) {

		if(args.length == 0) {
			// No options specified; make the default as the part 3 elevator submission
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
			// ...
		}
	}
}

package Main;
import EventBarriers.EventBarrier;
import EventBarriers.FloorEventBarrier;


public class Floor {

	private int numUpWaiters;
	private int numDownWaiters;
	private int floorID;
	private String floorName;
	
	private FloorEventBarrier myUpEventBarrier;
	private FloorEventBarrier myDownEventBarrier;
	
	public Floor(int id) {
		myUpEventBarrier = new FloorEventBarrier();
		myDownEventBarrier = new FloorEventBarrier();
		numUpWaiters = 0;
		numDownWaiters = 0;
		floorID = id+1;
		floorName = "F" + floorID;
	}

	public synchronized void incrementWaiter(int difference) { // difference = currentFloor - destination
		if (difference > 0) numDownWaiters++; 
		else numUpWaiters++;
	}
	
	public synchronized void decrementWaiter(int difference) {
		if (difference > 0) numDownWaiters--;
		else numUpWaiters--;
	}

	public synchronized EventBarrier getEventBarrier(int difference) {
		if (difference < 0) return myUpEventBarrier;
		else return myDownEventBarrier;
	}
	
	public synchronized boolean peopleWaiting(Direction d) {
		
		switch (d) {
			case UP:
				return numUpWaiters > 0;
			case DOWN:
				return numDownWaiters > 0;
		
		}
		
		return false;
	}

	public synchronized FloorEventBarrier getEventBarrier(Direction myDir) {
		
		switch (myDir) {
		case UP:
			return myUpEventBarrier;
		case DOWN:
			return myDownEventBarrier;
		}
		
		return null;
	}
	
	public String getName() {
		return floorName;
	}
	
	
	
	
}

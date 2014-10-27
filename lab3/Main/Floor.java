package Main;
import EventBarriers.EventBarrier;
import EventBarriers.FloorEventBarrier;


public class Floor {

	private int numUpWaiters;
	private int numDownWaiters;
	private int floorID;
	
	private EventBarrier myUpEventBarrier;
	private EventBarrier myDownEventBarrier;
	
	public Floor(int id) {
		myUpEventBarrier = new FloorEventBarrier();
		myDownEventBarrier = new FloorEventBarrier();
		numUpWaiters = 0;
		numDownWaiters = 0;
		floorID = id;
	}

	public void incrementWaiter(int difference) { // difference = currentFloor - destination
		if (difference > 0) numDownWaiters++; 
		else numUpWaiters++;
	}

	public EventBarrier getEventBarrier(int difference) {
		if (difference > 0) return myUpEventBarrier;
		else return myDownEventBarrier;
	}
	
	public boolean peopleWaiting(Direction d) {
		
		switch (d) {
			case UP:
				return numUpWaiters > 0;
			case DOWN:
				return numDownWaiters > 0;
		
		}
		
		return false;
	}

	public EventBarrier getEventBarrier(Direction myDir) {
		
		switch (myDir) {
		case UP:
			return myUpEventBarrier;
		case DOWN:
			return myDownEventBarrier;
		}
		
		return null;
	}
	
	
	
	
	
	
}

package EventBarriers;
import Elevators.AbstractElevator;
import Elevators.Elevator;
import Elevators.InfiniteElevator;

/**
 * 
 * This is the event barrier that is used on each floor of the building. It differs from
 * the parent EventBarrier in that the raise() method waits on a slightly different condition,
 * AND the barrier has an instance of the Elevator that called raise() in the first place.
 * <br> <br>
 * Each floor will have two of these: one for the up waiters, and one for the down waiters
 *
 */

public class FloorEventBarrier extends EventBarrier {
	
	private InfiniteElevator currentElevator;
	private boolean elevatorComing;
	
	public FloorEventBarrier() {
		super();
		currentElevator = null;
	}
	
	public void raise(InfiniteElevator el) {
		currentElevator = el;
		raise();		
		elevatorComing = false;
	}
	
	@Override
	public synchronized void raise() {
		eventInProg = true;
		notifyAll();

		while(numUnfinishedThreads > 0 && !currentElevator.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		eventInProg = false;
		notifyAll();
		//currentElevator = null;
	}
	
	public synchronized InfiniteElevator getElevator() {
		return currentElevator;
	}
	
	public synchronized void setElevatorComing(boolean bool) {
		elevatorComing = bool;
	}
	
	public synchronized boolean isElevatorComing() {
		return elevatorComing;
	}

	// changed
	public synchronized void decrementNumThread() {
		numUnfinishedThreads--;
	}

	public synchronized void setElevator(InfiniteElevator e) {
		currentElevator = e;
	}
	
}

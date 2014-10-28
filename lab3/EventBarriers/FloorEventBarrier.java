package EventBarriers;
import Elevators.Elevator;


public class FloorEventBarrier extends EventBarrier {
	
	private Elevator currentElevator;
	
	public FloorEventBarrier() {
		super();
		currentElevator = null;
	}
	
	public synchronized void raise(Elevator el) {
		currentElevator = el;
		raise();
		currentElevator = null;
	}
	
	@Override
	public synchronized void raise() {
		eventInProg = true;
		notifyAll();

		while(numUnfinishedThreads > 0 || !currentElevator.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		eventInProg = false;
	}
	
	public synchronized Elevator getElevator() {
		return currentElevator;
	}
}

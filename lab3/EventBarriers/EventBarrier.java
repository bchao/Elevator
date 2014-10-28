package EventBarriers;

/**
 * 
 * This is the event barrier for part I. Allows threads to wait at a barrier, and then a 
 * "gate keeper" comes along and raises the barrier and allows the waiting threads to do 
 * what they need to do
 *
 */

public class EventBarrier extends AbstractEventBarrier {
	
	protected boolean eventInProg;
	protected int numUnfinishedThreads;
	
	public EventBarrier() {
		eventInProg = false;
		numUnfinishedThreads = 0;
	}
	
	public synchronized void arrive() {
		numUnfinishedThreads++;
		
		while(!eventInProg){
			try{
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void raise() {
		eventInProg = true;
		notifyAll();

		while(numUnfinishedThreads > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		eventInProg = false;
	}

	public synchronized void complete() {
		numUnfinishedThreads--;
		notifyAll();
	}

	public int waiters() {
		return numUnfinishedThreads;
	}
	
	public boolean isEventInProg() {
		return eventInProg;
	}
}

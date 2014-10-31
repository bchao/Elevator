package Part1;

public class EventBarrier extends AbstractEventBarrier {
	private boolean eventInProg;
	private int numUnfinishedThreads;
	
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

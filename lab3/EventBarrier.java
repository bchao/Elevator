
public class EventBarrier extends AbstractEventBarrier {
	private boolean eventInProg;
	private int numUnfinishedThreads;
	
	public EventBarrier() {
		eventInProg = false;
		numUnfinishedThreads = 0;
	}
	
	public void arrive() {
		numUnfinishedThreads++;
		
		while(!eventInProg){
			try{
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void raise() {
		eventInProg = true;
		notifyAll();
		
		blockUntilAllThreadsComplete();
		eventInProg = false;
	}

	public void complete() {
		numUnfinishedThreads--;
		blockUntilAllThreadsComplete();
	}

	public int waiters() {
		return numUnfinishedThreads;
	}
	
	private void blockUntilAllThreadsComplete(){
		while(numUnfinishedThreads > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

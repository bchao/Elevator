public class GateKeeper implements Runnable {

	EventBarrier eventBarrier;
	
	public GateKeeper(EventBarrier e){
		eventBarrier = e;
	}
	
	public void run() {
		eventBarrier.raise();
	}
}
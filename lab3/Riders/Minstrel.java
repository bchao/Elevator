package Riders;
import EventBarriers.*;


public class Minstrel extends Thread {
	
	private EventBarrier eventBarrier;
	
	public Minstrel(EventBarrier e) {
		eventBarrier = e;
	}
	
	public void run() {
		eventBarrier.arrive();
		eventBarrier.complete();
	}
}

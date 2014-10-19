
public class Minstrel implements Runnable {
	
	private EventBarrier eventBarrier;
	
	public Minstrel(EventBarrier e) {
		this.eventBarrier = e;
	}
	
	public void run() {
		eventBarrier.arrive();
		
		eventBarrier.complete();
	}
}

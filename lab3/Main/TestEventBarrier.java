package Main;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import EventBarriers.EventBarrier;
import Riders.Minstrel;
import static org.junit.Assert.*;

public class TestEventBarrier {
	EventBarrier evBar;
	List<Thread> mList;
	
	@Test
	public void waitersTest() {
		int numThreads = 4;
		testHelper(numThreads);
		
		for(int i = 0; i<numThreads; i++) {
			mList.get(i).start();
		}
		
		sleepThread();

		assertEquals(4, evBar.waiters());
	}
	
	@Test
	public void raiseTest() {
		int numThreads = 5;
		testHelper(numThreads);
		
		for(int i = 0; i < numThreads - 1; i++) {
			mList.get(i).start();
		}
		
		evBar.raise();
		
		assertEquals(0, evBar.waiters()); // are all threads finished? should be
		assertFalse(evBar.isEventInProg()); // was condition var set back to false?
		
		mList.get(numThreads-1).start(); // send another thread to gate, gets rejected
		
		sleepThread();
		System.out.println(evBar.waiters());
		assertEquals(1, evBar.waiters());
		assertFalse(evBar.isEventInProg());

	}
	
	private void testHelper(int num){
		evBar = new EventBarrier();
		mList = new ArrayList<Thread>();
		
		for(int i = 0; i<num; i++)
			mList.add(new Minstrel(evBar));

	}
	
	private void sleepThread() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

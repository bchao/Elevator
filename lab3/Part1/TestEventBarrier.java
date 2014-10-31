package Part1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		
		assertFalse(evBar.isEventInProg());
		
		// last minstrel to squeeze through while EventBarrier raised
		mList.get(numThreads-1).start();
		
		assertFalse(evBar.isEventInProg());
	}
	
	public void testHelper(int num){
		evBar = new EventBarrier();
		mList = new ArrayList<Thread>();
		
		for(int i = 0; i<num; i++)
			mList.add(new Minstrel(evBar));

	}

}

package Elevators;
import EventBarriers.AbstractEventBarrier;
import EventBarriers.EventBarrier;
import Riders.*;

import java.util.*;

public abstract class AbstractElevator extends Thread {

	protected int numFloors; 
	protected int elevatorId;
	protected int maxOccupancyThreshold;
	protected List<Rider> myRiders;
	protected List<AbstractEventBarrier> myRiderEventBarriers;
	protected String myName;
	
	/**
	 * Other variables/data structures as needed goes here 
	 */

	public AbstractElevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		this.numFloors = numFloors;
		this.elevatorId = elevatorId;
		this.maxOccupancyThreshold = maxOccupancyThreshold;
		myName = "E" + elevatorId;
		myRiders = new ArrayList<Rider>();
		initializeRiderEventBarriers(numFloors);
	}



	/**
	 * Elevator control interface: invoked by Elevator thread.
 	 */

	/* Signal incoming and outgoing riders */
	public abstract void OpenDoors(); 	

	/**
	 * When capacity is reached or the outgoing riders are exited and
	 * incoming riders are in. 
 	 */
	public abstract void CloseDoors();

	/* Go to a requested floor */
	public abstract void VisitFloor(int floor);


	/**
	 * Elevator rider interface (part 1): invoked by rider threads. 
  	 */

	/* Enter the elevator */
	public abstract boolean Enter();
	
	/* Exit the elevator */
	public abstract void Exit();

	/* Request a destination floor once you enter */
 	public abstract void RequestFloor(int floor);	
	
	/* Other methods as needed goes here */
 	
	public abstract boolean isFull();
	
	public void addRider(Rider r) {
		myRiders.add(r);
	}
		
	private void initializeRiderEventBarriers(int numFloors) {
		for (int i = 0; i < numFloors; i++) {
			myRiderEventBarriers.add(new EventBarrier());
		}
	}

	public AbstractEventBarrier getElevatorWaitingBarrier(int destinationLevel) {		
		return myRiderEventBarriers.get(destinationLevel);
	}
	
}

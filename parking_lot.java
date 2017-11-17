import java.lang.*;
import java.util.*;

// properties of the parking lot
class ParkingLot{
	int capacity;

	boolean ParkingLot(int cap){ //intitalizer constructor
		private capacity = cap; 
		return true;
	}
}

// properties of the vehicle that is to be parked in the lot
class Vehicle{
	private String color;
	private String reg_no;
}

// properties of the each parking space
class ParkingSpace{
	private boolean isFree = true;
	private int slotNumber;
	private Vehicle vehicle;

	ParkingSpace(int slot){ // initializer constructor for each parking slot
		this.isFree = true;
		this.slotNumber = slot;
	}

	public void park(Vehicle v){
		vehicle = v;
		isFree = false;
	}

	public void freeSlot(){
		vehicle = null;
		isFree = true;
	}

	public int getSpotNumber(){
		return this.slotNumber;
	}
}


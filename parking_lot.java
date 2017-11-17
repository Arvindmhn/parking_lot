import java.lang.*;
import java.util.*;

// properties of the parking lot
class ParkingLot{
	long capacity;
	List<ParkingSpace> spaces;
	Map<Long,ParkingSpace> filledSpaces;

	boolean ParkingLot(long cap){ //intitalizer constructor
		capacity = cap; 
		spaces = new ArrayList<>(capacity);
		createSpaces();
		return true;
	}

	private void createSpaces(){
		for(i = 0 ; i < capacity; i++){
			spaces.add(new ParkingSpace(i + 1));
		}
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





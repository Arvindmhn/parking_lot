import java.lang.*;
import java.util.*;

// properties of the parking lot
class ParkingLot{
	long capacity;
	List<ParkingSpace> spaces;
	List<ParkingSpace> filledSpaces;

	boolean ParkingLot(long cap){ //intitalizer constructor
		capacity = cap; 
		spaces = new ArrayList<>(capacity);
		createSpaces();
		filledSpaces = new ArrayList<>(capacity); //maximum would be capacity
		return true;
	}

	private void createSpaces(){
		for(long i = 0 ; i < capacity; i++){
			spaces.add(new ParkingSpace(i + 1));
		}
	}


	public void park(Vehicle v){
		ParkingSpace space;
		space = closestFreeSpace();
		if (space != null){
			space.park(v);
			filledSpaces.add(space);
			return true;
		}
		else{
			return false;
		}
	}


	private closestFreeSpace(List<ParkingSpace> spaces){
		Iterator<ParkingSpace> it = spaces.iterator();
        boolean spaceFound = false;
        Space emptySpace = null;
        while (it.hasNext() && !spaceFound) {
            emptySpace = it.next();
            if (emptySpace.isSpaceFree()) {
                spaceFound = true;
            }
        }
        return emptySpace;
	}
}

// properties of the car that is to be parked in the lot
class Vehicle{
	private String color;
	private String reg_no;
}

// properties of the each parking space
class ParkingSpace{
	private boolean isFree = true;
	private long spaceNumber;
	private Vehicle vehicle;

	ParkingSpace(long spaceNo){ // initializer constructor for each parking space
		this.isFree = true;
		this.spaceNumber = spaceNo;
	}

	public void park(Vehicle v){
		vehicle = v;
		isFree = false;
	}

	public void freeSpace(){
		vehicle = null;
		isFree = true;
	}

	public boolean isSpaceFree(){
		return isFree;
	}

	public int getSpaceNumber(){
		return this.spaceNumber;
	}
}





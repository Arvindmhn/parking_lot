import java.lang.*;
import java.util.*;

// properties of the parking lot
class ParkingLot{
	long capacity;
	List<ParkingSpace> spaces;
	Map<Long,ParkingSpace> filledSpaces;

	ParkingLot(long cap){ //intitalizer constructor
		capacity = cap; 
		spaces = new ArrayList<>(capacity);
		createSpaces();
		filledSpaces = new HashMap<>; 
	}

	private void createSpaces(){
		for(long i = 0 ; i < capacity; i++){
			spaces.add(new ParkingSpace(i + 1));
		}
	}


	public long park(Vehicle v){
		ParkingSpace space;
		space = closestFreeSpace();
		if (space != null){
			space.park(v);
			filledSpaces.put(space.spaceNumber,space);
			return space.spaceNumber;
		}
		else{
			return -1;
		}
	}


	private ParkingSpace closestFreeSpace(List<ParkingSpace> spaces){
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

	Vehicle(String col, String reg){
		color = col;
		reg_no = reg;
	}
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


public class parking_lot{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		Stirng keyword = sc.nextLine();
		String cap = sc.nextLong();

		ParkingLot lotObj = new ParkingLot(cap); //create the lot with specified capacity

		while(true){
			String keyword = sc.nextLine();

			switch(keyword){
				case "park":
							String reg_no = sc.nextLine(); 
							String color = sc.nextLine();
							Vehicle veh = new Vehicle(color, reg_no);
							long parkNumber = lotObj.park(veh);
							if(parkNumber == -1)
								System.out.println("Sorry,   parking   lot   is   full");
							else
								System.out.println("Allocated   slot   number: "+parkNumber)
							break;

				case "leave": 
							String spaceNumber = sc.nextInt(); 
							filledSpaces.get(spaceNumber).freeSpace();
							filledSpaces.remove(spaceNumber);
							System.out.println("Slot   number   "+spaceNumber+"   is   free");
							break;

				case "status": 
							System.out.println("Slot No\tRegistration No.\tColour");
							for(Map.Entry<Long,ParkingSpace> entry : filledSpaces.entrySet()){
								long key = entry.getKey();
								ParkingSpace value = entry.getValue();
								System.out.println(key+"\t"+value.vehicle.reg_no+"\t"+value.vehicle.color);
							}
							break;
				//case "registration_numbers_for_cars_with_colour":
				//case "slot_numbers_for_cars_with_colour":
				//case "slot_number_for_registration_number":
			}
		}

	}
}





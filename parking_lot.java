import java.lang.*;
import java.util.*;
import java.lang.*;

// properties of the parking lot
class ParkingLot{
	long capacity;
	List<ParkingSpace> spaces;
	Map<Long,ParkingSpace> filledSpaces;

	ParkingLot(long cap){ //intitalizer constructor
		this.capacity = cap; 
		spaces = new ArrayList<>();
		createSpaces();
		filledSpaces = new HashMap<>(); 
		System.out.println("ParkingLot created");
	}

	void createSpaces(){
		for(long i = 0 ; i < capacity; i++){
			spaces.add(new ParkingSpace(i + 1));
		}
	}


	public void park(Vehicle v){
		ParkingSpace space;
		space = closestFreeSpace(spaces);
		if (space != null){
			space.park(v);
			filledSpaces.put(space.spaceNumber,space);
			System.out.println("Allocated   slot   number: "+space.spaceNumber);
		}
		else{
			System.out.println("Sorry,   parking   lot   is   full");
		}
	}


	ParkingSpace closestFreeSpace(List<ParkingSpace> spaces){
		Iterator<ParkingSpace> it = spaces.iterator();
        boolean spaceFound = false;
        ParkingSpace emptySpace = null;
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
	String color;
	String reg_no;

	Vehicle(String col, String reg){
		color = col;
		reg_no = reg;
	}
}

// properties of the each parking space
class ParkingSpace{
	boolean isFree = true;
	long spaceNumber;
	Vehicle vehicle;

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

	public long getSpaceNumber(){
		return this.spaceNumber;
	}
}


public class parking_lot{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String dummy_keyword = sc.nextLine();
		long cap = sc.nextLong();

		ParkingLot lotObj = new ParkingLot(cap); //create the lot with specified capacity

		while(true){
			String line = sc.nextLine();
			String[] words = line.split(" ");
			System.out.println(words[0]);
			switch(words[0]){
				case "park":
							String reg_no = words[1];
							String color = words[2];
							Vehicle veh = new Vehicle(color, reg_no);
							lotObj.park(veh);
							break;

				case "leave": 
							long spaceNumber = Integer.parseInt(words[1]);
							lotObj.filledSpaces.get(spaceNumber).freeSpace();
							lotObj.filledSpaces.remove(spaceNumber);
							System.out.println("Slot   number   "+spaceNumber+"   is   free");
							break;

				case "status": 
							System.out.println("Slot No\tRegistration No.\tColour");
							for(Map.Entry<Long,ParkingSpace> entry : lotObj.filledSpaces.entrySet()){
								long key = entry.getKey();
								ParkingSpace value = entry.getValue();
								System.out.println(key+"\t"+value.vehicle.reg_no+"\t"+value.vehicle.color);
							}
							break;

				case "registration_numbers_for_cars_with_colour": 
							String reqColor = words[1];
							for(Map.Entry<Long,ParkingSpace> entry : lotObj.filledSpaces.entrySet()){
								long key = entry.getKey();
								ParkingSpace value = entry.getValue();
								if (value.vehicle.color == reqColor)
									System.out.print(value.vehicle.reg_no + "\t");
							}
							break;

				case "slot_numbers_for_cars_with_colour":
							reqColor = words[1];
							for(Map.Entry<Long,ParkingSpace> entry : lotObj.filledSpaces.entrySet()){
								long key = entry.getKey();
								ParkingSpace value = entry.getValue();
								if (value.vehicle.color == reqColor)
									System.out.print(key + "\t");
							}
							break;

				case "slot_number_for_registration_number":
							String reqRegNo = words[1];
							long slot = -1;
							for(Map.Entry<Long,ParkingSpace> entry : lotObj.filledSpaces.entrySet()){
								long key = entry.getKey();
								ParkingSpace value = entry.getValue();
								if (value.vehicle.reg_no == reqRegNo){
									slot = key;
									break;
								}
							} 
							if (slot == -1){
								System.out.println("Not found");
							}
							else{
								System.out.println(slot);
							}
							break;
			}
		}

	}
}





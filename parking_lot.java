import java.lang.*;
import java.util.*;
import java.io.*;

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
		System.out.println("Created a parking lot with "+capacity+" slots");
	}

	void createSpaces(){
		for(long i = 0; i < capacity; i++){
			spaces.add(new ParkingSpace(i + 1));
		}
	}


	public void park(Vehicle v){
		ParkingSpace space;
		space = closestFreeSpace(spaces);
		if (space.isFree && space != null){
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
		if (args.length > 0){
			File file = new File(args[0]);
			BufferedReader br = null;
			if (file.exists()){
				try {
		            br = new BufferedReader( new FileReader(file));
		            String tempLine = br.readLine();
		            String create_keyword[] = tempLine.split(" ");
		            System.out.println(create_keyword[1]);
		            createLot(create_keyword, true, file);
		        } 
		        catch (FileNotFoundException e) {
		            System.err.println("Unable to find the file");
		        } 
		        catch (IOException e) {
		            System.err.println("Unable to read the file");
		        }
		    }
		}
		else{
			String[] create_keyword = sc.nextLine().split(" ");
			createLot(create_keyword, false, null);
		}
		
		
	}

	public static void createLot(String[] create_keyword, boolean isFile, File file){
		if (create_keyword[0].equals("create_parking_lot")){
			long cap = Long.parseLong(create_keyword[1]);
			ParkingLot lotObj = new ParkingLot(cap); //create the lot with specified capacity
			if (isFile == true)  
				processInput(lotObj, file);
			else
				processInput(lotObj, null);
		}
		else{
			System.out.println("Create the lot first");
		}
	}

	public static void processInput(ParkingLot lotObj, File file){
		BufferedReader br = null;
		if (file != null){
			try{
				br = new BufferedReader( new FileReader(file));
				br.readLine(); // skip first line
			}
			catch(FileNotFoundException e){

			}
			catch(IOException e){

			}
		}
			
		while(true){

				String line = "";
				String words[] = {};

				if (file != null){
					try {
						if((line = br.readLine()) != null){
							words = line.split(" ");
						}
					}
					catch(FileNotFoundException e){

					}
					catch(IOException e){

					}	
				}
				else{
					Scanner sc = new Scanner(System.in);
					line = sc.nextLine();
					words = line.split(" ");
				}
				try{
					switch(words[0]){
						case "park":
									String reg_no = words[1];
									String color = words[2];
									Vehicle veh = new Vehicle(color, reg_no);
									lotObj.park(veh);
									break;

						case "leave": 
									long spaceNumber = Long.parseLong(words[1]);
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
									int i = 0;
									List<String> temparr = new ArrayList<>();
									for(Map.Entry<Long,ParkingSpace> entry : lotObj.filledSpaces.entrySet()){
										long key = entry.getKey();
										ParkingSpace value = entry.getValue();
										if (value.vehicle.color.equals(reqColor))
											temparr.add((String)value.vehicle.reg_no);
									}
									System.out.println(Arrays.toString(temparr.toArray()).replace("[","").replace("]",""));
									break;

						case "slot_numbers_for_cars_with_colour":
									reqColor = ""; 
									reqColor = words[1];
									i = 0;
									temparr = new ArrayList<>();
									for(Map.Entry<Long,ParkingSpace> entry : lotObj.filledSpaces.entrySet()){
										long key = entry.getKey();
										ParkingSpace value = entry.getValue();
										if (value.vehicle.color.equals(reqColor)){
											//System.out.print(key+ " ");
											temparr.add(String.valueOf(key));
										}
									}
									System.out.println(Arrays.toString(temparr.toArray()).replace("[","").replace("]",""));
									break;

						case "slot_number_for_registration_number":
									String reqRegNo = words[1];
									long slot = -1;
									for(Map.Entry<Long,ParkingSpace> entry : lotObj.filledSpaces.entrySet()){
										long key = entry.getKey();
										ParkingSpace value = entry.getValue();
										if (value.vehicle.reg_no.equals(reqRegNo)){
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

						/*default : 	System.out.println("Invalid Input");
									break;*/
					}
				}
				catch(IndexOutOfBoundsException e){
					//last line of File
				}
			}
	}
}





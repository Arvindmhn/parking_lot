# Parking Lot

The application serves the purpose of alloting parking spaces to cars in a multi-storey parking lot. Each car is assigned aslot closest to the entry. 

## Features : ## 

1. Create lot of required capacity
2. Park cars by noting the details of the car and assigning nearest free space
3. Get the details of cars parked
4. Get slot number for given car registration_number 
5. Get all slot numbers for cars with colour
6. Get all registration numbers for cars with colour

## How to use the application : ## 

1. Clone the repository to your local by using 'git clone URL'
2. Run the script parking_lot.sh using the command './parking_lot.sh'
  - optionally the arguments to the program can be given as file (a sample input file is attached in the repository (tester_input.txt))
3. Please provide the command and value with one space interval in the same line
4. Kindly provide new commands in new lines
#### 5 Supported commands : ####
  - create_parking_lot "capacity" \n
  - park "registration_number" "colour"
  - leave "slot_number"
  - status 
  - registration_numbers_for_cars_with_colour "colour"
  - slot_numbers_for_cars_with_colour "colour"
  - slot_number_for_registration_number "registration_number"


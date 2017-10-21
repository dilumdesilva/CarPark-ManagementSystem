import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * OOP CW01 by Dilum De Silva (IIT NO - 2016142)
 */

public class WestminsterCarParkManager implements CarParkManager {

    //scanner to get user inputs
    Scanner sc = new Scanner(System.in);

    //free slot counter
    static int parkingSlotCount = 20;

    //to get the count of different vehicle types
    static int carCounter;
    static int vanCounter;
    static int bikeCounter;
    

    static EmptyParkingSlot emptySlot = new EmptyParkingSlot("Empty");

    //Creating 20 slots initializing it with null
    //static List<Vehicle> slots = new ArrayList<Vehicle>(Collections.<Vehicle>nCopies(20, emptySlot));
    static List<Vehicle> slots = new ArrayList<Vehicle>();




    //main method begins here
    public static void main(String[] args) {


        System.out.println();
        System.out.println("\n-----------------------------------------------------");
        System.out.println("\t  Westminster Car Park Management System");
        System.out.println("" +
                "" +
                "-----------------------------------------------------");
        System.out.println();

        WestminsterCarParkManager westminsterCarPark = new WestminsterCarParkManager();

        //calling the method which displays the main menu of the program
        westminsterCarPark.display();

    }

    //Method which displays the main menu to the user
    public void display(){
        try {
            sc = new Scanner(System.in);
            //to hold the user's selection from the main menu
            int SelectedOption;

            //new object of carParkManager
            WestminsterCarParkManager westminsterCarParkManager = new WestminsterCarParkManager();


            System.out.println("- - - - - - - - - - - Main Menu- - - - - - - - - - - ");

            System.out.println();
            System.out.println("1. Add a new Vehicle");
            System.out.println("2. Delete a Vehicle");
            System.out.println("3. Print a list of vehicle currently parked");
            System.out.println("4. The percentage of Cars, Vans and the Motorbikes currently parked.");
            System.out.println("5. Display Car Park Stats (Percentage of Each Vehicle Type)");
            System.out.println("6. Retrieve Parking Records");
            System.out.println("7. Parking Charges");
            System.out.println("0. Exit Program");

            System.out.println();
            System.out.print("Please enter your option:");
            SelectedOption = sc.nextInt();

            //switch case to perform the the task according to user selected option
            switch (SelectedOption) {
                case 1:
                    westminsterCarParkManager.addVehicle();
                    break;
                case 2:
                    westminsterCarParkManager.deleteVehicle();
                    break;
                case 3:
                    westminsterCarParkManager.printList();
                    break;
                case 4:
                    westminsterCarParkManager.percentages();
                    break;
                case 5:
                    westminsterCarParkManager.vehicleStats();
                    break;
                case 6:
                    westminsterCarParkManager.parkingLog();
                    break;
                case 7:
                    westminsterCarParkManager.parkingCharges();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("\nYou have entered an invalid option!");
                    System.out.println();
                    display();
                    break;

            }
        }catch (Exception e) {
            //Exception message
            System.out.println("\nInvalid option!");
            //to destroy if there are ant type mismatch exceptions
            sc.hasNextInt();
            display();
        }

    }

    //Method which adds a new vehicle to the system
    public void addVehicle(){
        try {
            sc = new Scanner(System.in);
            //to track the selected vehicle type
            int selectedVehicletype;

            System.out.println();
            System.out.println("# # # # # # # # # Add a new vehicle to the System # # # # # # # # # \n ");

            //to get number of free slots
            trackFreeSlots();

            if (parkingSlotCount > 0) {

                //to get the type of the vehicle to perform tasks
                System.out.println("\t 1 - Car");
                System.out.println("\t 2 - Van");
                System.out.println("\t 3 - Motorbike");

                System.out.print("Please select the type of the Vehicle: ");
                System.out.println();
                selectedVehicletype = sc.nextInt();

                //creating a new instance of the DateTime class to track the entered time of the vehicle
                DateTime dateTime = new DateTime();

                //switch-case to add vehicles depending on the vehicle type
                switch (selectedVehicletype) {

                    //Will perform tasks related to add a car
                    case 1:
                        Car car = new Car();
                        car.setVehicleType("Car");

                        System.out.println("\n\t# # # # Add a car to the park # # # # ");

                        //getting the id plate of the car
                        System.out.println("\nPlease enter the ID Plate number of the Car");
                        car.setIdPlate(sc.next());

                        //getting the brand of the car
                        System.out.println("Please enter the Brand of the Car");
                        car.setVehicleBrand(sc.next());

                        //to track the entrance time
                        timeValidator(dateTime);
                        //to track the entrance date
                        dateValidator(dateTime);

                        //getting the number of doors
                        System.out.println("Please enter the number of Doors of the Car");
                        car.setNumDoors(sc.nextInt());

                        //getting the color of the car
                        System.out.println("Please enter the Color of the Car");
                        car.setColor(sc.next());

                        //set the datetime object to the car
                        car.setDateTime(dateTime);

                        //reserve a slot to this particular car
                        slots.add(car);

                        //reduce the count of the parking slot by one
                        parkingSlotCount--;

                        //increase the count of the cars by one
                        carCounter++;

                        //write the data related to the car to a file
                        fileWriting(car);

                        //return to continue with the menu
                        returnToMenu();
                        break;

                    //Will perform tasks related to add a car
                    case 2:
                        //will check for the requirement to park a car
                        if (parkingSlotCount > 2) {

                            Van van = new Van();
                            van.setVehicleType("Van");

                            System.out.println("\n\t# # # # Add a van to the park # # # # ");

                            //getting the id plate of the van
                            System.out.println("\nPlease enter the ID Plate number of the Van");
                            van.setIdPlate(sc.next());

                            //getting the brand of the van
                            System.out.println("Please enter the Brand of the van");
                            van.setVehicleBrand(sc.next());

                            //setting the entrance time
                            timeValidator(dateTime);
                            //setting the entrance date
                            dateValidator(dateTime);

                            //getting the cargo volume of the van
                            System.out.println("Please enter the cargo volume of the van in Cubic Feet");
                            van.setCargoVolume(sc.nextDouble());

                            //set the datetime object to the Van
                            van.setDateTime(dateTime);

                            slots.add(van);

                            //reduce the count of the parking slot by two
                            parkingSlotCount--;
                            parkingSlotCount--;

                            //increase the count of the cars by one
                            vanCounter++;

                            //write the data related to the van to a file
                            fileWriting(van);

                            returnToMenu();

                        } else {
                            System.out.println("Sorry! There's no space available for a Van right now.");
                            returnToMenu();
                        }
                        break;

                    case 3:

                        Motorbike motorbike = new Motorbike();
                        motorbike.setVehicleType("Motorbike");

                        System.out.println("\n\t# # # Add a Motorbike to the park # # # ");
                        //getting the id plate of the bike
                        System.out.println("\nPlease enter the ID Plate number of the Motorbike");
                        motorbike.setIdPlate(sc.next());
                        //getting the brand of the bike
                        System.out.println("Please enter the Brand of the Motorbike");
                        motorbike.setVehicleBrand(sc.next());

                        //setting the entrance time
                        timeValidator(dateTime);

                        //setting the entrance date
                        dateValidator(dateTime);

                        //getting the engine capacity of the bike
                        System.out.println("Please enter the engine capacity of the Motorbike");
                        motorbike.setEngineCapacity(sc.next());

                        //set the datetime object to the bike
                        motorbike.setDateTime(dateTime);

                        slots.add(motorbike);

                        //reduce the count of the parking slot by one
                        parkingSlotCount--;
                        //increase the count of the cars by one
                        bikeCounter++;

                        //write the data related to the bike to the file
                        fileWriting(motorbike);
                        returnToMenu();
                        break;

                    default:
                        System.out.println("\nInvalid Input. Please try again.");
                        addVehicle();
                        break;
                }
            } else {
                return;
            }
        }catch (InputMismatchException ex){ //to handle the input InputMismatchException
            //Exception message
            System.out.println("\nInvalid Input. Please try again.");
            //to destroy if there are ant type mismatch exceptions
            sc.hasNextInt();
            addVehicle();
        }

    }

    //this method will track the count of available parking slots
    public void trackFreeSlots(){

        if(parkingSlotCount >0){

            if (parkingSlotCount == 1) {
                System.out.println("Hopefully, there's a parking slot remaining");
            }else {
                System.out.println(" \n There are " + parkingSlotCount + " remaining. \n");
            }
        }else {
            System.out.println("\nThere are no remaining slots. \nThanks");
        }
    }

    //Method which delete a vehicle from the system records
    public void deleteVehicle(){

        System.out.println();
        System.out.println("# # # # # # # # # Delete a vehicle # # # # # # # # # \n ");

        //to hold the total vehicle count of the system
        float totalVehicles = (carCounter+vanCounter+bikeCounter);

        //to check whether there are any vehicles in the park at the moment
        if (totalVehicles == 0) {
            System.out.println("Oops! You can't perform this action");
            System.out.println("There are no vehicles in the park at the moment! Please try again later.");

        }else {
            System.out.println("List of Vehicles in the parking\n");
            for (Vehicle vehicle : slots) {
                String ID = vehicle.getIdPlate();
                if (ID != null)
                    System.out.println("\t" + ID);
            }
            System.out.println("\nPlease select the vehicle you wish to delete");

            try {

                String deleteVehicle = sc.next();
                //getIndexProperty method will return an integer
                String leavingVehicle = slots.get(getIndexByProperty(deleteVehicle)).getVehicleType();
                System.out.println("\nA " + leavingVehicle + " is leaving the park.");
                if (leavingVehicle.equals("Car")) {
                    parkingSlotCount++;
                    carCounter--;
                } else if (leavingVehicle.equals("Van")) {
                    parkingSlotCount++;
                    parkingSlotCount++;
                    vanCounter--;
                } else if (leavingVehicle.equals("Motorbike")) {
                    parkingSlotCount++;
                    bikeCounter--;
                }
                slots.remove(getIndexByProperty(deleteVehicle));

            } catch (Exception e) {
                //catches the indexoutofbound exception
            }
        }
        returnToMenu(); //prompt the exit options

    }
    //returns the index of the desired vehicle
    private int getIndexByProperty(String yourString) {
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).getIdPlate() !=null && slots.get(i).getIdPlate().equalsIgnoreCase(yourString)) {
                //System.out.println(i);
                return i;
            }
        }
        System.out.println("\nSorry! There's no vehicle in the park with the specified ID plate.\n");
        return -1;// not there is list
    }



    //print list method
    public void printList(){
        System.out.println();
        System.out.println("- - - - - - - - List of Vehicles in the Parking - - - - - - - - \n ");

        float totalVehicles = (carCounter+vanCounter+bikeCounter);

        if (totalVehicles==0) {
            System.out.println("The parking is empty at the moment! Please try again later.");

        }else {

            sortArraylist();
            //makes it in the descending order
            Collections.reverse(slots);

            for (Vehicle vehicle : slots) {

                System.out.println("ID Plate - "+ vehicle.getIdPlate() + " " +
                        "Entrance Date & Time - "+
                        vehicle.getDateTime().getYear() + "-" + vehicle.getDateTime().getMonth() + "-" + vehicle.getDateTime().getDay() + " " +
                        vehicle.getDateTime().getHour() + ":" + vehicle.getDateTime().getMinute() + ":" +
                        vehicle.getDateTime().getSecond() + " The type of the vehicle - "+ vehicle.getVehicleType());
            }
        }
        returnToMenu(); //prompt the exit options

    }
    //sort the arraylist in the ascending order based on date time
    public void sortArraylist(){
        //sorts the date and time both and returns 0, -1 or 1
        Collections.sort(slots, (p1, p2) -> {
            int value;
            value = p1.getDateTime().getYear() - p2.getDateTime().getYear();
            if (value == 0)
                value = p1.getDateTime().getMonth() - p2.getDateTime().getMonth();
            if (value == 0)
                value = p1.getDateTime().getDay() - p2.getDateTime().getDay();
            if (value == 0)
                value = p1.getDateTime().getHour() - p2.getDateTime().getHour();
            if (value == 0)
                value = p1.getDateTime().getMinute() - p2.getDateTime().getMinute();
            if (value == 0)
                value = p1.getDateTime().getSecond() - p2.getDateTime().getSecond();
            return value;
        });
    }
    //to find the percentages of vehicles
    public void percentages(){
        float carPercentage,vanPercentage,bikePercentage;

        System.out.println();
        System.out.println("- - - - - - - Percentage of Vehicles in the parking - - - - - - - \n ");

        float totalVehicles = (carCounter+vanCounter+bikeCounter);

        if (totalVehicles==0) {
            System.out.println("The parking is empty at the moment! Please try again later.");

        }else {
            carPercentage = (carCounter * 100) / totalVehicles;
            vanPercentage = (vanCounter * 100) / totalVehicles;
            bikePercentage = (bikeCounter * 100) / totalVehicles;

            System.out.println("The percentage of Cars are " + carPercentage + "%");
            System.out.println("The percentage of Vans are " + vanPercentage + "%");
            System.out.println("The percentage of Motorbikes are " + bikePercentage + "%\n");
        }
        returnToMenu(); //prompt the exit options
    }

    //Vehicle stats
    public void vehicleStats(){
        System.out.println();
        System.out.println("- - - - - - - - Vehicle statistics - - - - - - - - \n ");

        float totalVehicles = (carCounter+vanCounter+bikeCounter);

        if (totalVehicles==0) {
            System.out.println("The parking is empty at the moment! Please try again later.");

        }else {
            sortArraylist();
            System.out.println("The vehicle that has been parked for the longest time - ");
            System.out.println("ID Plate - " + slots.get(0).getIdPlate());
            System.out.println("The type of the vehicle - "+ slots.get(0).getVehicleType());
            System.out.println("Entry Time - "+ slots.get(0).getDateTime().getHour() + ":" +
                    slots.get(0).getDateTime().getMinute() + ":" + slots.get(0).getDateTime().getSecond());
            System.out.println("Entry Date - "+ slots.get(0).getDateTime().getYear() + "-" +
                    slots.get(0).getDateTime().getMonth() + "-" + slots.get(0).getDateTime().getDay());


            System.out.println("\nThe last vehicle that was parked - ");
            System.out.println("ID Plate - "+ slots.get(slots.size()-1).getIdPlate());
            System.out.println("The type of the vehicle - "+ slots.get(slots.size()-1).getVehicleType());
            System.out.println("Entry Time - "+ slots.get(slots.size()-1).getDateTime().getHour() + ":" +
                    slots.get(slots.size()-1).getDateTime().getMinute() + ":" + slots.get(slots.size()-1).getDateTime().getSecond());
            System.out.println("Entry Date - "+ slots.get(slots.size()-1).getDateTime().getYear() + "-" +
                    slots.get(slots.size()-1).getDateTime().getMonth() + "-" + slots.get(slots.size()-1).getDateTime().getDay());

        }
        returnToMenu(); //prompt the exit options

    }

    //Calculates the parking charges
    public void parkingCharges(){
        String timeString;
        String[] time;

        String dateString;
        String[] date;

        String inputTime;

        System.out.println();
        System.out.println("- - - - - - - - Parking Charges - - - - - - - - \n ");

        float totalVehicles = (carCounter+vanCounter+bikeCounter);

        if (totalVehicles==0) {
            System.out.println("The parking is empty at the moment! Please try again later.");

        }else {
            //loop until the time is valid
            do {
                System.out.println("Please enter the current Time in the format of "+
                        "HH:MM:SS");

                timeString = sc.next();
                time = timeString.split(":");

                //display an error if the time is invalid
                if (Integer.parseInt(time[0]) >= 24 || Integer.parseInt(time[1]) >= 60 || Integer.parseInt(time[2]) >= 60) {
                    System.out.println("\n Invalid time. Please try again.\n ");
                    continue;
                }
            } while (Integer.parseInt(time[0]) >= 24 || Integer.parseInt(time[1]) >= 60 || Integer.parseInt(time[2]) >= 60);

            //loop until the date is valid
            do {
                System.out.println("Please enter the current Date in the format of "+
                        "YYYY-MM-DD");

                dateString = sc.next();
                date = dateString.split("-");

                //display an error if the date is invalid
                if (Integer.parseInt(date[1]) > 12 || Integer.parseInt(date[2]) > 31) {
                    System.out.println("\n Invalid date. Please try again.\n ");
                    continue;

                    //checks if the year is a 4 digit number or not
                } else if ((int) Math.log10(Integer.parseInt(date[0])) + 1 < 4) {
                    System.out.println("\n The year you entered appears to be invalid. Please try again.\n ");
                    continue;
                }

            }
            while ((int) Math.log10(Integer.parseInt(date[0])) + 1 < 4 || Integer.parseInt(date[1]) > 12 || Integer.parseInt(date[2]) > 31);

            System.out.println();
            //arranging the input date and time in the standard format to be converted to a Date object
            inputTime = date[0]+"-"+date[1]+"-"+date[2]+"T"+time[0]+":"+time[1]+":"+time[2]+"Z";

            //getting the millisecond value of the input date&time
            long inputMillisecond = dateFormatter(inputTime).getTime();

            System.out.println("\t\t\tParking Charges\n");

            try {
                System.out.println("\n\tHow would you like to view the charges?\n");
                System.out.println("\t1. Whole Car Park \t 2. Specific Vehicle");

                switch (sc.nextInt() ){
                    case 1:
                        String leftAlignFormat = "| %-17s | %-17d |%n";

                        System.out.format("+-------------------+-------------------+%n");
                        System.out.format("| Vehicle ID Plate  |  Total Charge (£) |%n");
                        System.out.format("+-------------------+-------------------+%n");

                        for (int i =0;i<slots.size();i++){
                            String vehicleTime;
                            long totalCost = 0;
                            long hourlyCost=0, dayCost = 0; //addition of these is the totalCost

                            //arranging the input date and time in the standard format to be converted to a Date object
                            vehicleTime = slots.get(i).getDateTime().getYear()+"-"+slots.get(i).getDateTime().getMonth()+"-"+
                                    slots.get(i).getDateTime().getDay()+"T"+slots.get(i).getDateTime().getHour()+":"+
                                    slots.get(i).getDateTime().getMinute()+":"+slots.get(i).getDateTime().getSecond()+"Z";

                            //getting the millisecond value of the vehicle date&time
                            long vehicleMillisecond = dateFormatter(vehicleTime).getTime();

                            //pass the millisecond values to the hourcalculator method and convert to days
                            long days = hourCalculator(inputMillisecond,vehicleMillisecond) / 24 ;
                            //hours left
                            long hours = hourCalculator(inputMillisecond,vehicleMillisecond) % 24 ;

                            //System.out.println("Hours" + hours + "days" + days);
                            //calculating the cost if vehicle has been parked for more than 1 day
                            if(days>0){
                                dayCost = days * 30;
                            }
                            //calculating the hourly cost
                            if(hours<=3){
                                hourlyCost = hours * 3 ;
                            }else if(hours>3 && hours<9){
                                hourlyCost = (hours - 3) * 4;
                                hourlyCost = hourlyCost +9;
                            }else if(hours>=9){
                                hourlyCost = 30 ;
                            }

                            //calculating the total cost
                            totalCost = dayCost + hourlyCost ;

                            System.out.format(leftAlignFormat, slots.get(i).getIdPlate(), totalCost);

                        }
                        break;
                    case 2:

                        leftAlignFormat = "| %-17s | %-17d |%n";

                        System.out.println("List of Vehicles in the parking\n");
                        for (Vehicle vehicle : slots) {
                            String ID = vehicle.getIdPlate();
                            if (ID != null)
                                System.out.println("\t" + ID);
                        }
                        System.out.println("\nPlease select the vehicle you wish the Charges");

                        String checkedVehicle = sc.next();

                        try{

                            System.out.format("+-------------------+-------------------+%n");
                            System.out.format("| Vehicle ID Plate  |  Total Charge (£) |%n");
                            System.out.format("+-------------------+-------------------+%n");

                            String vehicleTime;
                            long totalCost = 0;
                            long hourlyCost=0, dayCost = 0; //addition of these is the totalCost

                            //arranging the input date and time in the standard format to be converted to a Date object
                            vehicleTime = slots.get(getIndexByProperty(checkedVehicle)).getDateTime().getYear()+"-"+
                                    slots.get(getIndexByProperty(checkedVehicle)).getDateTime().getMonth()+"-"+
                                    slots.get(getIndexByProperty(checkedVehicle)).getDateTime().getDay()+"T"+
                                    slots.get(getIndexByProperty(checkedVehicle)).getDateTime().getHour()+":"+
                                    slots.get(getIndexByProperty(checkedVehicle)).getDateTime().getMinute()+":"+
                                    slots.get(getIndexByProperty(checkedVehicle)).getDateTime().getSecond()+"Z";

                            //getting the millisecond value of the vehicle date&time
                            long vehicleMillisecond = dateFormatter(vehicleTime).getTime();

                            //pass the millisecond values to the hourcalculator method and convert to days
                            long days = hourCalculator(inputMillisecond,vehicleMillisecond) / 24 ;
                            //hours left
                            long hours = hourCalculator(inputMillisecond,vehicleMillisecond) % 24 ;

                            //System.out.println("Hours" + hours + "days" + days);
                            //calculating the cost if vehicle has been parked for more than 1 day
                            if(days>0){
                                dayCost = days * 30;
                            }
                            //calculating the hourly cost
                            if(hours<=3){
                                hourlyCost = hours * 3 ;
                            }else if(hours>3 && hours<9){
                                hourlyCost = (hours - 3) * 4;
                                hourlyCost = hourlyCost + 9;
                            }else if(hours>=9){
                                hourlyCost = 30 ;
                            }

                            //calculating the total cost
                            totalCost = dayCost + hourlyCost ;

                            System.out.format(leftAlignFormat, slots.get(getIndexByProperty(checkedVehicle)).getIdPlate(), totalCost);

                        } catch (Exception e) {
                            //catches the indexoutofbound exception
                        }
                        break;

                    default:
                        System.out.println("\nInvalid option!");
                        display(); //return
                        break;
                }

            }catch (Exception ex3){
                System.out.println("\nInvalid option!");
                display(); //return
            }


            System.out.format("+-------------------+-------------------+%n");
            System.out.println("\nRates - First 3 hours - 3£ per hour , Starting from 4th Hour - 4£ per hour"
                    + "\n\n\t NOTE - The maximum charge for any 24 hour periods is 30£");
        }
        returnToMenu(); //prompt the exit options
    }


    //date formatter
    public Date dateFormatter(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = null;

        try {

            date = formatter.parse(dateInString.replaceAll("Z$", "+0000"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //hourCalculator Class
    public long hourCalculator(long inputTime,long vehicleTime){
        long duration = inputTime - vehicleTime;

        long hours = TimeUnit.MILLISECONDS.toHours(duration);

        return hours;
    }

    public void timeValidator(DateTime dateTime){
        try {
            String timeString;
            String[] time;

            //loop until the time is valid
            do {
                System.out.println("Please enter the entrance Time in the format of "+
                        "HH:MM:SS");

                timeString = sc.next();
                time = timeString.split(":");

                //display an error if the time is invalid
                if (Integer.parseInt(time[0]) >= 24 || Integer.parseInt(time[1]) >= 60 || Integer.parseInt(time[2]) >= 60) {
                    System.out.println("\n Invalid time. Please try again.\n ");
                    continue;
                }
            }
            while (Integer.parseInt(time[0]) >= 24 || Integer.parseInt(time[1]) >= 60 || Integer.parseInt(time[2]) >= 60);


            //setting the time
            dateTime.setHour(Integer.parseInt(time[0]));
            dateTime.setMinute(Integer.parseInt(time[1]));
            dateTime.setSecond(Integer.parseInt(time[2]));

        }catch (Exception ex1){
            System.out.println("\nSorry! Wrong time format. Please try again");
            addVehicle();
        }
    }

    public void dateValidator(DateTime dateTime){
        try {
            String dateString;
            String[] date;

            //loop until the date is valid
            do {
                System.out.println("Please enter the entrance Date in the format of "+
                        "YYYY-MM-DD");

                dateString = sc.next();
                date = dateString.split("-");

                //display an error if the date is invalid
                if (Integer.parseInt(date[1]) > 12 || Integer.parseInt(date[2]) > 31) {
                    System.out.println("\n Invalid date. Please try again.\n ");
                    continue;

                    //checks if the year is a 4 digit number or not
                } else if ((int) Math.log10(Integer.parseInt(date[0])) + 1 < 4) {
                    System.out.println("\n The year you entered appears to be invalid. Please try again.\n ");
                    continue;
                }

            }
            while ((int) Math.log10(Integer.parseInt(date[0])) + 1 < 4 || Integer.parseInt(date[1]) > 12 || Integer.parseInt(date[2]) > 31);

            //sets the date
            dateTime.setYear(Integer.parseInt(date[0]));
            dateTime.setMonth(Integer.parseInt(date[1]));
            dateTime.setDay(Integer.parseInt(date[2]));

        }catch (Exception ex2){
            System.out.println("\nSorry! Wrong date format. Please try again");
            addVehicle();
        }

    }
    //Method to write to a file
    public void fileWriting(Vehicle v) {
        try {
            int parkedYear = v.getDateTime().getYear();
            int parkedMonth = v.getDateTime().getMonth();
            int parkedDay = v.getDateTime().getDay();

            String fileName = String.valueOf(parkedYear)+"-"+String.valueOf(parkedMonth)+"-"+String.valueOf(parkedDay)+".txt";

            //File dir = new File("logs");//creating a folder called logs to store the text files
            //dir.mkdirs();

            File file = new File(fileName);

            //Creates the text file if doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(v.toString());
            bw.newLine();

            bw.close();

            //System.out.println("\nAll the data was successfully saved to a text file.\n");

        } catch (IOException e) {
            System.out.println("Sorry! The program could not locate the text file");

        }
    }

    //Retrieving the previous parking details
    public void parkingLog(){
        System.out.println();
        System.out.println("- - - - - - - - Parking Log - - - - - - - - \n ");
        System.out.print("Please enter the specific Day - ");

        int enteredDay=0,enteredMonth=0,enteredYear=0;

        enteredDay = sc.nextInt();
        if(enteredDay<=31) {
            System.out.print("Please enter the specific Month - ");
            enteredMonth=sc.nextInt();
            if(enteredMonth<=12) {
                System.out.print("Please enter the specific Year - ");
                enteredYear = sc.nextInt();
            }else {
                System.out.println("Invalid month! Please try again.");
                parkingLog();
            }
        }else {
            System.out.println("Invalid date! Please try again.");
            parkingLog();
        }
        System.out.println("");

        String fileName = String.valueOf(enteredYear)+"-"+String.valueOf(enteredMonth)+"-"+String.valueOf(enteredDay)+".txt";
        fileReading(fileName);
        returnToMenu(); //prompt the exit options

    }

    //Method to read the file
    public static void fileReading(String fileName) {

        //instance of the buffer reader
        BufferedReader br = null;

        try {
            //current line
            String line;
            br = new BufferedReader(new FileReader(fileName));

            if(!isEmptyFile(fileName)) {
                System.out.println("The list of vehicles parked on "+fileName+" (YYYY/MM/DD) "+"\n");

                String leftAlignFormat = "| %-13s | %-13s | %-16s | %-15s | %n";

                System.out.format("+---------------+---------------+------------------+-----------------+%n");
                System.out.format("|  Parked Date  |  Parked Time  | Vehicle ID Plate |  Vehicle Brand  |%n");
                System.out.format("+---------------+---------------+------------------+-----------------+%n");
                //reading line by line until the line becomes null
                while ((line = br.readLine()) != null) {
                    //splitting the line
                    String[] lineParts = line.split("/", -1);
                    //assigning the parts of the line
                    String date = lineParts[0];
                    String time = lineParts[1];
                    String id = lineParts[2];
                    String brand = lineParts[3];

                    System.out.format(leftAlignFormat, date, time,id,brand);
                }
                System.out.format("+---------------+---------------+------------------+-----------------+%n");
                //System.out.println("\nAll the data was successfully loaded from the text file. \n");
            }else {
                System.out.println("No vehicle was parked on "+fileName);
            }

        } catch (IOException e) {
            System.out.println("\nSorry! The program could not locate the text file");
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                System.out.println("\nOops! Something went wrong.");
            }
        }

    }
    //checking if the text file is empty
    static boolean isEmptyFile(String source) {
        try {
            for (String line : Files.readAllLines(Paths.get(source))) {
                if (line != null && !line.trim().isEmpty()) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Default to true.
        return true;
    }

    //method for the exist option
    public void returnToMenu(){
        try {

            System.out.println("\n\tWould you like to go back to the main menu?\n");
            System.out.println("\tPlease select \t 1. Yes \t 2. No");

            switch (sc.nextInt() ){
                case 1:
                    display(); //return
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid option!");
                    display(); //return
                    break;
            }

        }catch (Exception ex3){
            System.out.println("\nInvalid option!");
            display(); //return
        }


    }




}
/**
 * OOP CW01 by Dilum De Silva (IIT NO - 2016142)
 */
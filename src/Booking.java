//new
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;



//the program should start frm the owner - he needs to add tables, than , everything else is available
//not written code for clean-up from the owner ,
// and see all tables available for the customers as of date and time - I think maybe it will be easier to do from db

public class Booking {

    private static DBConnection bookingDb;

    public static void main(String[] args) {

        bookingDb = new DBConnection();

        //1. Owner side (what he can do - add, delete tables/seats, delete booking). 3 options
        //2. User side ( to book a table; to see reservations according to specific date and time) 2. options

        // Class: Main (switches 1. ar you an owner/user 2.depends on )
        // Class: Restaurant(with all the tables);
        // Class: Tables ( with table ID, number of seats, array of reservations, date and time)
        // Class: Person (name, phone number, number of people, table ID, date and time)
        // Class: Reservation

        ArrayList<Tables> listOfTables = new ArrayList<>();
        ArrayList<Person> listOfPersons = new ArrayList<>();
        //update existing data from db - for loop and also for every table - array of reservations

        int menuEntry;
        Scanner scanner = new Scanner(System.in);
        int numberOfTables= 0;//for add and remove functions - table id, that will be incremented every time when adding new tablw
        int numberOfReservations = 0; // the same for reservations
        do {
            // EXIT == 0
            System.out.println("Welcome to table booking system!");
            System.out.println("Please select :");
            System.out.println("1 - An owner of the restaurant");
            System.out.println("2 - A user");
            System.out.println("0 - Exit");
            menuEntry = scanner.nextInt();

            switch (menuEntry) {
                case 1:
//                  //an owner
                    int pin;
                    System.out.println("Please enter your pin code XXXX (0000):");
                    pin = scanner.nextInt();
                    if (pin == 0000 ){
                        int entry;
                        do{
                            System.out.println("Please select :");
                            System.out.println("1 - Show the list of tables ");
                            System.out.println("2 - Add a table ");
                            System.out.println("3 - Delete a table  ");
                            System.out.println("4 - Clean old irrelevant reservations, and customers data  ");//clean old
                            System.out.println("0 - Exit");
                            Tables newTable = new Tables();
                            entry = scanner.nextInt();
                            switch (entry){
                                case 1://show the list of tables - db
                                    for (Tables tab: listOfTables){
                                        System.out.println(tab.toString());
                                    }
                                    break;
                                case 2:
                                    // add a table
                                    //db+array



                                    numberOfTables++;
                                    System.out.println("Please add number of seats for the table :");
                                    newTable.setNumberOfSeats(scanner.nextInt());
                                    newTable.setTableID(numberOfTables);
                                    ArrayList<Reservation> reservationsForTable = new ArrayList<>();
                                    newTable.setReservationForTable(reservationsForTable);
                                    //ListOfTables.add(newTable);

                                    bookingDb.createTable(newTable);
                                    break;
                                case 3:
                                    //delete a table
                                    //should be deleted from DB and from array
                                    System.out.println("Please write ID of the table to be deleted:");
                                    int numberToDelete = scanner.nextInt();
                                    for (int i = 0;i<listOfTables.size(); i ++){
                                        if (listOfTables.get(i).getTableID() == numberToDelete){
                                            listOfTables.remove(i);
                                        }
                                    }
                                    break;
                                case 4: //from db
                                    String targetDate;
                                    System.out.println("Please enter the target date . Up to which all the data will be delete in format YYYY-MM-DD: ");
                                    targetDate = scanner.next();
                                    LocalDate newTargetDate = LocalDate.parse(targetDate);//from string to LocalDate
                                    //clean old
                                    //clean in db
                                    //update all 3 arrays
                                    System.out.println("Irrelevant data was cleaned!");
                                    break;
                                default:
                                    System.out.println("Menu item does not exist");
                            }
                        }while (entry != 0);
                    }
                    else{
                        System.out.println("The pin is incorrect");
                        System.out.println();
                        break;
                    }
                    break;
                case 2:
                    // USER
                    int userEntry;
                    do {
                        System.out.println("Please select what do you want to do:");
                        System.out.println("1 - See available tables ");
                        System.out.println("2 - Book a table ");
                        System.out.println("0 - Exit");

                        userEntry = scanner.nextInt();
                        switch (userEntry) {
                            case 1: //see available tables , compare LocalDates
                                //COMPLICATED, SHOULD BE DONE IN DATABASE , NOT CHANGING ANYTHING JUST PRINTING OUT
                                //see available tables
                                // entering data - number of persons
                                String date = null;
                                //String time= null;
                                int numberOfPersons= 0;
                                int reservationHours =0;
                                // System.out.println("Please select a date in format YYYY-MM-DD :");
                                //date = scanner.next();
                                /*System.out.println("Please select a time in format HH:MM :");
                                time = scanner.next();*/
                                System.out.println("How many persons you are: ");
                                numberOfPersons = scanner.nextInt();
                                //System.out.println("How many hours do you want your reservation to last? ");
                                //reservationHours = scanner.nextInt();
                                //LocalDate newDate = LocalDate.parse(date);
                                //System.out.println("the new date is " + newDate); check that parse is working
                                for (int i =0; i< listOfTables.size(); i++){ //prints out tables details in which number of seats >= number of persons
                                    if (listOfTables.get(i).getNumberOfSeats() >=numberOfPersons){
                                        /*//reservations for the date
                                            for(Reservation res : listOfTables.get(i).getReservationForTable()){//reservations for specific table
                                                if(res.getDate().equals(newDate)){
                                                    System.out.println(listOfTables.get(i).getReservationForTable());//print out the data for this table
                                                }
                                            }*/

                                        System.out.println(listOfTables.get(i));//prints tables with more or equal number of seats but
                                        // should print out all reservations for those tables with the specific date
                                    }
                                }

                                break;
                            case 2:
                                //book a table - change db, arrays

                                numberOfReservations++;
                                System.out.println("Please enter your name: ");
                                String name = scanner.next();
                                System.out.println("How many persons you are: ");
                                numberOfPersons = scanner.nextInt();
                                System.out.println("Please enter your phone number: ");//empty line??????
                                String phoneNumber = scanner.next();
                                System.out.println("Please select a date in format YYYY-MM-DD :");
                                date = scanner.next();
                                System.out.println("Please select a time in format HH:MM :");
                                String time = scanner.next();
                                System.out.println("How many hours do you want your reservation to last? ");
                                reservationHours = scanner.nextInt();
                                System.out.println("Please enter the table id, of the table you've chosen: ");
                                int tableId = scanner.nextInt();

                                LocalDate newDate = LocalDate.parse(date);
                                LocalTime newTime = LocalTime.parse(time);

                                // add a reservation to a table id
                                Person newPerson = new Person();
                                for (int i = 0; i<listOfTables.size();i++){
                                    if (listOfTables.get(i).getTableID() == tableId){
                                        Reservation newReservation = new Reservation(numberOfReservations, newDate , newTime , reservationHours, tableId );//reservation done
                                        //adding reservation to table
                                        listOfTables.get(i).addNewReservation(newReservation);
                                        //create a person
                                        newPerson = new Person( name, phoneNumber, numberOfPersons, tableId, numberOfReservations);
                                    }
                                }
                                System.out.println(newPerson + "is a new person");
                                break;
                            default:
                        }
                    }while (userEntry != 0);
                    break;
                default:
                    if(menuEntry != 0){// to check that the first input is not 0 - do while loop)
                        System.out.println("Menu item does not exist");
                    }
            }
        } while (menuEntry != 0);
    }
}

//09/05 what was done: added 2 more tables: person and reservation ,
//added methods- create person and create table that adds a row to db table from class in JAVA
//if the number of people is too big sout - no such a big table
//


//check if no tables in DB - sout the message
//check the booking time from 11:00 - 23:00



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
        int numberOfTables = 0;//for add and remove functions - table id, that will be incremented every time when adding new tablw
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
                    String pin = null;
                    System.out.println("Please enter your pin code XXXX (1111):");
                    pin = scanner.next();

                    if (pin.matches("1111")) {
                        int entry;
                        do {
                            System.out.println("Please select :");
                            System.out.println("1 - Show the list of tables ");
                            System.out.println("2 - Add a table ");
                            System.out.println("3 - Delete a table  ");
                            System.out.println("4 - Clean old irrelevant reservations, and customers data  ");//clean old
                            System.out.println("0 - Exit");
                            Tables newTable = new Tables();
                            entry = scanner.nextInt();
                            switch (entry) {
                                case 1://show the list of tables - db
                                    bookingDb.getTables();
                                    /*for (Tables tab: listOfTables){
                                        System.out.println(tab.toString());
                                    }*/
                                    break;
                                case 2:
                                    // add a table
                                    //db+array

                                    numberOfTables++;
                                    System.out.println("Please add number of seats for the table :");
                                    //newTable.setNumberOfSeats(scanner.nextInt());
                                    //newTable.setTableID(numberOfTables);
                                    //ArrayList<Reservation> reservationsForTable = new ArrayList<>();
                                    //newTable.setReservationForTable(reservationsForTable);
                                    //listOfTables.add(newTable);
                                    int numberOfSeats = 0;
                                    numberOfSeats = checkInputInt(numberOfSeats);
                                    newTable.setNumberOfSeats(numberOfSeats);
                                    bookingDb.createTable(newTable);
                                    break;
                                case 3: //check if there are no reservations for the table
                                    //delete a table
                                    System.out.println("Please write ID of the table to be deleted:");
                                    int numberToDelete = scanner.nextInt();

                                    bookingDb.deleteTable(numberToDelete);
                                    break;
                                case 4: // may be we dont need this item at all
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
                                    if (entry == 0) {
                                        continue;
                                    } else {
                                        System.out.println("Menu item does not exist");
                                        System.out.println();
                                    }
                            }
                        } while (entry != 0);
                    } else {
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
                                String date = null; //maybe to move to case 2?
                                String time = null; // maybe to move to case 2?
                                int numberOfPersons = 0;
                                int reservationHours = 0;

                                //to check for the date input
                                boolean wrongDateFormat = false;
                                do {
                                    wrongDateFormat = false;
                                    System.out.println("Please select a date in format YYYY-MM-DD :");
                                    date = scanner.next();
                                    try {
                                        LocalDate newDate = LocalDate.parse(date);
                                    } catch (DateTimeParseException e) {
                                        wrongDateFormat = true;
                                        System.out.println("The input is invalid , please enter one more time. ");
                                    }
                                }while (wrongDateFormat == true);
                                /*System.out.println("Please select a time in format HH:MM :");
                                time = scanner.next();*/
                                System.out.println("How many persons are you : ");
                                //numberOfPersons = scanner.nextInt();
                                numberOfPersons = 0;
                                numberOfPersons = checkInputInt(numberOfPersons);
                                //System.out.println("How many hours do you want your reservation to last? ");
                                //reservationHours = scanner.nextInt();
                                //LocalDate newDate = LocalDate.parse(date);
                                //System.out.println("the new date is " + newDate); check that parse is working
                                /*for (int i =0; i< listOfTables.size(); i++){ //prints out tables details in which number of seats >= number of persons
                                    if (listOfTables.get(i).getNumberOfSeats() >=numberOfPersons){
                                        //reservations for the date
                                            for(Reservation res : listOfTables.get(i).getReservationForTable()){//reservations for specific table
                                                if(res.getDate().equals(newDate)){
                                                    System.out.println(listOfTables.get(i).getReservationForTable());//print out the data for this table
                                                }
                                            }

                                        System.out.println(listOfTables.get(i));//prints tables with more or equal number of seats but
                                        // should print out all reservations for those tables with the specific date
                                    }
                                }*/
                                ArrayList<Tables> availableTables = new ArrayList<>();
                                availableTables = bookingDb.getAvailableTables(numberOfPersons);
                                //System.out.println(availableTables);
                                int tableId = 0;
                                ArrayList<Reservation> reservationsForTable = new ArrayList<>();
                                for (Tables table : availableTables) {
                                    tableId = table.getTableID();
                                    reservationsForTable = bookingDb.getAvailableReservations(tableId, date.toString(), table.getNumberOfSeats());
                                    if (reservationsForTable.size() == 0) {
                                        System.out.println("Table number " + table.getTableID() + " with " + table.getNumberOfSeats() + " number of seats has no reservations for this date");
                                    }
                                }


                                break;
                            case 2:
                                //book a table - change db, arrays
                                numberOfReservations++;
                                System.out.println("Please enter your name: "); //  check that its a name - a-zA-Z
                                String name = scanner.next();
                                System.out.println("How many persons you are: "); //check that its a positive number, not 0
                                //numberOfPersons = scanner.nextInt();
                                numberOfPersons = 0;
                                numberOfPersons = checkInputInt(numberOfPersons);
                                System.out.println("Please enter your phone number in format: XXXXXXXX: ");//empty line??????//check that it matches [0-9]*
                                String phoneNumber = null;
                                phoneNumber = checkInputPhoneNr(phoneNumber);
                                System.out.println("Please select a date in format YYYY-MM-DD :");//check the format is correct
                                date = scanner.next();

                                System.out.println("Please select a time in format HH:MM :"); //check the format is correct, time more than 11:00 and less than 23:00
                                time = scanner.next();
                                System.out.println("How many hours do you want your reservation to last? ");//check the input ans that time plus hours not more than 23:00,
                                reservationHours = scanner.nextInt();
                                System.out.println("Please enter the table id, of the table you've chosen: ");//an option to check available tables for the specific time
                                int tableID = scanner.nextInt();

                                LocalTime newTime = LocalTime.parse(time);
                                LocalDate newDate = LocalDate.parse(date);


                                // add a reservation to a table id
                                Person newPerson = new Person(); //creating a person that will be transferred to db
                                newPerson.setName(name);
                                newPerson.setTelephoneNumber(phoneNumber);
                                newPerson.setTableID(tableID);
                                newPerson.setNumberOfPersons(numberOfPersons);

                                bookingDb.createPerson(newPerson);

                                //add reservation
                                Reservation newReservation = new Reservation();
                                newReservation.setDate(newDate);
                                newReservation.setTime(newTime);
                                newReservation.setReservationHours(reservationHours);
                                newReservation.setTableID(tableID);
                                newReservation.setPersonTelephoneNumber(phoneNumber);

                                bookingDb.createReservation(newReservation);
                                System.out.println("Your reservation was recorded successfully!");
                                break;
                            default:
                        }
                    } while (userEntry != 0);
                    break;
                default:
                    /*if (menuEntry != 0) {// to check that the first input is not 0 - do while loop)
                        System.out.println("Menu item does not exist"); //prints when not needed- check
                    }*/
                    System.out.println("Menu item does not exist");
            }
        } while (menuEntry != 0);
    }


    /*private static int checkInputInt(int check1) { //to check for 0 and positive
        Scanner sc = new Scanner(System.in);
        String check0;
        check1 = 0;
        do {
            check0 = sc.next();
            if (check0.matches("\\d+")) {
                check1 = Integer.parseInt(check0);
            } else {
                System.out.println("Input is not recognized, enter a number: ");
            }
        } while (!(check0.matches("\\d+")));
        return check1;
    }*/

    private static int checkInputInt(int check1) { //to check for 0 and positive
        Scanner sc = new Scanner(System.in);
        String check0;
        check1 = 0;
        do {
            check0 = sc.next();
            if (check0.matches("^[1-9]\\d*$")) {
                check1 = Integer.parseInt(check0);
            } else {
                System.out.println("Input is not recognized, enter a number: ");
            }
        } while (!(check0.matches("^[1-9]\\d*$")));
        return check1;
    }

    private static String checkInputPhoneNr(String checkNr1) { //telephone number
        Scanner sc = new Scanner(System.in);
        do {
            checkNr1 = sc.next();
            if (checkNr1.matches("^\\d{8}$")) {
            } else {
                System.out.println("Input is not recognized, enter 8 numbers: ");
            }
        } while (!(checkNr1.matches("^\\d{8}$")));
        return checkNr1;
    }


  /*  public class DateValidation {
        public static void main(String[] args) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            // Input to be parsed should strictly follow the defined date format
            // above.
            format.setLenient(false);

            String date = "29/18/2017";
            try {
                format.parse(date);
            } catch (ParseException e) {
                System.out.println("Date " + date + " is not valid according to " +
                        ((SimpleDateFormat) format).toPattern() + " pattern.");
            }
        }
    }*/
}
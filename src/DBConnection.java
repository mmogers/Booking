
import java.sql.*;
import java.util.ArrayList;

public class DBConnection {


    private Connection conn;

    public DBConnection() { //creates 3 tables: tables, persons and reservations
        try {

                String dbUrl = "jdbc:sqlite:bookings.db";
                conn = DriverManager.getConnection(dbUrl);
            if (conn != null) {
                Statement statement = conn.createStatement();
                String sqlStatement =
                        "CREATE TABLE IF NOT EXISTS tables " +
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "number_of_seats INTEGER NOT NULL) ";

                statement.execute(sqlStatement);


                sqlStatement =
                        "CREATE TABLE IF NOT EXISTS reservations " +
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "date TEXT, " +
                                "time TEXT, " +
                                "reservation_duration INTEGER , " +
                                "table_id INTEGER , " +
                                "person_phone_number TEXT , " +
                                "FOREIGN KEY(table_id) REFERENCES tables(id) , " +
                                "FOREIGN KEY(person_phone_number) REFERENCES persons(telephone_number)) ";
                statement.execute(sqlStatement);


                sqlStatement =
                        "CREATE TABLE IF NOT EXISTS persons " +
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "name TEXT, " +
                                "telephone_number TEXT, " +
                                "number_of_persons INTEGER NOT NULL, " +
                                "table_id INTEGER , " +
                                "FOREIGN KEY(table_id) REFERENCES tables(id))";

                statement.execute(sqlStatement);

                System.out.println("DB created");
            }
        } catch (SQLException exception) {
            System.out.println("Database issues: " + exception);
        }
    }

//TABLE
    public void createTable(Tables table) { //adds row to tables in db from Tables(table) -class

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "INSERT INTO tables (" +
                    "number_of_seats) " +
                    "VALUES (" +
                    "" + table.getNumberOfSeats() + ")";
            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
    }

    public ArrayList<Tables> getTables() { //prints out all heroes from database, but doesn't add heroes to ArrayList

        ArrayList<Tables> tableList = new ArrayList<Tables>();

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM tables";

            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()) {

                // Create new Hero object + push all the tables
                Tables table = new Tables();
                table.setTableID(rs.getInt("id"));
                table.setNumberOfSeats(rs.getInt("number_of_seats"));
                tableList.add(table);
                System.out.println(table.toString());
            }


        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }

        return tableList;
    }


    public void deleteTable(int tableIDtoDelete) {

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "DELETE FROM tables WHERE (id = " + tableIDtoDelete + ")";
            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error : " + exception);
        }
    }

    public ArrayList<Tables> getAvailableTables(int numberOfPersons) { //prints out all tables with amount of seats more or equal to amount of persons

        ArrayList<Tables> tableList = new ArrayList<Tables>();

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM tables WHERE (number_of_seats >= " + numberOfPersons + ")";

            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()) {

                // Create new Hero object + push all the tables
                Tables table = new Tables();
                table.setTableID(rs.getInt("id"));
                table.setNumberOfSeats(rs.getInt("number_of_seats"));
                tableList.add(table);
                System.out.println(table.toString());
            }

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }

        return tableList;
    }

//PERSON
    public void createPerson(Person person) { //creates a row in db-persons from Person class

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "INSERT INTO persons (" +
                    "name, telephone_number, number_of_persons, table_id) " +
                    "VALUES (" +
                    "'" + person.getName() + "'," +
                    "'" + person.getTelephoneNumber() + "'," +
                    "" + person.getNumberOfPersons() + ", " +
                    "" + person.getTableID() + ")";

            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
    }
//RESERVATION
public void createReservation(Reservation reservation) { //creates a row in db-reservations from Person class

    try {

        Statement statement = conn.createStatement();
        String sqlStatement = "INSERT INTO reservations (" +
                "date, time, reservation_duration, table_id, person_phone_number) " +
                "VALUES (" +
                "'" + reservation.getDate() + "'," +
                "'" + reservation.getTime() + "'," +
                "'" + reservation.getReservationHours() + "'," +
                "" + reservation.getTableID() + ", " +
                "'" + reservation.getPersonTelephoneNumber() + "')";

        statement.execute(sqlStatement);

    } catch (SQLException exception) {
        System.out.println("Error: " + exception);
    }
}



}
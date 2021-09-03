import java.sql.*;
import java.util.ArrayList;

public class DBConnection {


    private Connection conn;

    public DBConnection() {
        try {

            String dbUrl = "jdbc:sqlite:bookings.db";
            conn = DriverManager.getConnection(dbUrl);

            Statement statement = conn.createStatement();
            String sqlStatement =
                            "CREATE TABLE IF NOT EXISTS tables "+
                            " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "number_of_seats INTEGER NOT NULL) " ;
            //"FOREIGN KEY(shop_id) REFERENCES shops(id) )";
            statement.execute(sqlStatement);

            System.out.println("DB created");
        } catch (SQLException exception) {
            System.out.println("Database issues: " + exception);
        }
    }


    public void createTable( Tables table ) {

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "INSERT INTO tables (" +
                    "number_of_seats) " +
                    "VALUES (" +
                    "" + table.getNumberOfSeats() + ")";
            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error getting Heroes list: " + exception);
        }
    }

    public ArrayList<Tables> getTables() { //prints out all heroes from database, but doesn't add heroes to ArrayList

        ArrayList<Tables> tableList = new ArrayList<Tables>();

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM tables";

            ResultSet rs = statement.executeQuery(sqlStatement);

            while ( rs.next() ) {

                // Create new Hero object + push all the tables
                Tables table = new Tables();
                table.setNumberOfSeats(rs.getInt("number_of_seats"));
                System.out.println(table.toString());
            }


        } catch (SQLException exception) {
            System.out.println("Error getting Heroes list: " + exception);
        }

        return tableList;
    }





}
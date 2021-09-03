public class Person {
    //private int ID;
    private String name;
    private String telephoneNumber;
    private int numberOfPersons;
    private int tableID;
    private int reservationID;

    public Person() {
    }

    public Person(String name, String telephoneNumber, int numberOfPersons, int tableID, int reservationID) {
        //this.ID = ID;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.numberOfPersons = numberOfPersons;
        this.tableID = tableID;
        this.reservationID = reservationID;
    }

    @Override
    public String toString() {
        return "Person{" +

                ", name='" + name + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", numberOfPersons=" + numberOfPersons +
                ", tableID=" + tableID +
                ", reservationID=" + reservationID +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
}

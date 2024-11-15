public class Passenger {

    static int id = 1; // static variable to give id for every new passenger
    String name;
    int age;
    String berthPreference;
    int PassengerId; // id for passengers that automatically created
    String alloted; // alloted type (L,M,U,RAC,WL)
    int number; // seatt number

    public Passenger(String name, int age, String berthPreference) {
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        this.PassengerId = id++;
        alloted = "";
        number = -1;

    }

}

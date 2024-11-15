import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class TicketBooking {

    // STATIC FOR ALL BECAUSE

    static int availableLowerBerths = 1; // upto 21
    static int availableMiddleBerths = 1; // upto 21
    static int availablUpperBerths = 1; // upto 21
    static int availableRacTickets = 1; // upto 18
    static int availableWaitingList = 1; // upto 10

    static Queue<Integer> waitingList = new LinkedList<>(); // queue fo WL Passenegers
    static Queue<Integer> racList = new LinkedList<>(); // queue for rac list
    static List<Integer> bookedTicketList = new ArrayList<>(); // list of booked passenegers

    static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(1)); // 1,2....21
    static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1)); // 1,2....21
    static List<Integer> uppderBerthsPositions = new ArrayList<>(Arrays.asList(1)); // 1,2....21
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1)); // 1,2....18
    static List<Integer> watingListPositions = new ArrayList<>(Arrays.asList(1)); // 1,2....10

    static Map<Integer, Passenger> passengers = new HashMap<>(); // map of passeneger ids to the paassenger

    public void bookTicket(Passenger p, int brethInfo, String allotedBerth) {
        p.number = brethInfo;
        p.alloted = allotedBerth;
        passengers.put(p.PassengerId, p);
        bookedTicketList.add(p.PassengerId);
        System.out.println("----------------------------------Booked Successfully");

    }

    public void addToRAC(Passenger p, int racInfo, String allotedRAC) {
        p.number = racInfo;
        p.alloted = allotedRAC;
        passengers.put(p.PassengerId, p);
        racList.add(p.PassengerId);
        availableRacTickets--;
        racPositions.remove(0);
        System.out.println("----------------------------------Added To RAC Successfully");

    }

    public void addToWaitingList(Passenger p, int waitingInfo, String allottedWL) {
        p.number = waitingInfo;
        p.alloted = allottedWL;
        passengers.put(p.PassengerId, p);
        waitingList.add(p.PassengerId);
        availableWaitingList--;
        watingListPositions.remove(0);
        System.out.println("----------------------------------Added To Waiting List Successfully");

    }

    public static void cancelTicket(int PassengerId) {
        Passenger p = passengers.get(PassengerId);
        passengers.remove(Integer.valueOf(PassengerId));

        bookedTicketList.remove(Integer.valueOf(PassengerId));

        int positionBooked = p.number; // take a booked position which is now free

        System.out.println("----------------------------------Cancelled Successfully");

        if (p.alloted.equals("L")) {
            availableLowerBerths++;
            lowerBerthsPositions.add(positionBooked);
        } else if (p.alloted.equals("M")) {
            availableMiddleBerths++;
            middleBerthsPositions.add(positionBooked);

        } else if (p.alloted.equals("U")) {
            availablUpperBerths++;
            uppderBerthsPositions.add(positionBooked);

        }

        // check if RAC is there
        if (racList.size() > 0) {
            Passenger passengerFromRAC = passengers.get(racList.poll()); // poll - queue first 1 head element will be
                                                                         // return
            int positionRac = passengerFromRAC.number;
            racPositions.add(positionRac);
            racList.remove(passengerFromRAC.PassengerId);
            availableRacTickets++;

            // check if any WL is there
            if (waitingList.size() > 0) {
                Passenger passengerFromWaitingList = passengers.get(waitingList.poll());
                int positionWL = passengerFromWaitingList.number;
                watingListPositions.add(positionWL);
                waitingList.remove(Integer.valueOf(passengerFromWaitingList.PassengerId));

                passengerFromWaitingList.number = racPositions.get(0);
                passengerFromWaitingList.alloted = "RAC";
                racPositions.remove(0);
                racList.add(passengerFromWaitingList.PassengerId);

                availableWaitingList++;
                availableRacTickets--;

            }
            Main.bookTicket(passengerFromRAC);

        }

    }

    public void printAvailable() {
        System.out.println("Available Lower Berths " + availableLowerBerths);
        System.out.println("Available Middle Berths " + availableMiddleBerths);
        System.out.println("Available Upper Berths " + availablUpperBerths);
        System.out.println("Available RACs " + availableRacTickets);
        System.out.println("Available Waiting List " + availableWaitingList);
        System.out.println("----------------------------------");
    }

    public void printPassengers() {
        if (passengers.size() == 0) {
            System.out.println("No details Of Passengers");
            return;
        }
        for (Passenger p : passengers.values()) {
            System.out.println("PASSENGER ID" + p.PassengerId);
            System.out.println("Name " + p.name);
            System.out.println("Age " + p.age);
            System.out.println("Status " + p.number + p.alloted);
            System.out.println("---------------------------");
        }
    }
}

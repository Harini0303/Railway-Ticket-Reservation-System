import java.util.*;

public class Main {
    public static void bookTicket(Passenger p) {
        TicketBooking booking = new TicketBooking();

        if (TicketBooking.availableWaitingList == 0) {
            System.out.println("No Ticket Available");
            return;
        }
        if ((p.berthPreference.equals("L") && TicketBooking.availableLowerBerths > 0) ||
                (p.berthPreference.equals("M") && TicketBooking.availableMiddleBerths > 0) ||
                (p.berthPreference.equals("U") && TicketBooking.availablUpperBerths > 0)) {
            System.out.println("Preffered Available Breths");

            if (p.berthPreference.equals("L")) {
                System.out.println("Lower Berth Given");
                booking.bookTicket(p, (TicketBooking.lowerBerthsPositions.get(0)), "L");
                TicketBooking.lowerBerthsPositions.remove(0);
                TicketBooking.availableLowerBerths--;
            }

            else if (p.berthPreference.equals("M")) {
                System.out.println("Middle Berth Given");
                booking.bookTicket(p, (TicketBooking.middleBerthsPositions.get(0)), "M");
                TicketBooking.middleBerthsPositions.remove(0);
                TicketBooking.availableMiddleBerths--;

            }

            else if (p.berthPreference.equals("U")) {
                System.out.println("Upper Berth Given");
                booking.bookTicket(p, (TicketBooking.uppderBerthsPositions.get(0)), "U");
                TicketBooking.uppderBerthsPositions.remove(0);
                TicketBooking.availablUpperBerths--;
            }
        }

        else if (TicketBooking.availableLowerBerths > 0) {
            System.out.println("Lower Berth Given");
            booking.bookTicket(p, (TicketBooking.lowerBerthsPositions.get(0)), "L");
            TicketBooking.lowerBerthsPositions.remove(0);
            TicketBooking.availableLowerBerths--;

        } else if (TicketBooking.availableMiddleBerths > 0) {
            System.out.println("Middle Berth Given");
            booking.bookTicket(p, (TicketBooking.middleBerthsPositions.get(0)), "M");
            TicketBooking.middleBerthsPositions.remove(0);
            TicketBooking.availableMiddleBerths--;
        } else if (TicketBooking.availablUpperBerths > 0) {
            System.out.println("Upper Berth Given");
            booking.bookTicket(p, (TicketBooking.uppderBerthsPositions.get(0)), "U");
            TicketBooking.uppderBerthsPositions.remove(0);
            TicketBooking.availablUpperBerths--;
        }

        // IF NO BERTH IS AVAILABLE GO TO RAC
        else if (TicketBooking.availableRacTickets > 0) {
            System.out.println("RAC Available");
            booking.addToRAC(p, (TicketBooking.racPositions.get(0)), "RAC");
        }

        // IF NO RAC AVAILABLE GO TO WAITING LIST (WL)
        else if (TicketBooking.availableWaitingList > 0) {
            System.out.println("Added to Waiting List");
            booking.addToWaitingList(p, (TicketBooking.watingListPositions.get(0)), "WL");

        }

    }

    // CANCEL TICKET FUNCTION
    public static void cancelTicket(int id) {
        TicketBooking booking = new TicketBooking();
        if (!booking.passengers.containsKey(id)) {
            System.out.println("Passengers Detail Unknown");
        } else {
            booking.cancelTicket(id);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean loop = true;

        while (loop) {
            System.out.println(
                    " 1.Book Tickets \n 2.Cancel Tickets \n 3.Availbale Tickets \n 4.Booked Tickets \n 5.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    // book ticket
                    System.out.println("Enter Passenger Name , Age , Preffered Berth(L, U, M)");
                    String name = sc.next();
                    int age = sc.nextInt();
                    String berthPreference = sc.next();
                    Passenger p = new Passenger(name, age, berthPreference);
                    // booking
                    bookTicket(p);

                }
                    break;

                // cancel ticket
                case 2: {
                    System.out.println("Enter Passenger Id to cancel");
                    int id = sc.nextInt();
                    cancelTicket(id);
                }

                    break;

                // available ticket priniting
                case 3: {
                    TicketBooking booking = new TicketBooking();
                    booking.printAvailable();
                }

                    break;

                // occupide ticket printing
                case 4: {
                    TicketBooking booking = new TicketBooking();
                    booking.printPassengers();
                }

                    break;

                case 5: {
                    loop = false;
                }

                    break;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
        sc.close();
    }
}

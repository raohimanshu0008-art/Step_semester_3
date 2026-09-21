import java.util.*;

public class BusTicket {
    String passengerName;
    String destination;
    boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || destination == null ||
            passengerName.trim().isEmpty() || destination.trim().isEmpty() ||
            !passengerName.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Invalid booking");
        }

        this.passengerName = passengerName;
        this.destination = destination;
    }

    public void markCheckedIn() {
        if (!checkedIn)
            checkedIn = true;
    }

    static void processBatch(String[][] rawBookings) {
        Set<String> accepted = new HashSet<>();
        int valid = 0, rejected = 0, duplicates = 0;

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length < 2 ||
                booking[0] == null || booking[1] == null ||
                booking[0].trim().isEmpty() || booking[1].trim().isEmpty() ||
                !booking[0].matches("[A-Za-z ]+")) {
                rejected++;
                continue;
            }

            String key = booking[0] + "|" + booking[1];

            if (accepted.contains(key)) {
                duplicates++;
                continue;
            }

            try {
                new BusTicket(booking[0], booking[1]);
                accepted.add(key);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(bookings);
    }
}
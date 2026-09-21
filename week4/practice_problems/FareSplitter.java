import java.util.*;

public class FareSplitter {
    String tripId;
    double totalFare;
    int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException("Invalid fare or passenger count");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    double[] fareBreakdown() {
        double[] shares = new double[passengerCount];

        if (totalFare == 0)
            return shares;

        double share = Math.floor((totalFare / passengerCount) * 100) / 100;

        for (int i = 0; i < passengerCount - 1; i++)
            shares[i] = share;

        shares[passengerCount - 1] =
                Math.round((totalFare - share * (passengerCount - 1)) * 100) / 100.0;

        return shares;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter f1 = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(f1.fareBreakdown()));

        FareSplitter f2 = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(f2.fareBreakdown()));
    }
}
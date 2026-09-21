public class BusTicketAccount {

    String bookingId;
    double ticketFare;

    static double penaltyRate;

    static {
        penaltyRate = 1.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty() || ticketFare < 0)
            throw new IllegalArgumentException("Invalid account");

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0)
            throw new IllegalArgumentException("Invalid minutes");

        return ticketFare * penaltyRate / 100 * minutesLate;
    }

    void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null)
            return;

        double penalty = account.calculatePenalty(minutesLate);

        System.out.println(account.bookingId +
                " | Amount: Rs " + amount +
                " | Penalty: Rs " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        if (accounts == null || amounts == null || minutesLateArray == null)
            return;

        int count = Math.min(accounts.length,
                    Math.min(amounts.length, minutesLateArray.length));

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double totalPenalty = 0;

        for (int i = 0; i < count; i++) {

            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double penalty = account.calculatePenalty(minutesLateArray[i]);
            totalPenalty += penalty;
            processed++;

            if (account instanceof SleeperAccount)
                sleeper++;
            else
                regular++;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                sleeper + " sleeper | " +
                regular + " regular");

        System.out.println("Grand total penalties = Rs " + totalPenalty);
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}

class SleeperAccount extends BusTicketAccount {

    SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}
public class DeliveryAccount {
    String studentId;
    double orderValue;

    static double minimumSurgePercent;

    static {
        minimumSurgePercent = 1.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty() || orderValue < 0)
            throw new IllegalArgumentException("Invalid account");

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0)
            throw new IllegalArgumentException("Invalid delay");

        if (delayMinutes == 0)
            return 0;

        double fee = 0;

        int first = Math.min(delayMinutes, 5);
        fee += first * orderValue * 0.005;

        if (delayMinutes > 5) {
            int second = Math.min(delayMinutes - 5, 10);
            fee += second * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int third = delayMinutes - 15;
            fee += third * orderValue * 0.02;
        }

        double minimumFee = orderValue * minimumSurgePercent / 100;

        return Math.max(fee, minimumFee);
    }

    void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null)
            return;

        double fee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumAccount)
            fee *= 0.5;

        System.out.println(account.studentId + " | Rs " + fee);
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        if (accounts == null || amounts == null || delayMinutesArray == null)
            return;

        int count = Math.min(accounts.length,
                    Math.min(amounts.length, delayMinutesArray.length));

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double total = 0;

        for (int i = 0; i < count; i++) {
            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double fee = account.calculateSurgeFee(delayMinutesArray[i]);

            if (account instanceof PremiumAccount) {
                fee *= 0.5;
                premium++;
            } else {
                regular++;
            }

            total += fee;
            processed++;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                premium + " premium | " +
                regular + " regular");

        System.out.println("Grand total surge fees = Rs " + total);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}

class PremiumAccount extends DeliveryAccount {

    PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}
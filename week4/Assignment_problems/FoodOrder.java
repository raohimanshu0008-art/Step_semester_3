public class FoodOrder {
    String studentName;
    String dishName;
    boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || dishName == null ||
            studentName.trim().isEmpty() || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order");
        }

        this.studentName = studentName;
        this.dishName = dishName;
    }

    void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered");
        } else {
            System.out.println("Order already delivered");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            try {
                if (order == null || order.length < 2)
                    throw new IllegalArgumentException();

                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(orders);

        FoodOrder order = new FoodOrder("Ravi", "Paneer");
        order.markDelivered();
        order.markDelivered();
    }
}
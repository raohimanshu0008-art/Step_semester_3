import java.util.*;

public class InventoryParser {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(",");

        if (a.length != 3)
            System.out.println("Invalid Record");
        else
            System.out.println("Product: " + a[0] + " | SKU: " + a[1] + " | Qty: " + a[2]);
    }
}
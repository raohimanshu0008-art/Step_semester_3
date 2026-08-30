import java.util.*;

public class ATMValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pin = sc.nextLine();

        if (pin.length() != 4)
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        else
            System.out.println("PIN length OK.");
    }
}
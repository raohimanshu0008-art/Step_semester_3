import java.util.*;

public class MaskedPhoneNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (s.length() != 10) {
            System.out.println("Invalid phone number");
            return;
        }

        for (char c : s.toCharArray())
            if (!Character.isDigit(c)) {
                System.out.println("Invalid phone number");
                return;
            }

        StringBuilder sb = new StringBuilder("XXXXXX-");
        sb.append(s.substring(6));
        System.out.println(sb);
    }
}
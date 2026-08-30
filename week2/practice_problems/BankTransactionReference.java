import java.util.*;

public class BankTransactionReference {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        if (s.length() < 3) {
            System.out.println("Invalid: wrong length");
            return;
        }

        s = s.substring(0, 3).toUpperCase() + s.substring(3);

        if (s.length() != 14) {
            System.out.println("Invalid: wrong length");
            return;
        }

        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(s.charAt(i))) {
                System.out.println("Invalid: bank code must be 3 letters");
                return;
            }

        for (int i = 3; i < 14; i++)
            if (!Character.isDigit(s.charAt(i))) {
                System.out.println("Invalid: non-digit body");
                return;
            }

        String date = s.substring(3, 9);
        String seq = s.substring(9);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(s.substring(0, 3)).append("] DATE: ")
          .append(date.substring(0, 2)).append("/")
          .append(date.substring(2, 4)).append("/")
          .append(date.substring(4)).append(" | SEQ: ").append(seq);

        System.out.println(sb);
    }
}
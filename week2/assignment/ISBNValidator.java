import java.util.*;

public class ISBNValidator {
    static String normalizeCode(String s) {
        s = s.trim();
        return s.substring(0, 3).toUpperCase() + s.substring(3);
    }

    static void validate(String s) {
        s = normalizeCode(s);

        if (s.length() != 13) {
            System.out.println("Invalid: wrong length");
            return;
        }

        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(s.charAt(i))) {
                System.out.println("Invalid: publisher code must be 3 letters");
                return;
            }

        for (int i = 3; i < 13; i++)
            if (!Character.isDigit(s.charAt(i))) {
                System.out.println("Invalid: body must be digits");
                return;
            }

        StringBuilder r = new StringBuilder();
        r.append("[").append(s.substring(0, 3)).append("] YEAR: ");
        r.append(s.substring(3, 7)).append(" | CATALOG: ");
        r.append(s.substring(7));
        System.out.println(r);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        validate(sc.nextLine());
    }
}
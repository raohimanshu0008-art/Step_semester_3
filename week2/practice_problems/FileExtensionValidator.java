import java.util.*;

public class FileExtensionValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int i = s.lastIndexOf('.');

        if (i == -1) {
            System.out.println("Rejected — invalid file type");
            return;
        }

        String e = s.substring(i + 1);

        if (e.equalsIgnoreCase("pdf") || e.equalsIgnoreCase("docx") || e.equalsIgnoreCase("zip"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected — invalid file type");
    }
}
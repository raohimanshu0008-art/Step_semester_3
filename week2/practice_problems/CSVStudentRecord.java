import java.util.*;

public class CSVStudentRecord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(",");

        if (a.length != 3)
            System.out.println("Invalid Record");
        else
            System.out.println("Name: " + a[0] + " | Roll No: " + a[1] + " | Dept: " + a[2]);
    }
}
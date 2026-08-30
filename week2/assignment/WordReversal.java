import java.util.*;

public class WordReversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words)
            result.append(new StringBuilder(word).reverse()).append(" ");

        System.out.println(result.toString().trim());
    }
}
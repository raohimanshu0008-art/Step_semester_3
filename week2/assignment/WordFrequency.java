import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().toLowerCase().replace(",", "").replace(".", "");

        Set<String> stop = new HashSet<>(Arrays.asList("the","was","and","a","is","of","in"));
        Map<String, Integer> map = new HashMap<>();

        for (String word : s.split("\\s+"))
            if (!stop.contains(word))
                map.put(word, map.getOrDefault(word, 0) + 1);

        map.entrySet().stream()
           .sorted((a, b) -> b.getValue() - a.getValue())
           .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}
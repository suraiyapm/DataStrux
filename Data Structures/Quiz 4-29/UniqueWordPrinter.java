import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.SequencedSet;

public class UniqueWordPrinter {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keybSc = new Scanner(System.in);
        System.out.print("filename? ");
        String filename = keybSc.next();
        Scanner sc = new Scanner(new File(filename));
        SequencedSet<String> words = new LinkedHashSet<>();
        while (sc.hasNext()) {
            String word = sc.next();
            words.add(word);
        }
        for (String word : words) {
            System.out.println(word);
        }
    }
}
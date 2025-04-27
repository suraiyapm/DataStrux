import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueWords {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keybSc = new Scanner(System.in);
        System.out.print("filename? ");
        String filename = keybSc.next();
        Scanner fileSc = new Scanner(new File(filename));
        Set<String> uniqueWords = new HashSet<>();

        while (fileSc.hasNext()) {
            String word = fileSc.next();
            uniqueWords.add(word);
        }
        System.out.println("Number of unique words: " + uniqueWords.size());
    }
}
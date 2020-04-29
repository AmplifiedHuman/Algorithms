import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BruteForceSearch {
    // Function returns the start index of substring if found, -1 otherwise
    public static int search(String txt, String pat) {
        int tLength = txt.length();
        int ptLength = pat.length();

        for (int i = 0; i <= tLength - ptLength; i++) {
            int j;
            for (j = 0; j < ptLength; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == ptLength) {
                return i;
            }
        }
        // no matches
        return -1;
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("src/input_files/mobydick.txt"))) {
            StringBuilder sb = new StringBuilder();
            while (in.hasNextLine()) {
                sb.append(in.nextLine());
            }
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter a phrase to search in Moby Dick: ");
            int result = search(sb.toString(), input.nextLine());
            if (result == -1) {
                System.out.println("Phrase not found.");
            } else {
                System.out.println("Phrase found at index: " + result);
            }
        } catch (IOException ex) {
            System.out.println("Text file not found");
            ex.printStackTrace();
        }
//         runBenchMark();
    }

    private static void runBenchMark() {
        String[] files = new String[]{"src/input_files/random.txt", "src/input_files/100_words.txt",
                "src/input_files/1000_words.txt", "src/input_files/10000_words.txt"};
        try (FileWriter writer = new FileWriter("src/output_files/brute_force.txt")) {
            for (String file : files) {
                Scanner in = new Scanner(new File(file));
                StringBuilder sb = new StringBuilder();
                while (in.hasNextLine()) {
                    sb.append(in.nextLine());
                }
                // run search for patterns of length 5 - 20, get average execution time
                long totalTime = 0;
                for (int i = 5; i <= sb.length(); i *= 1.5) {
                    long startTime = System.nanoTime();
                    search(sb.toString(), "aaabc");
                    long elapsedTime = System.nanoTime() - startTime;
                    totalTime += elapsedTime;
                }
                writer.write(String.format("%d %d\n", sb.length(), totalTime));
            }
        } catch (IOException ex) {
            System.out.println("IO error occurred.");
            ex.printStackTrace();
        }
    }
}

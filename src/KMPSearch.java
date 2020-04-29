import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KMPSearch {

    // Returns the index of the pattern
    public static int search(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        int[] prefixArray = computePrefixArray(pat);
        char[] patternArray = pat.toCharArray();
        char[] textArray = txt.toCharArray();
        int j = 0;
        for (int i = 0; i < N; i++) {
            while (j > 0 && (textArray[i] != patternArray[j])) {
                j = prefixArray[j - 1];
            }
            if (textArray[i] == patternArray[j]) {
                j++;
            }
            if (j == M) {
                return i - j + 1;
            }
        }
        return -1;
    }

    // compute prefix array given a string pattern
    public static int[] computePrefixArray(String pat) {
        int[] prefixArray = new int[pat.length()];
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        prefixArray[0] = 0;

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                prefixArray[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = prefixArray[len - 1];
                } else {
                    prefixArray[i] = len;
                    i++;
                }
            }
        }
        return prefixArray;
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

        // runBenchMark();
    }

    private static void runBenchMark() {
        String[] files = new String[]{"src/input_files/random.txt", "src/input_files/100_words.txt",
                "src/input_files/1000_words.txt", "src/input_files/10000_words.txt"};
        try (FileWriter writer = new FileWriter("src/output_files/kmp.txt")) {
            for (String file : files) {
                Scanner in = new Scanner(new File(file));
                StringBuilder sb = new StringBuilder();
                while (in.hasNextLine()) {
                    sb.append(in.nextLine());
                }
                long totalTime = 0;
                // run search for patterns of length 5 - text-length, get total time
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

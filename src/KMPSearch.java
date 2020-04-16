import java.util.Arrays;

public class KMPSearch {

    // Returns the index of the pattern
    public static int search(String pat, String txt) {
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

    public static int[] computePrefixArray(String pat) {
        int[] prefixArray = new int[pat.length()];
        char[] patternArray = pat.toCharArray();
        int j = 0;
        for (int i = 1; i < pat.length(); i++) {
            // Terminates when j == 0 or there's a match
            while (j > 0 && (patternArray[j] != patternArray[i])) {
                j = prefixArray[j - 1];
            }
            // increment j if pattern matches, compute entry
            if (patternArray[i] == patternArray[j]) {
                prefixArray[i] = j + 1;
                j++;
            }
        }
        return prefixArray;
    }

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        System.out.println(Arrays.toString(computePrefixArray("abcdabca")));
        System.out.println("Match at: " + search(pat, txt));
    }
}

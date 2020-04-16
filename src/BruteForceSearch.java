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
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        System.out.println(search(txt, pat));
    }
}

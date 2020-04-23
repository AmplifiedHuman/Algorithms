/******************************************************************************
 *  Compilation:  javac ThreeSumB.java
 *  Execution:    java ThreeSumB input.txt
 *
 *  Reads n integers
 *  and counts the number of triples that sum to exactly 0.
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *     - doesn't handle case when input has duplicates
 *     
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 ******************************************************************************/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.lang.*;


public class ThreeSumB {

    // Do not instantiate.
    private ThreeSumB() { }

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

  
    /**
     * Returns the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param a the array of integers
     * @return the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}
     */
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) count++;
            }
        }
        return count;
    }

    // Expected performance O(n ^ 2 log n) since binary search is O(log n), sorting is O(n log n)
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter(new File("src/output_files/threesumB.txt"));
        StringBuilder sb = new StringBuilder();
        // the rest input files
        String[] inputFiles = {"src/input_files/8ints.txt", "src/input_files/1Kints.txt", "src/input_files/2Kints.txt",
                "src/input_files/2Kints.txt", "src/input_files/4Kints.txt", "src/input_files/8Kints.txt",
                "src/input_files/16Kints.txt", "src/input_files/32Kints.txt"};
        int i = 8;
        for (String file : inputFiles) {
            In in = new In(file);
            int[] a = in.readAllInts();
            long startTime = System.nanoTime();
            int result = count(a);
            long elapsedTime = System.nanoTime() - startTime;
            sb.append(String.format("%d %d\n", i, elapsedTime));
            System.out.println(result);
            if (i == 8) {
                i = 1000;
            } else {
                i *= 2;
            }
        }
        fw.write(sb.toString());
        fw.close();
    } 
} 


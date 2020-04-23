/******************************************************************************
 *  Compilation:  javac ThreeSumA.java
 *  Execution:    java ThreeSum input.txt
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 ******************************************************************************/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ThreeSumA {

    // Do not instantiate.
    private ThreeSumA() {
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
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Expected performance O(n ^ 3)
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter(new File("src/output_files/threesumA.txt"));
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



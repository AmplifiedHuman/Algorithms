import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/* Russian Peasant Multiplication Algorithm
 *   Note that the n here is the larger number
 * Overall Runtime	O(log n), since we are halving the first number repeatedly
 * Worst Case	O(log n)
 *  Best Case	O(log n)
 */

public class RussianPeasant {
    public static long russianMultiply(int n, int m) {
        // running sum
        long sum = 0;
        while (n != 0) {
            // only add m to running sum if n is odd
            if (n % 2 != 0) {
                sum += m;
            }
            n /= 2;
            m *= 2;
        }
        return sum;
    }

    // Interactive demo of the algorithm
    public static void main(String[] args) {
        // prompts user for number and output result
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int x = Integer.parseInt(reader.nextLine());
        System.out.print("Enter second number: ");
        int y = Integer.parseInt(reader.nextLine());
        long startTime = System.nanoTime();
        System.out.println("Result: " + russianMultiply(x, y));
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time taken: " + elapsedTime + "ns");
//         runs analysis and saves it as a text file
//        System.out.println("Running Analysis...");
//        measureAndRecordPerformance(1000, Integer.MAX_VALUE, 1.05);
    }

    // Automatically time the algorithm with increasing inputs and stores the output
    public static void measureAndRecordPerformance(int start, int end, double factor) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (start < end) {
            long startTime = System.nanoTime();
            long result = russianMultiply(start, start);
            long elapsedTime = System.nanoTime() - startTime;
            sb.append(String.format("%d %d\n", start, elapsedTime));
            start *= factor;
        }
        FileWriter fileWriter = new FileWriter("src/output_files/russian_peasant.txt");
        fileWriter.write(sb.toString());
        fileWriter.close();
    }
}

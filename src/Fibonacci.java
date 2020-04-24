import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// The fibonacci sequence is defined as 0, 1, 1, 2, 3 ...
public class Fibonacci {
    // Runtime is O(n)
    public static long fibonacciIterative(int n) {
        if (n < 1) {
            return 0;
        }
        long fib = 1;
        long prevFib = 1;

        for (int i = 2; i < n; i++) {
            long temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }

    // Runtime is O(2 ^ n)
    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Runtime is O(n)
    public static long fibonacciMemoization(int n, long[] array) {
        // checks if the answer cached previously
        if (array[n] != 0) {
            return array[n];
        }
        long answer;
        // base case
        if (n == 0 || n == 1) {
            answer = n;
        } else {
            // recursive case
            answer = fibonacciMemoization(n - 1, array) + fibonacciMemoization(n - 2, array);
        }
        // cache computed answer
        array[n] = answer;
        return answer;
    }

    public static void main(String[] args) {
        // simple test
        Scanner reader = new Scanner(System.in);
        System.out.print("Compute nth fibonacci number (0-n): ");
        int n = Integer.parseInt(reader.nextLine());
        System.out.println("Recursive: " + fibonacciRecursive(n));
        System.out.println("Iterative: " + fibonacciIterative(n));
        System.out.println("Memoization: " + fibonacciMemoization(n, new long[n + 1]));
//        measureAndRecordPerformance(1, 47);
    }

    public static void measureAndRecordPerformance(int start, int end) throws IOException {
        // test each method
        StringBuilder recursive = new StringBuilder();
        StringBuilder iterative = new StringBuilder();
        StringBuilder memoization = new StringBuilder();
        while (start < end) {
            // time recursive
            long startTime = System.nanoTime();
            long result = fibonacciRecursive(start);
            long elapsedTime = System.nanoTime() - startTime;
            recursive.append(String.format("%d %d\n", start, elapsedTime));
            // time iterative
            startTime = System.nanoTime();
            result = fibonacciIterative(start);
            elapsedTime = System.nanoTime() - startTime;
            iterative.append(String.format("%d %d\n", start, elapsedTime));
            // time memoization
            // create array for caching result
            long[] cache = new long[start + 1];
            startTime = System.nanoTime();
            result = fibonacciMemoization(start, cache);
            elapsedTime = System.nanoTime() - startTime;
            memoization.append(String.format("%d %d\n", start, elapsedTime));
            // increase input by factor
            start++;
        }
        // write results
        FileWriter fileWriter = new FileWriter("src/output_files/fibonacci_recursive.txt");
        fileWriter.write(recursive.toString());
        fileWriter.close();
        fileWriter = new FileWriter("src/output_files/fibonacci_iterative.txt");
        fileWriter.write(iterative.toString());
        fileWriter.close();
        fileWriter = new FileWriter("src/output_files/fibonacci_memoization.txt");
        fileWriter.write(memoization.toString());
        fileWriter.close();
    }
}

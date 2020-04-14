import java.util.Scanner;

public class RussianPeasant {
    /* Russian Peasant Multiplication Algorithm
     * Complexity: O(log n)
     */
    public static int russianMultiply(int n, int m) {
        // running sum
        int sum = 0;
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

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int x = Integer.parseInt(reader.nextLine());
        System.out.print("Enter second number: ");
        int y = Integer.parseInt(reader.nextLine());
        long startTime = System.nanoTime();
        System.out.println("Result: " + russianMultiply(x, y));
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time taken: " + elapsedTime + "ns");
    }
}

public class Fibonacci {
    // sequence is defined as 0, 1, 1, 2, 3 ...
    public static int fibonacciIterative(int n) {
        if (n < 1) {
            return 0;
        }
        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciMemoization(int n, int[] array) {
        if (array[n] != 0) {
            return array[n];
        }
        int answer;
        if (n == 0 || n == 1) {
            answer = n;
        } else {
            answer = fibonacciMemoization(n - 1, array) + fibonacciMemoization(n - 2, array);
        }
        array[n] = answer;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(fibonacciIterative(45));
        System.out.println(fibonacciMemoization(45, new int[46]));
        System.out.println(fibonacciRecursive(45));
    }
}

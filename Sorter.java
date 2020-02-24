import java.util.Arrays;
import java.util.Random;

public class Sorter {
    public static void main(String[] args) {
        // size of input
        int n = 10000;
        int[] input = generateInput(n);
        // calculate elapse time for bubble sort
        long startTime = System.nanoTime();
        bubbleSort(input);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Bubble Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for selection sort
        input = generateInput(n);
        startTime = System.nanoTime();
        selectionSort(input);
        endTime = System.nanoTime();
        System.out.println("Selection Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Selection Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for insertion sort
        input = generateInput(n);
        startTime = System.nanoTime();
        insertionSort(input);
        endTime = System.nanoTime();
        System.out.println("Insertion Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Insertion Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for merge sort
        input = generateInput(n);
        startTime = System.nanoTime();
        mergeSort(input);
        endTime = System.nanoTime();
        System.out.println("Merge Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Merge Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);

//        calculate elapse time for bogo sort
//        input = generateInput(n);
//        startTime = System.nanoTime();
//        bogoSort(input);
//        endTime = System.nanoTime();
//        System.out.println("Bogo Sorted Array: " + Arrays.toString(input));
//        System.out.printf("Time elapsed for Bogo Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);
    }

    // Worst case: O(n ^ 2)
    // Best case: O(n ^ 2) for this implementation (can be shortened to O(n)
    // if we check if for 0 swaps in each iteration, if so terminate outer loop as the list is already sorted)
    private static void bubbleSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - 1 - i; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
    }

    // Best case: O(n ^ 2)
    // Worst case: O(n ^ 2)
    // Slightly faster as the exact order of growth is n ^ 2/ 2
    private static void selectionSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            int currentMinIndex = i;
            int currentMin = input[i];
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < currentMin) {
                    currentMinIndex = j;
                    currentMin = input[j];
                }
            }
            int temp = input[i];
            input[i] = input[currentMinIndex];
            input[currentMinIndex] = temp;
        }
    }

    // Best case: O(n)
    // Worst case: O(n ^ 2)
    private static void insertionSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            int j = i - 1;
            while (j >= 0 && input[j] > input[j + 1]) {
                int temp = input[j + 1];
                input[j + 1] = input[j];
                input[j] = temp;
                j--;
            }
        }
    }

    // Best case: O(n)
    // Worst case: ??? Unbounded (Infinity)
    private static void bogoSort(int[] input) {
        while (!isSorted(input)) {
            shuffle(input);
        }
    }

    // Best case: O(n log n)
    // Worst case: O(n log n)
    private static void mergeSort(int[] input) {
        if (input.length < 10) {
            selectionSort(input);
            return;
        }
        int mid = input.length / 2;
        // Create sub arrays
        int[] firstHalf = new int[mid];
        int[] secondHalf = new int[input.length - mid];
        // Initialise values
        System.arraycopy(input, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(input, mid, secondHalf, 0, secondHalf.length);
        mergeSort(firstHalf);
        mergeSort(secondHalf);
        if (firstHalf[firstHalf.length - 1] > secondHalf[0]) {
            merge(firstHalf, secondHalf, input);
        }
    }

    private static void merge(int[] firstHalf, int[] secondHalf, int[] input) {
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < firstHalf.length && j < secondHalf.length) {
            if (firstHalf[i] <= secondHalf[j]) {
                input[k] = firstHalf[i];
                i++;
            } else {
                input[k] = secondHalf[j];
                j++;
            }
            k++;
        }
        if (i < firstHalf.length) {
            System.arraycopy(input, k, firstHalf, i, firstHalf.length - i);
        }
        if (j < secondHalf.length) {
            System.arraycopy(input, k, secondHalf, j, firstHalf.length - j);
        }
    }

    // generates an input array of length n with bound n
    private static int[] generateInput(int n) {
        Random random = new Random();
        // Initialise array with random generated numbers
        int[] input = new int[n];
        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(n);
        }
        return input;
    }

    // shuffles an array in place using Fisher Yates shuffle
    private static void shuffle(int[] input) {
        Random random = new Random();
        for (int i = input.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            int swap = input[randomIndex];
            input[randomIndex] = input[i];
            input[i] = swap;
        }
    }

    // Checks if an array is sorted
    private static boolean isSorted(int[] input) {
        for (int i = 1; i < input.length; i++) {
            if (input[i] < input[i - 1]) {
                return false;
            }
        }
        return true;
    }
}


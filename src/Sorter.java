package src;

import java.util.Arrays;
import java.util.Random;

public class Sorter {
    public static void main(String[] args) {
        // size of input
        int n = 600;
        int[] input = generateInput(n);
        // calculate elapse time for bubble sort
        long startTime = System.nanoTime();
        bubbleSort(input);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Bubble Sort (%d elements): %fs\n\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for selection sort
        input = generateInput(n);
        startTime = System.nanoTime();
        selectionSort(input);
        endTime = System.nanoTime();
        System.out.println("Selection Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Selection Sort (%d elements): %fs\n\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for insertion sort
        input = generateInput(n);
        startTime = System.nanoTime();
        insertionSort(input);
        endTime = System.nanoTime();
        System.out.println("Insertion Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Insertion Sort (%d elements): %fs\n\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for shell sort (Knuth sequence)
        input = generateInput(n);
        startTime = System.nanoTime();
        shellSort(input);
        endTime = System.nanoTime();
        System.out.println("Shell Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Shell Sort (%d elements): %fs\n\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for merge sort
        input = generateInput(n);
        startTime = System.nanoTime();
        mergeSort(input);
        endTime = System.nanoTime();
        System.out.println("Merge Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Merge Sort (%d elements): %fs\n\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for quick sort
        input = generateInput(n);
        startTime = System.nanoTime();
        quickSort(input);
        endTime = System.nanoTime();
        System.out.println("Quick Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Quick Sort (%d elements): %fs\n\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for enhanced quick sort
        input = generateInput(n);
        startTime = System.nanoTime();
        enhancedQuickSort(input);
        endTime = System.nanoTime();
        System.out.println("Enhanced Quick Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Enhanced Quick Sort (%d elements): %fs\n\n", n, (endTime - startTime) / 1e9);

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
    // Worst case: O(n ^ 2) df
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
            int j = i;
            while (j >= 1 && input[j - 1] > input[j]) {
                int temp = input[j];
                input[j] = input[j - 1];
                input[j - 1] = temp;
                j--;
            }
        }
    }

    private static void insertionSort(int[] input, int low, int high) {
        if (high <= low) {
            return;
        }
        for (int i = low + 1; i < high; i++) {
            int j = i;
            while (j >= (low + 1) && input[j - 1] > input[j]) {
                int temp = input[j];
                input[j - 1] = temp;
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
    // Worst case: O (n ^ ( 3/2))
    private static void shellSort(int[] input) {
        int interval = 0;
        // This loop stops before interval is greater than input.length
        while (3 * interval < input.length) {
            interval = 3 * interval + 1;
        }
        // We continue to sort until interval falls negative
        while (interval > 0) {
            for (int i = interval; i < input.length; i++) {
                int value = input[i];
                int j = i;
                // shift values
                while (j >= interval && input[j - interval] > value) {
                    input[j] = input[j - interval];
                    j = j - interval;
                }
                // found correct position of value
                input[j] = value;
            }
            // Get next interval
            interval = (interval - 1) / 3;
        }
    }

    // Best case: O(n log n)
    // Worst case: O(n log n)
    private static void mergeSort(int[] input) {
        // If input length is less than 10, use selection sort
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
        // Recursively Merge Sort the two halves
        mergeSort(firstHalf);
        mergeSort(secondHalf);
        // We only do merging if the last element of the first half is larger than the first element in second half
        if (firstHalf[firstHalf.length - 1] > secondHalf[0]) {
            merge(firstHalf, secondHalf, input);
        }
    }

    private static void merge(int[] firstHalf, int[] secondHalf, int[] input) {
        int k = 0;
        // first array running index
        int i = 0;
        // second array running index
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
        // If we haven't process all of the elements in firstHalf, copy the rest into input
        if (i < firstHalf.length) {
            System.arraycopy(input, k, firstHalf, i, firstHalf.length - i);
        }
        // If we haven't process all of the elements in secondHalf, copy the rest into input
        if (j < secondHalf.length) {
            System.arraycopy(input, k, secondHalf, j, firstHalf.length - j);
        }
    }

    private static void enhancedQuickSort(int[] arr) {
        shuffle(arr);
        enhancedQuickSort(arr, 0, arr.length - 1);
    }

    private static void enhancedQuickSort(int[] arr, int low, int high) {
        if (low + 10 > high) {
            insertionSort(arr, low, high);
            return;
        }
        if (low >= high) {
            return;
        }
        // Get the pivot from partitioning
        int pivot = enhancedPartition(arr, low, high);
        // Quick sort upper half and lower half
        enhancedQuickSort(arr, low, pivot - 1);
        enhancedQuickSort(arr, pivot + 1, high);
    }

    private static int enhancedPartition(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        // sort the three elements
        if (arr[mid] < arr[low]) {
            int temp = arr[mid];
            arr[mid] = arr[low];
            arr[low] = temp;
        }
        if (arr[high] < arr[low]) {
            int temp = arr[high];
            arr[high] = arr[low];
            arr[low] = temp;
        }
        if (arr[high] < arr[mid]) {
            int temp = arr[high];
            arr[high] = arr[mid];
            arr[mid] = temp;
        }
        // swap pivot to last element
        int swap = arr[mid];
        arr[mid] = arr[high];
        arr[high] = swap;
        // Get pivotItem
        int pivotItem = arr[high];
        // Index of smaller element, to keep track of where we should insert the pivot
        int i = low - 1;
        // Ignore last element
        for (int j = low; j < high; j++) {
            if (arr[j] < pivotItem) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap pivot with the first bigger item
        int temp = arr[i + 1];
        arr[i + 1] = pivotItem;
        arr[high] = temp;
        // Return pivot index
        return i + 1;
    }

    // Best case: O(n log n)
    // Worse case: O(n ^ 2)
    private static void quickSort(int[] arr) {
        shuffle(arr);
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        // If one element return (base case)
        if (low >= high) {
            return;
        }
        // Get the pivot from partitioning
        int pivot = partition(arr, low, high);
        // Quick sort upper half and lower half
        quickSort(arr, low, pivot - 1);
        quickSort(arr, pivot + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        // Choose last element as the pivot
        int pivotItem = arr[high];
        // Index of smaller element, to keep track of where we should insert the pivot
        int i = low - 1;
        // Ignore last element
        for (int j = low; j < high; j++) {
            if (arr[j] < pivotItem) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap pivot with the first bigger item
        int temp = arr[i + 1];
        arr[i + 1] = pivotItem;
        arr[high] = temp;
        // Return pivot index
        return i + 1;
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


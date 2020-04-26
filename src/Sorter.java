import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sorter {
    public static void main(String[] args) {
        // Interactive Sorting Demo
        Scanner reader = new Scanner(System.in);
        // get choice
        while (true) {
            String choice;
            do {
                printHelpMessage();
                System.out.print("Selection: ");
                choice = reader.nextLine();
            } while (!choice.matches("[0-7xXTt]"));
            // terminates if x encountered
            if (choice.equalsIgnoreCase("x")) {
                break;
            }
            if (choice.equalsIgnoreCase("t")) {
                System.out.print("Enter starting number: ");
                int start = Integer.parseInt(reader.nextLine());
                System.out.print("Enter ending number: ");
                int end = Integer.parseInt(reader.nextLine());
                System.out.print("Enter growth factor: ");
                double factor = Double.parseDouble(reader.nextLine());
                System.out.println("Running Test Suite...");
                generateTestOutput(start, end, factor);
                System.out.println("Test completed.");
                continue;
            }
            int choiceNum = Integer.parseInt(choice);
            // get array length
            String length;
            do {
                System.out.print("Enter array length: ");
                length = reader.nextLine();
            } while (!length.matches("^[0-9]+$"));
            System.out.println();
            int inputLength = Integer.parseInt(length);
            // generate input
            int[] input = generateInput(inputLength);
            // store original array string
            String original = "Original Array: " + Arrays.toString(input);
            // choose sort
            long startTime;
            long elapsedTime = 0;
            switch (choiceNum) {
                case 0:
                    System.out.println("Bubble Sort: ");
                    startTime = System.nanoTime();
                    bubbleSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                case 1:
                    System.out.println("Selection Sort: ");
                    startTime = System.nanoTime();
                    selectionSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                case 2:
                    System.out.println("Insertion Sort: ");
                    startTime = System.nanoTime();
                    insertionSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                case 3:
                    System.out.println("Shell Sort: ");
                    startTime = System.nanoTime();
                    shellSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                case 4:
                    System.out.println("Bogo Sort: ");
                    startTime = System.nanoTime();
                    bogoSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                case 5:
                    System.out.println("Merge Sort: ");
                    startTime = System.nanoTime();
                    mergeSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                case 6:
                    System.out.println("Quick Sort: ");
                    startTime = System.nanoTime();
                    quickSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                case 7:
                    System.out.println("Enhanced Quick Sort: ");
                    startTime = System.nanoTime();
                    enhancedQuickSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    break;
                default:
                    System.out.println("Invalid operation.");
            }
            System.out.println("Time elapsed: " + elapsedTime + "ns");
            System.out.println(original);
            System.out.println("Sorted Array: " + Arrays.toString(input));
            System.out.println();
        }
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
        int[] secondHalf = new int[input.length - firstHalf.length];
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
            System.arraycopy(firstHalf, i, input, k, firstHalf.length - i);
        }
        // If we haven't process all of the elements in secondHalf, copy the rest into input
        if (j < secondHalf.length) {
            System.arraycopy(secondHalf, j, input, k, secondHalf.length - j);
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

    private static void printHelpMessage() {
        System.out.println("Please select a sort (0-7): ");
        System.out.println("[0] Bubble Sort");
        System.out.println("[1] Selection Sort");
        System.out.println("[2] Insertion Sort");
        System.out.println("[3] Shell Sort");
        System.out.println("[4] Bogo Sort");
        System.out.println("[5] Merge Sort");
        System.out.println("[6] Quick Sort");
        System.out.println("[7] Enhanced Quick Sort");
        System.out.println("[T] Start Test");
        System.out.println("[X] Quit");
    }

    private static void generateTestOutput(int start, int end, double factor) {
        try (
                FileWriter bubbleWriter = new FileWriter("src/output_files/bubble_sort.txt");
                FileWriter selectionWriter = new FileWriter("src/output_files/selection_sort.txt");
                FileWriter insertionWriter = new FileWriter("src/output_files/insertion_sort.txt");
                FileWriter shellWriter = new FileWriter("src/output_files/shell_sort.txt");
                FileWriter bogoWriter = new FileWriter("src/output_files/bogo_sort.txt");
                FileWriter mergeWriter = new FileWriter("src/output_files/merge_sort.txt");
                FileWriter quickWriter = new FileWriter("src/output_files/quick_sort.txt");
                FileWriter enhancedQuickWriter = new FileWriter("src/output_files/enhanced_quick_sort.txt")
        ) {

            int[] input;
            long startTime;
            long elapsedTime;

            while (start < end) {
                // guard for bogoSort to prevent long execution times
                if (start < 10) {
                    input = generateInput(start);
                    startTime = System.nanoTime();
                    bogoSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    bogoWriter.write(String.format("%d %d\n", start, elapsedTime));
                }
                // guard for n ** 2 algorithms and shell sort to prevent long execution times
                if (start < 30000) {
                    // bubble sort
                    input = generateInput(start);
                    startTime = System.nanoTime();
                    bubbleSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    bubbleWriter.append(String.format("%d %d\n", start, elapsedTime));
                    // insertion sort
                    input = generateInput(start);
                    startTime = System.nanoTime();
                    insertionSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    insertionWriter.append(String.format("%d %d\n", start, elapsedTime));
                    // selection sort
                    input = generateInput(start);
                    startTime = System.nanoTime();
                    selectionSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    selectionWriter.append(String.format("%d %d\n", start, elapsedTime));
                    // shell sort
                    input = generateInput(start);
                    startTime = System.nanoTime();
                    shellSort(input);
                    elapsedTime = System.nanoTime() - startTime;
                    shellWriter.append(String.format("%d %d\n", start, elapsedTime));
                }
                // fast sorts
                // merge sort
                input = generateInput(start);
                startTime = System.nanoTime();
                mergeSort(input);
                elapsedTime = System.nanoTime() - startTime;
                mergeWriter.append(String.format("%d %d\n", start, elapsedTime));
                // quick sort
                input = generateInput(start);
                startTime = System.nanoTime();
                quickSort(input);
                elapsedTime = System.nanoTime() - startTime;
                quickWriter.append(String.format("%d %d\n", start, elapsedTime));
                // enhanced quick sort
                input = generateInput(start);
                startTime = System.nanoTime();
                enhancedQuickSort(input);
                elapsedTime = System.nanoTime() - startTime;
                enhancedQuickWriter.append(String.format("%d %d\n", start, elapsedTime));
                start = (int) Math.ceil(start * factor);
                System.out.println(start);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }
}


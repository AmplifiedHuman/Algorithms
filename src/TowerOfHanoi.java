import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TowerOfHanoi {
    // Runtime is O(2 ^ n)
    public static void solveHanoi(int n, String disk, String source, String destination, String auxiliary) {
        if (n == 1) {
            System.out.println(String.format("Move %s from %s to %s.", disk, source, destination));
            return;
        }
        solveHanoi(n - 1, disk, source, auxiliary, destination);
        solveHanoi(1, disk, source, destination, auxiliary);
        solveHanoi(n - 1, disk, auxiliary, destination, source);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter the number of disks: ");
        int n = Integer.parseInt(reader.nextLine());
        solveHanoi(n, "Disk", "A", "C", "B");
//        measureAndRecordPerformance(1, 23);
    }

    public static void measureAndRecordPerformance(int start, int end) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            long startTime = System.nanoTime();
            solveHanoi(i, "Disk", "A", "C", "B");
            long elapsedTime = System.nanoTime() - startTime;
            sb.append(String.format("%d %d\n", i, elapsedTime));
        }
        FileWriter fileWriter = new FileWriter("src/output_files/tower_of_hanoi.txt");
        fileWriter.write(sb.toString());
        fileWriter.close();
    }
}

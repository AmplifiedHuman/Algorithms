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
        solveHanoi(3, "Disk", "A", "C", "B");
    }
}

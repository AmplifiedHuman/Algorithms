import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Interactive Demo
        Scanner reader = new Scanner(System.in);
        while (true) {
            String choice;
            do {
                printHelpMessage();
                System.out.print("Selection: ");
                choice = reader.nextLine();
            } while (!choice.matches("[0-7xX]"));
            // terminates if x encountered
            if (choice.equalsIgnoreCase("x")) {
                break;
            }
            int choiceNum = Integer.parseInt(choice);
            System.out.println("\n");
            switch (choiceNum) {
                case 0:
                    RussianPeasant.main(null);
                    break;
                case 1:
                    Fibonacci.main(null);
                    break;
                case 2:
                    TowerOfHanoi.main(null);
                    break;
                case 3:
                    Sorter.main(null);
                    break;
                case 4:
                    BruteForceSearch.main(null);
                    break;
                case 5:
                    KMPSearch.main(null);
                    break;
                default:
                    System.out.println("Selection error.");
                    break;
            }
        }
    }

    private static void printHelpMessage() {
        System.out.println("\n\nPlease select a program to run (some programs are not available and can only be run " +
                "through the terminal or their respective classes): ");
        System.out.println("[0] Russian Peasant Demo");
        System.out.println("[1] Nth Fibonacci Number");
        System.out.println("[2] Tower of Hanoi");
        System.out.println("[3] Sort Algorithms");
        System.out.println("[4] Brute Force Search");
        System.out.println("[5] KMP Search");
        System.out.println("[X] Quit");
    }
}

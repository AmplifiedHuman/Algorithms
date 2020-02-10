public class RussianPeasant {
    public static int russianMultiply(int n, int m) {
        int sum = 0;
        while (n != 0) {
            if (n % 2 != 0) {
                sum += m;
            }
            n /= 2;
            m *= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        final long startTime = System.nanoTime();
        System.out.println(russianMultiply(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2));
        final long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time taken: " + elapsedTime);
    }
}

/* Java function that takes in a string as a command line argument and returns a
 * compressed string that uses Run Length Encoding (RLE).
 */
public class RLE {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RLE.java [String]");
            System.exit(-1);
        }
        String uncompressedString = args[0];
        StringBuilder sb = new StringBuilder();
        char ch = 'x';
        int count = 0;
        for (int i = 0; i < uncompressedString.length(); i++) {
            char[] array = uncompressedString.toCharArray();
            if (i == 0) {
                ch = array[i];
            } else if (array[i] != ch) {
                sb.append(ch);
                if (count > 1) {
                    sb.append(count);
                }
                ch = array[i];
                count = 0;
            }
            count++;
            if (i == uncompressedString.length() - 1) {
                sb.append(ch);
                if (count > 1) {
                    sb.append(count);
                }
            }
        }
        System.out.println("Result: " + sb.toString());
    }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Modified Version of RunLength.java which accepts files as arguments
 */
public class ModifiedRLE {
    private static final int R = 256;
    private static final int LG_R = 8;
    private BinaryIn in;
    private BinaryOut out;

    public ModifiedRLE() {
    }

    public static void main(String[] args) {
        generateCompressionAnalysis();
    }

    // generate test report
    public static void generateCompressionAnalysis() {
        try (FileWriter compressionResult = new FileWriter(new File("src/compressed_files/RLE.txt"))) {
            String[] files = {"medTale.txt", "mobydick.txt", "q32x48.bin", "genomeVirus.txt", "bee.txt"};
            for (String file : files) {
                // records compression information
                System.out.println(file);
                String originalReadPath = String.format("src/input_files/%s", file);
                long originalCount = countBits(new BinaryIn(originalReadPath));
                String[] fileDetails = file.split("\\.");
                String compressedWritePath = String.format("src/compressed_files/%s_RLE_compressed.%s", fileDetails[0],
                        fileDetails[1]);
                // compresses file
                ModifiedRLE RLE = new ModifiedRLE();
                RLE.setInput(new BinaryIn(originalReadPath));
                RLE.setOutput(new BinaryOut(compressedWritePath));
                long startTime = System.nanoTime();
                RLE.compress();
                long compressedTime = System.nanoTime() - startTime;
                // records decompression information
                long compressedCount = countBits(new BinaryIn(compressedWritePath));
                String decompressedWritePath = String.format("src/compressed_files/%s_RLE_decompressed.%s",
                        fileDetails[0],
                        fileDetails[1]);
                // decompresses file
                RLE = new ModifiedRLE();
                RLE.setInput(new BinaryIn(compressedWritePath));
                RLE.setOutput(new BinaryOut(decompressedWritePath));
                startTime = System.nanoTime();
                RLE.expand();
                long decompressedTime = System.nanoTime() - startTime;
                long decompressedCount = countBits(new BinaryIn(decompressedWritePath));
                // output results to result file
                compressionResult.write(String.format("%s %d %d %f %d %d %d\n", file, originalCount, compressedCount,
                        compressedCount * 1.0 / originalCount, compressedTime, decompressedTime, decompressedCount));
            }
        } catch (IOException ex) {
            System.out.println("IO error occurred");
            ex.printStackTrace();
        }
    }


    // method to calculate bits in a BinaryIn Stream
    private static long countBits(BinaryIn stream) {
        long count;
        for (count = 0; !stream.isEmpty(); count++) {
            stream.readBoolean();
        }
        return count;
    }

    public void setInput(BinaryIn in) {
        this.in = in;
    }

    public void setOutput(BinaryOut out) {
        this.out = out;
    }

    // decompresses file
    public void expand() {
        boolean b = false;
        while (!in.isEmpty()) {
            int run = in.readInt(LG_R);
            for (int i = 0; i < run; i++)
                out.write(b);
            b = !b;
        }
        out.close();
    }

    /**
     * Reads a sequence of bits from standard input; compresses
     * them using run-length coding with 8-bit run lengths; and writes the
     * results to standard output.
     */
    public void compress() {
        char run = 0;
        boolean old = false;
        while (!in.isEmpty()) {
            boolean b = in.readBoolean();
            if (b != old) {
                out.write(run, LG_R);
                run = 1;
                old = !old;
            } else {
                if (run == R - 1) {
                    out.write(run, LG_R);
                    run = 0;
                    out.write(run, LG_R);
                }
                run++;
            }
        }
        out.write(run, LG_R);
        out.close();
    }
}

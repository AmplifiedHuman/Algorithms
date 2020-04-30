import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Huffman {

    /* We use 8 bits, which is 256 characters since readString reads in 8 bit characters */
    private static final int CHAR_SET_SIZE = 256;
    private BinaryIn in;
    private BinaryOut out;

    public Huffman() {
        in = null;
        out = null;
    }

    public static void main(String[] args) {
        // check argument length
        if (args.length != 1 && args.length != 3) {
            printHelpMessage();
            System.exit(-1);
        }
        // benchmark
        if (args.length == 1 && args[0].equalsIgnoreCase("benchmark")) {
            generateCompressionAnalysis();
            System.exit(0);
        }
        Huffman huffman = new Huffman();
        if (args.length == 3) {
            huffman.setInput(new BinaryIn(args[1]));
            huffman.setOutput(new BinaryOut(args[2]));
        }
        // huffman operation
        String option = args[0];
        if (option.equalsIgnoreCase("compress")) {
            huffman.compress();
        } else if (option.equalsIgnoreCase("decompress")) {
            huffman.decompress();
        } else {
            printHelpMessage();
        }

        if (!huffman.isStandardOutput()) {
            huffman.getOutput().close();
        }

        // repeatedCompression();
    }

    public static void generateCompressionAnalysis() {
        try (FileWriter compressionResult = new FileWriter(new File("./compressed_files/Huffman.txt"))) {
            String[] files = {"medTale.txt", "mobydick.txt", "q32x48.bin", "genomeVirus.txt", "bee.txt"};
            for (String file : files) {
                // records compression information
                System.out.println(file);
                String originalReadPath = String.format("./input_files/%s", file);
                long originalCount = countBits(new BinaryIn(originalReadPath));
                String[] fileDetails = file.split("\\.");
                String compressedWritePath = String.format("./compressed_files/%s_compressed.%s", fileDetails[0],
                        fileDetails[1]);
                // compresses file
                Huffman huffman = new Huffman();
                huffman.setInput(new BinaryIn(originalReadPath));
                huffman.setOutput(new BinaryOut(compressedWritePath));
                long startTime = System.nanoTime();
                huffman.compress();
                long compressedTime = System.nanoTime() - startTime;
                if (!huffman.isStandardOutput()) {
                    huffman.getOutput().close();
                }
                // records decompression information
                long compressedCount = countBits(new BinaryIn(compressedWritePath));
                String decompressedWritePath = String.format("./compressed_files/%s_decompressed.%s", fileDetails[0],
                        fileDetails[1]);
                // decompresses file
                huffman = new Huffman();
                huffman.setInput(new BinaryIn(compressedWritePath));
                huffman.setOutput(new BinaryOut(decompressedWritePath));
                startTime = System.nanoTime();
                huffman.decompress();
                long decompressedTime = System.nanoTime() - startTime;
                if (!huffman.isStandardOutput()) {
                    huffman.getOutput().close();
                }
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

    private static void fillEncoding(TrieNode root, String[] encodingArray, String binaryRepresentation) {
        // base case
        if (root.isCharacterNode()) {
            encodingArray[root.ch] = binaryRepresentation;
            return;
        }
        // left child and right child can't be null since each node has 0 or 2 children
        fillEncoding(root.leftChild, encodingArray, binaryRepresentation + "0");
        fillEncoding(root.rightChild, encodingArray, binaryRepresentation + "1");
    }

    private static TrieNode constructTrie(int[] count) {
        PriorityQueue<TrieNode> priorityQueue = new PriorityQueue<>();
        // add every character into a minimum priority queue
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                priorityQueue.add(new TrieNode((char) i, count[i], null, null));
            }
        }
        /* start constructing the trie, we get the smallest two nodes, merge them and keep repeating the process
           until there's only one node left */
        while (priorityQueue.size() > 1) {
            TrieNode firstMin = priorityQueue.poll();
            TrieNode secondMin = priorityQueue.poll();
            // merge and insert placeholder node
            assert secondMin != null;
            priorityQueue.add(new TrieNode('#', firstMin.count + secondMin.count, firstMin, secondMin));
        }
        // return root
        return priorityQueue.poll();
    }

    private static void printHelpMessage() {
        System.out.println("Read from stdin and output to stdout: java Huffman [compress|decompress] < inputfile");
        System.out.println("Read from input file and output to output file: java Huffman [compress|decompress] " +
                "[inputfile] [outputfile]");
        System.out.println("Run benchmark for provided input files: java Huffman benchmark");
    }

    // method to calculate bits in a BinaryIn Stream
    private static long countBits(BinaryIn stream) {
        long count;
        for (count = 0; !stream.isEmpty(); count++) {
            stream.readBoolean();
        }
        return count;
    }

    // simulate repeated compression
    private static void repeatedCompression() {
        try (FileWriter compressionResult = new FileWriter(new File(
                "src/compressed_files/repeated_compression.txt"))) {
            Huffman huffman = new Huffman();
            int count = 0;
            double compressionRatio;
            do {
                count++;
                // alternate between the files
                String inputFile;
                String outputFile;
                if (count % 2 == 1) {
                    inputFile = "src/compressed_files/bee_repeated_1.txt";
                    outputFile = "src/compressed_files/bee_repeated_2.txt";
                } else {
                    inputFile = "src/compressed_files/bee_repeated_2.txt";
                    outputFile = "src/compressed_files/bee_repeated_1.txt";
                }
                long originalBits = countBits(new BinaryIn(inputFile));
                System.out.println("Input file " + inputFile + ": " + originalBits);
                huffman.setInput(new BinaryIn(inputFile));
                huffman.setOutput(new BinaryOut(outputFile));
                huffman.compress();
                huffman.getOutput().close();
                long compressedBits = countBits(new BinaryIn(outputFile));
                compressionRatio = compressedBits * 1.0 / originalBits;
                compressionResult.write(String.format("%d %d %d %f\n", count, originalBits, compressedBits,
                        compressionRatio));
            } while (compressionRatio < 1);
        } catch (IOException ex) {
            System.out.println("IO error occurred");
            ex.printStackTrace();
        }
    }

    public void compress() {
        String input = isStandardInput() ? BinaryStdIn.readString() : in.readString();
        // initialise count array
        int[] count = new int[CHAR_SET_SIZE];
        // for every character, record and update its frequency
        for (char ch : input.toCharArray()) {
            count[ch] += 1;
        }
        TrieNode rootNode = constructTrie(count);
        // encoding array to store the encoding of each character
        String[] encodingArray = new String[CHAR_SET_SIZE];
        fillEncoding(rootNode, encodingArray, "");
        writeTrie(rootNode);
        // print original message length for easy decoding
        if (isStandardOutput()) {
            BinaryStdOut.write(input.length());
        } else {
            out.write(input.length());
        }
        for (char ch : input.toCharArray()) {
            String encodedString = encodingArray[ch];
            // write each character in bit representation
            for (char bit : encodedString.toCharArray()) {
                if (bit != '1' && bit != '0') {
                    throw new IllegalArgumentException("Undefined encoding");
                }
                boolean writeBit = bit == '1';
                if (isStandardOutput()) {
                    BinaryStdOut.write(writeBit);
                } else {
                    out.write(writeBit);
                }
            }
        }
        if (isStandardInput()) {
            BinaryStdIn.close();
        }
        if (isStandardOutput()) {
            BinaryStdOut.close();
        }
    }

    // uses preorder DFS to write trie to output
    private void writeTrie(TrieNode root) {
        if (root.isCharacterNode()) {
            if (isStandardOutput()) {
                BinaryStdOut.write(true);
                BinaryStdOut.write(root.ch, 8);
            } else {
                out.write(true);
                out.write(root.ch, 8);
            }
            return;
        }
        if (isStandardOutput()) {
            BinaryStdOut.write(false);
        } else {
            out.write(false);
        }
        writeTrie(root.leftChild);
        writeTrie(root.rightChild);
    }

    private void decompress() {
        // read trie from input
        TrieNode rootNode = readTrie();
        // get original uncompressed length
        int uncompressedLength = isStandardInput() ? BinaryStdIn.readInt() : in.readInt();
        // decode using the trie
        for (int i = 0; i < uncompressedLength; i++) {
            TrieNode tempNode = rootNode;
            // characters nodes can't be a prefix of another character node
            while (!tempNode.isCharacterNode()) {
                boolean inputBit = isStandardInput() ? BinaryStdIn.readBoolean() : in.readBoolean();
                if (inputBit) {
                    tempNode = tempNode.rightChild;
                } else {
                    tempNode = tempNode.leftChild;
                }
            }
            if (isStandardOutput()) {
                BinaryStdOut.write(tempNode.ch, 8);
            } else {
                out.write(tempNode.ch, 8);
            }
        }
        if (isStandardInput()) {
            BinaryStdIn.close();
        }
        if (isStandardOutput()) {
            BinaryStdOut.close();
        }
    }

    private TrieNode readTrie() {
        boolean isLeafNode = isStandardInput() ? BinaryStdIn.readBoolean() : in.readBoolean();
        // if it's a character, construct leaf node
        if (isLeafNode) {
            // count is 0 by default since we don't need it for decompressing
            char ch = isStandardInput() ? BinaryStdIn.readChar() : in.readChar();
            return new TrieNode(ch, 0, null, null);
        }
        // if it's a parent node, recursively read child nodes
        return new TrieNode('#', 0, readTrie(), readTrie());
    }

    private BinaryOut getOutput() {
        return out;
    }

    void setOutput(BinaryOut out) {
        this.out = out;
    }

    void setInput(BinaryIn in) {
        this.in = in;
    }

    private boolean isStandardInput() {
        return in == null;
    }

    private boolean isStandardOutput() {
        return out == null;
    }

    // non character nodes have a placeholder value '#'
    private static class TrieNode implements Comparable<TrieNode> {
        private final char ch;
        private final int count;
        private final TrieNode leftChild;
        private final TrieNode rightChild;

        public TrieNode(char ch, int count, TrieNode leftChild, TrieNode rightChild) {
            this.ch = ch;
            this.count = count;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        // check if node is a character node
        private boolean isCharacterNode() {
            return leftChild == null && rightChild == null;
        }

        @Override
        public int compareTo(TrieNode o) {
            return count - o.count;
        }
    }
}

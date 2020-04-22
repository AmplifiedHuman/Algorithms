import dependencies.BinaryStdIn;
import dependencies.BinaryStdOut;

import java.util.PriorityQueue;

public class Huffman {

    /* We use 8 bits, which is 256 characters since readString reads in 8 bit characters */
    private static final int CHAR_SET_SIZE = 256;

    public static void main(String[] args) {
    }

    public static void compress() {
        String input = BinaryStdIn.readString();
        // initialise count array
        int[] count = new int[CHAR_SET_SIZE];
        // for every character, record and update its frequency
        for (char ch : input.toCharArray()) {
            count[ch] += 1;
        }
        TrieNode rootNode = constructTrie(count);
        String[] encodingArray = new String[CHAR_SET_SIZE];
        fillEncoding(rootNode, encodingArray, "");
        writeTrie(rootNode);
        // print original message length for easy decoding
        for (char ch : input.toCharArray()) {
            String encodedString = encodingArray[ch];
            // write each character in bit representation
            for (char bit : encodedString.toCharArray()) {
                if (bit == '0') {
                    BinaryStdOut.write(false);
                } else if (bit == '1') {
                    BinaryStdOut.write(true);
                } else {
                    throw new IllegalArgumentException("Undefined encoding");
                }
            }
        }
        BinaryStdOut.close();
    }

    private static void fillEncoding(TrieNode root, String[] encodingArray, String binaryRepresentation) {
        // base case
        if (root.isCharacterNode()) {
            encodingArray[root.ch] = binaryRepresentation;
            return;
        }
        // left child and right child can't be null since each node has 0 or 2 children
        fillEncoding(root.leftChild, encodingArray, binaryRepresentation + "0");
        fillEncoding(root.rightChild, encodingArray, binaryRepresentation + "0");
    }

    // uses preorder DFS to write trie to output
    private static void writeTrie(TrieNode root) {
        if (root.isCharacterNode()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(root.ch, 8);
            return;
        }
        writeTrie(root.leftChild);
        writeTrie(root.rightChild);
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

    private static void decompress() {
        // read trie from input
        TrieNode rootNode = readTrie();
        // get original uncompressed length
        int uncompressedLength = BinaryStdIn.readInt();
        // decode using the trie
        for (int i = 0; i < uncompressedLength; i++) {
            TrieNode tempNode = rootNode;
            while (!tempNode.isCharacterNode()) {
                boolean next = BinaryStdIn.readBoolean();
                if (next) {
                    tempNode = tempNode.rightChild;
                } else {
                    tempNode = tempNode.leftChild;
                }
            }
            BinaryStdOut.write(tempNode.ch, 8);
        }
        BinaryStdOut.close();
    }

    private static TrieNode readTrie() {
        // if it's a character, construct leaf node
        if (BinaryStdIn.readBoolean()) {
            // count is 0 by default since we don't need it for decompressing
            return new TrieNode(BinaryStdIn.readChar(8), 0, null, null);
        }
        // if it's a parent node, recursively read child nodes
        return new TrieNode('#', 0, readTrie(), readTrie());
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

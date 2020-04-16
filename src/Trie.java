package src;

public class Trie {
    public static int ALPHABET_SIZE = 26;
    private TrieNode root;

    public Trie() {
        // creates a sentinel node
        root = new TrieNode();
    }

    public static void main(String[] args) {
        String[] keys = {"bank", "book", "bar", "bring", "film",
                "filter", "simple", "silt", "silver"};
        String[] nonKeys = {"ban", "ba", "barr", "flf", "slits", "flim", "simpl", "filt"};

        Trie trie = new Trie();
        for (String key : keys) {
            trie.insert(key);
        }
        // tests if all the keys are in the trie
        for (String key : keys) {
            // if incorrect result print error message
            if (!trie.search(key)) {
                System.out.println(String.format("Key %s should be in the trie.", key));
            }
        }
        System.out.println("Existing words search completed.");
        // tests if non existing words are not in the trie
        for (String nonKey : nonKeys) {
            // if incorrect result print error message
            if (trie.search(nonKey)) {
                System.out.println(String.format("String %s should not be in the trie.", nonKey));
            }
        }
        System.out.println("Non-existing words search completed.");
    }

    public void insert(String word) {
        // only supports lowercase alphabetical characters
        if (word == null || !word.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid word.");
        }
        word = word.toLowerCase();
        TrieNode pointer = root;
        // go through each character in string
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // no children, create new node and follow new child
            if (pointer.children[ch - 'a'] == null) {
                pointer.children[ch - 'a'] = new TrieNode();
            }
            // update pointer to point to child
            pointer = pointer.children[ch - 'a'];
            // set end of word if it's the last character
            if (i == word.length() - 1) {
                pointer.isEndOfWord = true;
            }
        }
    }

    public boolean search(String word) {
        // only supports lowercase alphabetical characters (case sensitive)
        if (word == null || !word.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid word.");
        }
        TrieNode pointer = root;
        for (char ch : word.toCharArray()) {
            if (pointer.children[ch - 'a'] == null) {
                // no such letter, return false
                return false;
            } else {
                // continue with it's child
                pointer = pointer.children[ch - 'a'];
            }
        }
        // check if last character is indeed the end of word
        return pointer.isEndOfWord;
    }


    /**
     * Private Trie Node class, children can be changed to a hashSet to save memory at the cost
     * of slightly slower performance (UTF-8 Trie, ASCII Trie)
     */
    private static class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            // initialise and instantiate children array, all values null by default
            children = new TrieNode[ALPHABET_SIZE];
            isEndOfWord = false;
        }
    }
}

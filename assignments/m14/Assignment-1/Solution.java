import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method for the program.
     * complexity O()
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        Scanner scan = new Scanner(System.in);
        String token = scan.nextLine();
        TST tst = new TST();
        for (String each : words) {
            for (int i = 0; i < each.length(); i++) {
                tst.put(each.substring(i, each.length()), 0);
            }
        }
        System.out.println(tst.keysWithPrefix(token));
    }
    /**
     * Loads words.
     * complexity O()
     * @return     array.
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
/**
 * Class for tst.
 *
 * @param      <Value>  The value
 */
class TST<Value> {
    /**
     * int variable.
     */
    private int n;
    /**
     * Node object.
     */
    private Node<Value> root;
    /**
     * Class for node.
     *
     * @param      <Value>  The value
     */
    class Node<Value> {
        /**
         * char variable.
         */
        private char character;
        /**
         * node object.
         */
        private Node<Value> left;
        /**
         * node object.
         */
        private Node<Value> middle;
        /**
         * node object.
         */
        private Node<Value> right;
        /**
         * value variable.
         */
        private Value value;
    }
    /**
     * Constructs the object.
     */
    TST() {

    }
    /**
     * checks if the string is present or not.
     * complexity O()
     * @param      one   One
     *
     * @return     boolean value.
     */
    public boolean contains(final String one) {
        return get(one) != null;
    }
    /**
     * helper method for the main get method.
     * comlexity O()
     * @param      one   One
     *
     * @return     the string.
     */
    public Value get(final String one) {
        Node<Value> node = get(root, one, 0);
        if (node == null) {
            return null;
        }
        return node.value;
    }
    /**
     * it returns the value.
     * complexity O()
     * @param      node  The node
     * @param      one   One
     * @param      d     the character
     *
     * @return     node object.
     */
    public Node<Value> get(final Node<Value> node,
        final String one, final int d) {
        if (node == null) {
            return null;
        }
        char ch = one.charAt(d);
        if (ch < node.character) {
            return get(node.left,  one, d);
        } else if (ch > node.character) {
            return get(node.right, one, d);
        } else if (d < one.length() - 1) {
            return get(node.middle, one, d + 1);
        } else {
            return node;
        }
    }
    /**
     * helper method for the main put method.
     * complexity O()
     * @param      one    One
     * @param      value  The value
     */
    public void put(final String one, final Value value) {
        if (!contains(one)) {
            n++;
        }
        root = put(root, one, value, 0);
    }
    /**
     * used to put the string with the assigned value.
     * complexity O()
     * @param      node   The node
     * @param      one    One
     * @param      value  The value
     * @param      d      the charcter index.
     *
     * @return     Node object.
     */
    public Node<Value> put(Node<Value> node,
        final String one, final Value value,
        final int d) {
        char ch = one.charAt(d);
        if (node == null) {
            node = new Node<Value>();
            node.character = ch;
        }
        if (ch < node.character) {
            node.left  = put(node.left,  one, value, d);
        } else if (ch > node.character) {
            node.right = put(node.right, one, value, d);
        } else if (d < one.length() - 1) {
            node.middle   = put(node.middle, one, value, d + 1);
        } else {
            node.value   = value;
        }
        return node;
    }
    /**
     * used to find the string with the prefix.
     * complexity O()
     * @param      one   One
     *
     * @return    Iterable.
     */
    public Iterable<String> keysWithPrefix(final String one) {
        Queue<String> que = new Queue<String>();
        Node<Value> x = get(root, one, 0);
        if (x == null) {
            return que;
        }
        if (x.value != null) {
            que.enqueue(one);
        }
        collect(x.middle, new StringBuilder(one), que);
        return que;
    }
    /**
     * checks for the strings with the preffix
     * in trie.
     * complexity O()
     * @param      node  The node
     * @param      one   One
     * @param      que   The que
     */
    public void collect(final Node<Value> node,
        final StringBuilder one, final Queue<String> que) {
        if (node == null) {
            return;
        }
        collect(node.left,  one, que);
        if (node.value != null){
            que.enqueue(one.toString()
            + node.character);
        }
        collect(node.middle, one.append(
            node.character), que);
        one.deleteCharAt(one.length() - 1);
        collect(node.right, one, que);
    }
}

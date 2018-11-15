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
     * complexity is O(L + logN)
     * L length of string, N trie size.
     * because we are calling the get method.
     * @param      one   One
     *
     * @return     boolean value.
     */
    public boolean contains(final String one) {
        return get(one) != null;
    }
    /**
     * helper method for the main get method.
     * complexity is O(L + logN)
     * L length of string, N trie size.
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
     * complexity is O(L + logN)
     * L length of string, N trie size.
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
     * complexity is O(L + logN)
     * L length of string, N trie size.
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
     * complexity is O(L + logN)
     * L length of string, N trie size.
     * @param      node   The node
     * @param      one    One
     * @param      value  The value
     * @param      d      the charcter index.
     *
     * @return     Node object.
     */
    public Node<Value> put(final Node<Value> node,
                           final String one, final Value value,
                           final int d) {
        Node node1 = node;
        char ch = one.charAt(d);
        if (node1 == null) {
            node1 = new Node<Value>();
            node1.character = ch;
        }
        if (ch < node1.character) {
            node1.left  = put(node1.left,  one, value, d);
        } else if (ch > node1.character) {
            node1.right = put(node1.right, one, value, d);
        } else if (d < one.length() - 1) {
            node1.middle   = put(node1.middle, one, value, d + 1);
        } else {
            node1.value   = value;
        }
        return node1;
    }
    /**
     * used to find the string with the prefix.
     * complexity is O(L + logN)
     * L length of string, N trie size.
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
     * complexity is O(L + logN)
     * L length of string, N trie size.
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
        if (node.value != null) {
            que.enqueue(one.toString()
                        + node.character);
        }
        collect(node.middle, one.append(
                    node.character), que);
        one.deleteCharAt(one.length() - 1);
        collect(node.right, one, que);
    }
    /**
     * Determines if prefix is present or not.
     *
     * @param      prefix  The prefix
     *
     * @return     True if prefix, False otherwise.
     */
    public boolean isPrefix(final String prefix) {
        return get(root, prefix, 0) != null;
    }
}

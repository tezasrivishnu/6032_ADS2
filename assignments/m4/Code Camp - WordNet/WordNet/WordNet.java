import java.util.ArrayList;
/**
 * Class for word net.
 */
class WordNet {
    /**
     * thousand value.
     */
    private static final int NUMBER = 1000000;
    /**
     * int variable.
     */
    private int nodes;
    /**
     * int variable.
     */
    private int distance;
    /**
     * int variable.
     */
    private int ancestor;
    /**
     * string variable.
     */
    private String hypernyms;
    /**
     * arraylist string variable.
     */
    private ArrayList<String> sarray;
    /**
     * diagraph class object.
     */
    private Digraph graph;
    /**
     * linear probing class object.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> linear;
    /**
     * Sap class object.
     */
    private Sap sap;
    /**
     * Constructs the object.
     *
     * @param      synset    The synset
     * @param      hypernym  The hypernym
     */
    WordNet(final String synset, final String hypernym) {
        linear = new LinearProbingHashST<String, ArrayList<Integer>>();
        sarray = new ArrayList<String>();
        nodes = readSynsets(synset);
        graph = readhypernyms(hypernym, nodes);
        sap = new Sap(graph);
    }
    /**
     * Reads synsets files.
     * complexity O(n) n is the number of files in
     * synset file.
     * @param      file  The file
     *
     * @return     int value.
     */
    public int readSynsets(final String file) {
        In in = new In("./Files/" + file);
        while (!in.isEmpty()) {
            nodes++;
            String[] tokens = in.readLine().split(",");
            int identity = Integer.parseInt(tokens[0]);
            sarray.add(identity, tokens[1]);
            String[] items = tokens[1].split(" ");
            for (int i = 0; i < items.length; i++) {
                ArrayList<Integer> array;
                if (linear.contains(items[i])) {
                    array = linear.get(items[i]);
                    array.add(identity);
                } else {
                    array = new ArrayList<Integer>();
                    array.add(identity);
                }
                linear.put(items[i], array);
            }
        }
        return nodes;
    }
    /**
     * reads the files and add the edges between the two nodes.
     * complexity O(n) n is the number of files in
     * file.
     * @param      file   The file
     * @param      node  The nodes
     *
     * @return     diagraph object.
     */
    public Digraph readhypernyms(final String file, final int node) {
        Digraph digraph = new Digraph(node);
        In in = new In("./Files/" + file);
        while (!in.isEmpty()) {
            String[] tokens = in.readString().split(",");
            for (int i = 1; i < tokens.length; i++) {
                digraph.addEdge(Integer.parseInt(tokens[0]),
                                Integer.parseInt(tokens[i]));
            }
        }
        DirectedCycle cycle = new DirectedCycle(digraph);
        int size = 0;
        for (int i = 0; i < node; i++) {
            if (digraph.outdegree(i) == 0) {
                size++;
            }
        }
        if (size > 1) {
            throw new IllegalArgumentException("Multiple roots");
        }
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        }
        return digraph;
    }
    /**
     * returns the diagraph object.
     * complexity O(1)
     * @return     object.
     */
    public Digraph print() {
        return graph;
    }
    /**
     * Determines if noun.
     * check if the word is present or not.
     * complexity O(n) since the complexity of search
     * in hash tble is n.
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        if (word.equals(null)) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        return linear.contains(word);
    }
    /**
     * finding the distance between two nodes.
     * complexity O(v) where v is the number of vertices.
     * @param      one   One
     * @param      two   Two
     *
     * @return     the distance.
     */
    public int distance(final String one, final String two) {
        if (!isNoun(one) || !isNoun(two)) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        sap(one, two);
        return distance;
    }
    /**
     * finding the anscestor.
     * complexity O(v) since the ancestor method in
     * SAP class is v(no of thevertices)
     * @param      one   One
     * @param      two   Two
     *
     * @return     String.
     */
    public String sap(final String one, final String two) {
        distance = NUMBER;
        ancestor = -1;
        for (int eachone : linear.get(one)) {
            for (int eachtwo : linear.get(two)) {
                int length = sap.length(eachone, eachtwo);
                if (length < distance) {
                    distance = length;
                    ancestor = sap.ancestor(eachone, eachtwo);
                }
            }
        }
        return sarray.get(ancestor);
    }
}

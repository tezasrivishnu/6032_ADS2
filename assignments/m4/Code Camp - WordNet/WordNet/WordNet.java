/*public class WordNet {

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)

    // returns all WordNet nouns
    public Iterable<String> nouns()

    // is the word a WordNet noun?
    public boolean isNoun(String word)

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)

    // do unit testing of this class
    public static void main(String[] args)
}
*/
import java.util.*;
public class WordNet {
    private int nodes;
    int distance;
    int ancestor;
    private String hypernyms;
    ArrayList<String> sarray;
    private Digraph graph;
    private LinearProbingHashST<String, ArrayList<Integer>> linear;
    Sap sap;
    WordNet(final String synset, final String hypernym) {
        linear = new LinearProbingHashST<String, ArrayList<Integer>>();
        sarray = new ArrayList<String>();
        nodes = readSynsets(synset);
        graph = readhypernyms(hypernym, nodes);
        sap = new Sap(graph);
    }
    public int readSynsets(final String file) {
        In in = new In("./Files/" + file);
        while (!in.isEmpty()) {
            nodes++;
            String[] tokens = in.readString().split(",");
            int identity = Integer.parseInt(tokens[0]);
            //sarray.add(identity, tokens[1]);
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
    public Digraph readhypernyms(final String file, int nodes)  {
        Digraph digraph = new Digraph(nodes);
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
        for (int i = 0; i < nodes; i++) {
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
    public Digraph print() {
        return graph;
    }


    public Iterable<String> nouns() {
        return linear.keys();
    }

    public boolean isNoun(final String word) {
        return linear.contains(word);
    }
    public int distance(String one, String two) {
        sap(one, two);
        return distance;
    }

    public String sap(String one, String two) {
        int distance = 1000000000;;
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
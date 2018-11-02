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
    private String hypernyms;
    private Digraph graph;
    private LinearProbingHashST<String, List<Integer>> linear;
    WordNet(final String synset, final String hypernym) {
        nodes = readSynsets(synset);
        graph = new Digraph(nodes);
        readhypernyms(hypernym);
        linear = new LinearProbingHashST<String, List<Integer>>();
    }
    public int readSynsets(final String file) {
        In in = new In("./Files/" + file);
        String[] items;
        String[] tokens;
        while (!in.isEmpty()) {
            ArrayList<Integer> array = new ArrayList<Integer>();
            nodes++;
            tokens = in.readString().split(",");
            int identity = Integer.parseInt(tokens[0]);
            if (tokens[1].length() > 1) {
                for (int i = 0; i < tokens[1].length(); i++) {
                    items = tokens[1].split(" ");
                    if (linear.contains(items[i])) {
                        array.addAll(linear.get(tokens[i]));
                        array.add(identity);
                        linear.put(tokens[1], array);
                    } else {
                        array.add(identity);
                        linear.put(items[i], array);
                    }
                }
            }
        }
        return nodes;
    }
    public void readhypernyms(final String file)  {
        In in = new In("./Files/" + file);
        while (!in.isEmpty()) {
            String[] tokens = in.readString().split(",");
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]),
                                Integer.parseInt(tokens[i]));
            }
        }
        DirectedCycle cycle = new DirectedCycle(graph);
        int size = 0;
        for (int i = 0; i < nodes; i++) {
            if (graph.outdegree(i) == 0) {
                size++;
            }
        }
        if (size > 1) {
            throw new IllegalArgumentException("Multiple roots");
        }
        else if (cycle.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else {
            System.out.println(graph);
        }

    }

    public Iterable<String> nouns() {
        return linear.keys();
    }

    public boolean isNoun(final String word) {
        return linear.contains(word);
    }
}
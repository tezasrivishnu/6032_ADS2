import java.util.HashSet;
/**
 * Class for sap.
 */
class Sap {
    /**
     * thousand value.
     */
    private static final int NUMBER = 1000000;
    /**
     * initializing the diagraph object.
     */
    private Digraph graph;
    /**
     * int variable.
     */
    private int minimum;
    /**
     * int variable.
     */
    private int ancestor;
    /**
     * Constructs the object.
     *
     * @param      g     diagraph object.
     */
    Sap(final Digraph g) {
        graph = new Digraph(g);
    }
    /**
     * finding the short distance between two vertices.
     * complexity O(v) v is the nuber of vertices.
     * @param      one   One
     * @param      two   Two
     *
     * @return     the distance.
     */
    public int length(final int one, final int two) {
        HashSet<Integer> first = new HashSet<Integer>();
        first.add(one);
        HashSet<Integer> second = new HashSet<Integer>();
        second.add(two);
        length(first, second);
        if (ancestor == -1) {
            return -1;
        } else {
            return minimum;
        }
    }
    /**
     * finding the short distance between two vertices.
     * complexity O(v) v is the nuber of vertices.
     * @param      one   One
     * @param      two   Two
     *
     * @return     the distance.
     */
    public int length(final Iterable<Integer> one,
        final Iterable<Integer> two) {
        minimum = NUMBER;
        ancestor = -1;
        BreadthFirstDirectedPaths first = new
        BreadthFirstDirectedPaths(graph, one);
        BreadthFirstDirectedPaths second = new
        BreadthFirstDirectedPaths(graph, two);
        for (int i = 0; i < graph.v(); i++) {
            if (first.hasPathTo(i) && second.hasPathTo(i)) {
                int length = first.distTo(i)
                + second.distTo(i);
                if (length < minimum) {
                    minimum = length;
                    ancestor = i;
                }
            }
        }
        return minimum;
    }
    /**
     * finding the ancestor of two vertices.
     * complexity O(v) v is the nuber of vertices.
     * @param      one   One
     * @param      two   Two
     *
     * @return     the ancestor.
     */
    public int ancestor(final int one, final int two) {
        length(one, two);
        return ancestor;
    }
}

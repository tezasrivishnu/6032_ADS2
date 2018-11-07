/**
 * Class for edge weighted.
 */
class EdgeWeighted {
    /**
     * int variable.
     */
    private int vertices;
    /**
     * int variable.
     */
    private int edges;
    /**
     * Bag class array.
     */
    private Bag<Edge>[] adjacent;
    /**
     * Constructs the object.
     * complexity O(v) v is the number of vertices.
     * @param      one   One
     */
    EdgeWeighted(final int one) {
        this.vertices = one;
        this.edges = 0;
        adjacent = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacent[i] = new Bag<Edge>();
        }
    }
    /**
     * Adds an edge.
     * complexity O(1)
     * @param      ed    edge object.
     */
    public void addEdge(final Edge ed) {
        int one = ed.either();
        int two = ed.other(one);
        adjacent[one].add(ed);
        adjacent[two].add(ed);
        edges += 1;
    }
    /**
     * returns the edges.
     * complexity O(v) v are the number of vertices.
     * @return     iterable.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<Edge>();
        for (int i = 0; i < vertices; i++) {
            int temp = 0;
            for (Edge one : adjacentEdges(i)) {
                if (i < one.other(i)) {
                    bag.add(one);
                } else if (i == one.other(i)) {
                    if (temp % 2 == 0) {
                        bag.add(one);
                    }
                    temp += 1;
                }
            }
        }
        return bag;
    }
    /**
     * edges to a particualr vertex.
     * complexity O(1)
     * @param      one   One
     *
     * @return     iterable array.
     */
    public Iterable<Edge> adjacentEdges(final int one) {
        return adjacent[one];
    }
    /**
     * No of vertices in graph.
     * complexity O(1)
     * @return     int value.
     */
    public int vertices() {
        return vertices;
    }
}

/**
 * Class for dijk.
 */
class Dijk {
    /**
     * int vriable.
     */
    private static final int NUMBER = 10000000;
    /**
     *the distace array to store.
     */
    private double[] distace;
    /**
     * edge class array.
     */
    private Edge[] edge;
    /**
     * Index min PQ object.
     */
    private IndexMinPQ<Double> min;
    /**
     *the constructor to initialize the objects.
     *complexity is O(E + V).
     * @param      graph  graph object.
     * @param      one  The source
     */
    Dijk(final EdgeWeighted graph, final int one) {
        distace = new double[graph.vertices()];
        edge = new Edge[graph.vertices()];
        min = new IndexMinPQ<Double>(graph.vertices());
        for (int i = 0; i < graph.vertices(); i++) {
            distace[i] = NUMBER;
        }
        distace[one] = 0.0;
        min.insert(one, distace[one]);
        while (!min.isEmpty()) {
            int two = min.delMin();
            for (Edge each : graph.adjacentEdges(two)) {
                relax(each, two);
            }
        }
    }
    /**
     *method to relax the edges.
     *complexity is O(logE)
     * @param      ed    The edge
     * @param      one  The vertex
     */
    public void relax(final Edge ed, final int one) {
        int two = ed.other(one);
        if (distace[two] > distace[one] + ed.getWeight()) {
            distace[two] = distace[one] + ed.getWeight();
            edge[two] = ed;
            if (min.contains(two)) {
                min.decreaseKey(two, distace[two]);
            } else {
                min.insert(two, distace[two]);
            }
        }
    }
    /**
     *the method returns the distance.
     *complexity O(1)
     * @param      one  vertex
     *
     * @return distance between two vertices.
     */
    public double distTo(final int one) {
        return distace[one];
    }
    /**
    * returns the shortest distance.
    * complexity O(E)
    * @param      one  The vertex
    *
    * @return shortest distance between two vertices.
    */
    public double distanceTo(final int one) {
        double total = 0.0;
        if (pathTo(one) == null) {
            return 0.0;
        }
        for (Edge each : pathTo(one)) {
            total += each.getWeight();
        }
        return total;
    }
    /**
    *shortest path to given vertex.
    *
    * @param      one  vertex.
    *complexity is O(ElogV)
    * @return iterable
    */
    public Iterable<Edge> pathTo(final int one) {
        if (!hasPath(one)) {
            return null;
        }
        Stack<Edge> sta = new Stack<Edge>();
        int two = one;
        for (Edge each = edge[one]; each != null; each = edge[two]) {
            sta.push(each);
            two = each.other(two);
        }
        return sta;
    }
    /**
     * Determines if it has path.
     * complexity O(1)
     * @param      one   One
     *
     * @return     True if has path, False otherwise.
     */
    public boolean hasPath(final int one) {
        return distace[one] < NUMBER;
    }
}

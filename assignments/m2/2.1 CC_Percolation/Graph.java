/**
 * Class for graph.
 */
public class Graph {
    /**
     * newline.
     */
    private static final String NEWLINE
    = System.getProperty("line.separator");
    /**
     * int value.
     */
    private final int v;
    /**
     * int value.
     */
    private int e;
    /**
     * bag object.
     */
    private Bag<Integer>[] adj;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  vi number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(final int vi) {
        if (vi < 0) {
            throw new
            IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.v = vi;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int ver() {
        return v;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int edg() {
        return e;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    // private void  v) {
    //     if (v < 0 || v >= V)
    // throw new IllegalArgumentException(
    // "vertex " + v + " is not between 0 and " + (V - 1));
    // }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  vi one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException
     * unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int vi, final int w) {
        e++;
        adj[vi].add(w);
        adj[w].add(vi);
    }

    /**
     * Determines if it has edge.
     *
     * @param      vi     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vi, final int w) {
        for (int each : adj[w]) {
            if (each == vi) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  vi the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int vi) {
        return adj[vi];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  vi the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int vi) {
        return adj[vi].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges " + NEWLINE);
        for (int i = 0; i < v; i++) {
            s.append(i + ": ");
            for (int w : adj[i]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

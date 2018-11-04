import java.util.NoSuchElementException;

/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * variable.
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
     * bag array object.
     */
    private Bag<Integer>[] adj;
    /**
     * integer array.
     */
    private int[] indegree;

    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param  vi the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(final int vi) {
        if (vi < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.v = vi;
        this.e = 0;
        indegree = new int[v];
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each
     * entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if the
     * endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the
     * number of vertices or edges is negative
     * @throws IllegalArgumentException if the
     * input stream is in the wrong format
     */
    public Digraph(final In in) {
        try {
            this.v = in.readInt();
            if (v < 0) {
                throw new IllegalArgumentException(
                    "number of vertices in a Digraph must be nonnegative");
            }
            indegree = new int[v];
            adj = (Bag<Integer>[]) new Bag[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new Bag<Integer>();
            }
            int ei = in.readInt();
            if (ei < 0) {
                throw new IllegalArgumentException(
                    "number of edges in a Digraph must be nonnegative");
            }
            for (int i = 0; i < ei; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(
                "invalid input format in Digraph constructor", e);
        }
    }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  g the digraph to copy
     */
    public Digraph(final Digraph g) {
        this(g.V());
        this.e = g.E();
        for (int i = 0; i < v; i++)
            this.indegree[i] = g.indegree(i);
        for (int v = 0; v < g.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int V() {
        return v;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int E() {
        return e;
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    /**
     * vertex is valid or not.
     *
     * @param      vi     int.
     */
    private void validateVertex(final int vi) {
        if (vi < 0 || vi >= v) {
            throw new IllegalArgumentException(
                "vertex " + vi + " is not between 0 and " + (v - 1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     * {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        e++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex {@code v}
     * in this digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(v);
        for (int i = 0; i < v; i++) {
            for (int w : adj(i)) {
                reverse.addEdge(w, i);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed
     * by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges " + NEWLINE);
        for (int j = 0; j < v; j++) {
            s.append(String.format("%d: ", j));
            for (int w : adj[j]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

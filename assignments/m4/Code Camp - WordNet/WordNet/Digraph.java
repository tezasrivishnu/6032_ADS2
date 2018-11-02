import java.util.NoSuchElementException;
/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * { var_description }.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * { var_description }.
     */
    private final int ver;           // number of vertices in this digraph
    /**
     * { var_description }.
     */
    private int edg;                 // number of edges in this digraph
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
    /**
     * { var_description }.
     */
    private int[] indegree;        // indegree[v] = indegree of vertex v
    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param      verr  The version
     * @throws     IllegalArgumentException  if {@code V < 0}
     */
    public Digraph(final int verr) {
        if (verr < 0) {
            throw new IllegalArgumentException(
            "Number of vertices in a Digraph must be nonnegative");
        }
        this.ver = verr;
        this.edg = 0;
        indegree = new int[ver];
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a new digraph that
     * is a deep copy of the specified digraph.
     *
     * @param      gr    The graphics
     */
    public Digraph(final Digraph gr) {
        this(gr.ver());
        this.edg = gr.edg();
        for (int v = 0; v < ver; v++) {
            this.indegree[v] = gr.indegree(v);
        }
        for (int v = 0; v < gr.ver(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : gr.adj[v]) {
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
    public int ver() {
        return ver;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int edg() {
        return edg;
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= ver) {
            throw new IllegalArgumentException("vertex "
                + v + " is not between 0 and " + (ver - 1));
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
        edg++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex {@code v} in
     * this digraph, as an iterable
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
        Digraph reverse = new Digraph(ver);
        for (int v = 0; v < ver; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
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
        s.append(ver + " vertices, " + edg + " edges " + NEWLINE);
        for (int v = 0; v < ver; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
/******************************************************************************
 *  Compilation:  javac Digraph.java
 *  Execution:    java Digraph filename.txt
 *  Dependencies: Bag.java In.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                http://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                http://algs4.cs.princeton.edu/42digraph/largeDG.txt
 *
 *  A graph, implemented using an array of lists.
 *  Parallel edges and self-loops are permitted.
 *
 *  % java Digraph tinyDG.txt
 *  13 vertices, 22 edges
 *  0: 5 1
 *  1:
 *  2: 0 3
 *  3: 5 2
 *  4: 3 2
 *  5: 4
 *  6: 9 4 8 0
 *  7: 6 9
 *  8: 6
 *  9: 11 10
 *  10: 12
 *  11: 4 12
 *  12: 9
 *
 ******************************************************************************/


import java.util.NoSuchElementException;

/**
 *  The {@code Digraph} class represents a directed graph of vertices
 *  named 0 through <em>V</em> - 1.
 *  It supports the following two primary operations: add an
 *  edge to the digraph,
 *  iterate over all of the vertices adjacent from a given vertex.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the vertices adjacent from a given vertex, which takes
 *  time proportional to the number of such vertices.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * string variable.
     */
    private static final String NEWLINE
        = System.getProperty("line.separator");
    /**
     * int variable.
     */
    private final int v;
    /**
     * int variable.
     */
    private int e;
    /**
     * bag class object.
     */
    private Bag<Integer>[] adj;
    /**
     * integr array.
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
     * followed by <em>E</em> pairs of vertices,
     * with each entry separated by whitespace.
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
                int vt = in.readInt();
                int w = in.readInt();
                addEdge(vt, w);
            }
        } catch (NoSuchElementException ex) {
            throw new IllegalArgumentException(
                "invalid input format in Digraph constructor", ex);
        }
    }

    /**
     * Initializes a new digraph that is a
     * deep copy of the specified digraph.
     *
     * @param  g the digraph to copy
     */
    public Digraph(final Digraph g) {
        this(g.v());
        this.e = g.e();
        for (int j = 0; j < v; j++) {
            this.indegree[j] = g.indegree(j);
        }
        for (int i = 0; i < g.v(); i++) {
            // reverse so that adjacency list
            //is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj[i]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[i].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int v() {
        return v;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int e() {
        return e;
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    /**
     * { function_description }.
     *
     * @param      vi    { parameter_description }
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
     * @param  vi the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     * {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int vi, final int w) {
        validateVertex(vi);
        validateVertex(w);
        adj[vi].add(w);
        indegree[w]++;
        e++;
    }

    /**
     * Returns the vertices adjacent from vertex
     * {@code v} in this digraph.
     *
     * @param  vi the vertex
     * @return the vertices adjacent from vertex {@code v}
     * in this digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int vi) {
        validateVertex(vi);
        return adj[vi];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  vi the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int vi) {
        validateVertex(vi);
        return adj[vi].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  vi the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int vi) {
        validateVertex(vi);
        return indegree[vi];
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
     * @return the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges " + NEWLINE);
        for (int i = 0; i < v; i++) {
            s.append(String.format("%d: ", i));
            for (int w : adj[i]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}

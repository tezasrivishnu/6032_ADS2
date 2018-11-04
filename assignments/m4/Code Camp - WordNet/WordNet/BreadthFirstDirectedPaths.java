/**
 *  The {@code BreadthDirectedFirstPaths} class represents a data
 *  type for finding
 *  shortest paths (number of edges) from a source vertex <em>s</em>
 *  (or set of source vertices) to every other vertex in the digraph.
 *  <p>
 *  This implementation uses breadth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is
 *  the number of edges.
 *  It uses extra space (not including the digraph) proportional
 *  to <em>V</em>.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class BreadthFirstDirectedPaths {
    /**
     * int inifinity.
     */
    private static final int INFINITY = Integer.MAX_VALUE;
    /**
     * boolean array.
     */
    private boolean[] marked;
    /**
     * inr array.
     */
    private int[] edgeTo;
    /**
     * int array.
     */
    private int[] distTo;
    /**
     * Computes the shortest path from {@code s} and
     * every other vertex in graph {@code G}.
     * @param g the digraph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public BreadthFirstDirectedPaths(final Digraph g, final int s) {
        marked = new boolean[g.v()];
        distTo = new int[g.v()];
        edgeTo = new int[g.v()];
        for (int v = 0; v < g.v(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(s);
        bfs(g, s);
    }

    /**
     * Computes the shortest path from any one of
     * the source vertices in {@code sources}
     * to every other vertex in graph {@code G}.
     * @param g the digraph
     * @param sources the source vertices
     * @throws IllegalArgumentException unless each vertex {@code v} in
     *         {@code sources} satisfies {@code 0 <= v < V}
     */
    public BreadthFirstDirectedPaths(final Digraph g,
        final Iterable<Integer> sources) {
        marked = new boolean[g.v()];
        distTo = new int[g.v()];
        edgeTo = new int[g.v()];
        for (int v = 0; v < g.v(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertices(sources);
        bfs(g, sources);
    }

    // BFS from single source

    /**
     * bfs method.
     *complexity O(e) e is number of vertives.
     * @param      g        { parameter_description }
     * @param s int value.
     */
    private void bfs(final Digraph g, final int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    // BFS from multiple sources

    /**
     * bfs method.
     *complexity O(e) e is number of vertives.
     * @param      g        { parameter_description }
     * @param      sources  The sources
     */
    private void bfs(final Digraph g,
        final Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Is there a directed path from the source {@code s}
     * (or sources) to vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a directed path,
     * {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest
     * path from the source {@code s}
     * (or sources) to vertex {@code v}?
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns a shortest path from {@code s} (or sources) to {@code v}, or
     * {@code null} if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(final int v) {
        validateVertex(v);

        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

    /**
     *throw an IllegalArgumentException unless {@code 0 <= v < V}.
     *@param v int value.
     */
    private void validateVertex(final int v) {
        int v1 = marked.length;
        if (v < 0 || v >= v1) {
            throw new IllegalArgumentException("vertex "
                 + v + " is not between 0 and " + (v1 - 1));
        }
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    /**
     * checking if vertexes are valid or not.
     *
     * @param      vertices  The vertices
     */
    private void validateVertices(final Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int v1 = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= v1) {
                throw new IllegalArgumentException("vertex "
                    + v + " is not between 0 and " + (v1 - 1));
            }
        }
    }

}

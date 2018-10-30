/**
 * Class for cc.
 */
public class CC {
    /**
     * boolean array.
     */
    private boolean[] marked;
    /**
     * int array.
     */// marked[v] = has vertex v been marked?
    private int[] id;
    /**
     * size array.
     */// id[v] = id of connected component containing v
    private int[] size;
    /**
     * int variable.
     */
    // size[id] = number of vertices ingiven component
    private int count; // number of connected components

    /**
     * Computes the connected components of the
     * undirected graph {@code G}.
     *
     * @param g the undirected graph
     */
    public CC(final Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        size = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    // depth-first search for a Graph
    /**
    * method depth first search.
    * @param v int
    * @param g graph object
    */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }


    /**
     * Returns the component id of the connected
     * component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of the connected
     * component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int id(final int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected
     * component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected
     * component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int size(final int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in
     * the graph {@code G}.
     *
     * @return the number of connected components in
     * the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w}
     * are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     */
    public boolean connected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * Returns true if vertices {@code v} and {@code w}
     * are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and
     * {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     * @deprecated Replaced by {@link #connected(int, int)}.
     */
    @Deprecated
    public boolean areConnected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    /**
     * validating.
     *
     * @param      v     int value
     */
    private void validateVertex(final int v) {
        int m = marked.length;
        if (v < 0 || v >= m) {
            throw new IllegalArgumentException("vertex "
                    + v + " is not between 0 and " + (m - 1));
        }
    }
}

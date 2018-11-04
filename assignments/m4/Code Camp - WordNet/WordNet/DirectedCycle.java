/**
 *  The {@code DirectedCycle} class represents a data type for
 *  determining whether a digraph has a directed cycle.
 *  The <em>hasCycle</em> operation determines whether the digraph has
 *  a directed cycle and, and of so, the <em>cycle</em> operation
 *  returns one.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the
 *  number of edges.
 *  Afterwards, the <em>hasCycle</em> operation takes constant time;
 *  the <em>cycle</em> operation takes time proportional
 *  to the length of the cycle.
 *  <p>
 *  See {@link Topological} to compute a topological order if the
 *  digraph is acyclic.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DirectedCycle {
    /**
     * boolean array.
     */
    private boolean[] marked;
    /**
     * int array.
     */
    private int[] edgeTo;
    /**
   * boolean array.
     */
    private boolean[] onStack;
    /**
     * stack object.
     */
    private Stack<Integer> cycle;
    /**
     * Determines whether the digraph {@code G} has
     * a directed cycle and, if so,
     * finds such a cycle.
     * @param g the digraph
     */
    public DirectedCycle(final Digraph g) {
        marked  = new boolean[g.v()];
        onStack = new boolean[g.v()];
        edgeTo  = new int[g.v()];
        for (int v = 0; v < g.v(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g, v);
            }
        }
    }

    // check that algorithm computes either the
    //topological order or finds a directed cycle
    /**
     * dfs method.
     * comlexity O(e) e is the number of edges.
     * @param      g     dia graph object.
     * @param      v     int.
     */
    private void dfs(final Digraph g, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return {@code true} if the digraph has a
     * directed cycle, {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a
     * directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable)
     * if the digraph has a directed cycle,
     *    and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }


    // certify that digraph has a directed cycle if it reports one

    /**
     * checking the cycle.
     *
     * @return     boolean value.
     */
    private boolean check() {
        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf(
                    "cycle begins with %d and ends with %d\n",
                    first, last);
                return false;
            }
        }


        return true;
    }
}

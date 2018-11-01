import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method for the program.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        Graph dg = new Graph(scan);
        Bipartite cy = new Bipartite(dg);
        if (cy.isBipar()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
/**
 * Class for bipartite.
 */
class Bipartite {
    /**
     * initialising the boolean variable.
     */
    private boolean flag;
    /**
     * initialising the int array.
     */
    private int[] edgeto;
    /**
     * initialising the boolean array.
     */
    private boolean[] color;
    /**
     * initialising the boolean array.
     */
    private boolean[] link;
    /**
     * initialising the stack class object.
     */
    private Stack<Integer> sta;
    /**
     * Constructs the object.
     *
     * @param      graph  The graph object
     */
    Bipartite(final Graph graph) {
        flag = true;
        link = new boolean[graph.vertices()];
        color = new boolean[graph.vertices()];
        edgeto = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!link[i]) {
                dfs(graph, i);
            }
        }
    }
    /**
     * depth first search method.
     * complexity O(e) e refers to no of
     * edges.
     * @param      graph  The graph
     * @param      one    One
     */
    public void dfs(final Graph graph, final int one) {
        link[one] = true;
        for (int two : graph.adj(one)) {
            if (sta != null) {
                return;
            } else if (!link[two]) {
                color[two] = !color[one];
                edgeto[two] = one;
                dfs(graph, two);
            } else if (color[two] == color[one]) {
                flag = false;
                sta = new Stack<Integer>();
                sta.push(two);
                for (int k = one; k != two; k = edgeto[k]) {
                    sta.push(k);
                }
                sta.push(two);
            }
        }
    }
    /**
     * Determines if the graph is bipartite or not.
     * complexity O(1)
     * @return     True if bipar, False otherwise.
     */
    public boolean isBipar() {
        return flag;
    }
}

import java.util.Scanner;
// interface Graph {
//     public int V();
//     public int E();
//     public void addEdge(int v, int w);
//     public Iterable<Integer> adj(int v);
//     public boolean hasEdge(int v, int w);
// }

/**
 * Class for list representation of graph.
 */
class ListGraph {
    /**
     * intializing the vertices variable.
     */
    private int node;
    /**
     * intializing the edge variable.
     */
    private int edge;
    /**
     * intializing the vertices size variable.
     */
    private int ncount;
    /**
     * intializing the edges size variable.
     */
    private int ecount;
    /**
     * intializing the string array for vertices.
     */
    private String[] tokens;
    /**
     * intializing bag array.
     */
    private Bag<Integer>[] adjacent;
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    ListGraph(final Scanner scan) {
        node = Integer.parseInt(scan.nextLine());
        edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        adjacent = new Bag[node];
        for (int i = 0; i < node; i++) {
            adjacent[i] = new Bag();
        }
        for (int j = 0; j < edge; j++) {
            String[] items = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(items[0]),
                    Integer.parseInt(items[1]));
        }
    }
    /**
     *the method is to add an edge between.
     *two vertices.
      * complexity is O(E)
     * E denotes the number of edges we have in graph.
     * @param      one  The vertex one
     * @param      two  The vertex two
     */
    public void addEdge(final int one, final int two) {
        if (one == two) {
            return;
        }
        if (hasEdge(one, two)) {
            ecount++;
        }
        adjacent[one].add(two);
        adjacent[two].add(one);
    }
    /**
     *returns the edges of graph.
     *
     * @return edges of graph
     */
    public int getEdges() {
        return ecount;
    }
    /**
     *returns the vertices of graph.
     *
     * @return vertices of graph
     */
    public int getNode() {
        return ncount;
    }
    /**
    *the method is to return the adjacent vertices
     *in the bag.
     * complexity O(1)
    * @param      one  The vertex
    *
    * @return  iterator.
    */
    public Iterable<Integer> adjacent(final int one) {
        return adjacent[one];
    }
    /**
     * string representation of the graph in form of list.
     * complexity O(N^2)
     * @return     string representation
     */
    public String toPrint() {
        String str = node + " vertices, "
                     + ecount + " edges" + "\n";
        if (ecount > 0) {
            int k = 0;
            //str += node + " vertices, "
            //+ ecount + " edges" + "\n";
            for (k = 0; k < node - 1; k++) {
                str += tokens[k] + ": ";
                for (int in : adjacent[k]) {
                    str += tokens[in] + " ";
                }
                str += "\n";
            }
            str += tokens[k] + ": ";
            for (int in : adjacent[k]) {
                str += tokens[in] + " ";
            }
        } else {
            str += "No edges";
            return str;
        }
        return str;
    }
    /**
    *the method is check whether there is a.
    *connection between two given vertices.
    *complexity is O(E)
    *E is the number of edges in graph.
    * @param      one  The vertex one
    * @param      two  The vertex two
    *
    * @return     True if has edge, False otherwise.
    */
    public boolean hasEdge(final int one, final int two) {
        for (int in : adjacent(one)) {
            if (in == two) {
                return false;
            }
        }
        return true;
    }
}
/**
 * Class for matrix graph.
 */
class MatrixGraph {
    /**
     * intializing the vertices variable.
     */
    private int node;
    /**
     * intializing the edge variable.
     */
    private int edge;
    /**
     * intializing the vertices size variable.
     */
    private int ncount;
    /**
     * intializing the edges size variable.
     */
    private int ecount;
    /**
     * intializing the string array for vertices.
     */
    private String[] tokens;
    /**
     * intializing the 2d array for edges.
     */
    private int[][] adjacent;
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    MatrixGraph(final Scanner scan) {
        node = Integer.parseInt(scan.nextLine());
        edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        adjacent = new int[node][node];
        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                adjacent[i][j] = 0;
            }
        }
        for (int j = 0; j < edge; j++) {
            String[] items = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(items[0]),
                    Integer.parseInt(items[1]));
        }
    }
    /**
     *the method is to add an edge between.
     *two vertices.
     * @param      one  The vertex one
     * @param      two  The vertex two
      * complexity is O(1) as we are just mking the index
      * as 1.
     */
    public void addEdge(final int one, final int two) {
        if (one == two) {
            return;
        }
        if (hasEdge(one, two)) {
            ecount++;
        }
        adjacent[one][two] = 1;
        adjacent[two][one] = 1;
    }
    /**
    *the method is check whether there is a.
    *connection between two given vertices.
    *the time complexity is O(1)
    * @param      one  The vertex one
    * @param      two  The vertex two
    *
    * @return     True if has edge, False otherwise.
    */
    public boolean hasEdge(final int one, final int two) {
        return adjacent[one][two] != 1
               && adjacent[two][one] != 1;
    }
    /**
     *returns the edges of graph.
     *
     * @return edges of graph
     */
    public int getEdges() {
        return ecount;
    }
    /**
     *returns the vertices of graph.
     *
     * @return vertices of graph
     */
    public int getNode() {
        return ncount;
    }
    /**
     *the method is to print the string format.
     *of graph.
     *the time complexity will be O(N^2)
     *N is the vertices here.
     *@return string representation.
     */
    public String toPrint() {
        String str = node + " vertices, "
                     + ecount + " edges" + "\n";
        if (ecount > 0) {
            int k = 0;
            //str += node + " vertices, "
            //+ ecount + " edges" + "\n";
            for (k = 0; k < adjacent.length; k++) {
                for (int in = 0; in
                        < adjacent[0].length; in++) {
                    str += adjacent[k][in] + " ";
                }
                str += "\n";
            }
            // str += tokens[k] + ": ";
            // for (int in : adjacent[k]) {
            //  str += in + " ";
            // }
        } else {
            str += "No edges";
            return str;
        }
        return str;
    }
}
/**
 * class Solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method for the program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String lismat = scan.nextLine();
        switch (lismat) {
        case "List":
            ListGraph lis = new ListGraph(scan);
            System.out.println(lis.toPrint());
            break;
        case "Matrix":
            MatrixGraph mat = new MatrixGraph(scan);
            System.out.println(mat.toPrint());
            break;
        default:
            break;
        }
    }
}
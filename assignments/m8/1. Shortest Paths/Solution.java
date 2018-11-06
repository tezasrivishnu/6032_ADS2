import java.util.Scanner;
import java.util.ArrayList;
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
     *main method for the program..
     *complexity is O(E + V)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");
        String[] edges = scan.nextLine().split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for (int j = 0; j < edges.length; j++) {
            list.add(edges[j]);
        }
        EdgeWeighted graph = new EdgeWeighted(Integer.
                                              parseInt(input[0]));
        for (int i = 0; i < Integer.parseInt(input[1]); i++) {
            String[] tokens = scan.nextLine().split(" ");
            graph.addEdge(new Edge(list.indexOf(tokens[0]),
                                   list.indexOf(tokens[1]),
                                   Integer.parseInt(tokens[2])));
        }
        int cases = Integer.parseInt(scan.nextLine());
        for (int k = 0; k < cases; k++) {
            String[] item = scan.nextLine().split(" ");
            Dijk dij = new Dijk(graph, list.indexOf(item[0]));
            System.out.println(
                dij.distanceTo(list.indexOf(item[1])));
        }
    }
}
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
    private int[] distace;
    /**
     * edge class array.
     */
    private Edge[] edge;
    /**
     * Index min PQ object.
     */
    private IndexMinPQ<Integer> min;
    /**
     *the constructor to initialize the objects.
     *complexity is O(E + V).
     * @param      graph  graph object.
     * @param      one  The source
     */
    Dijk(final EdgeWeighted graph, final int one) {
        distace = new int[graph.vertices()];
        edge = new Edge[graph.vertices()];
        min = new IndexMinPQ<Integer>(graph.vertices());
        for (int i = 0; i < graph.vertices(); i++) {
            distace[i] = NUMBER;
        }
        distace[one] = 0;
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
    public int distTo(final int one) {
        return distace[one];
    }
    /**
    * returns the shortest distance.
    * complexity O(E)
    * @param      one  The vertex
    *
    * @return shortest distance between two vertices.
    */
    public int distanceTo(final int one) {
        int total = 0;
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

import java.util.Scanner;
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
     * complexity O(n) n is the number of input connections.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = Integer.parseInt(scan.nextLine());
        int input = Integer.parseInt(scan.nextLine());
        EdgeWeighted edge = new EdgeWeighted(input);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            edge.addEdge(new Edge(Integer.parseInt(tokens[0]),
            Integer.parseInt(tokens[1]),
            Double.parseDouble(tokens[2])));

        }
        Kruskal krus = new Kruskal(edge);
        System.out.printf("%.5f", krus.totalWeight());

    }
}
/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     * int variable.
     */
    private int edge1;
    /**
     * int variable.
     */
    private int edge2;
    /**
     * double variable.
     */
    private double weight;
    /**
     * Constructs the object.
     * @param      one   One
     * @param      two   Two
     * @param      wei   The wei
     */
    Edge(final int one, final int two, final double wei) {
        this.edge1 = one;
        this.edge2 = two;
        this.weight = wei;
    }
    /**
     * Gets the weight.
     * complexity O(1)
     * @return     The weight.
     */
    public double getWeight() {
        return this.weight;
    }
    /**
     * gets the other edge connected.
     * complexity O(1)
     * @param      one   One
     *
     * @return     int value.
     */
    public int other(final int one) {
        if (one == edge1) {
            return this.edge2;
        }
        return this.edge1;
    }
    /**
     * retuns the edge.
     * complexity O(1)
     * @return     int value
     */
    public int either() {
        return this.edge1;
    }
    /**
     * compare two vertices weights.
     * complexity O(1)
     * @param      that  The that
     *
     * @return     int value.
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        }
        if (this.weight > that.weight) {
            return 1;
        }
        return 0;
    }
}
/**
 * Class for edge weighted.
 */
class EdgeWeighted {
    /**
     * int variable.
     */
    private int vertices;
    /**
     * int variable.
     */
    private int edges;
    /**
     * Bag class array.
     */
    private Bag<Edge>[] adjacent;
    /**
     * Constructs the object.
     * complexity O(v) v is the number of vertices.
     * @param      one   One
     */
    EdgeWeighted(final int one) {
        this.vertices = one;
        this.edges = 0;
        adjacent = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacent[i] = new Bag<Edge>();
        }
    }
    /**
     * Adds an edge.
     * complexity O(1)
     * @param      ed    edge object.
     */
    public void addEdge(final Edge ed) {
        int one = ed.either();
        int two = ed.other(one);
        adjacent[one].add(ed);
        adjacent[two].add(ed);
        edges += 1;
    }
    /**
     * returns the edges.
     * complexity O(v) v are the number of vertices.
     * @return     iterable.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<Edge>();
        for (int i = 0; i < vertices; i++) {
            int temp = 0;
            for (Edge one : adjacentEdges(i)) {
                if (i < one.other(i)) {
                    bag.add(one);
                } else if (i == one.other(i)) {
                    if (temp % 2 == 0) {
                        bag.add(one);
                    }
                    temp += 1;
                }
            }
        }
        return bag;
    }
    /**
     * edges to a particualr vertex.
     * complexity O(1)
     * @param      one   One
     *
     * @return     iterable array.
     */
    public Iterable<Edge> adjacentEdges(final int one) {
        return adjacent[one];
    }
    /**
     * No of vertices in graph.
     * complexity O(1)
     * @return     int value.
     */
    public int vertices() {
        return vertices;
    }
}
/**
 * Class for kruskal.
 */
class Kruskal {
    /**
     * double variable.
     */
    private double totalweight;
    /**
     * queue object.
     */
    private Queue<Edge> queue;
    /**
     * Constructs the object.
     * complexity O(ElogE) e is the number of edges.
     * @param      graph  The graph
     */
    Kruskal(final EdgeWeighted graph) {
        queue = new Queue<Edge>();
        totalweight = 0.0;
        MinPQ<Edge> min = new MinPQ<Edge>();
        for (Edge one : graph.edges()) {
            min.insert(one);
        }
        UF union = new UF(graph.vertices());
        while (!min.isEmpty() && queue.size()
                < graph.vertices()) {
            Edge temp = min.delMin();
            int one = temp.either();
            int two = temp.other(one);
            if (!union.connected(one, two)) {
                union.union(one, two);
                queue.enqueue(temp);
                totalweight += temp.getWeight();
            }
        }
    }
    /**
     * returns the total weight of the tree.
     * complexity O(1)
     * @return     double value.
     */
    public double totalWeight() {
        return totalweight;
    }
}

import java.util.Scanner;
import java.util.ArrayList;
class Solution {
	private Solution() {

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] input = scan.nextLine().split(" ");
		String[] edges = scan.nextLine().split(" ");
		ArrayList<String> list = new ArrayList<String>();
		for (int j = 0; j < edges.length; j++) {
			list.add(edges[j]);
		}
		EdgeWeightedGraph graph = new EdgeWeightedGraph(Integer.
			parseInt(input[0]));
		for (int i = 0; i < Integer.parseInt(input[1]); i++) {
			String[] tokens = scan.nextLine().split(" ");
			graph.addEdge(new Edge(list.indexOf(tokens[0]),
				list.indexOf(tokens[1]), Double.parseDouble(tokens[2])));
		}
		int cases = Integer.parseInt(scan.nextLine());
		for (int k = 0; k < cases; k++) {
			String[] item = scan.nextLine().split(" ");
			Dijk dij = new Dijk(graph, list.indexOf(item[0]));
			System.out.println(dij.distanceTo(list.indexOf(item[1])));
		}
	}
}
class Dijk {
	/**
     *the distace array to store.
     */
	private double[] distace;
	/**
	 * edge class array.
	 */
	private Edge[] edge;
	/**
	 * Index min PQ object.
	 */
	private IndexMinPQ<Double> min;
	/**
     *the constructor to initialize the objects.
     *complexity is O(E + V).
     * @param      grah  graph object.
     * @param      one  The source
     */
	Dijk(final EdgeWeightedGraph graph, final int one) {
		distace = new double[graph.V()];
		edge = new Edge[graph.V()];
		min = new IndexMinPQ<Double>(graph.V());
		for (int i = 0; i < graph.V(); i++) {
			distace[i] = 10000000.0;
		}
		distace[one] = 0.0;
		min.insert(one, distace[one]);
        while (!min.isEmpty()) {
            int two = min.delMin();
            for (Edge each : graph.adj(two))
                relax(each, two);
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
        if (distace[two] > distace[one] + ed.weight()) {
            distace[two] = distace[one] + ed.weight();
            edge[two] = ed;
            if (min.contains(two)) {
                min.decreaseKey(two, distace[two]);
            } else {
                min.insert(two, distace[two]);
            }
        }
	}
	public double distTo(final int one) {
        return distace[one];
    }
    public double distanceTo(final int one) {
        double total = 0;
        for (Edge each : pathTo(one)) {
            total += each.weight();
        }
        return total;
    }
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
    public boolean hasPath(final int one) {
        return distace[one] < 10000000.0;
    }
}
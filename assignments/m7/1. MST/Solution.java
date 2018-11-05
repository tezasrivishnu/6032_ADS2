import java.util.Scanner;
final class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int number = Integer.parseInt(scan.nextLine());
		int input = Integer.parseInt(scan.nextLine());
		EdgeWeighted edge = new EdgeWeighted(input);
		while (scan.hasNext()) {
			String[] tokens = scan.nextLine().split(" ");
			edge.addEdge(new Edge(Integer.parseInt(tokens[0]),
			                      Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));

		}
		Kruskal krus = new Kruskal(edge);
		System.out.printf("%.5f", krus.totalWeight());

	}
}
class Edge implements Comparable<Edge> {
	private int edge1;
	private int edge2;
	private double weight;
	Edge(final int one, final int two, final double wei) {
		this.edge1 = one;
		this.edge2 = two;
		this.weight = wei;
	}
	public double getWeight() {
		return this.weight;
	}
	public int other(final int one) {
		if (one == edge1) {
			return this.edge2;
		}
		return this.edge1;
	}
	public int either() {
		return this.edge1;
	}
	public int compareTo(Edge that) {
		if (this.weight < that.weight) {
			return -1;
		}
		if (this.weight > that.weight) {
			return 1;
		}
		return 0;
	}
}
class EdgeWeighted {
	private final int vertices;
	private int edges;
	private Bag<Edge>[] adjacent;
	EdgeWeighted(final int one) {
		this.vertices = one;
		this.edges = 0;
		adjacent = (Bag<Edge>[]) new Bag[vertices];
		for (int i = 0; i < vertices; i++) {
			adjacent[i] = new Bag<Edge>();
		}
	}
	public void addEdge(final Edge ed) {
		int one = ed.either();
		int two = ed.other(one);
		adjacent[one].add(ed);
		adjacent[two].add(ed);
		edges += 1;
	}
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
	public Iterable<Edge> adjacentEdges(final int one) {
		return adjacent[one];
	}
	public int vertices() {
		return vertices;
	}
}
class Kruskal {
	private double totalweight;
	private Queue<Edge> queue;
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
	public double totalWeight() {
		return totalweight;
	}
}
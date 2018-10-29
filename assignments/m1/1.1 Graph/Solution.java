import java.util.Scanner;
// interface Graph {
//     public int V();
//     public int E();
//     public void addEdge(int v, int w);
//     public Iterable<Integer> adj(int v);
//     public boolean hasEdge(int v, int w);
// }

class ListGraph {
	private int node;
	private int edge;
	private int ncount;
	private int ecount;
	private String[] tokens;
	private Bag<Integer>[] adjacent;
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
	public void addEdge(final int one, final int two) {
		if (one == two) {
			return;
		}
		if (!hasEdge(one, two)) {
			ecount++;
		}
		adjacent[one].add(two);
		adjacent[two].add(one);
	}
	public int getEdges() {
		return ecount;
	}
	public int getNode() {
		return ncount;
	}
	public Iterable<Integer> adjacent(final int one) {
		return adjacent[one];
	}
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
	public boolean hasEdge(final int one, final int two) {
		for (int in : adjacent(one)) {
			if (in == two) {
				return false;
			}
		}
		return true;
	}
}
class MatrixGraph {
	private int node;
	private int edge;
	private int ncount;
	private int ecount;
	private String[] tokens;
	private int[][] adjacent;
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
	public void addEdge(final int one, final int two) {
		if (one == two) {
			return;
		}
		if (!hasEdge(one, two)) {
			ecount++;
		}
		adjacent[one][two] = 1;
		adjacent[two][one] = 1;
	}
	public boolean hasEdge(final int one, final int two) {
		return adjacent[one][two] != 1
		       && adjacent[two][one] != 1;
	}
	public int getEdges() {
		return ecount;
	}
	public int getNode() {
		return ncount;
	}
	public int[] adjacent(final int one) {
		return adjacent[one];
	}
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
			// 	str += in + " ";
			// }
		} else {
			str += "No edges";
			return str;
		}
		return str;
	}
}
final class Solution {
	private Solution() {

	}
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

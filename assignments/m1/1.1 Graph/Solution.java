import java.util.Scanner;
// interface Graph {
//     public int V();
//     public int E();
//     public void addEdge(int v, int w);
//     public Iterable<Integer> adj(int v);
//     public boolean hasEdge(int v, int w);
// }

class Graph {
	int node;
	int edge;
	int ncount;
	int ecount;
	String[] tokens;
	Bag<Integer>[] adjacent;
	Graph(Scanner scan) {
		node = Integer.parseInt(scan.nextLine());
		edge = Integer.parseInt(scan.nextLine());
		tokens = scan.nextLine().split(",");
		adjacent = new Bag[node];
		for (int i = 0; i < node; i++) {
			adjacent[i] = new Bag();
		}
		for (int j = 0; j < node; j++) {
			String[] items = scan.nextLine().split(" ");
			addEdge(Integer.parseInt(items[0]),
			        Integer.parseInt(items[1]));
		}
	}
	public void addEdge(int one, int two) {
		ecount++;
		adjacent[one].add(two);
		adjacent[two].add(one);
	}
	public int getEdges() {
		return ecount;
	}
	public int getNode() {
		return ncount;
	}
	public Iterable<Integer> adjacent(int one) {
		return adjacent[one];
	}
	public String toPrint() {
		String str = "";
		int k = 0;
		str += node + " vertices, " + edge + " edges" + "\n";
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
        return str;
	}
}
class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String lismat = scan.nextLine();
		Graph gob = new Graph(scan);
		System.out.println(gob.toPrint());
	}
}

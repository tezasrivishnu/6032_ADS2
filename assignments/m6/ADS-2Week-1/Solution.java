import java.util.Scanner;
class PageRank {
	Digraph digraph;
	PageRank(Digraph graph) {
		this.digraph = graph;
	}
	public String toString() {
		String str = "";
		for (int i = 0; i < digraph.V() - 1; i++) {
			str += i + " - " + rank(i) + "\n";
		}
		str += digraph.V() + " - " + rank(digraph.V());
		return str;
	}
	public double rank(int one) {
		double out = 0.0;
		Iterable<Integer> adja = digraph.adj(one);
		for (int each : digraph.adj(one)) {
			double prev = 1 / digraph.V();
			for (int i = 0; i < 1000; i++) {
				out += prev / digraph.outdegree(each);
				prev = out;
				if (prev == out) {
					break;
				}
			}

		}
		return out;
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input = Integer.parseInt(scan.nextLine());
		Digraph graph = new Digraph(input);
		Bag<Integer>[] adjacent = new Bag[input];
		for (int i = 0; i < input; i++) {
			adjacent[i] = new Bag();
		}
		for (int k = 0; k < input; k++) {
			String[] items = scan.nextLine().split(" ");
			for (int j = 1; j < items.length; j++) {
				graph.addEdge(Integer.parseInt(items[0]),
				              Integer.parseInt(items[j]));
			}
		}
		PageRank page = new PageRank(graph);
		System.out.println(graph);

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}

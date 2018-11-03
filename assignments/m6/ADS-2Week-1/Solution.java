import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
	/**
	 * value of 1000.
	 */
	private static final int THOUSAND = 1000;
	/**
	 * digraph class object.
	 */
	private Digraph digraph;
	private Double[] values;
	/**
	 * Constructs the object.
	 *
	 * @param      graph  The graph
	 */
	PageRank(final Digraph graph) {
		this.digraph = graph;
		values = new Double[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {
			values[i] = 1.0 / digraph.V();
		}
		for (int i = 0; i < digraph.V(); i++) {
			if(digraph.outdegree(i) == 0) {
				for (int j = 0; j < digraph.V(); i++) {
					if( j != i) {
						digraph.addEdge(i, j);
					}
				}
			}
		}
		Double[] updavalues = new Double[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {
			updavalues[i] = 1.0 / digraph.V();
		}
		values = Arrays.copyOf(updavalues, updavalues.length);
		for (int i = 0; i < THOUSAND; i++) {
			Double[] rank = rank(values, digraph);
			values = rank;
		}
	}
	public Double[] rank(Double[] values, Digraph digraph) {
		Double[] iter = new Double[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {
			double rank = 0.0;
			for (int j = 0; j < digraph.V(); j++) {
				for (int adj : digraph.adj(j)) {
					if (adj == i) {
						rank += values[i] / (double)digraph.outdegree(j);
					}
				}
				iter[i] = rank;
			}
		}
		return iter;
	}
	/**
	 * Returns a string representation of the object.
	 * complexity O(v) v is the no of vertices.
	 * @return     String representation of the object.
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < digraph.V() - 1; i++) {
			str += i + " - " + values[i] + "\n";
		}
		str += digraph.V() - 1 + " - " + values[digraph.V() - 1];
		return str;
	}
}
/**
 * Class for web search.
 */
class WebSearch {

}

/**
 * Class for solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {

	}
	/**
	 * main method for the program.
	 * comlexity O(n) n is the number of vertices given.
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
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
		System.out.println(page.toString());

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the
		//graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file
		// path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}

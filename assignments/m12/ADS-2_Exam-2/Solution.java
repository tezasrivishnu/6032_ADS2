import java.util.Scanner;
import java.util.ArrayList;
public class Solution {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeighted weight = new EdgeWeighted(vertices);
		for (int i = 0; i < edges; i++) {
			String[] tokens = scan.nextLine().split(" ");
			weight.addEdge(new Edge(Integer.parseInt(tokens[0]),
			                        Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));
		}
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...

		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(weight.toString());
			break;

		case "DirectedPaths":
			String[] items = scan.nextLine().split(" ");
			Dijk dij = new Dijk(weight, Integer.parseInt(items[0]));
			double out = dij.distanceTo(Integer.parseInt(
			                                items[1]));
			if (out == 0.0) {
				System.out.println("No Path Found.");
			} else {
				System.out.println(out);
			}
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			String[] items1 = scan.nextLine().split(" ");
			Dijk dij1 = new Dijk(weight, Integer.parseInt(items1[0]));
			double out1 = dij1.distanceTo(Integer.parseInt(
			                                  items1[2]));
			if (out1 == 0.0) {
				System.out.println("No Path Found.");
			} else {
				double out2 = dij1.distanceTo(Integer.parseInt(
				                                  items1[1]));
				Dijk dij2 = new Dijk(weight, Integer.parseInt(items1[1]));
				double out3 = dij2.distanceTo(Integer.parseInt(
				                                  items1[2]));
				System.out.println(out2 + out3);
				ArrayList<Integer> path = new ArrayList<Integer>();
				for (Edge e : dij1.pathTo(Integer.parseInt(items1[1]))) {
					if (!path.contains(e.getEdge2())) {
						path.add(e.getEdge2());
					}
					if (!path.contains(e.getEdge1())) {
						path.add(e.getEdge1());
					}
				}
				for (Edge e : dij2.pathTo(Integer.parseInt(items1[2]))) {
					if (!path.contains(e.getEdge2())) {
						path.add(e.getEdge2());
					}
					if (!path.contains(e.getEdge1())) {
						path.add(e.getEdge1());
					}
				}
				String text = path.toString();
				System.out.println(text.replaceAll( "[^a-zA-Z0-9 ]" , "" ));
			}
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}
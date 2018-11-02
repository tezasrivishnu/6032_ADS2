import java.util.Scanner;
class Solution {
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String synsets = scan.nextLine();
		String hypernyms = scan.nextLine();
		String input = scan.nextLine();
		switch (input) {
		case "Graph":
			try {
				WordNet word = new WordNet(synsets, hypernyms);
				word.print();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			break;
		case "Queries":
			WordNet word = new WordNet(synsets, hypernyms);
			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split(" ");
				String ans = word.sap(tokens[1], tokens[2]);
				int dis = word.distance(tokens[1], tokens[2]);
				System.out.println(ans + " " + dis);
			}
			break;
		default:
			break;
		}
	}
}
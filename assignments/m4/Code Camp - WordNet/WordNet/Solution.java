import java.util.Scanner;
class Solution {
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String synsets = StdIn.readString();
		String hypernyms = StdIn.readString();
		String input = StdIn.readString();
		WordNet word = new WordNet(synsets, hypernyms);
		switch (input) {
		case "Graph":
			try {
				System.out.println(word.print());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			break;
		case "Queries":
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
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
			try {
				while (!StdIn.isEmpty()) {
					String nounA = StdIn.readString();
					String nounB = StdIn.readString();
					String ans = word.sap(nounA, nounB);
					int dis = word.distance(nounA, nounB);
					System.out.println(ans + " " + dis);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		default:
			break;
		}
	}
}
import java.util.Scanner;
class Solution {
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String synsets = StdIn.readString();
		String hypernyms = StdIn.readString();
		String input = StdIn.readString();
		try {
			WordNet word = new WordNet(synsets, hypernyms);
			if (input.equals("Graph")) {
				System.out.println(word.print());
			} else {
				while (!StdIn.isEmpty()) {
					String nounA = StdIn.readString();
					String nounB = StdIn.readString();
					String ans = word.sap(nounA, nounB);
					int dis = word.distance(nounA, nounB);
					System.out.println(ans + " " + dis);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

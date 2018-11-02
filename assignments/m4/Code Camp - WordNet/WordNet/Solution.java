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
					String one = StdIn.readString();
					String two = StdIn.readString();
					System.out.println("distance = " +
								word.distance(one, two) + ", ancestor = " +
								word.sap(one, two));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

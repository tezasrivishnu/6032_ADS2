import java.util.Scanner;
class Solution {
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String synsets = scan.nextLine();
		String hypernyms = scan.nextLine();
		String input = scan.nextLine();
		try {
			if (input.equals("Graph")) {
				WordNet word = new WordNet(synsets, hypernyms);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
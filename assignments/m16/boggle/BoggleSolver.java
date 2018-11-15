import java.util.*;
public class BoggleSolver {
	TST<Integer> dict = new TST<>();
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		for (String each : dictionary) {
            dict.put(each, 1);
        }
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
        TreeSet<String> words = new TreeSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                searchWords(board, i, j, words);
            }
        }
        return words;
    }

    private void searchWords(BoggleBoard board, int i, int j, TreeSet<String> words) {
        boolean[][] visited = new boolean[board.rows()][board.cols()];
        dfs(board, i, j, words, visited, "");
    }
     private void dfs(BoggleBoard board, int i, int j, Set<String> words, boolean[][] visited, String prefix) {
        if (visited[i][j]) {
            return;
        }

        char letter = board.getLetter(i, j);
        prefix = prefix + (letter == 'Q' ? "QU" : letter);

        if (prefix.length() > 2 && dict.contains(prefix)) {
            words.add(prefix);
        }
        visited[i][j] = true;
        if (i > 0) {
            dfs(board, i - 1, j, words, visited, prefix);
            if (j > 0) {
                dfs(board, i - 1, j - 1, words, visited, prefix);
            }
            if (j < board.cols() - 1) {
                dfs(board, i - 1, j + 1, words, visited, prefix);
            }
        }
        if (j > 0) {
            dfs(board, i, j - 1, words, visited, prefix);
        }
        if (j < board.cols() - 1) {
            dfs(board, i, j + 1, words, visited, prefix);
        }
        if (i < board.rows() - 1) {
            if (j > 0) {
                dfs(board, i + 1, j - 1, words, visited, prefix);
            }
            if (j < board.cols() - 1) {
                dfs(board, i + 1, j + 1, words, visited, prefix);
            }
            dfs(board, i + 1, j, words, visited, prefix);
        }
        visited[i][j] = false;
    }

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (dict.contains(word)) {
			if (word.length() < 3) {
				return 0;
			} else if (word.length() < 5) {
				return 1;
			} else if (word.length() < 6) {
				return 2;
			} else if (word.length() < 7) {
				return 3;
			} else if (word.length() < 8) {
				return 5;
			} else {
				return 11;
			}
		}
		return 0;
	}
}
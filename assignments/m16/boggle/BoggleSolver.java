import java.util.TreeSet;
import java.util.Set;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * TST object.
     */
    private TST<Integer> tst = new TST<>();
    /**
     * Constructs the object.
     * complexity O(n) n is the length of array.
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        for (String each : dictionary) {
            tst.put(each, 1);
        }
    }
    /**
     * Gets all valid words.
     * complexity O(V + E) v is the
     * no of nodes and E is the number of adacent vertices.
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        boolean[][] visit = new boolean[board.rows()][board.cols()];
        TreeSet<String> words = new TreeSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                dfs(board, i, j, words, visit, "");
            }
        }
        return words;
    }
    /**
     * depth first search method.
     * complexity O(V + E) v is the
     * no of nodes and E is the number of adacent vertices.
     * @param      board   The board
     * @param      one     One
     * @param      two     Two
     * @param      words   The words
     * @param      visit   The visit
     * @param      prefix  The prefix
     */
    public void dfs(final BoggleBoard board,
        final int one, final int two, final Set<String> words,
        final boolean[][] visit, final String prefix) {
        String word = prefix;
        if (visit[one][two]) {
            return;
        }
        char letter = board.getLetter(one, two);
        if (letter == 'Q') {
            word += "QU";
        } else {
            word += letter;
        }
        if (word.length() > 2 && tst.contains(word)) {
            words.add(word);
        }
        if (!tst.isPrefix(word)) {
            return;
        }
        visit[one][two] = true;
        if (one > 0) {
            dfs(board, one - 1, two, words, visit, word);
            if (two > 0) {
                dfs(board, one - 1, two - 1, words, visit, word);
            }
            if (two < board.cols() - 1) {
                dfs(board, one - 1, two + 1, words, visit, word);
            }
        }
        if (two > 0) {
            dfs(board, one, two - 1, words, visit, word);
        }
        if (two < board.cols() - 1) {
            dfs(board, one, two + 1, words, visit, word);
        }
        if (one < board.rows() - 1) {
            if (two > 0) {
                dfs(board, one + 1, two - 1, words, visit, word);
            }
            if (two < board.cols() - 1) {
                dfs(board, one + 1, two + 1, words, visit, word);
            }
            dfs(board, one + 1, two, words, visit, word);
        }
        visit[one][two] = false;
    }
    /**
     * fnds the score of the word.
     * complexity O(1)
     * @param      one   One
     *
     * @return     int value.
     */
    public int scoreOf(final String one) {
        if (tst.contains(one)) {
            if (one.length() <  (2 + 1)) {
                return 0;
            } else if (one.length() < (2 * 2) + 1) {
                return 1;
            } else if (one.length() < (2 * 2) + 2) {
                return 2;
            } else if (one.length() < (2 * 2 * 2) - 1) {
                return 2 + 1;
            } else if (one.length() < (2 * 2 * 2)) {
                return 2 + 2 + 1;
            } else {
                return (2 * 2 * 2) + (2 + 1);
            }
        }
        return 0;
    }
}

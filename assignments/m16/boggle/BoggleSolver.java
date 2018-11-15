import java.util.*;
public class BoggleSolver {
    TST<Integer> tst = new TST<>();
    public BoggleSolver(String[] dictionary) {
        for (String each : dictionary) {
            tst.put(each, 1);
        }
    }
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        boolean[][] visit = new boolean[board.rows()][board.cols()];
        TreeSet<String> words = new TreeSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                dfs(board, i, j, words, visit, "");
            }
        }
        return words;
    }
    public void dfs(BoggleBoard board, int one, int two, Set<String> words, boolean[][] visit, String prefix) {
        if (visit[one][two]) {
            return;
        }
        char letter = board.getLetter(one, two);
        if (letter == 'Q') {
            prefix += "QU";
        } else {
            prefix += letter;
        }
        if (prefix.length() > 2 && tst.contains(prefix)) {
            words.add(prefix);
        }
        visit[one][two] = true;
        if (one > 0) {
            dfs(board, one - 1, two, words, visit, prefix);
            if (two > 0) {
                dfs(board, one - 1, two - 1, words, visit, prefix);
            }
            if (two < board.cols() - 1) {
                dfs(board, one - 1, two + 1, words, visit, prefix);
            }
        }
        if (two > 0) {
            dfs(board, one, two - 1, words, visit, prefix);
        }
        if (two < board.cols() - 1) {
            dfs(board, one, two + 1, words, visit, prefix);
        }
        if (one < board.rows() - 1) {
            if (two > 0) {
                dfs(board, one + 1, two - 1, words, visit, prefix);
            }
            if (two < board.cols() - 1) {
                dfs(board, one + 1, two + 1, words, visit, prefix);
            }
            dfs(board, one + 1, two, words, visit, prefix);
        }
        visit[one][two] = false;
    }
    public int scoreOf(String one) {
        if (tst.contains(one)) {
            if (one.length() < 3) {
                return 0;
            } else if (one.length() < 5) {
                return 1;
            } else if (one.length() < 6) {
                return 2;
            } else if (one.length() < 7) {
                return 3;
            } else if (one.length() < 8) {
                return 5;
            } else {
                return 11;
            }
        }
        return 0;
    }
}
import java.math.BigInteger;
import java.util.Random;
class RabinKarp {
	private String pat;
	private long patHash;
	private int m;
	private long q;
	private int R;
	private long RM;
	public RabinKarp(String pat) {
		this.pat = pat;
		R = 256;
		m = pat.length();
		q = longRandomPrime();
		RM = 1;
		for (int i = 1; i <= m - 1; i++)
			RM = (R * RM) % q;
		patHash = hash(pat, m);
	}
	private long hash(String key, int m) {
		long h = 0;
		for (int j = 0; j < m; j++)
			h = (R * h + key.charAt(j)) % q;
		return h;
	}
	private boolean check(String txt, int i) {
		for (int j = 0; j < m; j++)
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}
	public int search(String txt) {
		int n = txt.length();
		if (n < m) return n;
		long txtHash = hash(txt, m);
		if ((patHash == txtHash) && check(txt, 0))
			return 0;
		for (int i = m; i < n; i++) {
			txtHash = (txtHash + q - RM * txt.charAt(i - m) % q) % q;
			txtHash = (txtHash * R + txt.charAt(i)) % q;
			int offset = i - m + 1;
			if ((patHash == txtHash) && check(txt, offset))
				return offset;
		}
		return n;
	}
	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}
}
class BoyerMoore {
	private final int R;
	private int[] right;
	private char[] pattern;
	private String pat;
	public BoyerMoore(String pat) {
		this.R = 256;
		this.pat = pat;
		right = new int[R];
		for (int c = 0; c < R; c++)
			right[c] = -1;
		for (int j = 0; j < pat.length(); j++)
			right[pat.charAt(j)] = j;
	}
	public int search(String txt) {
		int m = pat.length();
		int n = txt.length();
		int skip;
		for (int i = 0; i <= n - m; i += skip) {
			skip = 0;
			for (int j = m - 1; j >= 0; j--) {
				if (pat.charAt(j) != txt.charAt(i + j)) {
					skip = Math.max(1, j - right[txt.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0) return i;
		}
		return n;
	}
}
class KMP {
	private final int R;
	private int[][] dfa;
	private char[] pattern;
	private String pat;
	public KMP(String pat) {
		this.R = 256;
		this.pat = pat;
		int m = pat.length();
		dfa = new int[R][m];
		dfa[pat.charAt(0)][0] = 1;
		for (int x = 0, j = 1; j < m; j++) {
			for (int c = 0; c < R; c++)
				dfa[c][j] = dfa[c][x];
			dfa[pat.charAt(j)][j] = j + 1;
			x = dfa[pat.charAt(j)][x];
		}
	}
	public int search(String txt) {
		int m = pat.length();
		int n = txt.length();
		int i, j;
		for (i = 0, j = 0; i < n && j < m; i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if (j == m) {
			return i - m;
		}
		return n;
	}
}
class Brute {
	Brute() {

	}
    public int search1(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (txt.charAt(i+j) != pat.charAt(j))
                    break;
            }
            if (j == m) return i;
        }
        return n;
    }
     public static int search2(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == m) return i - m;
        else        return n;
    }
}
class Solution {
	public static void main(String[] args) {
		String txt = loadWords();
        String pat = "itisafarfarbetterthingthatidothanihaveeverdone";
        double rbstartTime   = System.currentTimeMillis();
        RabinKarp searcher = new RabinKarp(pat);
        int rboffset = searcher.search(txt);
        double rbendTime   = System.currentTimeMillis();
        double rbtotalTime = rbendTime - rbstartTime;
        StdOut.println("found pattern at index: " + rboffset);
        System.out.println("time taken for Rabin Karp: " + rbtotalTime + " milliseconds");
        System.out.println();

        double bmstartTime   = System.currentTimeMillis();
        BoyerMoore boyermoore1 = new BoyerMoore(pat);
        int bmoffset = boyermoore1.search(txt);
        double bmendTime   = System.currentTimeMillis();
        double bmtotalTime = bmendTime - bmstartTime;
        StdOut.println("found pattern at index: " + bmoffset);
        System.out.println("time taken for Boyer Moore: " + bmtotalTime + " milliseconds");
        System.out.println();

        double kmpstartTime   = System.currentTimeMillis();
        KMP kmp1 = new KMP(pat);
        int kmpoffset1 = kmp1.search(txt);
        double kmpendTime   = System.currentTimeMillis();
        double kmptotalTime = kmpendTime - kmpstartTime;
        StdOut.println("found pattern at index: " + kmpoffset1);
        System.out.println("time taken for KMP: " + kmptotalTime + " milliseconds");
        System.out.println();

        Brute brute = new Brute();
        double bstartTime   = System.currentTimeMillis();
        int boffset1 = brute.search2(pat, txt);
        double bendTime   = System.currentTimeMillis();
        double btotalTime = bendTime - kmpstartTime;
        StdOut.println("found pattern at index: " + boffset1);
        System.out.println("time taken for bruteforce: " + btotalTime + " milliseconds");
        System.out.println();
	}
	public static String loadWords() {
        In in = new In("/Files/text.txt");
        String str = "";
        String[] words = in.readAllStrings();
        int i;
        for (i = 0; i < words.length - 1; i++) {
            if (words[i].length() != 0) {
                str += words[i] + " ";
            }
        }
        if (words[i].length() != 0) {
            str += words[i];
        }
        str = str.toLowerCase();
        str = str.replaceAll("[^a-z]", "");
        return str.toLowerCase();
    }
}
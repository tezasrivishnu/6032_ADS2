import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
	private static final int R = 256;
	public static void transform() {
		String str = BinaryStdIn.readString();
		CircularSuffixArray circular = new CircularSuffixArray(str);
		int n = circular.length();
		for (int i = 0; i < n; i++) {
			if (circular.index(i) == 0) {
				BinaryStdOut.write(i);
				break;
			}
		}
		for (int i = 0; i < n; i++) {
			BinaryStdOut.write(str.charAt((circular.index(i) + n - 1) % n));
		}
		BinaryStdOut.close();
	}
	public static void inverseTransform() {
		int str = BinaryStdIn.readInt();
		String string = BinaryStdIn.readString();
		int n = string.length();
		int[] next = new int[n], 
		int[] count = new int[R + 1];
		for (int i = 0; i < n; i++) {
			count[string.charAt(i) + 1]++;
		}
		for (int r = 0; r < R; r++) {
			count[r + 1] += count[r];
		}
		for (int i = 0; i < n; i++) {
			next[count[string.charAt(i)]++] = i;
		}
		for (int i = next[str], j = 0; j < n; j++, i = next[i]) {
			BinaryStdOut.write(string.charAt(i));
		}
		BinaryStdOut.close();
	}
	public static void main(String[] args) {
		if (args[0].equals("-")) {
			transform();
		} else if (args[0].equals("+")) {
			inverseTransform();
		}
	}
}

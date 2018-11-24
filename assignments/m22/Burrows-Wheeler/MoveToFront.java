// import edu.princeton.cs.algs4.Queue;
// import edu.princeton.cs.algs4.BinaryStdIn;
// import edu.princeton.cs.algs4.BinaryStdOut;
public class MoveToFront {
	private static int R = 256;
    public static void encode() {
        char[] tokens = array();
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            char character, count, temp;
            for (count = 0, temp = tokens[0]; ch != tokens[count]; count++) {
                character = tokens[count];
                tokens[count] = temp;
                temp = character;
            }
            tokens[count] = temp;
            BinaryStdOut.write(count);
            tokens[0] = ch;
        }
        BinaryStdOut.close();
    }
    public static void decode() {
        char[] tokens = array();
        while (!BinaryStdIn.isEmpty()) {
            char count = BinaryStdIn.readChar();
            System.out.println(count);
            BinaryStdOut.write(tokens[count]);
            char index = tokens[count];
            while (count > 0) {
                tokens[count] = tokens[--count];
            }
            tokens[0] = index;
        }
        BinaryStdOut.close();
    }

    private static char[] array() {
        char[] tokens = new char[R];
        for (char i = 0; i < R; i++) {
            tokens[i] = i;
        }
        return tokens;
    }

// import java.util.LinkedList;
// public class MoveToFront {
// 	private static final int R = 256;
// 	public static void encode() {
// 		String str = BinaryStdIn.readString();
// 		char[] input = str.toCharArray();
// 		LinkedList<Integer> list = new LinkedList<Integer>();
// 		for (int i = 0; i < R; i++) {
// 			list.add(i);
// 		}
// 		for (int i = 0; i < input.length; i++) {
// 			int idx = list.indexOf((int) input[i]);
// 			BinaryStdOut.write((char) idx);
// 			int obj = list.remove(idx);
// 			list.add(0, obj);
// 		}
// 		BinaryStdOut.close();
// 	}
// 	public static void decode() {
// 		String str = BinaryStdIn.readString();
// 		char[] input = str.toCharArray();
// 		// System.out.println(str);
// 		LinkedList<Integer> list = new LinkedList<Integer>();
// 		for (int i = 0; i < R; i++)
// 			list.add(i);

// 		for (int i = 0; i < input.length; i++) {
// 			int obj = list.remove((int) input[i]);
// 			list.add(0, obj);
// 			BinaryStdOut.write((char) obj);
// 		}
// 		BinaryStdOut.close();
// 	}
	public static void main(String[] args) {
		if (args[0].equals("-")) {
			encode();
		} else if (args[0].equals("+")) {
			decode();
		}
	}
}
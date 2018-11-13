import java.util.Scanner;
import java.util.Arrays;
public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		Scanner scan = new Scanner(System.in);
		String token = scan.nextLine();
		TST tst = new TST();
		for (String each : words) {
			// String[] suf = new String[each.length()];
            for (int i = 0; i < each.length(); i++) {
            	// suf[i] = each.substring(i,each.length());
            	tst.put(each.substring(i,each.length()), 0);
            }
        }
        System.out.println(tst.keysWithPrefix(token));
		//Your code goes here...
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}
class TST<Value> {
	int n;
	Node<Value> root;
	class Node<Value> {
		char character;
		Node<Value> left;
		Node<Value> middle;
		Node<Value> right;
		Value value;
	}
	TST() {

	}
	public boolean contains(String one) {
		return get(one) != null;
	}
	public Value get(String one) {
		Node<Value> node = get(root, one, 0);
		if (node == null) {
			return null;
		}
		return node.value;
	}
	public Node<Value> get(Node<Value> node, String one, int d) {
		if (node == null) {
			return null;
		}
		char ch = one.charAt(d);
		if (ch < node.character) {
			return get(node.left,  one, d);
		} else if (ch > node.character) {
			return get(node.right, one, d);
		} else if (d < one.length() - 1) {
			return get(node.middle, one, d + 1);
		} else {
			return node;
		}
	}
	public void put(String one, Value value) {
		if (!contains(one)) n++;
		root = put(root, one, value, 0);
	}

	public Node<Value> put(Node<Value> node, String one, Value value, int d) {
		char ch = one.charAt(d);
		if (node == null) {
			node = new Node<Value>();
			node.character = ch;
		}
		if (ch < node.character) {
			node.left  = put(node.left,  one, value, d);
		} else if (ch > node.character) {
			node.right = put(node.right, one, value, d);
		} else if (d < one.length() - 1) {
			node.middle   = put(node.middle, one, value, d + 1);
		} else {
			node.value   = value;
		}
		return node;
	}
	public Iterable<String> keysWithPrefix(String one) {
		Queue<String> que = new Queue<String>();
		Node<Value> x = get(root, one, 0);
		if (x == null) {
			return que;
		}
		if (x.value != null) {
			que.enqueue(one);
		}
		collect(x.middle, new StringBuilder(one), que);
		return que;
	}
	private void collect(Node<Value> node, StringBuilder one, Queue<String> que) {
        if (node == null) return;
        collect(node.left,  one, que);
        if (node.value != null) que.enqueue(one.toString() + node.character);
        collect(node.middle, one.append(node.character), que);
        one.deleteCharAt(one.length() - 1);
        collect(node.right, one, que);
    }
}
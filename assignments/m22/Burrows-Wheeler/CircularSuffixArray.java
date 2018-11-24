import java.util.Arrays;
public class CircularSuffixArray {
    private int[] in;
    private int length;
    private class Node implements Comparable<Node> {
        int value;
        String str;
        Node(String string, int co) {
            this.str = string;
            this.value = co;
        }
        public int compareTo(Node other) {
            for (int i = 0; i < str.length(); i++) {
                char one = str.charAt(( i + this.value) % str.length());
                char two = str.charAt(( i + other.value) % str.length());
                if(one > two) {
                    return 1;
                } else if (one < two) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public CircularSuffixArray(String s) {
        length = s.length();
        in = new int[length];
        Node[] node = new Node[length];
        for (int i = 0; i < length; i++) {
            node[i] = new Node(s, i);
        }
        Arrays.sort(node);
        for (int i = 0; i < length; i++) {
            in[i] = node[i].value;
        }
    }

    public int length() {
    	return length;
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
    	return in[i];
    }
}

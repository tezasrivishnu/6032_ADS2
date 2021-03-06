import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;
/**
 * class Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    // Don't modify this method.

    /**
     * main method for the program.
     * complexity O()
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash
                = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(
                            t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.

    /**
     * reading the files in string.
     * @param      file  The file
     *
     * @return     returns the words in array.
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
    /**
     * Loads a dictionary.
     * complexity O(n) n is the length of the array.
     * @param      file  The file
     *
     * @return     BinarysearchST.
     */
    public static BinarySearchST<String, Integer>
    loadDictionary(final String file) {
        BinarySearchST<String, Integer>  st
            = new BinarySearchST<String, Integer>();
        int counter = 1;
        String[] tokens = toReadFile(file);
        // for (int i = 0; i < tokens.length; i++) {
        //  String item = tokens[i].toLowerCase();
        //  if (!st.contains(item)) {
        //      for (int j = i + 1; j < tokens.length; j++) {
        //          if (item.equals
        //          (tokens[j].toLowerCase())) {
        //              counter++;
        //          }
        //      }
        //      st.put(item, counter);
        //      counter = 1;
        //  }
        // }

        for (String each : tokens) {
            Integer count = st.get(each.toLowerCase());
            if (count == null) {
                count = 1;
            } else {
                ++count;
            }
            st.put(each.toLowerCase(), count);
        }
        // your code goes here
        return st;
    }

}
/**
 * Class for t 9.
 */
class T9 {
    /**
     * TST object.
     */
    private TST<Integer> tst;
    /**
     * Constructs the object.
     * complexity O(n) n is size of the binaryST
     * @param      st    binarysearchST.
     */
    protected T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST<Integer>();
        for (int i = 0; i < st.size(); i++) {
            String temp = st.select(i);
            tst.put(temp, st.get(temp));
        }
    }

    // get all the prefixes that match with given prefix.

    /**
     * Gets all words.
     * complexity O(L + logN) as we are calling tst method.
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        return tst.keysWithPrefix(prefix);
        // return null;
    }
    /**
     * gives the words as per the input sequence.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     iterable string.
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        TreeSet<String> potential = new TreeSet<String>();
        for (String word : tst.keys()) {
            String[] tokens = word.split("");
            String str = "";
            String str1 = "";
            for (String each : tokens) {
                if (each.equals("a") || each.equals("b") || each.equals("c")) {
                    str += "2";
                    str1 += each;
                } else if (each.equals("d") || each.equals("e") || each.equals("f")) {
                    str += "3";
                    str1 += each;

                } else if (each.equals("g") || each.equals("h") || each.equals("i")) {
                    str += "4";
                    str1 += each;

                } else if (each.equals("j") || each.equals("k") || each.equals("l")) {
                    str += "5";
                    str1 += each;

                } else if (each.equals("m") || each.equals("n") || each.equals("o")) {
                    str += "6";
                    str1 += each;

                } else if (each.equals("p") || each.equals("q")  || each.equals("r") || each.equals("s")) {
                    str += "7";
                    str1 += each;

                } else if (each.equals("t") || each.equals("u") || each.equals("v")) {
                    str += "8";
                    str1 += each;

                } else if (each.equals("w") || each.equals("x")  || each.equals("y") || each.equals("z")) {
                    str += "9";
                    str1 += each;

                } else {
                    continue;
                }
            }
            if (str.equals(t9Signature) && tst.contains(str1)) {
                potential.add(word);
            }
        }
        return potential;
    }

    // return all possibilities(words), find top k with highest frequency.

    /**
     * Gets the suggestions.
     * complexity O(m*n) m the size of words, n is the
     * prefixes of each word in words bag.
     * @param      words  The words
     * @param      k      the frequency
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words,
                                           final int k) {
        MaxPQ<Integer> max = new MaxPQ<Integer>();
        for (String each : words) {
            max.insert((Integer) tst.get(each));
        }
        TreeSet<String> list = new TreeSet<String>();
        for (int i = 0; i < k; i++) {
            if (max.size() != 0) {
                int output = max.delMax();
                for (String word : words) {
                    if (output == (Integer) tst.get(word)) {
                        list.add(word);
                    }
                }
            }
        }
        return list;
    }

    // final output
    // Don't modify this method.
    /**
     * gives the correct words in the dictionary.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            int value.
     *
     * @return     iterable string.
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}

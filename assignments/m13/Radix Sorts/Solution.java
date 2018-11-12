import java.util.Scanner;
/**
 * Class for least significant digit.
 */
class LSD {
  /**
   * int variable.
   */
  private static final int CHAR = 256;
  /**
   * Constructs the object.
   */
  protected LSD() {

  }
  /**
   * sorting of the input given.
   * complexity O(w*n) w----->fixed length.
   * n------>array length
   * @param      arr   The array
   * @param      one   lenght of array.
   */
  public void sort(final String[] arr, final int one) {
    int size = arr.length;
    int charac = CHAR;
    String[] aux = new String[size];
    for (int d = one - 1; d >= 0; d--) {
      int[] count = new int[charac + 1];
      for (int i = 0; i < size; i++) {
        count[arr[i].charAt(d) + 1]++;
      }
      for (int r = 0; r < charac; r++) {
        count[r + 1] += count[r];
      }
      for (int i = 0; i < size; i++) {
        aux[count[arr[i].charAt(d)]++] = arr[i];
      }
      for (int i = 0; i < size; i++) {
        arr[i] = aux[i];
      }
    }
  }
  /**
   * Returns a string representation of the object.
   * comlexity O(n) n is the length of array.
   * @param      one   One
   *
   * @return     String representation of the object.
   */
  public String toString(final String[] one) {
    String s = "[";
    for (int i = 0; i < one.length - 1; i++) {
      s += one[i] + ", ";
    }
    s += one[one.length - 1] + "]";
    return s;
  }
}
/**
 * Class for solution.
 */
final class Solution {
  /**
   * Constructs the object.
   */
  private Solution() {

  }
  /**
   * main method for the program.
   * complexity O(n + w*n) as we are taking n inputs and
   * calling sort method in LSD class.
   * @param      args  The arguments
   */
  public static void main(final String[] args) {
    Scanner scan = new Scanner(System.in);
    int input = Integer.parseInt(scan.nextLine());
    String[] tokens = new String[input];
    for (int i = 0; i < tokens.length; i++) {
      tokens[i] = scan.nextLine();
    }
    LSD lsd = new LSD();
    lsd.sort(tokens, tokens[0].length());
    System.out.println(lsd.toString(tokens));
  }
}

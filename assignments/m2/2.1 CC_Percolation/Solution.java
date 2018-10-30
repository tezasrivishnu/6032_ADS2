import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
    *the array.
    */
    private boolean[] boo;
    /**
     *object declaration for graph class.
     */
    private Graph gra;
    /**
     *array size.
     */
    private int asize;
    /**
     *size.
     */
    private int size;
    /**
     *first row.
     */
    private int up;
    /**
     * last row.
     */
    private int down;
    /**
     * initializing count.
     */
    private int count;
    /**
     * Constructs the object.
     */
    Percolation() {

    }
    /**
     * Constructs the object.
     *
     * @param      one   One
     */
    Percolation(final int one) {
        this.asize = one;
        this.size = one * one;
        this.up = size;
        this.down = size + 1;
        this.count = 0;
        gra = new Graph(size + 2);
        boo = new boolean[size];
        for (int i = 0; i < asize; i++) {
            gra.addEdge(up, i);
            gra.addEdge(down, size - i - 1);
        }
    }
    /**
     * opening a link between two elements.
     * complexity O(n^2) as the complexity of the connection
     * methos is n^2.
     * @param      one   One
     * @param      two   Two
     */
    public void open(final int one, final int two) {
        //System.out.println(one + " " + two);
        int in = oneDimen(one, two);
        //System.out.println(in);
        boo[in] = true;
        count++;
        int top = in - asize;
        int bottom = in + asize;
        //System.out.println(top + " " + bottom);
        if (asize == 1) {
            gra.addEdge(up, in);
            gra.addEdge(down, in);
        }
        if (bottom < size) {
            connections(in, bottom);
        }
        if (top >= 0) {
            connections(in, top);
        }
        if (two == 1) {
            if (two != asize) {
                connections(in, in + 1);
            }
            return;
        }
        if (two == asize) {
            connections(in, in - 1);
            return;
        }
        connections(in, in + 1);
        connections(in, in - 1);
    }
    /**
     * adding the edge to nodes.
     * complexity O(N^2) as the complexity of
     * addedge method is n^2.
     * @param      one   One
     * @param      two   Two
     */
    public void connections(final int one, final int two) {
        if (boo[two] && !gra.hasEdge(one, two)) {
            gra.addEdge(one, two);
        }
    }
    /**
     * converting 2d to 1d array.
     * complexity O(1).
     * @param      one   One
     * @param      two   Two
     *
     * @return     returns the index od 1d array.
     */
    public int oneDimen(final int one, final int two) {
        return (asize * (one - 1)) + (two - 1);
    }
    /**
     * Determines if open.
     * complexity O(1)
     * @param      one   One
     * @param      two   Two
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int one, final int two) {
        return boo[oneDimen(one, two)];
    }
    /**
     * Opens sites.
     * complexity O(1)
     * @return     count
     */
    public int openSites() {
        return count;
    }
    /**
     * finding if the graph percolates or not.
     * complexity O(n^2) as the complexity of
     * connectionss method is n^2
     * @return     true or false.
     */
    public boolean percolates() {
        CC connect = new CC(gra);
        return connect.connected(up, down);
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
     * main program for the program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Percolation per = new Percolation(n);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            per.open(Integer.parseInt(tokens[0]),
                     Integer.parseInt(tokens[1]));
        }
        System.out.println(per.percolates()
                           && per.openSites() != 0);
    }
}
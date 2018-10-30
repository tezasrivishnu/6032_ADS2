import java.util.Scanner;
class Percolation {
    boolean[] boo;
    Graph gra;
    int asize;
    int size;
    int up;
    int down;
    int count;
    Percolation() {

    }
    Percolation(int one) {
        this.asize = one;
        this.size = one * one;
        this.up = size;
        this.down = size + 1;
        this.count = 0;
        gra = new Graph(size + 2);
        boo = new boolean[size];
        for (int i = 0; i < asize; i++) {
            gra.addEdge(up,i);
            gra.addEdge(down, size - i - 1);
        }
    }
    public void open(int one, int two) {
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
    public void connections(int one, int two) {
        if (boo[two] && !gra.hasEdge(one, two)) {
            gra.addEdge(one, two);
        }
    }
    public int oneDimen(int one, int two) {
        return (asize * (one - 1)) + (two - 1);
    }
    public boolean isOpen(int one, int two) {
        return boo[oneDimen(one, two)];
    }
    public int openSites() {
        return count;
    }
    public boolean percolates() {
        CC connect = new CC(gra);
        return connect.connected(up, down);
    }
}
class Solution {
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
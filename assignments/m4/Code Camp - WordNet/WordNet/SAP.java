/*public class SAP {

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)

    // do unit testing of this class
    public static void main(String[] args)
}*/
import java.util.*;
class Sap {
    private Digraph graph;
    int minimum;
    int ancestor;
    Sap(Digraph g) {
        graph = new Digraph(g);
    }
    public int length(int one, int two) {
        Set<Integer> first = new HashSet<Integer>();
        first.add(one);
        Set<Integer> second = new HashSet<Integer>();
        second.add(two);
        length(first, second);
        if (ancestor == -1) {
            return -1;
        } else {
            return minimum;
        }
    }
    public int length(Iterable<Integer> one, Iterable<Integer> two) {
        minimum = 1000000000;
        ancestor = -1;
        BreadthFirstDirectedPaths first = new BreadthFirstDirectedPaths(graph, one);
        BreadthFirstDirectedPaths second = new BreadthFirstDirectedPaths(graph, two);
        for (int i = 0; i < graph.V(); i++) {
            if (first.hasPathTo(i) && second.hasPathTo(i)) {
                int length = first.distTo(i) + second.distTo(i);
                if (length < minimum) {
                    minimum = length;
                    ancestor = i;
                }
            }
        }
        return minimum;
    }
    public int ancestor(int one, int two) {
        length(one, two);
        return ancestor;
    }
}
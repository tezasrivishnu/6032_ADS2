/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     * int variable.
     */
    private int edge1;
    /**
     * int variable.
     */
    private int edge2;
    /**
     * double variable.
     */
    private double weight;
    /**
     * Constructs the object.
     * @param      one   One
     * @param      two   Two
     * @param      wei   The wei
     */
    Edge(final int one, final int two, final double wei) {
        this.edge1 = one;
        this.edge2 = two;
        this.weight = wei;
    }
    /**
     * Gets the edge 1.
     * complexity O(1)
     * @return     The edge 1.
     */
    public int getEdge1() {
        return edge1;
    }
    /**
     * Gets the edge 2.
     * complexity O(1)
     * @return     The edge 2.
     */
    public int getEdge2() {
        return edge2;
    }
    /**
     * Gets the weight.
     * complexity O(1)
     * @return     The weight.
     */
    public double getWeight() {
        return this.weight;
    }
    /**
     * gets the other edge connected.
     * complexity O(1)
     * @param      one   One
     *
     * @return     int value.
     */
    public int other(final int one) {
        if (one == edge1) {
            return this.edge2;
        }
        return this.edge1;
    }
    /**
     * retuns the edge.
     * complexity O(1)
     * @return     int value
     */
    public int either() {
        return this.edge1;
    }
    /**
     * compare two vertices weights.
     * complexity O(1)
     * @param      that  The that
     *
     * @return     int value.
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        }
        if (this.weight > that.weight) {
            return 1;
        }
        return 0;
    }
}

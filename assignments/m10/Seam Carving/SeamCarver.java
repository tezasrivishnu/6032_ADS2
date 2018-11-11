/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**
     * double value.
     */
    private static final double BILLION = 1000000.0;
    /**
     * double value.
     */
    private static final double THOUSAND = 1000.0;
    /**
     * pricture class object.
     */
    private Picture pic;
    // create a seam carver object based on the given picture

    /**
     * Constructs the object.
     *
     * @param      picture  The picture
     */
    public SeamCarver(final Picture picture) {
        this.pic = picture;
    }
    // current picture

    /**
     * returns the picture object.
     * comlexity O(1).
     * @return     the picture object.
     */
    public Picture picture() {
        return pic;
    }
    // width of current picture

    /**
     * returns the width of the picture.
     * complexity O(1)
     * @return     the int value.
     */
    public int width() {
        return pic.width();
    }

    // height of current picture

    /**
     * returns the height of the picture.
     * complexity O(1)
     * @return     the int value.
     */
    public int height() {
        return pic.height();
    }

    // energy of pixel at column x and row y

    /**
     * calculating the energy of a particular pixel.
     * complexity O(1).
     * @param      one   One
     * @param      two   Two
     *
     * @return     double value.
     */
    public double energy(final int one, final int two) {
        if (one == 0 || one == width() - 1
                || two == 0 || two == height() - 1) {
            return THOUSAND;
        }
        double onered = Math.abs(pic.get(one - 1, two).getRed()
                                 - pic.get(one + 1, two).getRed());

        double onegreen = Math.abs(pic.get(one - 1, two).getGreen()
                                   - pic.get(one + 1, two).getGreen());

        double oneblue = Math.abs(pic.get(one - 1, two).getBlue()
                                  - pic.get(one + 1, two).getBlue());
        double twored = Math.abs(pic.get(one, two - 1).getRed()
                                 - pic.get(one, two + 1).getRed());

        double twogreen = Math.abs(pic.get(one, two - 1).getGreen()
                                   - pic.get(one, two + 1).getGreen());

        double twoblue = Math.abs(pic.get(one, two - 1).getBlue()
                         - pic.get(one, two + 1).getBlue());

        double total =  ((onered * onered) + (oneblue * oneblue)
            + (onegreen * onegreen))
                        + ((twored * twored)
                            + (twoblue * twoblue)
                            + (twogreen * twogreen));
        return Math.sqrt(total);
    }

    //sequence of indices for horizontal seam
    // public int[] findHorizontalSeam() {
    //  return new int[0];
    // }
    /**
     * method relaxing the edges.
     * complexity O(1)
     * @param      i       row
     * @param      j       column
     * @param      edgeTo  The edge to
     * @param      distTo  The distance to
     */
    public void relaxVertical(final int i, final int j,
        final int[][] edgeTo,
                              final double[][] distTo) {
        if (distTo[i][j + 1]
            >= distTo[i][j] + energy(i, j + 1)) {
            distTo[i][j + 1]
            = distTo[i][j] + energy(i, j + 1);
            edgeTo[i][j + 1] = i;
        }
        if (i > 0 && distTo[i - 1][j + 1]
            > distTo[i][j] + energy(i - 1, j + 1)) {
            distTo[i - 1][j + 1] = distTo[i][j]
            + energy(i - 1, j + 1);
            edgeTo[i - 1][j + 1] = i;
        }
        if (i < width() - 1 && distTo[i + 1][j + 1]
            > distTo[i + 1][j] + energy(i + 1, j + 1)) {
            distTo[i + 1][j + 1] =
            distTo[i][j] + energy(i + 1, j + 1);
            edgeTo[i + 1][j + 1] = i;
        }
    }


    // sequence of indices for vertical seam

    /**
     * finding the vertical seam of the picture.
     * complexity O(v*e)
     * @return     the seam array.
     */
    public int[] findVerticalSeam() {
        double[][] energy = new double[width()][height()];
        int[] vertexTo = new int[height()];
        double[][] distTo = new double[width()][height()];
        int[][] edgeTo = new int[width()][height()];
        if (width() - 1 == 0 || height() - 1 == 0) {
            return vertexTo;
        }
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                energy[i][j] = energy(i, j);
                distTo[i][j] = BILLION;
                if (j == 0) {
                    distTo[i][0] = THOUSAND;
                    edgeTo[i][0] = i;
                }
            }
        }
        for (int j = 0; j < height() - 1; j++) {
            for (int i = 0; i < width(); i++) {
                relaxVertical(i, j, edgeTo, distTo);
            }
        }
        int min = 0;
        for (int i = 1; i < width() - 1; i++) {
            if (distTo[min][height() - 1]
                > distTo[i][height() - 1]) {
                min = i;
            }
        }
        vertexTo[height() - 1] = min;
        int count = height() - 2;
        while (count >= 0) {
            vertexTo[count] =
            edgeTo[vertexTo[count + 1]][count + 1];
            count--;
        }
        return vertexTo;
    }

    // remove horizontal seam from current picture

    /**
     * Removes a horizontal seam.
     * complexity O(v*e) as we are calling vertical
     * seam method.
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {
        Picture original = pic;
        Picture transpose
            = new Picture(original.height(),
                          original.width());
        for (int j = 0; j < transpose.width(); j++) {
            for (int i = 0; i < transpose.height(); i++) {
                transpose.set(j, i, original.get(i, j));
            }
        }
        this.pic = transpose;
        transpose = null;
        original = null;
        removeVerticalSeam(seam);
        original = pic;
        transpose = new Picture(original.height(),
                                original.width());
        for (int j = 0; j < transpose.width(); j++) {
            for (int i = 0; i < transpose.height(); i++) {
                transpose.set(j, i, original.get(i, j));
            }
        }
        this.pic = transpose;
        transpose = null;
        original = null;
    }

    // remove vertical seam from current picture

    /**
     * Removes a vertical seam.
     * complexity O(v*e)
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {
        Picture original = pic;
        Picture vert = new Picture(original.width() - 1,
            original.height());

        for (int i = 0; i < vert.height(); i++) {
            for (int j = 0; j < seam[i]; j++) {
                vert.set(j, i, original.get(j, i));
            }
            for (int j = seam[i]; j < vert.width(); j++) {
                vert.set(j, i, original.get(j + 1, i));
            }
        }
        this.pic = vert;
    }
    /**
     * find the horizontal seam to remove.
     * complexity O(v*e) as we are calling find vertical
     * seam method.
     * @return     int array.
     */
    public int[] findHorizontalSeam() {
        Picture first = pic;
        Picture transpose = new Picture(first.height(),
            first.width());

        for (int j = 0; j < transpose.width(); j++) {
            for (int i = 0; i < transpose.height(); i++) {
                transpose.set(j, i, first.get(i, j));
            }
        }
        this.pic = transpose;
        int[] arr = findVerticalSeam();
        this.pic = first;

        return arr;
    }
}
//      // int[][] edgeTo = new int[height()][width()];
//      // double[][] distTo = new double[height()][width()];
//      // for (int i = 0; i < width(); i++) {
//      //  for (int j = 0; j < height(); j++) {
//      //      distTo[i][j] = BILLION;
//      //  }
//      // }
//      // for (int i = 0; i < height(); i++) {
//      //  distTo[i][0] = THOUSAND;
//      // }
//      // for (int j = 0; j < width() - 1; j++) {
//      //  for (int i = 0; i < height(); i++) {
//      //      relaxHorizantal(i, j, edgeTo, distTo);
//      //  }
//      // }
//      // double dis = BILLION;
//      // int min = 0;
//      // for (int i = 0; i < height(); i++) {
//      //  if (dis > distTo[i][width() - 1]) {
//      //      dis = distTo[i][width() - 1];
//      //      min = i;
//      //  }
//      // }
//      // int[] arr = new int[width()];
//      // for (int j = width() - 1, i = min; j >= 0; j--) {
//      //  arr[j] = i;
//      //  i -= edgeTo[i][j];
//      // }
//      // return arr;
//      // return new int[0];
//  }
//  // public void relaxHorizantal
//  (int i, int j, int[][] edgeTo,
//  double[][] distTo) {
//  //  int k = j + 1;
//  //  for (int w = -1; w <= 1; w++) {
//  //      int l = i + w;
//  //      if (l < 0 || l >= height()) {
//  //          continue;
//  //      }
//  //      if (w == 0) {
//  //          if (distTo[l][k] >=
//  distTo[i][j]  + energy(k, l)) {
//  //              distTo[l][k] =
//  distTo[i][j]  + energy(k, l);
//  //              edgeTo[l][k] = w;
//  //          }
//  //      }
//  //      if (distTo[l][k] > distTo[i][j]  + energy(k, l)) {
//  //          distTo[l][k] = distTo[i][j]  + energy(k, l);
//  //          edgeTo[l][k] = w;
//  //      }
//  //  }
//  // }
// }

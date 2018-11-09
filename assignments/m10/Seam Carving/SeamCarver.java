/**
 * Class for seam carver.
 */
public class SeamCarver {
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
			return 1000.0;
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

		double total =  ((onered * onered) + (oneblue * oneblue) + (onegreen * onegreen))
		                + ((twored * twored) + (twoblue * twoblue) + (twogreen * twogreen));
		return Math.sqrt(total);
	}

	//sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}
	public void relax(int i, int j, int[][] edgeTo,
	                  double[][] distTo) {
		if (distTo[i][j + 1] >= distTo[i][j] + energy(i, j + 1)) {
			distTo[i][j + 1] = distTo[i][j] + energy(i, j + 1);
			edgeTo[i][j + 1] = i;
		}
		if (i > 0 && distTo[i - 1][j + 1] > distTo[i][j] + energy(i - 1, j + 1)) {
			distTo[i - 1][j + 1] = distTo[i][j] + energy(i - 1, j + 1);
			edgeTo[i - 1][j + 1] = i;
		}
		if (i < width() - 1 && distTo[i + 1][j + 1] > distTo[i + 1][j] + energy(i + 1, j + 1)) {
			distTo[i + 1][j + 1] = distTo[i][j] + energy(i + 1, j + 1);
			edgeTo[i + 1][j + 1] = i;
		}
	}


	// sequence of indices for vertical seam
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
				distTo[i][j] = 10000000.0;
				if (j == 0) {
					distTo[i][0] = 1000.0;
					edgeTo[i][0] = i;
				}
			}
		}
		for (int j = 0; j < height() - 1; j++) {
			for (int i = 0; i < width(); i++) {
				relax(i, j, edgeTo, distTo);
			}
		}
		int min = 0;
		for (int i = 1; i < width() - 1; i++) {
			if (distTo[min][height() - 1] > distTo[i][height() - 1]) {
				min = i;
			}
		}
		vertexTo[height() - 1] = min;
		int count = height() - 2;
		while (count >= 0) {
			vertexTo[count] = edgeTo[vertexTo[count + 1]][count + 1];
			count--;
		}
		return vertexTo;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}
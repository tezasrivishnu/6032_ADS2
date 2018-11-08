
public class SeamCarver {
	Picture pic;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		this.pic = picture;
	}
	// current picture
	public Picture picture() {
		return pic;
	}
	// width of current picture
	public int width() {
		return pic.width();
	}

	// height of current picture
	public int height() {
		return pic.height();
	}

	// energy of pixel at column x and row y
	public double energy(int one, int two) {
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

		return ((onered * onered) + (oneblue * oneblue) + (onegreen * onegreen))
		       + ((twored * twored) + (twoblue * twoblue) + (twogreen * twogreen));
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}
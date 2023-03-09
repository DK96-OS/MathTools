package mathtools.numbers.structs;

/** A pair of primitive 64-bit floating point numbers that are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class DoublePair {

	public double getFirst() {
		return mFirst;
	}

	public double getSecond() {
		return mSecond;
	}

	public void setFirst(final double value) {
		mFirst = value;
	}

	public void setSecond(final double value) {
		mSecond = value;
	}

	private double mFirst;

	private double mSecond;

	public DoublePair(
		final double first,
		final double second
	) {
		mFirst = first;
		mSecond = second;
	}

}
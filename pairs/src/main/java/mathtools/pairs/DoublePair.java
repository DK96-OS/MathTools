package mathtools.pairs;

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

	protected double mFirst;

	protected double mSecond;

	/** Create a Mutable DoublePair.
	 * @param first The first double in the pair.
	 * @param second The second double in the pair.
	 */
	public DoublePair(
		final double first,
		final double second
	) {
		mFirst = first;
		mSecond = second;
	}

	/** Create a Fixed DoublePair instance.
	 * @return A new DoublePairFixed.
	 */
	public DoublePairFixed toFixed() {
		return new DoublePairFixed(mFirst, mSecond);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DoublePairFixed) {
			final DoublePairFixed other = (DoublePairFixed) obj;
			return mFirst == other.first
					&& mSecond == other.second;
		} else if (obj instanceof DoublePair) {
			final DoublePair other = (DoublePair) obj;
			return mFirst == other.mFirst
					&& mSecond == other.mSecond;
		} else
			return false;
	}

	@Override
	public String toString() {
		return "(" + mFirst + ", " + mSecond + ")";
	}

}
package mathtools.pairs;

/** A pair of primitive 8-bit integers that are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class BytePair {

	/** Obtain the first byte in the pair
	 * @return The first byte.
	 */
	public byte getFirst() {
		return mFirst;
	}

	/** Obtain the second byte in the pair
	 * @return The second byte.
	 */
	public byte getSecond() {
		return mSecond;
	}

	public void setFirst(final byte value) {
		mFirst = value;
	}

	public void setSecond(final byte value) {
		mSecond = value;
	}

	protected byte mFirst;

	protected byte mSecond;

	/** Create a Mutable BytePair.
	 * @param first The first byte in the pair.
	 * @param second The second byte in the pair.
	 */
	public BytePair(
		final byte first,
		final byte second
	) {
		mFirst = first;
		mSecond = second;
	}

	/** Create a Fixed BytePair instance.
	 * @return A new BytePairFixed.
	 */
	public BytePairFixed toFixed() {
		return new BytePairFixed(mFirst, mSecond);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BytePairFixed) {
			final BytePairFixed other = (BytePairFixed) obj;
			return mFirst == other.first
					&& mSecond == other.second;
		} else if (obj instanceof BytePair) {
			final BytePair other = (BytePair) obj;
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
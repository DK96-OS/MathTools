package mathtools.pairs;

/** A pair of primitive 64-bit integers that are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class LongPair {

	public long getFirst() {
		return mFirst;
	}

	public long getSecond() {
		return mSecond;
	}

	public void setFirst(final long value) {
		mFirst = value;
	}

	public void setSecond(final long value) {
		mSecond = value;
	}

	protected long mFirst;

	protected long mSecond;

	/** Create a Mutable LongPair.
	 * @param first The first long in the pair.
	 * @param second The second long in the pair.
	 */
	public LongPair(
		final long first,
		final long second
	) {
		mFirst = first;
		mSecond = second;
	}

	/** Create a Fixed LongPair instance.
	 * @return A new LongPairFixed.
	 */
	public LongPairFixed toFixed() {
		return new LongPairFixed(mFirst, mSecond);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LongPairFixed) {
			final LongPairFixed other = (LongPairFixed) obj;
			return mFirst == other.first
					&& mSecond == other.second;
		} else if (obj instanceof LongPair) {
			final LongPair other = (LongPair) obj;
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
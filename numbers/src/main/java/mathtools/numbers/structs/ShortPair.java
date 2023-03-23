package mathtools.numbers.structs;

/** A pair of primitive 16-bit integers that are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class ShortPair {

	public short getFirst() {
		return mFirst;
	}

	public short getSecond() {
		return mSecond;
	}

	public void setFirst(final short value) {
		mFirst = value;
	}

	public void setSecond(final short value) {
		mSecond = value;
	}

	protected short mFirst;

	protected short mSecond;

	public ShortPair(
		final short first,
		final short second
	) {
		mFirst = first;
		mSecond = second;
	}

	/** Create a Fixed ShortPair instance.
	 * @return A new ShortPairFixed.
	 */
	public ShortPairFixed toFixed() {
		return new ShortPairFixed(mFirst, mSecond);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShortPairFixed) {
			final ShortPairFixed other = (ShortPairFixed) obj;
			return mFirst == other.first
					&& mSecond == other.second;
		} else if (obj instanceof ShortPair) {
			final ShortPair other = (ShortPair) obj;
			return mFirst == other.mFirst
					&& mSecond == other.mSecond;
		} else
			return false;
	}

}
package mathtools.numbers.structs;

/** A pair of primitive 8-bit integers that are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class BytePair {

	public byte getFirst() {
		return mFirst;
	}

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

	public BytePair(
		final byte first,
		final byte second
	) {
		mFirst = first;
		mSecond = second;
	}

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

}
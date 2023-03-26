package mathtools.numbers.structs;

/** A pair of primitive 32-bit integers that are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class IntPair {

	public int getFirst() {
		return mFirst;
	}

	public int getSecond() {
		return mSecond;
	}

	public void setFirst(final int value) {
		mFirst = value;
	}

	public void setSecond(final int value) {
		mSecond = value;
	}

	protected int mFirst;

	protected int mSecond;
	
	/** Create a Mutable IntPair.
	 * @param first The first int in the pair.
	 * @param second The second int in the pair.
	 */
	public IntPair(
		final int first,
		final int second
	) {
		mFirst = first;
		mSecond = second;
	}

	/** Create a Fixed IntPair instance.
	 * @return A new IntPairFixed.
	 */
	public IntPairFixed toFixed() {
		return new IntPairFixed(mFirst, mSecond);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntPairFixed) {
			final IntPairFixed other = (IntPairFixed) obj;
			return mFirst == other.first
					&& mSecond == other.second;
		} else if (obj instanceof IntPair) {
			final IntPair other = (IntPair) obj;
			return mFirst == other.mFirst
					&& mSecond == other.mSecond;
		} else
			return false;
	}

}
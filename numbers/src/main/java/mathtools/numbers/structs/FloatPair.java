package mathtools.numbers.structs;

/** A pair of primitive 32-bit floating point numbers that are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class FloatPair {

	public float getFirst() {
		return mFirst;
	}

	public float getSecond() {
		return mSecond;
	}

	public void setFirst(final float value) {
		mFirst = value;
	}

	public void setSecond(final float value) {
		mSecond = value;
	}

	protected float mFirst;

	protected float mSecond;

	public FloatPair(
		final float first,
		final float second
	) {
		mFirst = first;
		mSecond = second;
	}

	/** Create a Fixed FloatPair instance.
	 * @return A new FloatPairFixed.
	 */
	public FloatPairFixed toFixed() {
		return new FloatPairFixed(mFirst, mSecond);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FloatPairFixed) {
			final FloatPairFixed other = (FloatPairFixed) obj;
			return mFirst == other.first
					&& mSecond == other.second;
		} else if (obj instanceof FloatPair) {
			final FloatPair other = (FloatPair) obj;
			return mFirst == other.mFirst
					&& mSecond == other.mSecond;
		} else
			return false;
	}

}
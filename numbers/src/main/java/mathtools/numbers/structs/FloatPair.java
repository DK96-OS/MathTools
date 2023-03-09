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

	private float mFirst;

	private float mSecond;

	public FloatPair(
		final float first,
		final float second
	) {
		mFirst = first;
		mSecond = second;
	}

}
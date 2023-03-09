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

	private short mFirst;

	private short mSecond;

	public ShortPair(
		final short first,
		final short second
	) {
		mFirst = first;
		mSecond = second;
	}

}
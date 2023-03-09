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

	private byte mFirst;

	private byte mSecond;

	public BytePair(
		final byte first,
		final byte second
	) {
		mFirst = first;
		mSecond = second;
	}

}
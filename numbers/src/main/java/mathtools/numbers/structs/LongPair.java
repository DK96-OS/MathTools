package mathtools.numbers.structs;

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

	private long mFirst;

	private long mSecond;

	public LongPair(
		final long first,
		final long second
	) {
		mFirst = first;
		mSecond = second;
	}

}
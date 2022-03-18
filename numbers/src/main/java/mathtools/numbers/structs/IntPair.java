package mathtools.numbers.structs;

/** A pair of primitive integers
 * @author DK96-OS : 2022 */
public class IntPair {

	public int getFirst() { return mFirst; }

	public int getSecond() { return mSecond; }

	public void setFirst(final int value) { mFirst = value; }

	public void setSecond(final int value) { mSecond = value; }

	private int mFirst;
	private int mSecond;

	public IntPair(
		final int first,
		final int second
	) {
		mFirst = first;
		mSecond = second;
	}

}
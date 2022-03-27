package mathtools.numbers.structs;

/** A pair of primitive integers
 * @author DK96-OS : 2022 */
public class BytePair {

	public byte getFirst() { return m1; }

	public byte getSecond() { return m2; }

	public void setFirst(final byte value) { m1 = value; }

	public void setSecond(final byte value) { m2 = value; }

	private byte m1;
	private byte m2;

	public BytePair(
		final byte first,
		final byte second
	) {
		m1 = first;
		m2 = second;
	}

}
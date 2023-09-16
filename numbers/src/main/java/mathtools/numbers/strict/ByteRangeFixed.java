package mathtools.numbers.strict;

/** An implementation of ByteRange where endpoints are fixed.
 * @author DK96-OS : 2022
 */
public class ByteRangeFixed
	implements ByteRange {

	public final byte start;

	public final byte last;

	/** Create a Fixed ByteRangeFixed.
	 *  Note: if arguments are not in ascending order, they will be reversed.
	 * @param start The start value of the range.
	 * @param last The last value in the range.
	 */
	public ByteRangeFixed(
		final byte start,
		final byte last
	) {
		if (start <= last) {
			this.start = start;
			this.last = last;
		} else {
			this.start = last;
			this.last = start;
		}
	}

	@Override
	public byte getStartValue() {
		return start;
	}

	@Override
	public byte getLastValue() {
		return last;
	}

}
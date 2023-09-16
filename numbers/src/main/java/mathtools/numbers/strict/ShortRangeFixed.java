package mathtools.numbers.strict;

/** An implementation of ShortRange where endpoints are fixed.
 * @author DK96-OS : 2022
 */
public class ShortRangeFixed
	implements ShortRange {

	public final short start;

	public final short last;

	/** Create a Fixed ShortRangeFixed.
	 *  Note: if arguments are not in ascending order, they will be reversed.
	 * @param start The start value of the range.
	 * @param last The last value in the range.
	 */
	public ShortRangeFixed(
		final short start,
		final short last
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
	public short getStartValue() {
		return start;
	}

	@Override
	public short getLastValue() {
		return last;
	}

}
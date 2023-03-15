package mathtools.numbers.strict;

/** An implementation of IntRange where endpoints are fixed.
 * @author DK96-OS : 2022
 */
public class LongRangeFixed
    implements LongRange {

    /** The Start value of the Integer Range.
     */
    public final long start;

    /** The Last value in the Integer Range.
     */
    public final long last;

    /** Create a Fixed IntRange.
     *  Note: if arguments are not in ascending order, they will be reversed.
     * @param start The start value of the range.
     * @param last The last value in the range.
     */
    public LongRangeFixed(
        final long start,
        final long last
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
    public long getStartValue() {
        return start;
    }

    @Override
    public long getLastValue() {
        return last;
    }

}
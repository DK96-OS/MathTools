package mathtools.numbers.strict;

/** An implementation of IntRange where endpoints are fixed.
 * @author DK96-OS : 2022
 */
public class IntRangeFixed
    implements IntRange {

    /** The Start value of the Integer Range.
     */
    public final int start;

    /** The Last value in the Integer Range.
     */
    public final int last;

    /** Create a Fixed IntRange.
     *  Note: if arguments are not in ascending order, they will be reversed.
     * @param start The start value of the range.
     * @param last The last value in the range.
     */
    public IntRangeFixed(
        final int start,
        final int last
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
    public int getStartValue() {
        return start;
    }

    @Override
    public int getLastValue() {
        return last;
    }

}
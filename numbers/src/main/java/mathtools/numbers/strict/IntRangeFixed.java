package mathtools.numbers.strict;

/** An implementation of IntRange where endpoints are fixed.
 * @author DK96-OS : 2022
 */
public class IntRangeFixed
    implements IntRange {

    public final int start;

    public final int last;

    /**
     * @param start The start value of the range.
     * @param last The last value in the range.
     */
    public IntRangeFixed(
        final int start,
        final int last
    ) {
        this.start = start;
        this.last = last;
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

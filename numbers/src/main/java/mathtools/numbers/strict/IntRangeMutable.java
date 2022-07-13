package mathtools.numbers.strict;

/** An implementation of IntRange where endpoints are mutable.
 * @author DK96-OS : 2022
 */
public class IntRangeMutable
    implements IntRange {

    private int mStart;

    private int mLast;

    /**
     * @param start The start value of the range.
     * @param last The last value in the range.
     */
    public IntRangeMutable(
        final int start,
        final int last
    ) {
        mStart = start;
        mLast = last;
    }

    @Override
    public int getStartValue() {
        return mStart;
    }

    @Override
    public int getLastValue() {
        return mLast;
    }

}
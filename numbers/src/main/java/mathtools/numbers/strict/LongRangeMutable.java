package mathtools.numbers.strict;

/** An implementation of LongRange where endpoints are mutable.
 * @author DK96-OS : 2022 - 2023
 */
public class LongRangeMutable
    implements LongRange {

    private long mStart;

    private long mLast;

    /** Create an IntRange that has mutable endpoints.
     *  Note: if arguments are not in ascending order, they will be reversed.
     * @param start The start value of the range.
     * @param last The last value in the range.
     */
    public LongRangeMutable(
        final long start,
        final long last
    ) {
        if (start <= last) {
            mStart = start;
            mLast = last;
        } else {
            mStart = last;
            mLast = start;
        }
    }

    @Override
    public long getStartValue() {
        return mStart;
    }

    @Override
    public long getLastValue() {
        return mLast;
    }

    /** Set the Start of the Range.
     * @param newStart The new start value of the range.
     * @return Whether the start value was valid relative to the last value.
     */
    public boolean setStart(
        final long newStart
    ) {
        if (newStart <= mLast) {
            mStart = newStart;
            return true;
        } else
            return false;
    }

    /** Set the Last value of the Range.
     * @param newLast The new last value in the range.
     * @return Whether the last value was valid relative to the start value.
     */
    public boolean setLast(
        final long newLast
    ) {
        if (newLast >= mStart) {
            mLast = newLast;
            return true;
        } else
            return false;
    }

    /** Expand the Range to include the given value.
     * @param value The value to include in the range.
     * @return Whether the Range was modified to include the given value.
     */
    public boolean expandRangeTo(
        final long value
    ) {
        if (value < mStart) {
            mStart = value;
            return true;
        } else if (value > mLast) {
            mLast = value;
            return true;
        } else
            return false;
    }

}
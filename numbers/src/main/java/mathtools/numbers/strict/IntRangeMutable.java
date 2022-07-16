package mathtools.numbers.strict;

/** An implementation of IntRange where endpoints are mutable.
 * @author DK96-OS : 2022
 */
public class IntRangeMutable
    implements IntRange {

    private int mStart;

    private int mLast;

    /** Create an IntRange that has mutable endpoints.
     *  Note: if arguments are not in ascending order, they will be reversed.
     * @param start The start value of the range.
     * @param last The last value in the range.
     */
    public IntRangeMutable(
        final int start,
        final int last
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
    public int getStartValue() {
        return mStart;
    }

    @Override
    public int getLastValue() {
        return mLast;
    }

    /** Set The Start of the Range to a spacific value.
     * @param newStart The new start value of the range.
     * @return Whether the input was valid, and the operation succeeded.
     */
    public boolean setStart(
        final int newStart
    ) {
        if (newStart < mStart) {
            mStart = newStart;
            return true;
        } else if (newStart <= mLast) {
            if (newStart == mStart) return false;
            mStart = newStart;
            return true;
        } else
            return false;
    }

    /** Set The Last value in the Range.
     * @param newLast The new last value in the range.
     * @return Whether the input value was valid, and the range was modified.
     */
    public boolean setLast(
        final int newLast
    ) {
        if (newLast > mLast) {
            mLast = newLast;
            return true;
        } else if (newLast < mLast) {
            if (newLast < mStart) return false;
            mLast = newLast;
            return true;
        } else
            return false;
    }

    /** Expand the Range to incude the given value.
     * @param value The value to inlcude in the range.
     * @return Whether the Range was modified to include the given value.
     */
    public boolean expandRangeTo(
        final int value
    ) {
        return false;
    }

}
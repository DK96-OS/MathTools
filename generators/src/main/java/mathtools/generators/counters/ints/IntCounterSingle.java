package mathtools.generators.counters.ints;

/** Counts the number of times a specific value occurs
 * @author DK96-OS : 2022 */
public class IntCounterSingle implements IntCounterInterface {

    protected final int mValue;

    protected long mCount;

    /** Any integer can be counted
     * @param value The value that will be counted */
    public IntCounterSingle(final int value) {
        mValue = value;
        mCount = 0;
    }

    @Override
    public boolean count(
            final int value
    ) {
        return value == mValue && ++mCount > 0;
    }

    /** Obtain the value that is being counted */
    int getValue() { return mValue; }

    /** Obtain the number of times the value has occurred */
    public long getCount() { return mCount; }

    /** Increase the count of value by a given amount
     * @param value The value to count a set number of times
     * @param count The number to add to the value's count
     * @return True if the operation was successful */
    public boolean countBy(
            final int value,
            final long count
    ) {
        // Only the single value is counted
        if (value == mValue) {
            // Must count by positive number
            if (count < 1) return false;
            // Check for overflow
            // Prevent worst case, where overflow may be non-negative
            if (count == Long.MAX_VALUE) {
                if (mCount == 0) {
                    mCount = Long.MAX_VALUE;
                    return true;
                } else
                    return false;
            }
            final long newCount = count + mCount;
            if (newCount > 0) {
                mCount = newCount;
                return true;
            } else
                return false;
        } else
            return false;
    }

}

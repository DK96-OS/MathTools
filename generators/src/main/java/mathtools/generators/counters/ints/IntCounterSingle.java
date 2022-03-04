package mathtools.generators.counters.ints;

/** Counts the number of times a specific value occurs
 * @author DK96-OS : 2022 */
public class IntCounterSingle implements IntCounterInterface {

    protected final int mValue;

    protected long mCount;

    public IntCounterSingle(int value) {
        mValue = value;
        mCount = 0;
    }

    @Override
    public boolean count(
            int value
    ) {
        return value == mValue && ++mCount > 0;
    }

    /** Obtain the value that is being counted */
    int getValue() { return mValue; }

    /** Obtain the number of times the value has occurred */
    public long getCount() { return mCount; }

}

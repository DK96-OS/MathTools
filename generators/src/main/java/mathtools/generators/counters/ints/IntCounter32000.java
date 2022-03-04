package mathtools.generators.counters.ints;

import com.google.common.collect.ImmutableList;

import java.util.List;

import javax.annotation.Nullable;

/** 32-bit Integer Counter that can count up to 32767
 *  Up to (2^31 - 1) consecutive integer values can be counted together
 * @author DK96-OS : 2022 */
public class IntCounter32000 implements IntCounterInterface {

    /** The lowest value in the range to be counted */
    final int mStartValue;

    /** Array containing counts */
    private final short[] mArray;

    public IntCounter32000(
            int startValue, int endValue
    ) {
        int diff = endValue - startValue;
        if (diff < 0)
            throw new IllegalArgumentException("");
        mStartValue = startValue;
        mArray = new short[diff];
    }

    @Override
    public boolean count(
            int value
    ) {
        int diff = value - mStartValue;
        // Ensure value is within valid range
        if (diff < 0 || diff >= mArray.length)
            return false;
        // Increment, return false if overflow occurs
        return ++mArray[diff] > 0;
    }

    /** Obtain the count for the given value
     * @param value The value to get the count of
     * @return The current count for the value, or null if value out of bounds */
    @Nullable
    public Short getCountOf(
            int value
    ) {
        int diff = value - mStartValue;
        if (diff < 0 || diff >= mArray.length)
            return null;
        return mArray[diff];
    }

    /** Get the Count of all values as a List
     * The zeroth index of the List corresponds to the Range StartValue */
    public List<Integer> toList() {
        ImmutableList.Builder<Integer> b =
                ImmutableList.builderWithExpectedSize(mArray.length);
        for (short s : mArray) b.add((int) s);
        return b.build();
    }

    /** Obtain the Counter Array
     * @return The internal array for this counter */
    short[] getValueArray() { return mArray; }

}
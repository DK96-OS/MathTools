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
        boolean isValid = startValue < endValue;
        if (endValue == Integer.MAX_VALUE) {
            if (startValue < 1) isValid = false;
        } else if (startValue == Integer.MIN_VALUE) {
            if (endValue > -1) isValid = false;
        }
        int diff = endValue - (startValue - 1);
        if (diff < 0) isValid = false;
        if (!isValid) throw new IllegalArgumentException(
                "Value Range too wide: (" + startValue + ", " + endValue + " )"
        );
        mStartValue = startValue;
        mArray = new short[diff];
    }

    /** Obtain the array index of the given value */
    private int indexOf(
            int value
    ) {
        int diff = value - mStartValue;
        // Ensure value is within valid range
        if (diff < 0 || diff >= mArray.length)
            return -1;
        return diff;
    }

    @Override
    public boolean count(
            int value
    ) {
        int diff = indexOf(value);
        // Ensure value is within valid range
        if (diff < 0) return false;
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

    /** Increase the count of a specific value by a given amount
     * @param value The value to count a set number of times
     * @param count The number to add to the value's count
     * @return True if the operation was successful */
    public boolean countBy(
            int value,
            short count
    ) {
        int diff = indexOf(value);
        // Ensure value and number is within valid range
        if (diff < 0 || count < 1)
            return false;
        int sum = ((int) mArray[diff]) + count;
        // return false if overflow occurs
        if (sum > Short.MAX_VALUE)
            return false;
        mArray[diff] = (short) sum;
        return true;
    }

}
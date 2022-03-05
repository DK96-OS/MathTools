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

    /** Valid ranges are positive, and contain up to Integer.MaxValue numbers
     * @param startValue The first value in the range being counted
     * @param endValue The last value in the range being counted */
    public IntCounter32000(
            final int startValue,
            final int endValue
    ) {
        // Range is positive, contains at least one value
        boolean isValid = startValue <= endValue;
        // Range containing Max Value
        if (endValue == Integer.MAX_VALUE) {
            if (startValue < 1) isValid = false;
        // Range containing Min Value
        } else if (startValue == Integer.MIN_VALUE) {
            if (endValue > -1) isValid = false;
        }
        final int diff = endValue - (startValue - 1);
        // Ranges larger than Integer.MaxValue are not supported
        if (diff < 0) isValid = false;
        if (!isValid) throw new IllegalArgumentException(
                "Value Range too wide: (" + startValue + ", " + endValue + " )"
        );
        mStartValue = startValue;
        mArray = new short[diff];
    }

    /** Obtain the array index of the given value */
    private int indexOf(
            final int value
    ) {
        final int diff = value - mStartValue;
        // Ensure value is within valid range
        if (diff < 0 || diff >= mArray.length)
            return -1;
        return diff;
    }

    @Override
    public boolean count(
            final int value
    ) {
        final int index = indexOf(value);
        // Ensure value is within valid range
        if (index < 0) return false;
        // Increment, return false if overflow occurs
        return ++mArray[index] > 0;
    }

    /** Obtain the count for the given value
     * @param value The value to get the count of
     * @return The current count for the value, or null if value out of bounds */
    @Nullable
    public Short getCountOf(
            final int value
    ) {
        final int index = indexOf(value);
        // Ensure valid index
        if (index < 0) return null;
        return mArray[index];
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
            final int value,
            final short count
    ) {
        // Must count by positive number
        if (count < 1) return false;
        // Ensure value index is within valid range
        final int index = indexOf(value);
        if (index < 0) return false;
        //
        final int sum = ((int) mArray[index]) + count;
        // return false if overflow occurs
        if (sum > Short.MAX_VALUE)
            return false;
        mArray[index] = (short) sum;
        return true;
    }

}
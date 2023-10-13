package mathtools.arrays.sum;

import java.math.BigInteger;

/** A BigInteger sum data structure
 *  Tries to add primitive number types where possible
 * @author DK96-OS : 2022 */
public final class BigIntSumBuffer {

    /** The limit for when a long will be added directly to BigInteger */
    public final long longLimit = Long.MAX_VALUE / 2;
    public final long longLimitNegative = Long.MIN_VALUE / 2;

    /** The integer limit is much higher */
    public final long intLimit = Long.MAX_VALUE - Integer.MAX_VALUE;
    public final long intLimitNegative = Long.MIN_VALUE - Integer.MIN_VALUE;

    /** The BigInteger part of the sum */
    private BigInteger mSum = BigInteger.ZERO;

    /** The primitive number type part of the sum */
    private long mBuffer = 0L;

    /** Obtain the current sum
     * @return A BigInteger containing the sum of all numbers added */
    public BigInteger getSum() {
        if (mBuffer != 0L) {
            mSum = mSum.add(BigInteger.valueOf(mBuffer));
            mBuffer = 0L;
        }
        return mSum;
    }

    /** Add a 64-bit Integer to the sum
     * @param number The Long to add to the sum */
    public void add(
            final long number
    ) {
        if (number > 0) {
            if (number < longLimit) {
                mBuffer += number;
                if (mBuffer > longLimit) {
                    mSum = mSum.add(BigInteger.valueOf(mBuffer));
                    mBuffer = 0;
                }
            } else {
                // number is large enough to add directly
                mSum = mSum.add(BigInteger.valueOf(number));
            }
        } else if (number < 0) {
            // If number is above the negative limit
            if (number > longLimitNegative) {
                mBuffer += number;
                // check if limit is exceeded
                if (mBuffer < longLimitNegative) {
                    mSum = mSum.add(BigInteger.valueOf(mBuffer));
                    mBuffer = 0;
                }
            } else
                mSum = mSum.add(BigInteger.valueOf(number));
        }
    }

    /** Add a 32-bit integer to the sum
     * @param number The Integer to add to the sum */
    public void add(
            final int number
    ) {
        if (number > 0) {
            // When safe to add
            if (mBuffer < intLimit) mBuffer += number;
            // check addition before adding to sum
            else {
                final long checkedAdd = mBuffer + number;
                if (checkedAdd > mBuffer) { // success
                    mSum = mSum.add(BigInteger.valueOf(checkedAdd));
                    mBuffer = 0;
                } else {    // overflow
                    mSum = mSum.add(BigInteger.valueOf(mBuffer));
                    mBuffer = number;
                }
            }
        } else if (number < 0) {
            // When safe to add
            if (mBuffer > intLimitNegative) mBuffer += number;
            // check addition before adding to sum
            else {
                final long checkedAdd = mBuffer + number;
                if (checkedAdd < mBuffer) { // success
                    mSum = mSum.add(BigInteger.valueOf(checkedAdd));
                    mBuffer = 0;
                } else {    // overflow
                    mSum = mSum.add(BigInteger.valueOf(mBuffer));
                    mBuffer = number;
                }
            }
        }
    }

    /** Set the long buffer value - for testing purposes
     * @param number The number to set the buffer value to */
    void setLongBuffer(
            final long number
    ) {
        mBuffer = number;
    }

}
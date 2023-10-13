package mathtools.arrays.sum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/** Testing [BigIntSumBuffer]
 * @author DK96-OS : 2022 */
public final class BigIntSumBufferTest {

    private static final BigInteger bigIntMax =
            BigInteger.valueOf(Integer.MAX_VALUE);

    private static final BigInteger bigLongMax =
            BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger bigLongMin =
            BigInteger.valueOf(Long.MIN_VALUE);

    private BigIntSumBuffer mInstance;

    @BeforeEach
    void testSetup() { mInstance = new BigIntSumBuffer(); }

    @Test
    void testLimits() {
        System.out.printf(
                "Long Limits: %d < l < %d\n",
                mInstance.longLimitNegative,
                mInstance.longLimit
        );
        System.out.printf(
                "Integer Limits: %d < l < %d",
                mInstance.intLimitNegative,
                mInstance.intLimit
        );
    }

    @Test
    void testNegativeLong() {
        mInstance.add(-2L);
        assertEquals(
            BigInteger.valueOf(-2),
            mInstance.getSum()
        );
        mInstance.add(Long.MIN_VALUE + 2L);
        assertEquals(
            BigInteger.valueOf(Long.MIN_VALUE),
            mInstance.getSum()
        );
    }

    @Test
    void testNegativeInt() {
        mInstance.add(-2);
        assertEquals(
            BigInteger.valueOf(-2),
            mInstance.getSum()
        );
        mInstance.add(Integer.MIN_VALUE + 2);
        assertEquals(
            BigInteger.valueOf(Integer.MIN_VALUE),
            mInstance.getSum()
        );
    }

    @Test
    void testLargeInt() {
        final int largeInt = Integer.MAX_VALUE - 5;
        final BigInteger largeIntBig = BigInteger.valueOf(largeInt);
        //
        for (int i = 0; i < 10; i++) mInstance.add(largeInt);
        //
        assertEquals(
                largeIntBig.multiply(BigInteger.TEN),
                mInstance.getSum()
        );
    }

    @Test
    void testLongsAndIntegers() {
        final long belowLimit = mInstance.longLimit - 1;
        final BigInteger belowLimitBig = BigInteger.valueOf(belowLimit);
        //
        mInstance.add(belowLimit);
        mInstance.add(belowLimit);
        mInstance.add(Integer.MAX_VALUE);
        //
        assertEquals(
            belowLimitBig.multiply(
                BigInteger.valueOf(2)
            ).add(bigIntMax),
            mInstance.getSum()
        );
    }

    @Test
    void testIntegerNearLimit() {
        mInstance.setLongBuffer(
                mInstance.intLimit);
        mInstance.add(
                Integer.MAX_VALUE);
        assertEquals(
                bigLongMax, mInstance.getSum());
    }

    @Test
    void testNegativeIntegerNearLimit() {
        mInstance.setLongBuffer(
                mInstance.intLimitNegative);
        mInstance.add(
                Integer.MIN_VALUE);
        assertEquals(
                bigLongMin, mInstance.getSum());
    }

    @Test
    void testIntegerOverflow() {
        mInstance.setLongBuffer(
                mInstance.intLimit + 1);
        mInstance.add(
                Integer.MAX_VALUE);
        assertEquals(
                bigLongMax.add(BigInteger.ONE), mInstance.getSum());
    }

    @Test
    void testNegativeIntegerOverflow() {
        mInstance.setLongBuffer(
                mInstance.intLimitNegative - 1);
        mInstance.add(
                Integer.MIN_VALUE);
        assertEquals(
                bigLongMin.subtract(BigInteger.ONE), mInstance.getSum());
    }

    @Test
    void testAdd_ZeroInputs_DoesNothing() {
        mInstance.add(0L);
        mInstance.add(0);
        assertEquals(
            BigInteger.ZERO, mInstance.getSum()
        );
    }

}
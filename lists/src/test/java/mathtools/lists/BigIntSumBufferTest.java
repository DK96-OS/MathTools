package mathtools.lists;

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

    private BigIntSumBuffer mBuffer;

    @BeforeEach
    void testSetup() { mBuffer = new BigIntSumBuffer(); }

    @Test
    void testLimits() {
        System.out.printf(
                "Long Limits: %d < l < %d\n",
                BigIntSumBuffer.longLimitNegative,
                BigIntSumBuffer.longLimit
        );
        System.out.printf(
                "Integer Limits: %d < l < %d",
                BigIntSumBuffer.intLimitNegative,
                BigIntSumBuffer.intLimit
        );
    }

    @Test
    void testNegativeLong() {
        mBuffer.add(-2L);
        assertEquals(
                BigInteger.TWO.negate(), mBuffer.getSum());
        //
        mBuffer.add(Long.MIN_VALUE + 2L);
        assertEquals(
                BigInteger.valueOf(Long.MIN_VALUE), mBuffer.getSum());
    }

    @Test
    void testNegativeInt() {
        mBuffer.add(-2);
        assertEquals(
                BigInteger.TWO.negate(), mBuffer.getSum());
        //
        mBuffer.add(Integer.MIN_VALUE + 2);
        assertEquals(
                BigInteger.valueOf(Integer.MIN_VALUE), mBuffer.getSum());
    }

    @Test
    void testLargeInt() {
        final int largeInt = Integer.MAX_VALUE - 5;
        final BigInteger largeIntBig = BigInteger.valueOf(largeInt);
        //
        for (int i = 0; i < 10; i++) mBuffer.add(largeInt);
        //
        assertEquals(
                largeIntBig.multiply(BigInteger.TEN),
                mBuffer.getSum()
        );
    }

    @Test
    void testLongsAndIntegers() {
        final long belowLimit = BigIntSumBuffer.longLimit - 1;
        final BigInteger belowLimitBig = BigInteger.valueOf(belowLimit);
        //
        mBuffer.add(belowLimit);
        mBuffer.add(belowLimit);
        mBuffer.add(Integer.MAX_VALUE);
        //
        assertEquals(
                belowLimitBig.multiply(BigInteger.TWO).add(bigIntMax),
                mBuffer.getSum()
        );
    }

    @Test
    void testIntegerNearLimit() {
        mBuffer.setLongBuffer(
                BigIntSumBuffer.intLimit);
        mBuffer.add(
                Integer.MAX_VALUE);
        assertEquals(
                bigLongMax, mBuffer.getSum());
    }

    @Test
    void testNegativeIntegerNearLimit() {
        mBuffer.setLongBuffer(
                BigIntSumBuffer.intLimitNegative);
        mBuffer.add(
                Integer.MIN_VALUE);
        assertEquals(
                bigLongMin, mBuffer.getSum());
    }

    @Test
    void testIntegerOverflow() {
        mBuffer.setLongBuffer(
                BigIntSumBuffer.intLimit + 1);
        mBuffer.add(
                Integer.MAX_VALUE);
        assertEquals(
                bigLongMax.add(BigInteger.ONE), mBuffer.getSum());
    }

    @Test
    void testNegativeIntegerOverflow() {
        mBuffer.setLongBuffer(
                BigIntSumBuffer.intLimitNegative - 1);
        mBuffer.add(
                Integer.MIN_VALUE);
        assertEquals(
                bigLongMin.subtract(BigInteger.ONE), mBuffer.getSum());
    }

}
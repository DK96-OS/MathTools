package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/** Testing [BigIntSumBuffer]
 * @author DK96-OS : 2022 */
public class BigIntSumBufferTest {

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
        System.out.println("Hi");
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
        mBuffer.add(largeInt);
        assertEquals(
                largeIntBig, mBuffer.getSum());
        //
        mBuffer.add(largeInt);
        assertEquals(
                largeIntBig.multiply(BigInteger.TWO), mBuffer.getSum());
    }

    @Test
    void testAddingNumbers() {
        final long belowLimit = (Long.MAX_VALUE - 3) / 2;
        final BigInteger belowLimitBig = BigInteger.valueOf(belowLimit);
        //
        mBuffer.add(belowLimit);
        assertEquals(
                belowLimitBig, mBuffer.getSum());
        //
        mBuffer.add(Integer.MAX_VALUE);
        assertEquals(
                belowLimitBig.add(BigInteger.valueOf(Integer.MAX_VALUE)),
                mBuffer.getSum()
        );
    }

}
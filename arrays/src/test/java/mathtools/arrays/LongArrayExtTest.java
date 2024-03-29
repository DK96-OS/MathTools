package mathtools.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/** Testing the LongArray extension functions
 * @author DK96-OS : 2022 */
public final class LongArrayExtTest {

    private static final BigInteger longMax =
        BigInteger.valueOf(Long.MAX_VALUE);

    private static final BigInteger eight = BigInteger.valueOf(8);

    private static long[] newArray(
        final int size,
        final long startVal
    ) {
        if (size < 0 || size > 200_000_000)
            throw new IllegalArgumentException();
        final long[] array = new long[size];
        Arrays.fill(array, startVal);
        return array;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testToListBaseCases(
        final int arraySize
    ) {
        final List<Long> list = LongArrayExt.toList(
            newArray(arraySize, 4)
        );
        for (int i = 0; i < arraySize; i++)
            assertEquals(4, list.get(i));
    }

    @Test
    void testSumOfIntegerSizedValues() {
        final long[] array = newArray(8, Integer.MAX_VALUE);
        assertEquals(
            BigInteger.valueOf(8L * Integer.MAX_VALUE),
            LongArrayExt.sum(array)
        );
    }

    @Test
    void testSum_ZeroLengthArray_ReturnsZero() {
        assertEquals(
            BigInteger.ZERO,
            LongArrayExt.sum(new long[0])
        );
    }

    @Test
    void testSum_SingleLengthArray_ReturnsValue() {
        long value = Long.MAX_VALUE;
        assertEquals(
            BigInteger.valueOf(value),
            LongArrayExt.sum(new long[]{ value })
        );
    }

    @Test
    void testSumOfMaxValues() {
        final long[] array = newArray(8, Long.MAX_VALUE);
        assertEquals(
            longMax.multiply(eight),
            LongArrayExt.sum(array)
        );
    }

    @Test
    void testSumOfHalfMaxValues() {
        final long halfMax = (Long.MAX_VALUE - 3) / 2;
        final long[] array = newArray(8, halfMax);
        assertEquals(
            eight.multiply(BigInteger.valueOf(halfMax)),
            LongArrayExt.sum(array)
        );
    }

    @Test
    void testSumOfQuarterMaxValues() {
        final long quarterMax = ((Long.MAX_VALUE / 2) + 1) / 2;
        final long[] array = newArray(8, quarterMax);
        assertEquals(
            eight.multiply(BigInteger.valueOf(quarterMax)),
            LongArrayExt.sum(array)
        );
    }

    @Test
    void testSumOfNegativeValues() {
        final long quarterMax = -((Long.MAX_VALUE / 2) + 1) / 2;
        final long[] array = newArray(8, quarterMax);
        assertEquals(
            eight.multiply(BigInteger.valueOf(quarterMax)),
            LongArrayExt.sum(array)
        );
    }

    @Test
    void testAllNonZero_ReturnsTrue() {
        assertTrue(
            LongArrayExt.allNonZero(new long[]{1})
        );
        assertTrue(
            LongArrayExt.allNonZero(
                new long[]{-1, 7, 1, 4, 20}
            )
        );
    }

    @Test
    void testAllNonZero_EmptyArray_ReturnsTrue() {
        assertTrue(
            LongArrayExt.allNonZero(new long[0])
        );
    }

    @Test
    void testAllNonZero_AllocatedArray_ReturnsFalse() {
        assertFalse(
            LongArrayExt.allNonZero(new long[5])
        );
        assertFalse(
            LongArrayExt.allNonZero(new long[20])
        );
    }

    @Test
    void testAllNonZero_ArrayWithAnyZero_ReturnsFalse() {
        assertFalse(
            LongArrayExt.allNonZero(new long[] {20, 0, 1})
        );
        assertFalse(
            LongArrayExt.allNonZero(new long[] {202, 1240, 411, 0})
        );
    }


    @Test
    void testGetMinAndMax_EmptyArray_ReturnsNull() {
        assertNull(
                LongArrayExt.getMinAndMax(new long[0])
        );
    }

    @Test
    void testGetMinAndMax_SingleElementArray_ReturnsElement() {
        final long value = 5;
        long[] result = LongArrayExt.getMinAndMax(
                new long[]{ value }
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(value, result[0]);
        assertEquals(value, result[1]);
    }

    @Test
    void testGetMinAndMax_AscendingArray() {
        final long[] ascendingArray = new long[256];
        for (short i = 0; i < ascendingArray.length; ++i) {
            ascendingArray[i] = Byte.MIN_VALUE + i;
        }
        long[] result = LongArrayExt.getMinAndMax(
                ascendingArray
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(Byte.MIN_VALUE, result[0]);
        assertEquals(Byte.MAX_VALUE, result[1]);
    }

    @Test
    void testGetMinAndMax_DescendingArray() {
        final long[] descendingArray = new long[256];
        for (int i = 0; i < descendingArray.length; ++i) {
            descendingArray[i] = Byte.MAX_VALUE - i;
        }
        long[] result = LongArrayExt.getMinAndMax(
                descendingArray
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(Byte.MIN_VALUE, result[0]);
        assertEquals(Byte.MAX_VALUE, result[1]);
    }

    @Test
    void testGetMinAndMax_PseudorandomValues() {
        var input = new long[]{
            -453, 893, 510, -500, 1000, 200, 100, 0, 0
        };
        var result = LongArrayExt.getMinAndMax(input);
        assertNotNull(result);
        assertEquals(
            -500L,
            result[0]
        );
        assertEquals(
            1000,
            result[1]
        );
    }

}
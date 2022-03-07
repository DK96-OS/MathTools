package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
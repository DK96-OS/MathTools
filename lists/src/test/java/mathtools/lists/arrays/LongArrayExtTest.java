package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/** Testing the LongArray extension functions
 * @author DK96-OS : 2022 */
public final class LongArrayExtTest {

    private static final long ZERO = 0;

    private static long[] newArray(
            final int size,
            final long startVal
    ) {
        if (size < 0 || size > 200_000_000)
            throw new IllegalArgumentException();
        final long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = startVal + i;
        }
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
            assertEquals(
                    i + 4, list.get(i));
    }

}

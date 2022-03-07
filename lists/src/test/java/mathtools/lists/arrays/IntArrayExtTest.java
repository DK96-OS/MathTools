package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/** Testing the IntegerArray extension functions
 * @author DK96-OS : 2022 */
public final class IntArrayExtTest {

    private static int[] newArray(
            final int size,
            final int startVal
    ) {
        if (size < 0 || size > 200_000_000)
            throw new IllegalArgumentException();
        final int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = startVal + i;
        return array;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testToListBaseCases(
            final int arraySize
    ) {
        final List<Integer> list = IntArrayExt.toList(
                newArray(arraySize, 4)
        );
        for (int i = 0; i < arraySize; i++) {
            assertEquals(
                    (int) (i + 4), list.get(i));
        }
    }

    @Test
    void testSum() {
        final int[] array = newArray(10, Short.MAX_VALUE);
        assertEquals(
                10 * Short.MAX_VALUE, IntArrayExt.sum(array));
    }

}
package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/** Testing the ShortArray extension functions
 * @author DK96-OS : 2022 */
public final class ShortArrayExtTest {

    private static short[] newArray(
            final int size,
            final short startVal
    ) {
        if (size < 0 || size > 200_000_000)
            throw new IllegalArgumentException();
        final short[] array = new short[size];
        for (int i = 0; i < size; i++) {
            array[i] = (short) (startVal + i);
        }
        return array;
    }

    @Test
    void testSum() {
        short[] array = new short[50];
        assertEquals(
            0L, ShortArrayExt.sum(array)
        );
        //
        for (int i = 50; i < 100; i++) {
            array[i - 50] = (short) (i * 40);
        }
        // { 50 .. 99 } 400
        // = { 50 * 50 + 1 .. 49 } 400
        // = { 2500 + 50 * 24 + 25 } 400
        // = { 3725 } 400
        // = { 12000 + 2800 + 80 + 20 } * 100
        // = 149000
        assertEquals(
                149000, ShortArrayExt.sum(array)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testToListBaseCases(
            final int arraySize
    ) {
        final List<Short> list = ShortArrayExt.toList(
                newArray(arraySize, (short) 4)
        );
        for (int i = 0; i < arraySize; i++) {
            assertEquals(
                    (short) (i + 4), list.get(i));
        }
    }

}
package mathtools.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testAllNonZero_ReturnsTrue() {
        assertTrue(
            ShortArrayExt.allNonZero(new short[]{1})
        );
        assertTrue(
            ShortArrayExt.allNonZero(
                new short[]{-1, 7, 1, 4, 20}
            )
        );
    }

    @Test
    void testAllNonZero_EmptyArray_ReturnsTrue() {
        assertTrue(
            ShortArrayExt.allNonZero(new short[0])
        );
    }

    @Test
    void testAllNonZero_AllocatedArray_ReturnsFalse() {
        assertFalse(
            ShortArrayExt.allNonZero(new short[5])
        );
        assertFalse(
            ShortArrayExt.allNonZero(new short[20])
        );
    }

    @Test
    void testAllNonZero_ArrayWithAnyZero_ReturnsFalse() {
        assertFalse(
            ShortArrayExt.allNonZero(
                new short[] {20, 0, 1}
            )
        );
        assertFalse(
            ShortArrayExt.allNonZero(
                new short[] {102, 124, 41, 0}
            )
        );
    }

    @Test
    void testGetMinAndMax_EmptyArray_ReturnsNull() {
        assertNull(
                ShortArrayExt.getMinAndMax(new short[0])
        );
    }

    @Test
    void testGetMinAndMax_SingleElementArray_ReturnsElement() {
        final short value = 5;
        short[] result = ShortArrayExt.getMinAndMax(
                new short[]{ value }
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(value, result[0]);
        assertEquals(value, result[1]);
    }

    @Test
    void testGetMinAndMax_AscendingArray() {
        final short[] ascendingArray = new short[256];
        for (short i = 0; i < ascendingArray.length; ++i) {
            ascendingArray[i] = (short) (Byte.MIN_VALUE + i);
        }
        short[] result = ShortArrayExt.getMinAndMax(
                ascendingArray
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(Byte.MIN_VALUE, result[0]);
        assertEquals(Byte.MAX_VALUE, result[1]);
    }

    @Test
    void testGetMinAndMax_DescendingArray() {
        final short[] descendingArray = new short[256];
        for (short i = 0; i < descendingArray.length; ++i) {
            descendingArray[i] = (short) (Byte.MAX_VALUE - i);
        }
        short[] result = ShortArrayExt.getMinAndMax(
                descendingArray
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(Byte.MIN_VALUE, result[0]);
        assertEquals(Byte.MAX_VALUE, result[1]);
    }

}
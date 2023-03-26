package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
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
        Arrays.fill(array, startVal);
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
        for (int i = 0; i < arraySize; i++)
            assertEquals(4, list.get(i));
    }

    @Test
    void testSum() {
        final int[] array = newArray(10, Short.MAX_VALUE);
        assertEquals(
                10 * Short.MAX_VALUE, IntArrayExt.sum(array));
    }

    @Test
    void testAllNonZero_ReturnsTrue() {
        assertTrue(
            IntArrayExt.allNonZero(new int[]{1})
        );
        assertTrue(
            IntArrayExt.allNonZero(
                new int[]{-1, 7, 1, 4, 20}
            )
        );
    }

    @Test
    void testAllNonZero_EmptyArray_ReturnsTrue() {
        assertTrue(
            IntArrayExt.allNonZero(new int[0])
        );
    }

    @Test
    void testAllNonZero_AllocatedArray_ReturnsFalse() {
        assertFalse(
            IntArrayExt.allNonZero(new int[5])
        );
        assertFalse(
            IntArrayExt.allNonZero(new int[20])
        );
    }

    @Test
    void testAllNonZero_ArrayWithAnyZero_ReturnsFalse() {
        assertFalse(
            IntArrayExt.allNonZero(new int[] {20, 0, 1})
        );
        assertFalse(
            IntArrayExt.allNonZero(new int[] {202, 1240, 411, 0})
        );
    }

    @Test
    void testGetMinAndMax_EmptyArray_ReturnsNull() {
        assertNull(
                IntArrayExt.getMinAndMax(new int[0])
        );
    }

    @Test
    void testGetMinAndMax_SingleElementArray_ReturnsElement() {
        final int value = 5;
        int[] result = IntArrayExt.getMinAndMax(
            new int[]{ value }
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(value, result[0]);
        assertEquals(value, result[1]);
    }

    @Test
    void testGetMinAndMax_AscendingArray() {
        final int[] ascendingArray = new int[256];
        for (short i = 0; i < ascendingArray.length; ++i) {
            ascendingArray[i] = (int) (Byte.MIN_VALUE + i);
        }
        int[] result = IntArrayExt.getMinAndMax(
            ascendingArray
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(Byte.MIN_VALUE, result[0]);
        assertEquals(Byte.MAX_VALUE, result[1]);
    }

    @Test
    void testGetMinAndMax_DescendingArray() {
        final int[] descendingArray = new int[256];
        for (int i = 0; i < descendingArray.length; ++i) {
            descendingArray[i] = Byte.MAX_VALUE - i;
        }
        int[] result = IntArrayExt.getMinAndMax(
            descendingArray
        );
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(Byte.MIN_VALUE, result[0]);
        assertEquals(Byte.MAX_VALUE, result[1]);
    }
    
}
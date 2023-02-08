package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

/** Testing the ByteArray extension functions
 * @author DK96-OS : 2022 */
public final class ByteArrayExtTest {

    @Nonnull
    static byte[] newArray(
        final int size,
        final byte initValue
    ) {
        if (0 > size || 200_000_000 < size)
            throw new IllegalArgumentException();
        final byte[] array = new byte[size];
        // Set the initial values
        Arrays.fill(array, initValue);
        return array;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testToListBaseCases(
        final int arraySize
    ) {
        final List<Byte> list = ByteArrayExt.toList(
            newArray(arraySize, (byte) 4)
        );
        for (int i = 0; i < arraySize; i++)
            assertEquals((byte) 4, list.get(i));
    }

    @Test
    void testSum() {
        final byte[] array = newArray(8, (byte) 7);
        assertEquals(
            56, ByteArrayExt.sum(array));
    }

    @Test
    void testSumNegative() {
        final byte[] array = newArray(8, (byte) -7);
        assertEquals(
            -56, ByteArrayExt.sum(array));
    }

    @Test
    void testSumCancellation() {
        final byte[] array = newArray(10, (byte) 64);
        Arrays.fill(array, 4, 9, (byte) -64);
        assertEquals(
            0, ByteArrayExt.sum(array));
    }

    @Test
    void testClear() {
        final byte[] input = new byte[]{5, 6, 8};
        ByteArrayExt.clear(input);
        assertEquals(
            0L, ByteArrayExt.sum(input)
        );
    }

    @Test
    void testAllNonZero_ReturnsTrue() {
        assertTrue(
            ByteArrayExt.allNonZero(new byte[]{1})
        );
        assertTrue(
            ByteArrayExt.allNonZero(
                new byte[]{-1, 7, 1, 4, 20}
            )
        );
    }

    @Test
    void testAllNonZero_EmptyArray_ReturnsTrue() {
        assertTrue(
            ByteArrayExt.allNonZero(new byte[0])
        );
    }

    @Test
    void testAllNonZero_AllocatedArray_ReturnsFalse() {
        assertFalse(
            ByteArrayExt.allNonZero(new byte[5])
        );
        assertFalse(
            ByteArrayExt.allNonZero(new byte[20])
        );
    }

    @Test
    void testAllNonZero_ArrayWithAnyZero_ReturnsFalse() {
        assertFalse(
            ByteArrayExt.allNonZero(
                new byte[] {20, 0, 1}
            )
        );
        assertFalse(
            ByteArrayExt.allNonZero(
                new byte[] {102, 124, 41, 0}
            )
        );
    }
    
}
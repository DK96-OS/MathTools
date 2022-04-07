package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
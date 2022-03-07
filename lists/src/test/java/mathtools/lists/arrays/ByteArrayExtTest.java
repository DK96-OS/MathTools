package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/** Testing the ByteArray extension functions
 * @author DK96-OS : 2022 */
public final class ByteArrayExtTest {

    private static final byte ZERO = 0;

    private static byte[] newArray(
            final int size,
            final byte startVal
    ) {
        if (size < 0 || size > 200_000_000)
            throw new IllegalArgumentException();
        final byte[] array = new byte[size];
        for (int i = 0; i < size; i++) {
            array[i] = (byte) (startVal + i);
        }
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
        for (int i = 0; i < arraySize; i++) {
            assertEquals(
                    (byte) (i + 4), list.get(i));
        }
    }

    @Test
    void testSum() {
        final byte[] array = newArray(10, (byte) 7);
        assertEquals(
                70, ByteArrayExt.sum(array));
    }

}
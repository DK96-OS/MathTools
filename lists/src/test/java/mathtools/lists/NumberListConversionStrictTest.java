package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static mathtools.lists.NumberListConversionStrict.toByteStrict;
import static mathtools.lists.NumberListConversionStrict.toShortStrict;

import org.junit.jupiter.api.Test;

import java.util.List;

/** Testing the Strict NumberList Conversion functions
 * @author DK96-OS : 2022 */
public final class NumberListConversionStrictTest {

    private final List<Number> shortList = NumberListTestResources.shortList;
    private final List<Number> intList = NumberListTestResources.intList;

    @Test
    void testByteIgnoreInvalid() {
        List<Byte> result = toByteStrict(
                shortList, true
        );
        assertEquals(1, result.size());
        assertEquals((byte) 100, result.get(0));
        // Convert the Int List
        result = toByteStrict(
                intList, true
        );
        assertEquals(1, result.size());
        assertEquals((byte) 100, result.get(0));
    }

    @Test
    void testByteThrowsInvalid() {
        assertThrows(NumberFormatException.class, () ->
                toByteStrict(shortList, false));
        assertThrows(NumberFormatException.class, () ->
                toByteStrict(intList, false));
    }

    @Test
    void testShortIgnoreInvalid() {
        List<Short> result = toShortStrict(
                shortList, true
        );
        assertEquals(2, result.size());
        assertEquals((short) 100, result.get(0));
        // Convert the Int List
        result = toShortStrict(
                intList, true
        );
        assertEquals(2, result.size());
        assertEquals((short) 100, result.get(0));
    }

    @Test
    void testShortThrowsInvalid() {
        assertDoesNotThrow(
                () -> toShortStrict(shortList, false));
        assertThrows(NumberFormatException.class, () ->
                toShortStrict(intList, false));
    }

}
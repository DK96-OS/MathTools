package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static mathtools.lists.NumberListConversionStrict.toByteStrict;
import static mathtools.lists.NumberListConversionStrict.toIntStrict;
import static mathtools.lists.NumberListConversionStrict.toLongStrict;
import static mathtools.lists.NumberListConversionStrict.toShortStrict;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/** Testing the Strict NumberList Conversion functions
 * @author DK96-OS : 2022 */
public final class NumberListConversionStrictTest {

    private final List<Number> shortList = NumberListTestResources.shortList;
    private final List<Number> intList = NumberListTestResources.intList;
    private final List<Number> longList = NumberListTestResources.longList;

    @Test
    void testEmptyLists() {
        final List<Number> emptyList = Collections.emptyList();
        assertEquals(
            0, toByteStrict(emptyList, false).size());
        assertEquals(
            0, toShortStrict(emptyList, false).size());
        assertEquals(
            0, toIntStrict(emptyList, false).size());
        assertEquals(
            0, toLongStrict(emptyList, false).size());
    }

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
        assertEquals(
            2, result.size());
        assertEquals(
            (short) 100, result.get(0));
        // Convert the Int List
        result = toShortStrict(
            intList, true
        );
        assertEquals(
            2, result.size());
        assertEquals(
            (short) 100, result.get(0));
    }

    @Test
    void testShortThrowsInvalid() {
        assertDoesNotThrow(
            () -> toShortStrict(shortList, false));
        //
        assertThrows(NumberFormatException.class,
            () -> toShortStrict(intList, false));
    }

    @Test
    void testIntIgnoreInvalid() {
        List<Integer> result = toIntStrict(
            intList, true
        );
        assertEquals(
            3, result.size());
        assertEquals(
            (int) 100, result.get(0));
        // Convert the Long List
        result = toIntStrict(
            longList, true
        );
        assertEquals(
            3, result.size());
        assertEquals(
            (int) 100, result.get(0));
    }

    @Test
    void testIntThrowsInvalid() {
        assertDoesNotThrow(
            () -> toIntStrict(shortList, false));
        assertDoesNotThrow(
            () -> toIntStrict(shortList, false));
        //
        assertThrows(NumberFormatException.class,
            () -> toIntStrict(longList, false));
    }

    @Test
    void testLongIgnoreInvalid() {
        List<Long> result = toLongStrict(
            intList, true
        );
        assertEquals(
            3, result.size());
        assertEquals(
            100L, result.get(0));
        //
        result = toLongStrict(
            longList, true
        );
        assertEquals(
            4, result.size());
        assertEquals(
            100L, result.get(0));
    }

    @Test
    void testLongThrowsInvalid() {
        // Only floats and doubles will throw for long
        assertDoesNotThrow(
            () -> toLongStrict(List.of(12f), false));
        assertDoesNotThrow(
            () -> toLongStrict(List.of(12.0), false));
        //
        assertThrows(IllegalArgumentException.class,
            () -> toLongStrict(List.of(12.3f), false));
        assertThrows(IllegalArgumentException.class,
            () -> toLongStrict(List.of(12.3), false));
    }

}
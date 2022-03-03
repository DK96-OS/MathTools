package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static mathtools.lists.NumberListConversionStrict.toByteStrict;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/** Testing the Strict NumberList Conversion functions
 * @author DK96-OS : 2022 */
public final class NumberListConversionStrictTest {

    private static final List<Number> shortList = new ArrayList<>();
    private static final List<Number> intList = new ArrayList<>();

    static {
        shortList.add((short) 100);
        shortList.add((short) Short.MAX_VALUE);
        //
        intList.add((int) 100);
        intList.add((int) Short.MAX_VALUE);
        intList.add((int) Integer.MAX_VALUE);
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

}

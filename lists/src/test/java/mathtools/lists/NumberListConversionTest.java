package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/** Testing NumberList Conversion
 * @author DK96-OS : 2022 */
public final class NumberListConversionTest {

    /**  */
    @Test
    void testShortToByte() {
        ArrayList<Number> shortList = new ArrayList<>();
        shortList.add((short) 100);
        shortList.add((short) 200);
        shortList.add((short) 400);
        List<Byte> result = NumberListConversion.toByte(shortList);
        assertEquals((byte) 100, result.get(0));
        assertEquals((byte) (200 - 256), result.get(1));
        assertEquals((byte) (400 - 256), result.get(2));
    }

    @Test
    void testFloatToByte() {
        ArrayList<Number> floatList = new ArrayList<>();
        floatList.add(0.23f);
        floatList.add(10.2f);
        floatList.add(200.3f);
        List<Byte> result = NumberListConversion.toByte(floatList);
        assertEquals((byte) 0, result.get(0));
        assertEquals((byte) 10, result.get(1));
        assertEquals((byte) (200 - 256), result.get(2));
    }

}

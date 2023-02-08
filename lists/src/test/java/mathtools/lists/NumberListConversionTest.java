package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/** Testing NumberList Conversion
 * @author DK96-OS : 2022 */
public final class NumberListConversionTest {

    @Test
    public void testToDouble() {
        List<Number> input = NumberListTestResources.intList;
        List<Double> result = NumberListConversion.toDouble(input);
        assertEquals(
            input.size(), result.size()
        );
        for (int index = 0;
            index < input.size();
            index++
        ) assertEquals(
            input.get(index).doubleValue(),
            result.get(index)
        );
    }

    @Test
    public void testToFloat() {
        List<Number> input = NumberListTestResources.intList;
        List<Float> result = NumberListConversion.toFloat(input);
        assertEquals(
            input.size(), result.size()
        );
        for (int index = 0;
            index < input.size();
            index++
        ) assertEquals(
            input.get(index).floatValue(),
            result.get(index)
        );
    }

    @Test
    public void testToLong() {
        List<Number> input = NumberListTestResources.intList;
        List<Long> result = NumberListConversion.toLong(input);
        assertEquals(
            input.size(), result.size()
        );
        for (int index = 0;
            index < input.size();
            index++
        ) assertEquals(
            input.get(index).longValue(),
            result.get(index)
        );
    }

    @Test
    public void testToInt() {
        List<Number> input = NumberListTestResources.shortList;
        List<Integer> result = NumberListConversion.toInt(input);
        assertEquals(
            input.size(), result.size()
        );
        for (int index = 0;
             index < input.size();
             index++
        ) assertEquals(
            input.get(index).intValue(),
            result.get(index)
        );
    }

    @Test
    public void testToShort_InputIntegers_ReliesOnShortValue() {
        List<Number> input = NumberListTestResources.intList;
        List<Short> result = NumberListConversion.toShort(input);
        assertEquals(
            input.size(), result.size()
        );
        for (int index = 0;
             index < result.size();
             index++
        ) assertEquals(
            input.get(index).shortValue(),
            result.get(index)
        );
    }

    /**  */
    @Test
    void testToByte_InputShorts() {
        ArrayList<Number> input = new ArrayList<>();
        input.add((short) 100);
        input.add((short) 200);
        input.add((short) 400);
        List<Byte> result = NumberListConversion.toByte(input);
        assertEquals(
            (byte) 100, result.get(0)
        );
        assertEquals(
            (byte) (200 - 256), result.get(1)
        );
        assertEquals(
            (byte) (400 - 256), result.get(2)
        );
    }

    @Test
    void testToByte_InputFloats() {
        ArrayList<Number> input = new ArrayList<>();
        input.add(0.23f);
        input.add(10.2f);
        input.add(200.3f);
        List<Byte> result = NumberListConversion.toByte(input);
        assertEquals(
            (byte) 0, result.get(0)
        );
        assertEquals(
            (byte) 10, result.get(1)
        );
        assertEquals(
            (byte) (200 - 256), result.get(2)
        );
    }

}
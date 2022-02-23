package mathtools.lists.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Testing the ShortArray extension functions
 * @author DK96-OS : 2022 */
public class ShortArrayExtTest {

    private static final byte ZERO = 0;

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

}
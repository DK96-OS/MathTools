package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/** Testing the [IntCounter32000]
 * @author DK96-OS : 2022 */
public class IntCounter32000Test {

    private IntCounter32000 mCounter;

    @AfterEach
    void testCleanup() { mCounter = null; }

    @Test
    void test0To10() {
        mCounter = new IntCounter32000(0, 10);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++)
                assertTrue(mCounter.count(i));
        }
        short[] result = mCounter.getValueArray();
        for (int i = 0; i < 11; i++)
            assertEquals(10, result[i]);
    }

    @Test
    void testInvalidArgs() {
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(20, 19);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(20, 20);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(Integer.MIN_VALUE, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mCounter = new IntCounter32000(Integer.MIN_VALUE, Integer.MAX_VALUE);
        });
    }

}

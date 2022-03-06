package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/** Testing [IntCounter127]
 * @author DK96-OS : 2022 */
public final class IntCounter127Test {

    private IntCounter127 mCounter;

    @AfterEach
    void testCleanup() { mCounter = null; }

    @Test
    void test0To9() {
        mCounter = new IntCounter127(0, 9);
        for (int i = 1; i < 10; i++) {
            assertTrue(mCounter.countBy(i, (byte) i));
        }
        final byte[] result = mCounter.getValueArray();
        for (int i = 0; i < 10; i++) {
            assertEquals(i, result[i]);
        }
        final List<Integer> list = mCounter.toList();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void testInvalidConstructorArgs() {
        // Reversed Range
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounter127(20, 19));
        // Range Starts at Min, contains more than Max numbers
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounter127(Integer.MIN_VALUE, -1));
        // Range Ends at Max, contains more than Max numbers
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounter127(0, Integer.MAX_VALUE));
        // Range contains all Integer values
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounter127(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void testValidConstructorMaxValue() {
        final int start = Integer.MAX_VALUE - (IntCounter127.MAX_RANGE_SIZE - 1);
        mCounter = new IntCounter127(start, Integer.MAX_VALUE);
        assertTrue(
                mCounter.count(Integer.MAX_VALUE));
        assertTrue(
                mCounter.count(start));
        assertFalse(
                mCounter.count(0));
        assertFalse(
                mCounter.count(start - 1));
    }

    @Test
    void testInvalidConstructorLargeValues() {
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounter127(Integer.MIN_VALUE, -2));
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounter127(1, Integer.MAX_VALUE));
    }

    @Test
    void testCountBy() {
        mCounter = new IntCounter127(1, 10);
        assertTrue(
                mCounter.countBy(1, (byte) 100));
        assertTrue(
                mCounter.countBy(1, (byte) 27));
        assertEquals(
                (byte) 127, mCounter.getCountOf(1));
        // Now all count operations should fail
        assertFalse(
                mCounter.countBy(1, (byte) 1));
        assertFalse(
                mCounter.countBy(1, (byte) 1));
    }

    @Test
    void testCountByInvalidArgs() {
        mCounter = new IntCounter127(1, 10);
        // Value not in range
        assertFalse(
                mCounter.countBy(0, (byte) 1));
        // count must be non-zero positive number
        assertFalse(
                mCounter.countBy(1, (byte) 0));
        assertFalse(
                mCounter.countBy(1, (byte) -2));
    }

    @Test
    void testGetCountOfInvalidArgs() {
        mCounter = new IntCounter127(1, 10);
        assertNull(
                mCounter.getCountOf(0));
        assertNull(
                mCounter.getCountOf(20));
    }

    @Test
    void testCountOverflow() {
        mCounter = new IntCounter127(1, 8);
        assertTrue(
                mCounter.countBy(4, Byte.MAX_VALUE));
        assertFalse(
                mCounter.count(4));
        assertEquals(
                Byte.MAX_VALUE, mCounter.getCountOf(4));
    }

}
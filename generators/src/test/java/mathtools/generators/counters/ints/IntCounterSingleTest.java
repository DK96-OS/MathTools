package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [IntCounterSingle]
 * @author DK96-OS : 2022 */
public final class IntCounterSingleTest {

    private IntCounterSingle mCounter;

    @BeforeEach
    void testSetup() {
        mCounter = new IntCounterSingle(99);
    }

    @AfterEach
    void testCleanup() { mCounter = null; }

    @Test
    void testConstructor() {
        assertEquals(
            99, mCounter.getValue());
        assertEquals(
            0, mCounter.getCount());
    }

    @Test
    void testCount() {
        assertTrue(
            mCounter.count(99));
        assertTrue(
            mCounter.count(99));
        //
        assertFalse(
            mCounter.count(98));
        assertFalse(
            mCounter.count(-99));
        //
        assertEquals(
            2, mCounter.getCount());
        assertEquals(
            99, mCounter.getValue());
    }

    @Test
    void testCountOverflow() {
        assertTrue(
            mCounter.countBy(99, Long.MAX_VALUE));
        assertEquals(
            Long.MAX_VALUE, mCounter.getCount());
        //
        assertFalse(
            mCounter.count(99));
        //
        assertEquals(
            Long.MAX_VALUE, mCounter.getCount());
    }

    @Test
    void testCountBy() {
        assertTrue(
            mCounter.countBy(99, 2));
        assertEquals(
            2, mCounter.getCount());
        //
        assertTrue(
            mCounter.countBy(99, 300));
        assertEquals(
            302, mCounter.getCount());
        // Does not count values other than 99
        assertFalse(
            mCounter.countBy(88, 10));
    }

    @Test
    void testCountByOverflow() {
        // Go to Long.MaxValue
        assertTrue(
            mCounter.countBy(99, Long.MAX_VALUE));
        assertEquals(
            Long.MAX_VALUE, mCounter.getCount());
        // Any more count operations will fail
        assertFalse(
            mCounter.countBy(99, 1));
        assertFalse(
            mCounter.countBy(99, 100));
        assertFalse(
            mCounter.countBy(99, Long.MAX_VALUE));
        // Count stays at Long.MaxValue
        assertEquals(
            Long.MAX_VALUE, mCounter.getCount());
    }

    @Test
    void testCountByInvalidArgs() {
        // Does not count zero
        assertFalse(
            mCounter.countBy(99, 0));
        assertEquals(
            0, mCounter.getCount());
        // Does not count negative values
        assertFalse(
            mCounter.countBy(99, Long.MIN_VALUE));
        assertEquals(
            0, mCounter.getCount());
    }

    @Test
    void testClear() {
        assertTrue(
            mCounter.countBy(99, 77));
        assertEquals(
            77, mCounter.getCount());
        mCounter.clear();
        assertEquals(
            0, mCounter.getCount());
    }

}
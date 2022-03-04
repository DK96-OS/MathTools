package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/** Testing [IntCounterSingle]
 * @author DK96-OS : 2022 */
public final class IntCounterSingleTest {

    private IntCounterSingle mCounter;

    @AfterEach
    void testCleanup() { mCounter = null; }

    @Test
    void testConstructor() {
        mCounter = new IntCounterSingle(99);
        assertEquals(
                99, mCounter.getValue());
        assertEquals(
                0, mCounter.getCount());
    }

    @Test
    void testCount() {
        mCounter = new IntCounterSingle(99);
        assertTrue(mCounter.count(99));
        assertTrue(mCounter.count(99));
        //
        assertFalse(mCounter.count(98));
        assertFalse(mCounter.count(-99));
        //
        assertEquals(
                2, mCounter.getCount());
        assertEquals(
                99, mCounter.getValue());
    }

}

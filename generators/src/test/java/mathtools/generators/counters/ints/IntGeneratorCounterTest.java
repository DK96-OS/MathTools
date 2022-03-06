package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;

import mathtools.generators.elements.ints.IntElementInterface;
import mathtools.generators.elements.ints.IntRangeElement;

/** Testing [IntGeneratorCounter]
 * @author DK96-OS : 2022 */
public final class IntGeneratorCounterTest {

    private IntCounter mCounter;
    private IntElementInterface mGenerator;
    private IntGeneratorCounter mGC;

    @Nonnull
    private IntGeneratorCounter newGC() {
        return new IntGeneratorCounter(mGenerator, mCounter);
    }

    @AfterEach
    void testCleanup() {
        mCounter = null;
        mGenerator = null;
        mGC = null;
    }

    @Test
    void testMismatchedCounter() {
        mCounter = new IntCounter32000(10, 20);
        mGenerator = new IntRangeElement(1, 1);
        mGC = newGC();
        assertFalse(
                mGC.countGeneratedValues(1));
        assertEquals(
                1, mGC.getRejectedValue());
    }

    @Test
    void testSingleValuedCounter() {
        mCounter = new IntCounterSingle(10);
        mGenerator = new IntRangeElement(10, 10);
        mGC = newGC();
        assertTrue(
                mGC.countGeneratedValues(100));
        assertEquals(
                (long) 100,
                ((IntCounterSingle) mCounter).getCount()
        );
    }

    @Test
    void testTwoValuedCounter() {
        mCounter = new IntCounter32000(10, 11);
        mGenerator = new IntRangeElement(10, 11);
        mGC = newGC();
        assertTrue(
                mGC.countGeneratedValues(100));
        final IntCounter32000 counter = (IntCounter32000) mCounter;
        final Short tenCount = counter.getCountOf(10);
        final Short elevenCount = counter.getCountOf(11);
        assertNotNull(tenCount);
        assertNotNull(elevenCount);
        assertEquals(100, tenCount + elevenCount);
        assert 0 < tenCount;
        assert 0 < elevenCount;
    }

}
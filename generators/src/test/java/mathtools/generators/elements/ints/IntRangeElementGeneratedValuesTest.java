package mathtools.generators.elements.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import mathtools.generators.counters.ints.IntCounter32000;
import mathtools.generators.counters.ints.IntGeneratorCounter;

/** Measuring the Generated Values of [IntRangeElement]
 * @author DK96-OS : 2022 */
public final class IntRangeElementGeneratedValuesTest {

    private IntElementInterface generator;
    private IntCounter32000 counter;
    private IntGeneratorCounter runner;

    @AfterEach
    void testCleanup() {
        counter = null;
        generator = null;
    }

    @Test
    void testPositive16() {
        counter = new IntCounter32000(1, 16);
        generator = new IntRangeElement(1, 16);
        runner = new IntGeneratorCounter(generator, counter);
        assertTrue(
                runner.countGeneratedValues(1600));
        final List<Integer> results = counter.toList();
        assertEquals(
                16, results.size());
        assertTrue(
                isNonEmpty(results));
    }

    @Test
    void testNegative16() {
        counter = new IntCounter32000(-16, -1);
        generator = new IntRangeElement(-16, -1);
        runner = new IntGeneratorCounter(generator, counter);
        assertTrue(
                runner.countGeneratedValues(1600));
        final List<Integer> results = counter.toList();
        assertEquals(
                16, results.size());
        assertTrue(
                isNonEmpty(results));
    }

    @Test
    void testMaxValueRange() {
        final int start = Integer.MAX_VALUE - 15;
        final int end = Integer.MAX_VALUE;
        counter = new IntCounter32000(start, end);
        generator = new IntRangeElement(start, end);
        IntGeneratorCounter runner = new IntGeneratorCounter(
                generator, counter
        );
        assertTrue(
                runner.countGeneratedValues(1600));
        final List<Integer> results = counter.toList();
        assertEquals(
                16, results.size());
        assertTrue(
                isNonEmpty(results));
    }

    private boolean isNonEmpty(
            List<Integer> list
    ) {
        for (int i = 0; i < list.size(); i++) {
            if (0 == list.get(i)) return false;
        }
        return true;
    }

}

package mathtools.generators.elements.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.generators.elements.ints.IntRangeElement.calculateBound;

import org.junit.jupiter.api.Test;

/** Testing the IntRange Element CalculateBound static method
 * @author DK96-OS : 2022 */
public final class IntRangeElementCalculateBoundTest {

    private static final int MIN = Integer.MIN_VALUE;
    private static final int MAX = Integer.MAX_VALUE;

    @Test
    void testBaseCaseZero() {
        // It doesn't matter exactly which number is returned,
        // [0, 1] are reserved for special cases
        int bound = calculateBound(20, 20);
        assert bound == 0 || bound == 1;
        bound = calculateBound(-20, -20);
        assert bound == 0 || bound == 1;
    }

    @Test
    void testBaseCaseOne() {
        // For ranges with 2 possible values,
        // [2] is also a valid bound
        // but random boolean is likely better than relying on bound of 2.
        int bound = calculateBound(20, 21);
        assert bound == 0 || bound == 1 || bound == 2;
        bound = calculateBound(-21, -20);
        assert bound == 0 || bound == 1 || bound == 2;
    }

    @Test
    void testPositiveBounds() {
        assertEquals(31, calculateBound(20, 50));
        // When Int.MaxValue is at the end of the range
        assertEquals(MAX - 9, calculateBound(10, MAX));
        // When Int.MinValue is at the start of the range
        assertEquals(-(MIN + 9), calculateBound(MIN, -10));
        assertEquals(-(MIN + 1), calculateBound(MIN, -2));
    }

    @Test
    void testReversedArgs() {
        // Base Cases
        int bound = calculateBound(21, 20);
        assert bound == 0 || bound == 1 || bound == 2;
        bound = calculateBound(-20, -21);
        assert bound == 0 || bound == 1 || bound == 2;
        // Positive Bounds
        assertEquals(31, calculateBound(50, 20));
        assertEquals(MAX - 9, calculateBound(MAX, 10));
        assertEquals(-(MIN + 9), calculateBound(-10, MIN));
    }

    @Test
    void testMaxPositiveBound() {
        // The Highest Positive Bound
        assertEquals(MAX, calculateBound(1, MAX));
        // Highest Positive Bound, without Int.MaxValue at end of range
        assertEquals(MAX, calculateBound(0, MAX - 1));
        // Shift range by 200
        assertEquals(MAX, calculateBound(-200, MAX - 201));
    }

    @Test
    void testFullIntegerRange() {
        assertEquals(0, calculateBound(MIN, MAX));
    }

    @Test
    void testNegativeBounds() {
        // Negative bound is used for ranges larger than Int.MaxValue
        assertEquals(
            (int) (MAX + 1L + 2L * MIN),
            calculateBound(0, MAX)
        );
        assertEquals(
            (int) (MAX + 11L + 2L * MIN),
            calculateBound(-10, MAX)
        );
        assertEquals(
            (int) (MAX + 1001L + 2L * MIN),
            calculateBound(-1000, MAX)
        );
        assertEquals(
            (int) (MAX + 1001L - 20L + 2L * MIN),
            calculateBound(-1000, MAX - 20)
        );
        assertEquals(
            (int) (2 * (MAX - 1L) + 2L * MIN),
            calculateBound(MIN + 2, MAX - 2)
        );
    }

}
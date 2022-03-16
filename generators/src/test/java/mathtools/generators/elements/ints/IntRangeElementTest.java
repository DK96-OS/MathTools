package mathtools.generators.elements.ints;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/** Testing the IntRange Element class
 * @author DK96-OS : 2022 */
public final class IntRangeElementTest {

    private IntRangeElement elem;
    private static final int MIN = Integer.MIN_VALUE;
    private static final int MAX = Integer.MAX_VALUE;

    @AfterEach
    void testCleanup() {
        elem = null;
    }

    @Test
    void testConstructorRangeReversed() {
        // The constructor will switch the range endpoints
        elem = new IntRangeElement(50, 10);
        assertEquals(10, elem.getStart());
        assertEquals(50, elem.getEnd());
        assertEquals(41, elem.getBound());
        // The alternate constructor with Random inject
        elem = new IntRangeElement(50, 10, new Random(5));
        assertEquals(10, elem.getStart());
        assertEquals(50, elem.getEnd());
        assertEquals(41, elem.getBound());
    }

    @Test
    void testConstructorMaxRange() {
        elem = new IntRangeElement(MIN, MAX);
        assertEquals(MIN, elem.getStart());
        assertEquals(MAX, elem.getEnd());
    }

    @Test
    void testConstructorNegativeBound() {
        elem = new IntRangeElement(0, MAX);
        assertEquals(0, elem.getStart());
        assertEquals(MAX, elem.getEnd());
        assertEquals(
            (int) (MAX + 1L + 2L * MIN),
            elem.getBound()
        );
    }

    @Test
    void testSetRangeReversed() {
        elem = new IntRangeElement(0, 1);
        assertFalse(elem.setRange(20, -10));
        assertFalse(elem.setRange(-20, -30));
        //
        assertFalse(elem.setRange(MAX, MIN));
        assertEquals(0, elem.getStart());
        assertEquals(1, elem.getEnd());
        assertEquals(2, elem.getBound());
    }

    @Test
    void testSetRangeSameValues() {
        elem = new IntRangeElement(0, 1);
        assertFalse(elem.setRange(0, 1));
        //
        assertTrue(elem.setRange(50, 50));
        assertFalse(elem.setRange(50, 50));
    }

    @Test
    void testSetRangeAcceptableValues() {
        elem = new IntRangeElement(0, 1);
        assertTrue(elem.setRange(0, 2000));
        assertEquals(0, elem.getStart());
        assertEquals(2000, elem.getEnd());
        assertEquals(2001, elem.getBound());
        //
        assertTrue(elem.setRange(70, 2000));
        assertEquals(70, elem.getStart());
        assertEquals(2000, elem.getEnd());
        assertEquals(1 + 2000 - 70, elem.getBound());
        //
        assertTrue(elem.setRange(-700, 200_000));
        assertEquals(-700, elem.getStart());
        assertEquals(200_000, elem.getEnd());
        assertEquals(1 + 200_000 + 700, elem.getBound());
    }

    @Test
    void testGenerateLargeNegative() {
        int endPoint = MIN + 4;
        elem = new IntRangeElement(MIN, endPoint);
        assertEquals(5, elem.getBound());
        // All valid values must appear
        boolean[] hasAppeared = new boolean[5];
        for (int i = 0; i < 100; i++) {
            int value = elem.generate();
            assertTrue(value <= endPoint);
            int index = value - MIN;
            hasAppeared[index] = true;
        }
        assertArrayEquals(trueArray(5), hasAppeared);
    }

    @Test
    void testGenerateLargePositive() {
        int startPoint = MAX - 4;
        elem = new IntRangeElement(startPoint, MAX);
        assertEquals(5, elem.getBound());
        // All valid values must appear
        boolean[] hasAppeared = new boolean[5];
        for (int i = 0; i < 100; i++) {
            int value = elem.generate();
            assertTrue(startPoint <= value);
            int index = MAX - value;
            hasAppeared[index] = true;
        }
        assertArrayEquals(trueArray(5), hasAppeared);
    }

    private boolean[] trueArray(int size) {
        boolean[] array = new boolean[size];
        for (int i = 0; i < size; i++)
            array[i] = true;
        return array;
    }

    @Test
    void testBoundPropertyBase() {
        elem = new IntRangeElement(0, 1);
        assertEquals(2, elem.getBound());
        //
        assertTrue(elem.setRange(30, 31));
        assertEquals(2, elem.getBound());
        //
        assertTrue(elem.setRange(30, 30));
        assertEquals(0, elem.getBound());
    }

    @Test
    void testBoundPropertyNegativeValues() {
        elem = new IntRangeElement(0, MAX);
        assertEquals(
            (int) (MAX + 1L + 2L * MIN),
            elem.getBound()
        );
        elem = new IntRangeElement(MIN, 0);
        assertEquals(
            (int) (MAX + 2L + 2L * MIN),
            elem.getBound()
        );
        elem = new IntRangeElement(MIN, -1);
        assertEquals(
            (int) (MAX + 1L + 2L * MIN),
            elem.getBound()
        );
    }

}
package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.lists.NumberComparison.isEquivalent;

import org.junit.jupiter.api.Test;

/** Testing the NumberComparison functions
 * @author DK96-OS : 2022 */
public class NumberComparisonShortTest {

    @Test
    void testWholeNumbersTrue() {
        assertTrue(
            isEquivalent((byte) 20, (short) 20));
        assertTrue(
            isEquivalent((short) 20, (short) 20));
        assertTrue(
            isEquivalent((int) 20, (short) 20));
        assertTrue(
            isEquivalent((long) 20, (short) 20));
    }

    @Test
    void testWholeNumbersFalse() {
        assertFalse(
            isEquivalent((short) 33, (short) 44));
        assertFalse(
            isEquivalent((short) -55, (short) 55));
        //
        assertFalse(
            isEquivalent((byte) 11, (short) 55));
        assertFalse(
            isEquivalent((int) 11, (short) 55));
        assertFalse(
            isEquivalent((long) 11, (short) 55));
        //
        assertFalse(
            isEquivalent((long) 327688, (short) 32767));
        assertFalse(
            isEquivalent((long) 327688, (short) 32767));
    }

    @Test
    void testDecimalTrue() {
        assertTrue(
            isEquivalent(24f, (short) 24));
        assertTrue(
            isEquivalent(24.0, (short) 24));
        assertTrue(
            isEquivalent(2888f, (short) 2888));
        assertTrue(
            isEquivalent(2888.0, (short) 2888));
        //
        assertTrue(
            isEquivalent(-2888f, (short) -2888));
        assertTrue(
            isEquivalent(-2888.0, (short) -2888));
    }

    @Test
    void testDecimalFalse() {
        assertFalse(
            isEquivalent(25f, (short) 33));
        assertFalse(
            isEquivalent(25.0, (short) 33));
        assertFalse(
            isEquivalent(0.1f, (short) 0));
        assertFalse(
            isEquivalent(0.1, (short) 0));
        //
        assertFalse(
            isEquivalent(-10.1f, (short) -10));
        assertFalse(
            isEquivalent(-10.1, (short) -10));
        assertFalse(
            isEquivalent(-10.001f, (short) -10));
        assertFalse(
            isEquivalent(-10.001, (short) -10));
        //
        assertFalse(
            isEquivalent(327670f, (short) 32767));
        assertFalse(
            isEquivalent(327670.0, (short) 32767));
        assertFalse(
            isEquivalent(-327670f, (short) -32767));
        assertFalse(
            isEquivalent(-327670.0, (short) -32767));
        //
        assertFalse(
            isEquivalent(32767.001f, (short) 32767));
        assertFalse(
            isEquivalent(32767.001, (short) 32767));
    }

}
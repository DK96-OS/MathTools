package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.lists.NumberComparison.isEquivalent;

import org.junit.jupiter.api.Test;

/** Testing the NumberComparison functions
 * @author DK96-OS : 2022 */
public class NumberComparisonTest {

    @Test
    void testByteEquivalenceTrue() {
        assertTrue(isEquivalent((byte) 100, (byte) 100));
        assertTrue(isEquivalent((byte) -100, (byte) -100));
        //
        assertTrue(isEquivalent((short) 100, (byte) 100));
        assertTrue(isEquivalent((short) -100, (byte) -100));
        //
        assertTrue(isEquivalent((int) -100, (byte) -100));
        assertTrue(isEquivalent((long) -100, (byte) -100));
    }

    @Test
    void testByteEquivalenceFalse() {
        assertFalse(isEquivalent((byte) 33, (byte) 44));
        assertFalse(isEquivalent((short) 200, (byte) 200));
        assertFalse(isEquivalent((int) 200, (byte) 200));
        assertFalse(isEquivalent((long) 200, (byte) 200));
        //
        assertFalse(isEquivalent((float) 200, (byte) 200));
        assertFalse(isEquivalent((double) 200, (byte) 200));
    }

    @Test
    void testByteEquivalenceFloatTrue() {
        assertTrue(isEquivalent(0.0f, (byte) 0));
        assertTrue(isEquivalent(0.0, (byte) 0));
        assertTrue(isEquivalent(20.0f, (byte) 20));
        assertTrue(isEquivalent(20.0, (byte) 20));
        assertTrue(isEquivalent(120.0f, (byte) 120));
        assertTrue(isEquivalent(120.0, (byte) 120));
        //
        assertTrue(isEquivalent(-120.0f, (byte) -120));
        assertTrue(isEquivalent(-120.0, (byte) -120));
        assertTrue(isEquivalent(-127.0f, (byte) -127));
        assertTrue(isEquivalent(-127.0, (byte) -127));
    }

    @Test
    void testByteEquivalenceFloatFalse() {
        assertFalse(isEquivalent(0.1f, (byte) 0));
        assertFalse(isEquivalent(0.1, (byte) 0));
        assertFalse(isEquivalent(1.0001f, (byte) 1));
        assertFalse(isEquivalent(1.0001, (byte) 1));
        //
        assertFalse(isEquivalent(-1.0001f, (byte) -1));
        assertFalse(isEquivalent(-1.0001, (byte) -1));
        assertFalse(isEquivalent(-0.0001f, (byte) -0));
        assertFalse(isEquivalent(-0.0001, (byte) -0));
        //
        assertFalse(isEquivalent(-127.0001f, (byte) -127));
        assertFalse(isEquivalent(-127.0001, (byte) -127));
    }

    @Test
    void testShortEquivalenceTrue() {
        assertTrue(isEquivalent((byte) 20, (short) 20));
        assertTrue(isEquivalent((short) 20, (short) 20));
        assertTrue(isEquivalent((int) 20, (short) 20));
        assertTrue(isEquivalent((long) 20, (short) 20));
        //
        assertTrue(isEquivalent(20f, (short) 20));
        assertTrue(isEquivalent(20.0, (short) 20));
    }

    @Test
    void testShortEquivalenceFalse() {
        assertFalse(isEquivalent((short) 33, (short) 44));
        assertFalse(isEquivalent((short) -55, (short) 55));
        //
        assertFalse(isEquivalent((byte) 11, (short) 55));
        assertFalse(isEquivalent((int) 11, (short) 55));
        assertFalse(isEquivalent((long) 11, (short) 55));
        //
        assertFalse(isEquivalent((long) 327688, (short) 32767));
        assertFalse(isEquivalent((long) 327688, (short) 32767));
    }

    @Test
    void testShortEquivalenceFloatTrue() {
        assertTrue(isEquivalent(24f, (short) 24));
        assertTrue(isEquivalent(24.0, (short) 24));
        assertTrue(isEquivalent(2888f, (short) 2888));
        assertTrue(isEquivalent(2888.0, (short) 2888));
        //
        assertTrue(isEquivalent(-2888f, (short) -2888));
        assertTrue(isEquivalent(-2888.0, (short) -2888));
    }

    @Test
    void testShortEquivalenceFloatFalse() {
        assertFalse(isEquivalent(25f, (short) 33));
        assertFalse(isEquivalent(25.0, (short) 33));
        assertFalse(isEquivalent(0.1f, (short) 0));
        assertFalse(isEquivalent(0.1, (short) 0));
        //
        assertFalse(isEquivalent(-10.1f, (short) -10));
        assertFalse(isEquivalent(-10.1, (short) -10));
        assertFalse(isEquivalent(-10.001f, (short) -10));
        assertFalse(isEquivalent(-10.001, (short) -10));
        //
        assertFalse(isEquivalent(327670f, (short) 32767));
        assertFalse(isEquivalent(327670.0, (short) 32767));
        assertFalse(isEquivalent(-327670f, (short) -32767));
        assertFalse(isEquivalent(-327670.0, (short) -32767));
        //
        assertFalse(isEquivalent(32767.001f, (short) 32767));
        assertFalse(isEquivalent(32767.001, (short) 32767));
    }

}

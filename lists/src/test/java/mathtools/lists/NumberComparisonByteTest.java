package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.lists.NumberComparison.isEquivalent;

import org.junit.jupiter.api.Test;

/** Testing NumberComparison functions of Byte type
 * @author DK96-OS : 2022 */
public final class NumberComparisonByteTest {

	@Test
	void testWholeNumbersTrue() {
		assertTrue(
			isEquivalent((byte) 100, (byte) 100));
		assertTrue(
			isEquivalent((byte) -100, (byte) -100));
		//
		assertTrue(
			isEquivalent((short) 100, (byte) 100));
		assertTrue(
			isEquivalent((short) -100, (byte) -100));
		//
		assertTrue(
			isEquivalent((int) -100, (byte) -100));
		assertTrue(
			isEquivalent((long) -100, (byte) -100));
	}

	@Test
	void testWholeNumbersFalse() {
		assertFalse(
			isEquivalent((byte) 33, (byte) 44));
		assertFalse(
			isEquivalent((short) 200, (byte) 200));
		assertFalse(
			isEquivalent((int) 200, (byte) 200));
		assertFalse(
			isEquivalent((long) 200, (byte) 200));
		//
		assertFalse(
			isEquivalent((float) 200, (byte) 200));
		assertFalse(
			isEquivalent((double) 200, (byte) 200));
	}

	@Test
	void testDecimalTrue() {
		assertTrue(
			isEquivalent(0.0f, (byte) 0));
		assertTrue(
			isEquivalent(0.0, (byte) 0));
		assertTrue(
			isEquivalent(20.0f, (byte) 20));
		assertTrue(
			isEquivalent(20.0, (byte) 20));
		assertTrue(
			isEquivalent(120.0f, (byte) 120));
		assertTrue(
			isEquivalent(120.0, (byte) 120));
		//
		assertTrue(
			isEquivalent(-120.0f, (byte) -120));
		assertTrue(
			isEquivalent(-120.0, (byte) -120));
		assertTrue(
			isEquivalent(-127.0f, (byte) -127));
		assertTrue(
			isEquivalent(-127.0, (byte) -127));
	}

	@Test
	void testDecimalFalse() {
		assertFalse(
			isEquivalent(0.1f, (byte) 0));
		assertFalse(
			isEquivalent(0.1, (byte) 0));
		assertFalse(
			isEquivalent(1.0001f, (byte) 1));
		assertFalse(
			isEquivalent(1.0001, (byte) 1));
		//
		assertFalse(
			isEquivalent(-1.0001f, (byte) -1));
		assertFalse(
			isEquivalent(-1.0001, (byte) -1));
		assertFalse(
			isEquivalent(-0.0001f, (byte) -0));
		assertFalse(
			isEquivalent(-0.0001, (byte) -0));
		//
		assertFalse(
			isEquivalent(-127.0001f, (byte) -127));
		assertFalse(
			isEquivalent(-127.0001, (byte) -127));
	}

}
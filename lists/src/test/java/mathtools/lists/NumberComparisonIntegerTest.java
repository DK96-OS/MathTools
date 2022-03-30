package mathtools.lists;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.lists.NumberComparison.isEquivalent;

import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;

/** Testing NumberComparison functions of Byte type
 * @author DK96-OS : 2022 */
public final class NumberComparisonIntegerTest {

	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	/** Compares all of the whole number types against the int primitive
	 * @param expectation The expected result to be returned from all types
	 * @param number The number that will provide all of the typed values
	 * @param primitive The integer primitive to use in the tests */
	private static void compareWholeTypes(
		final boolean expectation,
		@Nonnull final Number number,
		final int primitive
	) throws IllegalStateException {
		// Check the long type first
		final boolean result = isEquivalent(
			number.longValue(), primitive
		);
		// Check if any of the other comparisons are different
		if (
			result != isEquivalent(number.intValue(), primitive)
				|| result != isEquivalent(number.shortValue(), primitive)
				|| result != isEquivalent(number.byteValue(), primitive)
			//
		) throw new IllegalStateException(
			"Equivalence is inconsistent for comparison:" + number + ", " +  primitive
		);
		// Check whether the expectation was true or false
		if (expectation)
			assertTrue(result);
		else
			assertFalse(result);
	}

	@Test
	public void testWholeNumbersTrue() {
		compareWholeTypes(
			true,100,100);
		compareWholeTypes(
			true,-100,-100);
	}

	@Test
	void testWholeNumbersFalse() {
		// When the values are clearly not equal
		compareWholeTypes(
			false, 100, 99);
		compareWholeTypes(
			false, -100, 100);
	}

	@Test
	void testLargeIncompatible() {
		// When the value cannot be represented by integer
		final long maxPlus1 = MAX + 1L;
		assertFalse(
			isEquivalent((byte) maxPlus1, MIN));
		assertFalse(
			isEquivalent((short) maxPlus1, MIN));
		// Special case where overflow occurs
		assertTrue(
			isEquivalent((int) maxPlus1, MIN));
		//
		assertFalse(
			isEquivalent(maxPlus1, MIN));
	}

	@Test
	void testDecimalTrue() {
		assertTrue(
			isEquivalent(0.0f, 0));
		assertTrue(
			isEquivalent(0.0, 0));
		assertTrue(
			isEquivalent(20.0f, 20));
		assertTrue(
			isEquivalent(20.0, 20));
		assertTrue(
			isEquivalent(120.0f, 120));
		assertTrue(
			isEquivalent(120.0, 120));
		//
		assertTrue(
			isEquivalent(-120.0f, -120));
		assertTrue(
			isEquivalent(-120.0, -120));
		assertTrue(
			isEquivalent(-127.0f, -127));
		assertTrue(
			isEquivalent(-127.0, -127));
		//
		assertTrue(
			isEquivalent((float) 200, 200));
		assertTrue(
			isEquivalent((double) 200, 200));
	}

	@Test
	void testDecimalFalse() {
		assertFalse(
			isEquivalent(0.1f, 0));
		assertFalse(
			isEquivalent(0.1, 0));
		assertFalse(
			isEquivalent(1.0001f, 1));
		assertFalse(
			isEquivalent(1.0001, 1));
		//
		assertFalse(
			isEquivalent(-1.0001f, -1));
		assertFalse(
			isEquivalent(-1.0001, -1));
		assertFalse(
			isEquivalent(-0.0001f, -0));
		assertFalse(
			isEquivalent(-0.0001, -0));
		//
		assertFalse(
			isEquivalent(-127.0001f, -127));
		assertFalse(
			isEquivalent(-127.0001, -127));
	}

}
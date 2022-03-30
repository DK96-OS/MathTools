package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.lists.NumberComparison.isEquivalent;

import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;

/**  */
public final class NumberComparisonLongTest {

	static final long MAX = Long.MAX_VALUE;
	static final long MIN = Long.MIN_VALUE;

	/** Compares all of the whole number types against the long primitive
	 * @param expectation The expected result to be returned from all types
	 * @param number The number that will provide all of the typed values
	 * @param primitive The long primitive to use in the tests */
	private static void compareWholeTypes(
		final boolean expectation,
		@Nonnull final Number number,
		final long primitive
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
	public void testWholeNumberTrue() {
		compareWholeTypes(
			true,100L,100L);
		compareWholeTypes(
			true,-100L,-100L);
	}

	@Test
	void testWholeNumbersFalse() {
		// When the values are clearly not equal
		compareWholeTypes(
			false, 100L, 99L);
		compareWholeTypes(
			false, -100L, 100L);
	}

	@Test
	void testLargeNumbers() {
		assertFalse(
			isEquivalent((byte) MAX, MAX));
		assertFalse(
			isEquivalent((short) MAX, MAX));
		assertFalse(
			isEquivalent((int) MAX, MAX));
		//
		assertTrue(
			isEquivalent(MAX, MAX));
	}

	@Test
	void testLargeNegativeNumbers() {
		assertFalse(
			isEquivalent((byte) MIN, MIN));
		assertFalse(
			isEquivalent((short) MIN, MIN));
		assertFalse(
			isEquivalent((int) MIN, MIN));
		//
		assertTrue(
			isEquivalent(MIN, MIN));
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
			isEquivalent(0.1f, 0L));
		assertFalse(
			isEquivalent(0.1, 0L));
		assertFalse(
			isEquivalent(1.0001f, 1L));
		assertFalse(
			isEquivalent(1.0001, 1L));
		//
		assertFalse(
			isEquivalent(-1.0001f, -1L));
		assertFalse(
			isEquivalent(-1.0001, -1L));
		assertFalse(
			isEquivalent(-0.0001f, -0L));
		assertFalse(
			isEquivalent(-0.0001, -0L));
	}

	@Test
	void testDecimalOutOfPrecision() {
		// The check fails because there are too many SFs for Float
		assertTrue(
			isEquivalent(-100027.0001f, -100027L));
		// The double is able to hold this number
		assertFalse(
			isEquivalent(-100027.0001, -100027L));
	}

}
package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static mathtools.numbers.factors.IntOperations.exponent;

import org.junit.jupiter.api.Test;

import mathtools.numbers.structs.IntPair;

/** Testing [IntOperations] Exponent function.
 * @author DK96-OS : 2022
 */
public final class IntOperationsExponentTest {

	@Test
	void testExponent() {
		assertPairEquals(
			1, 0, exponent(1, 2));
		assertPairEquals(
			4, 0, exponent(2, 2));
		assertPairEquals(
			16, 0, exponent(2, 4));
		assertPairEquals(
			32, 0, exponent(2, 5));
		assertPairEquals(
			256, 0, exponent(2, 8));
		assertPairEquals(
			1024, 0, exponent(2, 10));
		assertPairEquals(
			2048, 0, exponent(2, 11));
	}

	@Test
	void testExponentZeroAndOneCases() {
		assertPairEquals(
			1, 0, exponent(1, 5));
		assertPairEquals(
			0, 0, exponent(0, 5));
		//
		assertPairEquals(
			1, 0, exponent(6, 0));
		assertPairEquals(
			1, 0, exponent(-6, 0));
		assertPairEquals(
			6, 0, exponent(6, 1));
	}

	@Test
	void testExponentNegativeOneX() {
		assertPairEquals(
			1, 0, exponent(-1, 0));
		assertPairEquals(
			-1, 0, exponent(-1, 1));
		assertPairEquals(
			1, 0, exponent(-1, 2));
		assertPairEquals(
			-1, 0, exponent(-1, 3));
	}

	@Test
	void testExponentOverflow() {
		// This would overflow 16-bit short
		assertPairEquals(
			65536, 0, exponent(256, 2));
		// Overflow 32-bit int
		// Should be 256 ^ 4 = 256 * 256 * 256 * 256 = 4294967296
		// Instead, 256 ^ 3 = 16777216, with a remaining power of 1
		assertPairEquals(
			16777216, 0, exponent(256, 3));
		assertPairEquals(
			16777216, 1, exponent(256, 4));
		// Should be 256 ^ 5
		// Instead, 256 ^ 3 = 16777216, with a remaining power of 2
		assertPairEquals(
			16777216, 2, exponent(256, 5));
	}

	@Test
	void testExponentOverflowTricky() {
		assertPairEquals(
			66049, 0, exponent(257, 2));
		assertPairEquals(
			16974593, 0, exponent(257, 3));
		assertPairEquals(
			16974593, 1, exponent(257, 4));
	}

	@Test
	void testExponentMaxValue() {
		final int max = Integer.MAX_VALUE;
		// Any multiplication will overflow
		IntPair result = exponent(max, 3);
		// The power on the remaining exponent is (power - 1)
		assertPairEquals(
			max, 2, result);
		//
		result = exponent(max, 4);
		assertPairEquals(
			max, 3, result);
	}

	@Test
	void testExponentNegativePower() {
		for (int pwr = -1; pwr >= -10; pwr--)
			assertPairEquals(
				2, pwr, exponent(2, pwr)
			);
	}

	@Test
	void testExponentNegativeX() {
		assertPairEquals(
			1, 0, exponent(-2, 0));
		assertPairEquals(
			-2, 0, exponent(-2, 1));
		assertPairEquals(
			4, 0, exponent(-2, 2));
		assertPairEquals(
			-8, 0, exponent(-2, 3));
		assertPairEquals(
			16, 0, exponent(-2, 4));
	}

	/** Assert that actual is not null, and it's values match expectations.
	 */
	static void assertPairEquals(
		final int expectedFirst,
		final int expectedSecond,
		final IntPair actual
	) {
		assertNotNull(actual);
		assertEquals(expectedFirst, actual.getFirst());
		assertEquals(expectedSecond, actual.getSecond());
	}

}
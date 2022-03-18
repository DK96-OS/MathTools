package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.factors.IntOperations.exponent;
import static mathtools.numbers.factors.IntOperations.tenShift;
import static mathtools.numbers.factors.IntPairTest.assertPairEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/** Testing functions operating on 32-bit Integers
 * @author DK96-OS : 2022 */
public final class IntOperationsTest {

	private static final int MAX = Integer.MAX_VALUE;

	@Test
	void testTenShift() {
		for (byte e = 0; e < 8; ++e) {
			//
			final char[] arr = new char[e];
			Arrays.fill(arr, '0');
			//
			for (byte x = 1; x < 10; x++) {
				assertEquals(
					x + String.valueOf(arr),
					Integer.toString(tenShift(x, e))
				);
			}
		}
	}

	@Test
	void testTenShiftDown() {
		for (byte e = -1; e >= -7; --e) {
			int x = tenShift(4, -e);
			assertEquals(4, tenShift(x, e));
			//
			x = tenShift(40, -e);
			assertEquals(40, tenShift(x, e));
		}
	}

	@Test
	void testTenShiftNegativeX() {
		assertEquals(-4_000, tenShift(-4, 3));
		assertEquals(-40_000, tenShift(-4, 4));
		assertEquals(-400_000, tenShift(-4, 5));
		assertEquals(-4_000_000, tenShift(-4, 6));
		assertEquals(-40_000_000, tenShift(-4, 7));
	}

	@Test
	void testTenShiftNegativeXNegativeExp() {
		assertEquals(-400, tenShift(-4_000, -1));
		assertEquals(-40, tenShift(-4_000, -2));
		assertEquals(-4, tenShift(-4_000, -3));
		assertEquals(-400, tenShift(-4_000_000, -4));
		assertEquals(-40, tenShift(-4_000_000, -5));
		assertEquals(-4, tenShift(-4_000_000, -6));
	}

	@Test
	void testTenShiftLargeExp() {
		// clearly too large - exponent is big
		assertEquals(MAX, tenShift(5, 1200));
		assertEquals(MAX, tenShift(5, 12));
		assertEquals(MAX, tenShift(2, 10));
	}

	@Test
	void testTenShiftLargeNegativeExp() {
		assertEquals(0, tenShift(6000, -4));
		assertEquals(0, tenShift(6000, -5));
		assertEquals(0, tenShift(60_000, -9));
		//
		assertEquals(2147, tenShift(MAX, -6));
		assertEquals(214, tenShift(MAX, -7));
		assertEquals(21, tenShift(MAX, -8));
		assertEquals(2, tenShift(MAX, -9));
		assertEquals(0, tenShift(MAX, -10));
		assertEquals(0, tenShift(MAX, -90));
	}

	@Test
	void testTenShiftApproximateLimit() {
		assertEquals(MAX - 1, tenShift(MAX - 1, 0));
		assertEquals(2_000_000_000, tenShift(200_000_000, 1));
		assertEquals(2_000_000_000, tenShift(20_000_000, 2));
		assertEquals(2_000_000_000, tenShift(2_000_000, 3));
		assertEquals(2_000_000_000, tenShift(200_000, 4));
		assertEquals(2_147_400_000, tenShift(21474, 5));
		assertEquals(2_147_000_000, tenShift(2147, 6));
		assertEquals(2_140_000_000, tenShift(214, 7));
		assertEquals(2_100_000_000, tenShift(21, 8));
		assertEquals(2_000_000_000, tenShift(2, 9));
	}

	@Test
	void testTenShiftLargeXOverflow() {
		// integer overflow
		assertEquals(MAX, tenShift(MAX, 1));
		assertEquals(MAX, tenShift(MAX, 4));
		assertEquals(MAX, tenShift(30_000, 5));
		assertEquals(MAX, tenShift(20_000, 6));
		assertEquals(MAX, tenShift(50_000, 6));
		// combination of x and exponent
		assertEquals(MAX, tenShift(200, 8));
		assertEquals(MAX, tenShift(200, 9));
	}

	@Test
	void testExponent() {
		assertPairEquals(1, 0, exponent(1, 2));
		assertPairEquals(4, 0, exponent(2, 2));
		assertPairEquals(16, 0, exponent(2, 4));
		assertPairEquals(32, 0, exponent(2, 5));
		assertPairEquals(256, 0, exponent(2, 8));
		assertPairEquals(1024, 0, exponent(2, 10));
		assertPairEquals(2048, 0, exponent(2, 11));
	}

	@Test
	void testExponentZeroAndOneCases() {
		assertPairEquals(1, 0, exponent(1, 5));
		assertPairEquals(0, 0, exponent(0, 5));
		//
		assertPairEquals(1, 0, exponent(6, 0));
		assertPairEquals(1, 0, exponent(-6, 0));
		assertPairEquals(6, 0, exponent(6, 1));
	}

	@Test
	void testExponentNegativeOneX() {
		assertPairEquals(1, 0, exponent(-1, 0));
		assertPairEquals(-1, 0, exponent(-1, 1));
		assertPairEquals(1, 0, exponent(-1, 2));
		assertPairEquals(-1, 0, exponent(-1, 3));
	}

	@Test
	void testExponentOverflow() {
		// This would overflow 16-bit short
		assertPairEquals(65536, 0, exponent(256, 2));
		// Overflow 32-bit int
		// Should be 256 ^ 4 = 256 * 256 * 256 * 256 = 4294967296
		// Instead, 256 ^ 3 = 16777216, with a remaining power of 1
		assertPairEquals(16777216, 1, exponent(256, 4));
		// Should be 256 ^ 5
		// Instead, 256 ^ 3 = 16777216, with a remaining power of 2
		assertPairEquals(16777216, 2, exponent(256, 5));
	}

	@Test
	void testExponentOverflowTricky() {
		assertPairEquals(66049, 0, exponent(257, 2));
		assertPairEquals(16974593, 0, exponent(257, 3));
		assertPairEquals(16974593, 1, exponent(257, 4));
	}

	@Test
	void testExponentMaxValue() {
		assertPairEquals(214748364, 3, exponent(214748364, 3));
		assertPairEquals(2147483647, 4, exponent(Integer.MAX_VALUE, 4));
	}

	@Test
	void testExponentNegativePower() {
		for (int pwr = -1; pwr >= -10; pwr--)
			assertPairEquals(2, pwr, exponent(2, pwr));
	}

	@Test
	void testExponentNegativeX() {
		assertPairEquals(1, 0, exponent(-2, 0));
		assertPairEquals(-2, 0, exponent(-2, 1));
		assertPairEquals(4, 0, exponent(-2, 2));
		assertPairEquals(-8, 0, exponent(-2, 3));
		assertPairEquals(16, 0, exponent(-2, 4));
	}

}
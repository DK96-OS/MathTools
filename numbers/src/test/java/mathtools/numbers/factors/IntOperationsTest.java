package mathtools.numbers.factors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.numbers.factors.IntOperations.tenShift;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/** Testing functions operating on 32-bit Integers
 * @author DK96-OS : 2022 */
public final class IntOperationsTest {

	private static final int MAX = Integer.MAX_VALUE;

	@Test
	void testTenShift() {
		char[] zArray;
		for (byte e = 0; 8 > e; ++e) {
			//
			zArray = new char[e];
			Arrays.fill(zArray, '0');
			final String s = String.valueOf(zArray);
			//
			for (byte x = 1; 10 > x; x++) {
				assertEquals(
					x + s,
					Integer.toString(tenShift(x, e))
				);
			}
		}
	}

	@Test
	void testTenShiftDown() {
		for (byte e = -1; -7 <= e; --e) {
			int x = tenShift(4, -e);
			assertEquals(
				4, tenShift(x, e));
			//
			x = tenShift(40, -e);
			assertEquals(
				40, tenShift(x, e));
		}
	}

	@Test
	void testTenShiftNegativeX() {
		assertEquals(
			-4_000, tenShift(-4, 3));
		assertEquals(
			-40_000, tenShift(-4, 4));
		assertEquals(
			-400_000, tenShift(-4, 5));
		assertEquals(
			-4_000_000, tenShift(-4, 6));
		assertEquals(
			-40_000_000, tenShift(-4, 7));
	}

	@Test
	void testTenShiftNegativeXNegativeExp() {
		assertEquals(
			-400, tenShift(-4_000, -1));
		assertEquals(
			-40, tenShift(-4_000, -2));
		assertEquals(
			-4, tenShift(-4_000, -3));
		assertEquals(
			-400, tenShift(-4_000_000, -4));
		assertEquals(
			-40, tenShift(-4_000_000, -5));
		assertEquals(
			-4, tenShift(-4_000_000, -6));
	}

	@Test
	void testTenShiftLargeExp() {
		// clearly too large - exponent is big
		assertEquals(
			MAX, tenShift(5, 1200));
		assertEquals(
			MAX, tenShift(5, 12));
		assertEquals(
			MAX, tenShift(2, 10));
	}

	@Test
	void testTenShiftLargeNegativeExp() {
		assertEquals(
			0, tenShift(6000, -4));
		assertEquals(
			0, tenShift(6000, -5));
		assertEquals(
			0, tenShift(60_000, -9));
		//
		assertEquals(
			2147, tenShift(MAX, -6));
		assertEquals(
			214, tenShift(MAX, -7));
		assertEquals(
			21, tenShift(MAX, -8));
		assertEquals(
			2, tenShift(MAX, -9));
		assertEquals(
			0, tenShift(MAX, -10));
		assertEquals(
			0, tenShift(MAX, -90));
	}

	@Test
	void testTenShiftApproximateLimit() {
		assertEquals(
			MAX - 1, tenShift(MAX - 1, 0));
		assertEquals(
			2_000_000_000, tenShift(200_000_000, 1));
		assertEquals(
			2_000_000_000, tenShift(20_000_000, 2));
		assertEquals(
			2_000_000_000, tenShift(2_000_000, 3));
		assertEquals(
			2_000_000_000, tenShift(200_000, 4));
		assertEquals(
			2_147_400_000, tenShift(21474, 5));
		assertEquals(
			2_147_000_000, tenShift(2147, 6));
		assertEquals(
			2_140_000_000, tenShift(214, 7));
		assertEquals(
			2_100_000_000, tenShift(21, 8));
		assertEquals(
			2_000_000_000, tenShift(2, 9));
	}

	@Test
	void testTenShiftLargeXOverflow() {
		// integer overflow
		assertEquals(
			MAX, tenShift(MAX, 1));
		assertEquals(
			MAX, tenShift(MAX, 4));
		assertEquals(
			MAX, tenShift(30_000, 5));
		assertEquals(
			MAX, tenShift(20_000, 6));
		assertEquals(
			MAX, tenShift(50_000, 6));
		// combination of x and exponent
		assertEquals(
			MAX, tenShift(200, 8));
		assertEquals(
			MAX, tenShift(200, 9));
	}

}
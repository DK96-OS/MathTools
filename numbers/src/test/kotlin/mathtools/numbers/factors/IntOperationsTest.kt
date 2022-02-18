package mathtools.numbers.factors

import mathtools.numbers.factors.IntOperations.exponent
import mathtools.numbers.factors.IntOperations.tenShift
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing functions operating on 32-bit Integers
 * @author DK96-OS : 2022 */
class IntOperationsTest {

	@Test
	fun testTenShift() {
		for (e in 0 .. 7) {
			val zeros = buildString {
				for (i in 0 until e) append('0')
			}
			for (x in 1 .. 9) {
				assertEquals(
					"$x$zeros",
					tenShift(x, e).toString()
				)
			}
		}
	}

	@Test
	fun testTenShiftDown() {
		for (e in -1 downTo -7) {
			val x = tenShift(4, -e)
			assertEquals(
				4, tenShift(x, e)
			)
			val x2 = tenShift(40, -e)
			assertEquals(
				40, tenShift(x2, e)
			)
		}
	}

	@Test
	fun testTenShiftNegativeX() {
		assertEquals(-4_000, tenShift(-4, 3))
		assertEquals(-40_000, tenShift(-4, 4))
		assertEquals(-400_000, tenShift(-4, 5))
		assertEquals(-4_000_000, tenShift(-4, 6))
		assertEquals(-40_000_000, tenShift(-4, 7))
	}

	@Test
	fun testTenShiftNegativeXNegativeExp() {
		assertEquals(-400, tenShift(-4_000, -1))
		assertEquals(-40, tenShift(-4_000, -2))
		assertEquals(-4, tenShift(-4_000, -3))
		assertEquals(-400, tenShift(-4_000_000, -4))
		assertEquals(-40, tenShift(-4_000_000, -5))
		assertEquals(-4, tenShift(-4_000_000, -6))
	}

	@Test
	fun testTenShiftLargeExp() {
		// clearly too large - exponent is big
		assertEquals(Int.MAX_VALUE, tenShift(5, 1200))
		assertEquals(Int.MAX_VALUE, tenShift(5, 12))
		assertEquals(Int.MAX_VALUE, tenShift(2, 10))
	}

	@Test
	fun testTenShiftLargeNegativeExp() {
		assertEquals(0, tenShift(6000, -4))
		assertEquals(0, tenShift(6000, -5))
		assertEquals(0, tenShift(60_000, -9))
		//
		assertEquals(2147, tenShift(Int.MAX_VALUE, -6))
		assertEquals(214, tenShift(Int.MAX_VALUE, -7))
		assertEquals(21, tenShift(Int.MAX_VALUE, -8))
		assertEquals(2, tenShift(Int.MAX_VALUE, -9))
		assertEquals(0, tenShift(Int.MAX_VALUE, -10))
		assertEquals(0, tenShift(Int.MAX_VALUE, -90))
	}

	@Test
	fun testTenShiftApproximateLimit() {
		assertEquals(Int.MAX_VALUE - 1, tenShift(Int.MAX_VALUE - 1, 0))
		assertEquals(2_000_000_000, tenShift(200_000_000, 1))
		assertEquals(2_000_000_000, tenShift(20_000_000, 2))
		assertEquals(2_000_000_000, tenShift(2_000_000, 3))
		assertEquals(2_000_000_000, tenShift(200_000, 4))
		assertEquals(2_147_400_000, tenShift(21474, 5))
		assertEquals(2_147_000_000, tenShift(2147, 6))
		assertEquals(2_140_000_000, tenShift(214, 7))
		assertEquals(2_100_000_000, tenShift(21, 8))
		assertEquals(2_000_000_000, tenShift(2, 9))
	}

	@Test
	fun testTenShiftLargeXOverflow() {
		// integer overflow
		assertEquals(Int.MAX_VALUE, tenShift(Int.MAX_VALUE, 1))
		assertEquals(Int.MAX_VALUE, tenShift(Int.MAX_VALUE, 4))
		assertEquals(Int.MAX_VALUE, tenShift(30_000, 5))
		assertEquals(Int.MAX_VALUE, tenShift(20_000, 6))
		assertEquals(Int.MAX_VALUE, tenShift(50_000, 6))
		// combination of x and exponent
		assertEquals(Int.MAX_VALUE, tenShift(200, 8))
		assertEquals(Int.MAX_VALUE, tenShift(200, 9))
	}

	@Test
	fun testExponent() {
		assertEquals(1 to 0, exponent(1, 2))
		assertEquals(4 to 0, exponent(2, 2))
		assertEquals(16 to 0, exponent(2, 4))
		assertEquals(32 to 0, exponent(2, 5))
		assertEquals(256 to 0, exponent(2, 8))
		assertEquals(1024 to 0, exponent(2, 10))
		assertEquals(2048 to 0, exponent(2, 11))
	}

	@Test
	fun testExponentZeroAndOneCases() {
		assertEquals(1 to 0, exponent(1, 5))
		assertEquals(0 to 0, exponent(0, 5))
		//
		assertEquals(1 to 0, exponent(6, 0))
		assertEquals(1 to 0, exponent(-6, 0))
		assertEquals(6 to 0, exponent(6, 1))
	}

	@Test
	fun testExponentNegativeOneX() {
		assertEquals(1 to 0, exponent(-1, 0))
		assertEquals(-1 to 0, exponent(-1, 1))
		assertEquals(1 to 0, exponent(-1, 2))
		assertEquals(-1 to 0, exponent(-1, 3))
	}

	@Test
	fun testExponentOverflow() {
		// This would overflow 16-bit short
		assertEquals(65536 to 0, exponent(256, 2))
		// Overflow 32-bit int
		// Should be 256 ^ 4 = 256 * 256 * 256 * 256 = 4294967296
		// Instead, 256 ^ 3 = 16777216, with a remaining power of 1
		assertEquals(16777216 to 1, exponent(256, 4))
		// Should be 256 ^ 5
		// Instead, 256 ^ 3 = 16777216, with a remaining power of 2
		assertEquals(16777216 to 2, exponent(256, 5))
	}

	@Test
	fun testExponentOverflowTricky() {
		assertEquals(66049 to 0, exponent(257, 2))
		assertEquals(16974593 to 0, exponent(257, 3))
		assertEquals(16974593 to 1, exponent(257, 4))
	}

	@Test
	fun testExponentMaxValue() {
		assertEquals(214748364 to 3, exponent(214748364, 3))
		assertEquals(2147483647 to 4, exponent(Int.MAX_VALUE, 4))
	}

	@Test
	fun testExponentNegativePower() {
		for (pwr in -1 downTo -10)
			assertEquals(2 to pwr, exponent(2, pwr))
	}

	@Test
	fun testExponentNegativeX() {
		assertEquals(1 to 0, exponent(-2, 0))
		assertEquals(-2 to 0, exponent(-2, 1))
		assertEquals(4 to 0, exponent(-2, 2))
		assertEquals(-8 to 0, exponent(-2, 3))
		assertEquals(16 to 0, exponent(-2, 4))
	}

}
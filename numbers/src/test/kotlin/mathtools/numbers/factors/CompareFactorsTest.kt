package mathtools.numbers.factors

import mathtools.numbers.factors.CompareFactors.gcd
import mathtools.numbers.factors.CompareFactors.lcm
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the Compare Factors object
 * @author DK96-OS : 2022 */
class CompareFactorsTest {

	@Test
	fun testGCDBaseCases() {
		// ones
		assertEquals(1, gcd(1, 1))
		assertEquals(1, gcd(1, 8))
		assertEquals(1, gcd(8, 1))
		// zero
		assertEquals(1, gcd(0, 8))
		assertEquals(1, gcd(8, 0))
		// equal
		assertEquals(10, gcd(10, 10))
	}

	@Test
	fun testGCD() {
		assertEquals(4, gcd(12, 16))
		assertEquals(4, gcd(16, 12))
		//
		assertEquals(9, gcd(18, 27))
		assertEquals(9, gcd(27, 18))
		//
		assertEquals(21, gcd(42, 147))
		assertEquals(21, gcd(147, 42))
	}

	@Test
	fun testGCDNegative() {
		assertEquals(4, gcd(-12, 16))
		assertEquals(4, gcd(12, -16))
		assertEquals(4, gcd(-16, 12))
		assertEquals(4, gcd(16, -12))
	}

	@Test
	fun testLCMBaseCases() {
		assertEquals(1, lcm(1, 1))
		assertEquals(2, lcm(1, 2))
		assertEquals(2, lcm(2, 1))
		//
		assertEquals(2, lcm(2, 1))
		assertEquals(1, lcm(0, 1))
		assertEquals(1, lcm(1, 0))
	}

	@Test
	fun testNegative() {
		assertEquals(4, lcm(-4, 4))
		assertEquals(4, lcm(-4, -4))
		assertEquals(4, lcm(4, -4))
		//
		assertEquals(28, lcm(7, -4))
		assertEquals(28, lcm(-7, 4))
	}

	@Test
	fun testPowersOf2() {
		assertEquals(2, lcm(2, 2))
		assertEquals(4, lcm(2, 4))
		assertEquals(4, lcm(4, 4))
		assertEquals(8, lcm(8, 4))
		assertEquals(16, lcm(8, 16))
		//
		assertEquals(32, lcm(8, 32))
		assertEquals(32, lcm(16, 32))
		assertEquals(32, lcm(32, 32))
		//
		assertEquals(64, lcm(64, 32))
		assertEquals(64, lcm(64, 16))
		assertEquals(64, lcm(64, 8))
		assertEquals(64, lcm(64, 4))
		assertEquals(64, lcm(64, 2))
		assertEquals(64, lcm(64, 2))
		//
		assertEquals(128, lcm(64, 128))
		assertEquals(128, lcm(32, 128))
		assertEquals(256, lcm(32, 256))
	}

	@Test
	fun testTwosAndThrees() {
		assertEquals(6, lcm(2, 3))
		assertEquals(6, lcm(2, 6))
		//
		assertEquals(12, lcm(4, 3))
		assertEquals(12, lcm(4, 6))
		assertEquals(12, lcm(4, 12))
		assertEquals(12, lcm(12, 6))
		//
		assertEquals(18, lcm(9, 6))
		assertEquals(18, lcm(18, 6))
		assertEquals(18, lcm(18, 9))
		//
		assertEquals(24, lcm(12, 8))
		assertEquals(24, lcm(8, 12))
		assertEquals(24, lcm(24, 12))
		assertEquals(24, lcm(12, 24))
		//
		assertEquals(36, lcm(6, 36))
		assertEquals(36, lcm(36, 6))
	}

}
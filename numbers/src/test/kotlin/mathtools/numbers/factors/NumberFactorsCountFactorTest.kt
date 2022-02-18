package mathtools.numbers.factors

import mathtools.numbers.factors.NumberFactors.countFactor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the Number Factors countFactor function
 * @author DK96-OS : 2022 */
class NumberFactorsCountFactorTest {

	@Test
	fun testFactor2() {
		assertEquals(2, countFactor(2, 4))
		assertEquals(2, countFactor(2, 12))
		assertEquals(2, countFactor(2, 36))
	}

	@Test
	fun testFactor5() {
		assertEquals(1, countFactor(5, 5))
		assertEquals(2, countFactor(5, 25))
		assertEquals(3, countFactor(5, 125))
		//
		assertEquals(1, countFactor(5, 20))
		assertEquals(1, countFactor(5, 40))
		assertEquals(2, countFactor(5, 50))
		assertEquals(2, countFactor(5, 150))
		//
		assertEquals(2, countFactor(5, 3050))
	}

	@Test
	fun testNegativeFactors() {
		assertEquals(2, countFactor(-2, 12))
		assertEquals(1, countFactor(-5, 20))
	}

	@Test
	fun testNegativeComposites() {
		assertEquals(2, countFactor(2, -12))
		assertEquals(1, countFactor(5, -20))
	}

	@Test
	fun testNegativeInputs() {
		assertEquals(2, countFactor(-2, -12))
		assertEquals(1, countFactor(-5, -20))
	}

	@Test
	fun testInvalidArgs() {
		assertEquals(0, countFactor(0, 12))
		assertEquals(0, countFactor(1, 12))
	}

}
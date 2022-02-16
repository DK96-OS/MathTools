package mathtools.numbers.factors

import mathtools.numbers.factors.NumberFactors.divideOutFactor
import mathtools.numbers.factors.NumberFactors.isProductOf2
import mathtools.numbers.factors.NumberFactors.isProductOf5
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Testing the Number Factors static functions
 * @author DK96-OS : 2022 */
class NumberFactorsTest {

	@Test
	fun testProductOf2() {
		assertEquals(false, isProductOf2(0))
		for (n in 1 .. 65 step 2)
			assertEquals(false, isProductOf2(n.toLong()))
		for (n in 2 .. 66 step 2)
			assertEquals(true, isProductOf2(n.toLong()))
	}

	@Test
	fun testProductOf2Negative() {
		for (n in 1 .. 65 step 2)
			assertEquals(false, isProductOf2(-n.toLong()))
		for (n in 2 .. 66 step 2)
			assertEquals(true, isProductOf2(-n.toLong()))
	}

	@Test
	fun testProductOf2Large() {
		val lowerBound = Long.MAX_VALUE - 100
		for (n in Long.MAX_VALUE downTo lowerBound step 2)
			assertEquals(false, isProductOf2(n))
		for (n in Long.MAX_VALUE - 1 downTo lowerBound step 2)
			assertEquals(true, isProductOf2(n))
	}

	@Test
	fun testProductOf5() {
		for (n in 0 until 5)
			assertEquals(false, isProductOf5(n.toLong()))
		for (n in 5 .. 200 step 5)
			assertEquals(true, isProductOf5(n.toLong()))
	}

	@Test
	fun testProductOf5Large() {
		for (n in 10_101 .. 200_101 step 500)
			assertEquals(false, isProductOf5(n.toLong()))
		for (n in 10_100 .. 200_100 step 500)
			assertEquals(true, isProductOf5(n.toLong()))
	}

	@Test
	fun testProductOf5Negative() {
		for (n in 0 until 5)
			assertEquals(false, isProductOf5(-n.toLong()))
		for (n in 5 .. 200 step 5)
			assertEquals(true, isProductOf5(-n.toLong()))
	}

	@Test
	fun testDivideOutFactor2() {
		assertEquals(1, divideOutFactor(2, 32))
		assertEquals(3, divideOutFactor(2, 6))
		assertEquals(3, divideOutFactor(2, 24))
		assertEquals(3, divideOutFactor(2, 48))
		assertEquals(15, divideOutFactor(2, 30))
		assertEquals(375, divideOutFactor(2, 375))
	}

	@Test
	fun testDivideOutFactor5() {
		assertEquals(1, divideOutFactor(5, 25))
		assertEquals(3, divideOutFactor(5, 15))
		assertEquals(3, divideOutFactor(5, 75))
		assertEquals(3, divideOutFactor(5, 375))
		assertEquals(6, divideOutFactor(5, 30))
		assertEquals(12, divideOutFactor(5, 60))
		assertEquals(64, divideOutFactor(5, 64))
	}

	@Test
	fun testDivideOutFactorNegative() {
		assertEquals(3, divideOutFactor(-2, 6))
		assertEquals(3, divideOutFactor(-2, 24))
		assertEquals(3, divideOutFactor(-2, 48))
		assertEquals(15, divideOutFactor(-2, 30))
	}

	@Test
	fun testDivideOutFactorNegativeNumber() {
		assertEquals(-3, divideOutFactor(-2, -6))
		assertEquals(-3, divideOutFactor(-2, -24))
		assertEquals(-3, divideOutFactor(-2, -48))
		assertEquals(-15, divideOutFactor(-2, -30))
	}

	@Test
	fun testDivideOutFactorInvalid() {
		assertEquals(300, divideOutFactor(1, 300))
		assertEquals(300, divideOutFactor(0, 300))
		assertEquals(300, divideOutFactor(-1, 300))
	}

	@ParameterizedTest
	@ValueSource(ints = [2, 3, 5, 7, 11, 13, 17])
	fun testDivideOutFactorLarge(factor: Int) {
		assertEquals(353, divideOutFactor(factor, 353))
		assertEquals(2909, divideOutFactor(factor, 2909))
		assertEquals(7211, divideOutFactor(factor, 7211))
		assertEquals(9013, divideOutFactor(factor, 9013))
	}

}
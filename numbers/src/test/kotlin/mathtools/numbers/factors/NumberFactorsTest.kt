package mathtools.numbers.factors

import mathtools.numbers.factors.NumberFactors.isProductOf2
import mathtools.numbers.factors.NumberFactors.isProductOf5
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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

}
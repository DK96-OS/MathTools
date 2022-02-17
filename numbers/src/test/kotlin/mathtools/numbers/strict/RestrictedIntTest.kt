package mathtools.numbers.strict

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the RestrictedInt class
 * @author DK96-OS : 2019 - 2022 */
class RestrictedIntTest {

	@Test
	fun testIncrementLoop() {
		val i = RestrictedInt(1, 10)
		for (k in 1 .. 9) {
			assertEquals(k, i.value)
			assertEquals(k + 1, i.inc())
		}
		assertEquals(10, i.value)
		assertEquals(true, i.isIncreasing)
		assertEquals(9, i.inc())
		assertEquals(false, i.isIncreasing)
		for (k in 9 downTo 2) {
			assertEquals(k, i.value)
			assertEquals(k - 1, i.inc())
		}
		assertEquals(1, i.value)
		assertEquals(false, i.isIncreasing)
		assertEquals(2, i.inc())
		assertEquals(true, i.isIncreasing)
	}

	@Test
	fun testGetThenIncrement() {
		val i = RestrictedInt(1, 10)
		for (k in 1 .. 9) {
			assertEquals(k, i.value)
			assertEquals(k, i.getAndInc())
		}
		for (k in 10 downTo 1) {
			assertEquals(k, i.value)
			assertEquals(k, i.getAndInc())
		}
	}

	@Test
	fun testRandomize() {
		val restrictedRange = 20_012 .. 50_456
		val i = RestrictedInt(1, restrictedRange)
		for (k in 0 until 2000) {
			assert(i.randomize() in restrictedRange)
		}
	}

	@Test
	fun testRandomizeWithInnerRange() {
		val restrictedRange = 20_012 .. 50_456
		val i = RestrictedInt(1, restrictedRange)
		val innerRange = 40_000 .. 42_000
		for (k in 0 until 100) {
			assert(i.randomize(innerRange) in innerRange)
		}
	}

	@Test
	fun testRandomizeWithInvalidRange() {
		val restrictedRange = 20_012 .. 50_456
		val i = RestrictedInt(1, restrictedRange)
		val invalidRange = 400_000 .. 420_000
		for (k in 0 until 100) {
			assert(i.randomize(invalidRange) in restrictedRange)
		}
	}

	@Test
	fun testConstructor() {
		var i = RestrictedInt(1, 100 .. 200)
		assertEquals(100, i.value)
		i = RestrictedInt(400, 100 .. 200)
		assertEquals(200, i.value)
		//
		i = RestrictedInt(10, 10 .. 20, false)
		assertEquals(10, i.getAndInc())
		assertEquals(11, i.value)
	}

	@Test
	fun testTrySetN() {
		val i = RestrictedInt(100, 100 .. 200)
		i.trySetN(150)
		assertEquals(150, i.value)
		i.trySetN(210)
		assertEquals(200, i.value)
		i.trySetN(90)
		assertEquals(100, i.value)
	}

	@Test
	fun testAdditionOperation() {
		val n1 = RestrictedInt(20, 1 .. 50)
		val n2 = RestrictedInt(10, 2 .. 45)
		val sum1 = n1 + n2
		val sum2 = n2 + n1
		val sum3 = n1 + 10
		val sum4 = n1 + 10f
		assertEquals(30, sum1)
		assertEquals(30, sum2)
		assertEquals(30, sum3)
		assertEquals(30f, sum4)
	}

	@Test
	fun testSubtractionOperation() {
		val n1 = RestrictedInt(20, 1 .. 50)
		val n2 = RestrictedInt(10, 2 .. 45)
		val diff1 = n1 - n2
		val diff2 = n2 - n1
		val diff3 = n1 - 10
		val diff4 = n1 - 10f
		assertEquals(10, diff1)
		assertEquals(-10, diff2)
		assertEquals(10, diff3)
		assertEquals(10f, diff4)
	}

	@Test
	fun testMultiplicationOperation() {
		val n1 = RestrictedInt(20, 1 .. 50)
		val n2 = RestrictedInt(10, 2 .. 45)
		val sum1 = n1 * n2
		val sum2 = n2 * n1
		val sum3 = n1 * 10
		val sum4 = n1 * 10f
		assertEquals(200, sum1)
		assertEquals(200, sum2)
		assertEquals(200, sum3)
		assertEquals(200f, sum4)
	}

	@Test
	fun testDivisionOperation() {
		val n1 = RestrictedInt(20, 1 .. 50)
		val n2 = RestrictedInt(10, 2 .. 45)
		val sum1 = n1 / n2
		val sum2 = n2 / n1
		val sum3 = n1 / 10
		val sum4 = n1 / 10f
		assertEquals(2, sum1)
		assertEquals(0, sum2)
		assertEquals(2, sum3)
		assertEquals(2f, sum4)
	}

	@Test
	fun testToString() {
		val i = RestrictedInt(20, 1 .. 40)
		assertEquals("20", i.toString())
	}

}
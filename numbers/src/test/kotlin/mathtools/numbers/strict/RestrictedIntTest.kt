package mathtools.numbers.strict

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the RestrictedInt class
 * @author DK96-OS : 2019 - 2022 */
class RestrictedIntTest {

	@Test
	fun testIncrementLoop() {
		val i = RestrictedInt(1, 10)
		for (k in 1 until i.range.last) {
			assertEquals(k, i.value)
			assertEquals(k + 1, i.inc())
		}
		assertEquals(i.range.last, i.value)
		assertEquals(true, i.isIncreasing)
		assertEquals(i.range.last - 1, i.inc())
		assertEquals(false, i.isIncreasing)
		for (k in i.range.last - 1 downTo i.range.first + 1) {
			assertEquals(k, i.value)
			assertEquals(k - 1, i.inc())
		}
		assertEquals(1, i.value)
		assertEquals(false, i.isIncreasing)
		assertEquals(2, i.inc())
		assertEquals(true, i.isIncreasing)
	}

	@Test
	fun testIncrementLoop2() {
		val i = RestrictedInt(2, 5, isIncreasing = false)
		assertEquals(1, i.inc())
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
		val i = RestrictedInt(1, 20_012 .. 50_456)
		val innerRange = 40_000 .. 42_000
		for (k in 0 until 100) {
			assert(i.randomize(innerRange) in innerRange)
		}
	}

	@Test
	fun testRandomizeWithLowerBoundOutOfRange() {
		val i = RestrictedInt(10, 200 .. 600)
		val rangeTooLow = 100 .. 250
		val validRange = 200 .. 250
		for (k in 0 until 300)
			assert(i.randomize(rangeTooLow) in validRange)
	}

	@Test
	fun testRandomizeWithUpperBoundOutOfRange() {
		val i = RestrictedInt(10, 200 .. 600)
		val rangeTooHigh = 500 .. 900
		val validRange = 500 .. 600
		for (k in 0 until 300)
			assert(i.randomize(rangeTooHigh) in validRange)
	}

	@Test
	fun testRandomizeWithMaxIntValue() {
		val i = RestrictedInt(1, 2_000_000_000 .. Int.MAX_VALUE)
		val innerRange1 = 2_100_000_000 .. Int.MAX_VALUE
		val innerRange2 = 1_100_000_000 .. Int.MAX_VALUE
		//
		for (k in 0 until 300) {
			assertEquals(true, i.randomize() in i.range)
			assertEquals(true, i.randomize(innerRange1) in innerRange1)
			assertEquals(true, i.randomize(innerRange2) in i.range)
		}
	}

	@Test
	fun testRandomizeWithInvalidRange() {
		val i = RestrictedInt(1, 20_012 .. 50_456)
		val invalidRange = 400_000 .. 420_000
		for (k in 0 until 100) {
			assert(i.randomize(invalidRange) in i.range)
		}
	}

	@Test
	fun testRandomizeWithInvalidRange2() {
		val i = RestrictedInt(1, 200, 600)
		val invalidRange = 5000 .. 4000
		for (k in 0 until 100)
			assert(i.randomize(invalidRange) in i.range)
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
		// Restricted Digit
		val d = RestrictedDigit(5)
		assertEquals(25, n1 + d)
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
		// Restricted Digit
		val d = RestrictedDigit(5)
		assertEquals(15, n1 - d)
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
		// Restricted Digit
		val d = RestrictedDigit(5)
		assertEquals(100, n1 * d)
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
		// Restricted Digit
		val d = RestrictedDigit(5)
		assertEquals(4, n1 / d)
	}

	@Test
	fun testCompareTo() {
		val n1 = RestrictedInt(20, 1 .. 50)
		assertEquals(true, n1 > 10)
		assertEquals(true, n1 < 21)
		assertEquals(0, n1.compareTo(20))
		assertEquals(-1, n1.compareTo(21))
		assertEquals(1, n1.compareTo(19))
	}

	@Test
	fun testToString() {
		val i = RestrictedInt(20, 1 .. 40)
		assertEquals("20", i.toString())
	}

}
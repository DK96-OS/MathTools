package mathtools.numbers.strict

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the RestrictedDigit class
 * @author DK96-OS : 2019 - 2022 */
class RestrictedDigitTest {

	@Test
	fun testIncrementLoop() {
		val d = RestrictedDigit()
		for (i in 1 .. 8) {
			assertEquals(i.toByte(), d.digit)
			assertEquals(false, d.inc())
		}
		assertEquals((9).toByte(), d.digit)
		assertEquals(true, d.inc())
		assertEquals((0).toByte(), d.digit)

	}

	@Test
	fun testRandomize() {
		val d = RestrictedDigit(1, 1, 7)
		val range = d.minimum .. d.maximum
		assertEquals(1 .. 7, range)
		val hasBeenChosen = BooleanArray(7)
		for (i in 0 until 200) {
			val chosen = d.randomize()
			val chosenIdx = chosen - 1
			if (!hasBeenChosen[chosenIdx])
				hasBeenChosen[chosenIdx] = true
		}
		for (wasChosen in hasBeenChosen)
			assertEquals(true, wasChosen)
	}

	@Test
	fun testAdditionOperation() {
		val d = RestrictedDigit(5)
		val sum1 = d + 6
		val sum2 = d + 6f
		assertEquals(11, sum1)
		assertEquals(11f, sum2)
	}

	@Test
	fun testSubtractionOperation() {
		val d = RestrictedDigit(5)
		val diff1 = d - 3
		val diff2 = d - 2f
		assertEquals(2, diff1)
		assertEquals(3f, diff2)
	}

	@Test
	fun testMultiplicationOperation() {
		val d = RestrictedDigit(5)
		assertEquals(50, d * 10)
		assertEquals(50f, d * 10f)
	}

	@Test
	fun testDivisionOperation() {
		val d = RestrictedDigit(8)
		assertEquals(2, d / 4)
		assertEquals(2f, d / 4f)
	}

	@Test
	fun testToString() {
		val d = RestrictedDigit(7)
		assertEquals("7", d.toString())
	}

}
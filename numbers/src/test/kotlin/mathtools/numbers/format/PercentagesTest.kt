package mathtools.numbers.format

import mathtools.numbers.format.Percentages.strictPercent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing Percentages functions
 * @author DK96-OS : 2022 */
class PercentagesTest {
	
	@Test
	fun testStrictPercent() {
		assertEquals("1%", strictPercent(0.01f, 0))
		assertEquals("2%", strictPercent(0.02f, 0))
		assertEquals("20%", strictPercent(0.2f, 0))
		assertEquals("21%", strictPercent(0.21f, 0))
		assertEquals("30%", strictPercent(0.3f, 0))
		assertEquals("31%", strictPercent(0.31f, 0))
	}

	@Test
	fun testStrictPercent1Decimal() {
		assertEquals("1.0%", strictPercent(0.01f, 1))
		assertEquals("2.5%", strictPercent(0.025f, 1))
		assertEquals("20.0%", strictPercent(0.2f, 1))
		assertEquals("21.1%", strictPercent(0.211f, 1))
		assertEquals("30.3%", strictPercent(0.303f, 1))
		assertEquals("31.8%", strictPercent(0.3177f, 1))
	}

	@Test
	fun testStrictPercent3Decimal() {
		assertEquals("1.01%", strictPercent(0.0101f, 3))
		assertEquals("2.575%", strictPercent(0.02575f, 3))
		assertEquals("20.0%", strictPercent(0.200f, 3))
		assertEquals("21.111%", strictPercent(0.21111f, 3))
		assertEquals("30.301%", strictPercent(0.303006f, 3))
		assertEquals("31.778%", strictPercent(0.317777f, 3))
	}

	@Test
	fun testStrictPercent7Decimal() {
		// Note that 7 is higher than allowed, reduced to 5
		assertEquals("1.0101%", strictPercent(0.010101f, 7))
		assertEquals("2.57503%", strictPercent(0.02575025f, 7))
		assertEquals("20.0%", strictPercent(0.200000f, 7))
		assertEquals("21.111%", strictPercent(0.21111f, 7))
		assertEquals("30.30104%", strictPercent(0.3030104f, 7))
		assertEquals("31.7777%", strictPercent(0.317777f, 7))
	}

	@Test
	fun testStrictPercentNegative() {
		assertEquals("0%", strictPercent(-0.01f, 0))
		assertEquals("0%", strictPercent(-0.02f, 0))
		assertEquals("0%", strictPercent(-0.2f, 0))
	}

	@Test
	fun testStrictPercentNegativeDecimals() {
		assertEquals("1%", strictPercent(0.01f, -1))
		assertEquals("3%", strictPercent(0.03f, -3))
	}

	@Test
	fun testStrictPercentSpecialValues() {
		assertEquals("0%", strictPercent(Float.NaN, 0))
		assertEquals("100%", strictPercent(Float.POSITIVE_INFINITY, 0))
		assertEquals("0%", strictPercent(Float.NEGATIVE_INFINITY, 0))
	}

}
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

}
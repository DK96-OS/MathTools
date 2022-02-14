package mathtools.lists.doubles

import mathtools.lists.DoubleList.findLessThan
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the DoubleList FindLessThan function
 * Developed by DK96-OS : 2022 */
class DoubleListFindLessThanTest {

	private val u101 = toDouble(uniform101)

	@Test
	fun testSingleItem() {}

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findLessThan(
				emptyList<Double>(), 10.0,
			).size
		)
	}

	@Test
	fun testMaxValue() {
		assertEquals(
			0, findLessThan(
				listOf(Double.MAX_VALUE), Double.MAX_VALUE
			).size
		)
	}

	@Test
	fun testInfiniteLimits() {
		assertEquals(
			u101.size, findLessThan(
				u101, Double.POSITIVE_INFINITY,
			).size
		)
		assertEquals(
			0, findLessThan(
				u101, Double.NEGATIVE_INFINITY,
			).size
		)
	}

	@Test
	fun testNaNLimit() {
		assertEquals(
			0, findLessThan(
				u101, Double.NaN,
			).size
		)
	}

}
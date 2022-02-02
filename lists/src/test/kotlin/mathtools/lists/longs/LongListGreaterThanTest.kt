package mathtools.lists.longs

import mathtools.lists.LongList.findGreaterThan
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the GreaterThan function on Long Lists
 * Developed by DK96-OS : 2022 */
class LongListGreaterThanTest {

	private val u101 = toLong(uniform101)

	/** Greater Than works on single item and empty lists */
	@Test
	fun testFindGreaterThanSingleItem() {
		assertEquals(
			0, findGreaterThan(
				listOf(5L), 5L
			).size
		)
		assertEquals(
			1, findGreaterThan(
				listOf(5L), 4L
			).size
		)
	}

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findGreaterThan(
				emptyList(), 5L
			).size
		)
	}

	@Test
	fun testFindGreaterThanMaxValue() {
		assertEquals(
			1, findGreaterThan(
				listOf(Long.MAX_VALUE), 2
			).size
		)
		// Max Value Limit
		assertEquals(
			0, findGreaterThan(
				listOf(Long.MAX_VALUE), Long.MAX_VALUE
			).size
		)
	}

	@Test
	fun testBadArguments() {
		assertEquals(
			u101.size, findGreaterThan(
				u101, Long.MIN_VALUE, 0
			).size
		)
		assertEquals(
			0, findGreaterThan(
				u101, 5L, -1
			).size
		)
		assertEquals(
			0, findGreaterThan(
				u101, 5L, u101.size
			).size
		)
	}

}
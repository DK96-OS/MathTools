package mathtools.lists.ints

import mathtools.lists.IntList.findGreaterThan
import mathtools.lists.NumberListConversion.toInt
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the IntList FindGreaterThan function
 * Developed by DK96-OS : 2022 */
class IntListFindGreaterThanTest {

	private val u101 = toInt(uniform101)

	@Test
	fun testSingleItem() {
		assertEquals(
			0, findGreaterThan(
				listOf(5), 5
			).size
		)
		assertEquals(
			listOf(0), findGreaterThan(
				listOf(5), 4
			)
		)
	}

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findGreaterThan(
				emptyList(), 5
			).size
		)
	}

	@Test
	fun testFindGreaterThanMaxValue() {
		assertEquals(
			1, findGreaterThan(
				listOf(Int.MAX_VALUE), 2
			).size
		)
		// Max Value Limit
		assertEquals(
			0, findGreaterThan(
				listOf(Int.MAX_VALUE), Int.MAX_VALUE
			).size
		)
	}

	@Test
	fun testBadArguments() {
		assertEquals(
			u101.size, findGreaterThan(
				u101, Int.MIN_VALUE, 0
			).size
		)
		assertEquals(
			0, findGreaterThan(
				u101, 5, -1
			).size
		)
		assertEquals(
			0, findGreaterThan(
				u101, 5, u101.size
			).size
		)
	}

}
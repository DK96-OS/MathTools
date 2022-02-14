package mathtools.lists.longs

import mathtools.lists.LongList.findLessThan
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the IntList FindLessThan function
 * Developed by DK96-OS : 2022 */
class LongListFindLessThanTest {

	private val u101 = toLong(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findLessThan(
				emptyList(), 5L
			).size
		)
	}

	@Test
	fun testSingleItem() {
		assertEquals(
			0, findLessThan(
				listOf(5L), 5L
			).size
		)
		assertEquals(
			listOf(0), findLessThan(
				listOf(5L), 6L
			).size
		)
	}

	@Test
	fun testMaxValue() {
		assertEquals(
			0, findLessThan(
				listOf(Long.MAX_VALUE), Long.MAX_VALUE
			).size
		)
	}

}
package mathtools.lists.ints

import mathtools.lists.IntList.findLessThan
import mathtools.lists.NumberListConversion.toInt
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the IntList FindLessThan function
 * Developed by DK96-OS : 2022 */
class IntListFindLessThanTest {

	private val u101 = toInt(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findLessThan(
				emptyList(), 5
			).size
		)
	}

	@Test
	fun testSingleItem() {
		assertEquals(
			0, findLessThan(
				listOf(5), 5
			).size
		)
		assertEquals(
			listOf(0), findLessThan(
				listOf(5), 6
			).size
		)
	}

	@Test
	fun testMaxValue() {
		assertEquals(
			0, findLessThan(
				listOf(Int.MAX_VALUE), Int.MAX_VALUE
			).size
		)
	}

}
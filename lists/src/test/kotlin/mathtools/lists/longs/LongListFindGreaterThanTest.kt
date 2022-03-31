package mathtools.lists.longs

import mathtools.lists.LongList.findGreaterThan
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the GreaterThan function on Long Lists
 * @author DK96-OS : 2022 */
class LongListFindGreaterThanTest {

	private val u101 = toLong(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findGreaterThan(
				emptyList(), 5L
			).size
		)
	}

	@Test
	fun testSingleItem() {
		assertEquals(
			0, findGreaterThan(
				listOf(5L), 5L
			).size
		)
		assertEquals(
			listOf(0), findGreaterThan(
				listOf(5L), 4L
			)
		)
	}

	@Test
	fun testMaxValue() {
		assertEquals(
			listOf(0), findGreaterThan(
				listOf(Long.MAX_VALUE), 2
			)
		)
		// Max Value Limit
		assertEquals(
			0, findGreaterThan(
				listOf(Long.MAX_VALUE), Long.MAX_VALUE
			).size
		)
	}

	@Test
	fun testStartArg() {
		val res = Array<List<Int>?>(3) { null }
		res[0] = findGreaterThan(
			u101, 78, 90
		)
		res[1] = findGreaterThan(
			u101, 78, 100
		)
		res[2] = findGreaterThan(
			u101, 75, -1
		)
		assertEquals(
			listOf(99, 100), res[0])
		assertEquals(
			listOf(100), res[1])
		assertEquals(
			5, res[2]?.size)
	}

	@Test
	fun testEndArg() {
		val res = Array<List<Int>?>(3) { null }
		res[0] = findGreaterThan(
			u101, 78, 0, 105
		)
		res[1] = findGreaterThan(
			u101, 78, 0, 100
		)
		res[2] = findGreaterThan(
			u101, 78, 0, -1
		)
		assertEquals(
			listOf(99, 100), res[0])
		assertEquals(
			listOf(99), res[1])
		assertEquals(
			0, res[2]?.size)
	}

	@Test
	fun testBadArgs() {
		assertEquals(
			u101.size, findGreaterThan(
				u101, Long.MIN_VALUE, 0
			).size
		)
		assertEquals(
			0, findGreaterThan(
				u101, 5L, u101.size
			).size
		)
	}

}
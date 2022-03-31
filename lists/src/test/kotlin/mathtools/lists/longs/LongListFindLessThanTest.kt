package mathtools.lists.longs

import mathtools.lists.LongList.findLessThan
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the IntList FindLessThan function
 * @author DK96-OS : 2022 */
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
			)
		)
	}

	@Test
	fun testMaxValue() {
		assertEquals(
			0, findLessThan(
				listOf(Long.MAX_VALUE), Long.MAX_VALUE
			).size
		)
		assertEquals(
			listOf(0), findLessThan(
				listOf(5L), Long.MAX_VALUE
			)
		)
	}

	@Test
	fun testLimitMinValue() {
		assertEquals(
			0, findLessThan(
				listOf(45L, Long.MIN_VALUE), Long.MIN_VALUE
			).size
		)
	}

	@Test
	fun testStartArg() {
		val res = Array<List<Int>?>(2) { null }
		res[0] = findLessThan(
			u101, -17, 1
		)
		res[1] = findLessThan(
			u101, -17, 2
		)
		assertEquals(
			listOf(1, 2), res[0])
		assertEquals(
			listOf(2), res[1])
	}

	@Test
	fun testStartArgNegative() {
		assertEquals(
			3, findLessThan(
				u101, -17, -1
			).size
		)
		assertEquals(
			3, findLessThan(
				u101, -17, -1, 3
			).size
		)
	}

	@Test
	fun testEndArg() {
		val res = Array<List<Int>?>(4) { null }
		res[0] = findLessThan(
			u101, -17, 0, 6
		)
		res[1] = findLessThan(
			u101, -17, 0, 2
		)
		res[2] = findLessThan(
			u101, -17, 0, -1
		)
		res[3] = findLessThan(
			u101, -17, 0, 102
		)
		assertEquals(
			listOf(0, 1, 2), res[0])
		assertEquals(
			listOf(0, 1), res[1])
		assertEquals(
			0, res[2]?.size)
		assertEquals(
			listOf(0, 1, 2), res[3])
	}

	@Test
	fun testSublistSearch() {
		val res = Array<List<Int>?>(2) { null }
		res[0] = findLessThan(
			u101, -17, 1, 5
		)
		res[1] = findLessThan(
			u101.reversed(), -17, 95, 100
		)
		assertEquals(
			listOf(1, 2), res[0])
		assertEquals(
			listOf(98, 99), res[1])
	}

	@Test
	fun testBadIndexArgs() {
		val res = Array<List<Int>?>(2) { null }
		res[0] = findLessThan(
			u101, -17, 99, 99
		)
		res[1] = findLessThan(
			u101, -17, 99, 98
		)
		assertEquals(
			listOf(0, 0),
			res.map { it?.size }
		)
	}

}
package mathtools.lists.ints

import mathtools.lists.IntList.findGreaterThan
import mathtools.lists.NumberListConversion.toInt
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the IntList FindGreaterThan function
 * @author DK96-OS : 2022 */
class IntListFindGreaterThanTest {

	private val u101 = toInt(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findGreaterThan(
				emptyList(), 5
			).size
		)
	}

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
	fun testMaxValue() {
		assertEquals(
			listOf(0), findGreaterThan(
				listOf(Int.MAX_VALUE), 2
			)
		)
		// Max Value Limit
		assertEquals(
			0, findGreaterThan(
				listOf(Int.MAX_VALUE), Int.MAX_VALUE
			).size
		)
	}

	@Test
	fun testStartArg() {
		val res = Array<List<Int>?>(3) { null }
		res[0] = findGreaterThan(
			u101, 77, 96
		)
		res[1] = findGreaterThan(
			u101, 77, 99
		)
		res[2] = findGreaterThan(
			u101, 77, 120
		)
		assertEquals(
			listOf(98, 99, 100), res[0])
		assertEquals(
			listOf(99, 100), res[1])
		assertEquals(
			0, res[2]?.size)
	}

	@Test
	fun testStartArgNegative() {
		assertEquals(
			3, findGreaterThan(
				u101, 77, -1
			).size
		)
	}

	@Test
	fun testEndArg() {
		val res = Array<List<Int>?>(4) { null }
		res[0] = findGreaterThan(
			u101, -17, 0, 6
		)
		res[1] = findGreaterThan(
			u101, -17, 0, 5
		)
		res[2] = findGreaterThan(
			u101, -17, 0, -1
		)
		res[3] = findGreaterThan(
			u101, 77, 0, 120
		)
		assertEquals(
			listOf(4, 5), res[0])
		assertEquals(
			listOf(4), res[1])
		assertEquals(
			0, res[2]?.size)
		assertEquals(
			listOf(98, 99, 100), res[3])
	}

	@Test
	fun testSublistSearch() {
		val res = Array<List<Int>?>(2) { null }
		res[0] = findGreaterThan(
			u101, -17, 4, 6
		)
		res[1] = findGreaterThan(
			u101, 77, 95, 100
		)
		assertEquals(
			listOf(4, 5), res[0])
		assertEquals(
			listOf(98, 99), res[1])
	}

	@Test
	fun testBadArguments() {
		assertEquals(
			u101.size, findGreaterThan(
				u101, Int.MIN_VALUE, 0
			).size
		)
		assertEquals(
			5, findGreaterThan(
				u101, 75, -1
			).size
		)
		assertEquals(
			0, findGreaterThan(
				u101, 5, u101.size
			).size
		)
	}

}
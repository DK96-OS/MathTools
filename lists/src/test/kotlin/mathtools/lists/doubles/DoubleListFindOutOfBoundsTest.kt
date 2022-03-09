package mathtools.lists.doubles

import mathtools.lists.DoubleList.findOutOfBounds
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DoubleList's FindOutOfBounds function */
class DoubleListFindOutOfBoundsTest {

	private val u101 = toDouble(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findOutOfBounds(
				emptyList(), 1.0, 2.0
			).size
		)
	}

	@Test
	fun testSingleItemList() {
		assertEquals(
			0, findOutOfBounds(
				listOf(5.0), 0.0, 9.0
			).size
		)
		assertEquals(
			listOf(0), findOutOfBounds(
				listOf(5.0), -6.0, 4.9999
			)
		)
		assertEquals(
			listOf(0), findOutOfBounds(
				listOf(6.0), 6.000000001, Double.MAX_VALUE
			)
		)
	}

	@Test
	fun testPositiveInfinity() {
		assertEquals(
			0, findOutOfBounds(
				listOf(Double.POSITIVE_INFINITY),
				0.0, Double.POSITIVE_INFINITY
			).size
		)
		assertEquals(
			listOf(0), findOutOfBounds(
				listOf(Double.POSITIVE_INFINITY),
				0.0, Double.MAX_VALUE
			)
		)
	}

	@Test
	fun testNegativeInfinity() {
		assertEquals(
			0, findOutOfBounds(
				listOf(Double.NEGATIVE_INFINITY),
				Double.NEGATIVE_INFINITY, 0.0
			).size
		)
		assertEquals(
			listOf(0), findOutOfBounds(
				listOf(Double.NEGATIVE_INFINITY),
				0.0, Double.MAX_VALUE
			)
		)
		assertEquals(
			listOf(0), findOutOfBounds(
				listOf(Double.NEGATIVE_INFINITY),
				-Double.MAX_VALUE, Double.MIN_VALUE
			)
		)
	}

	@Test
	fun testInvalidArguments() {
		// Reversed Bounds
		assertEquals(
			0, findOutOfBounds(
				u101, 60.0, 40.0
			).size
		)
		// Upper Bound is NaN
		assertEquals(
			0, findOutOfBounds(
				u101, 10.0, Double.NaN,
			).size
		)
		// Lower Bound is NaN
		assertEquals(
			0, findOutOfBounds(
				u101, Double.NaN, 20.0
			).size
		)
	}

	@Test
	fun testEqualBounds() {
		// No elements equal to bounds
		assertEquals(
			u101.indices.toList(),
			findOutOfBounds(
				u101, 123.1, 123.1
			)
		)
		// One Element is equal to bounds
		assertEquals(
			u101.size - 1,
			findOutOfBounds(
				u101, 30.0, 30.0
			).size
		)
	}

	@Test
	fun testU101With20OutOfBoundsEachSide() {
		val res = findOutOfBounds(
			u101, 0.0, 60.0,
		)
		assertEquals(40, res.size)
		for (i in 0 until 20)
			assertEquals(
				(-20.0 + i), u101[res[i]]
			)
		for (i in 61 .. 80 )
			assertEquals(
				i.toDouble(), u101[res[i - 41]]
			)
	}

	@Test
	fun testU101AllOutOfBounds() {
		assertEquals(
			u101.indices.toList(),
			findOutOfBounds(
				u101, -200.0, -100.0
			)
		)
	}

	@Test
	fun testStartArg() {
		val res1 = findOutOfBounds(
			u101, -10.0, 80.0, 9
		)
		val res2 = findOutOfBounds(
			u101, -10.0, 80.0, 10
		)
		val res3 = findOutOfBounds(
			u101, -10.0, 79.0, 9
		)
		assertEquals(listOf(9), res1)
		assertEquals(0, res2.size)
		assertEquals(listOf(9, 100), res3)
	}

	@Test
	fun testEndArg() {
		val res1 = findOutOfBounds(
			u101, -20.0, 79.0, 0, 100
		)
		val res2 = findOutOfBounds(
			u101, -20.0, 79.0, 0, 105
		)
		val res3 = findOutOfBounds(
			u101, -19.0, 70.0, 0, 92
		)
		assertEquals(0, res1.size)
		assertEquals(listOf(100), res2)
		assertEquals(listOf(0, 91), res3)
	}

	@Test
	fun testSublistSearch() {
		val res1 = findOutOfBounds(
			u101, -10.0, 70.0, 9, 92
		)
		val res2 = findOutOfBounds(
			u101, -10.0, 70.0, 10, 92
		)
		val res3 = findOutOfBounds(
			u101, -10.0, 70.0, 9, 91
		)
		assertEquals(listOf(9, 91), res1)
		assertEquals(91, res2[0])
		assertEquals(9, res3[0])
	}

	@Test
	fun testBadIndexArgs() {
		val res = Array<List<Int>?>(3) { null }
		res[0] = findOutOfBounds(
			u101, -10.0, 70.0, 91, 91
		)
		res[1] = findOutOfBounds(
			u101, -10.0, 70.0, 91, 90
		)
		res[2] = findOutOfBounds(
			u101, -10.0, 70.0, -1, 91
		)
		assertEquals(
			listOf(0, 0, 0),
			res.map { it?.size }
		)
	}

	@Test
	fun testSplitSublistSearch() {
		val res = Array<List<Int>?>(4) { null }
		// Split search into 4 parts
		val indexRangePairs = listOf(
			0 to 24, 25 to 50, 51 to 75, 76 to 100,
		)
		for (i in 0 until 4)
			res[i] = findOutOfBounds(
				u101, 10.0, 50.0,
				indexRangePairs[i].first, indexRangePairs[i].second + 1
			)
		assertEquals(25, res[0]!!.size)
		assertEquals(5, res[1]!!.size)
		assertEquals(5, res[2]!!.size)
		assertEquals(25, res[3]!!.size)
	}

}
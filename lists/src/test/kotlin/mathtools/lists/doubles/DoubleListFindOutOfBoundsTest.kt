package mathtools.lists.doubles

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
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
	fun testBadArguments() {
		// Reversed Bounds
		assertEquals(
			0, findOutOfBounds(
				u101, 60.0, 40.0
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
		val result = findOutOfBounds(
			u101, 0.0, 60.0,
		)
		assertEquals(40, result.size)
		for (i in 0 until 20)
			assertEquals(
				(-20.0 + i), u101[result[i]]
			)
		for (i in 61 .. 80 )
			assertEquals(
				i.toDouble(), u101[result[i - 41]]
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
		val result1 = findOutOfBounds(
			u101, -10.0, 80.0, 9
		)
		val result2 = findOutOfBounds(
			u101, -10.0, 80.0, 10
		)
		val result3 = findOutOfBounds(
			u101, -10.0, 79.0, 9
		)
		assertEquals(listOf(9), result1)
		assertEquals(0, result2.size)
		assertEquals(listOf(9, 100), result3)
	}

	@Test
	fun testEndArg() {
		val result1 = findOutOfBounds(
			u101, -20.0, 79.0, 0, 100
		)
		val result2 = findOutOfBounds(
			u101, -20.0, 79.0, 0, 101
		)
		val result3 = findOutOfBounds(
			u101, -19.0, 70.0, 0, 92
		)
		assertEquals(0, result1.size)
		assertEquals(100, result2[0])
		assertEquals(listOf(0, 91), result3)
	}

	@Test
	fun testSublistSearch() {
		val result1 = findOutOfBounds(
			u101, -10.0, 70.0, 9, 92
		)
		val result2 = findOutOfBounds(
			u101, -10.0, 70.0, 10, 92
		)
		val result3 = findOutOfBounds(
			u101, -10.0, 70.0, 9, 91
		)
		assertEquals(listOf(9, 91), result1)
		assertEquals(91, result2[0])
		assertEquals(9, result3[0])
	}

	@Test
	fun testBadIndexArgs() {
		val results = Array<List<Int>?>(3) { null }
		results[0] = findOutOfBounds(
			u101, -10.0, 70.0, 91, 91
		)
		results[1] = findOutOfBounds(
			u101, -10.0, 70.0, 91, 90
		)
		results[2] = findOutOfBounds(
			u101, -10.0, 70.0, -1, 91
		)
		assertEquals(
			listOf(0, 0, 0),
			results.map { it?.size }
		)
	}

	@Test
	fun testSplitSublistSearch() {
		val results = Array<Deferred<List<Int>>?>(4) { null }
		runBlocking {
			for (i in 0 until 4) {
				val startIdx = i * 25
				val endIdx = if (i == 3)
					101 else startIdx + 25
				results[i] = async {
					findOutOfBounds(
						u101, 10.0, 50.0,
						startIdx, endIdx
					)
				}
			}
			assertEquals(25, results[0]!!.await().size)
			assertEquals(5, results[1]!!.await().size)
			assertEquals(4, results[2]!!.await().size)
			assertEquals(26, results[3]!!.await().size)
		}
	}

}
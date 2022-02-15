package mathtools.lists.longs

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import mathtools.lists.LongList.findOutOfRange
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DoubleList's findOutOfRange function
 * @author DK96-OS : 2022 */
class LongListFindOutOfRangeTest {

	private val u101 = toLong(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findOutOfRange(
				emptyList(), 1L.. 20
			).size
		)
	}

	@Test
	fun testSingleItemList() {
		assertEquals(
			0, findOutOfRange(
				listOf(5L), 0L .. 9
			).size
		)
		assertEquals(
			listOf(0), findOutOfRange(
				listOf(5L), -6L .. 4L
			)
		)
		assertEquals(
			listOf(0), findOutOfRange(
				listOf(6L), 7 .. Long.MAX_VALUE
			)
		)
	}

	@Test
	fun testBadArguments() {
		// Reversed Bounds
		assertEquals(
			0, findOutOfRange(
				u101, 60L..40L
			).size
		)
	}

	@Test
	fun testEqualBounds() {
		// No elements equal to bounds
		assertEquals(
			u101.size,
			findOutOfRange(
				u101, 121L..121
			).size
		)
		// One Element is equal to bounds
		assertEquals(
			u101.size - 1,
			findOutOfRange(
				u101, 30L ..30L
			).size
		)
	}

	@Test
	fun testU101With20EachSide() {
		val result = findOutOfRange(
			u101, 0 ..60L,
		)
		assertEquals(40, result.size)
		for (i in 0 until 20)
			assertEquals(
				(-20L + i), u101[result[i]]
			)
		for (i in 61 .. 80 )
			assertEquals(
				i.toLong(), u101[result[i - 41]]
			)
	}

	@Test
	fun testU101AllOutOfRange() {
		assertEquals(
			u101.indices.toList(),
			findOutOfRange(
				u101, -200L .. -100
			)
		)
	}

	@Test
	fun testStartArg() {
		val result1 = findOutOfRange(
			u101, -10 .. 80L, 9
		)
		val result2 = findOutOfRange(
			u101, -10 .. 80L, 10
		)
		val result3 = findOutOfRange(
			u101, -10 .. 79L, 9
		)
		assertEquals(listOf(9), result1)
		assertEquals(0, result2.size)
		assertEquals(listOf(9, 100), result3)
	}

	@Test
	fun testEndArg() {
		val result1 = findOutOfRange(
			u101, -20 .. 79L, 0, 100
		)
		val result2 = findOutOfRange(
			u101, -20 ..79L, 0, 105
		)
		val result3 = findOutOfRange(
			u101, -19 .. 70L, 0, 92
		)
		assertEquals(0, result1.size)
		assertEquals(100, result2[0])
		assertEquals(listOf(0, 91), result3)
	}

	@Test
	fun testSublistSearch() {
		val res = Array<List<Int>?>(3) { null }
		res[0] = findOutOfRange(
			u101, -10 .. 70L, 9, 92
		)
		res[1] = findOutOfRange(
			u101, -10 .. 70L, 10, 92
		)
		res[2] = findOutOfRange(
			u101, -10 .. 70L, 9, 91
		)
		assertEquals(listOf(9, 91), res[0])
		assertEquals(listOf(91), res[1])
		assertEquals(listOf(9), res[2])
	}

	@Test
	fun testSplitSublistSearch() {
		runBlocking {
			val results = Array<Deferred<List<Int>>?>(4) { null }
			val indexRangePairs = listOf(
				0 to 24, 25 to 50, 51 to 74, 75 to 101
			)
			for (i in 0 until 4)
				results[i] = async {
					findOutOfRange(
						u101, 10 .. 50L,
						indexRangePairs[i].first,
						indexRangePairs[i].second,
					)
				}
			assertEquals(25, results[0]!!.await().size)
			assertEquals(5, results[1]!!.await().size)
			assertEquals(5, results[2]!!.await().size)
			assertEquals(25, results[3]!!.await().size)
		}
	}

}
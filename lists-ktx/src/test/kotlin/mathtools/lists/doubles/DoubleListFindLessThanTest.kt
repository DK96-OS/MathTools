package mathtools.lists.doubles

import mathtools.lists.DoubleList.findLessThan
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.math.roundToInt

/** Testing the DoubleList FindLessThan function
 * @author DK96-OS : 2022 */
class DoubleListFindLessThanTest {

	private val u101 = toDouble(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findLessThan(
				emptyList(), 10.0,
			).size
		)
	}

	@Test
	fun testSingleItem() {
		assertEquals(
			0, findLessThan(
				listOf(5.5), 5.5
			).size
		)
		assertEquals(
			listOf(0), findLessThan(
				listOf(5.5), 5.51
			)
		)
	}

	@Test
	fun testMaxValue() {
		assertEquals(
			0, findLessThan(
				listOf(Double.MAX_VALUE), Double.MAX_VALUE
			).size
		)
	}

	@Test
	fun testInfiniteLimits() {
		assertEquals(
			u101.size, findLessThan(
				u101, Double.POSITIVE_INFINITY,
			).size
		)
		assertEquals(
			0, findLessThan(
				u101, Double.NEGATIVE_INFINITY,
			).size
		)
	}

	@Test
	fun testNaNLimit() {
		assertEquals(
			0, findLessThan(
				u101, Double.NaN,
			).size
		)
	}

	@Test
	fun testStartArg() {
		val res = Array<List<Int>?>(2) { null }
		res[0] = findLessThan(
			u101, -17.5, 1
		)
		res[1] = findLessThan(
			u101.reversed(), -17.5, 99
		)
		assertEquals(listOf(1, 2), res[0])
		assertEquals(listOf(99, 100), res[1])
	}

	@Test
	fun testStartArgNegative() {
		assertEquals(
			3, findLessThan(
				u101, -17.5, -1
			).size
		)
		assertEquals(
			3, findLessThan(
				u101, -17.5, -1, 3
			).size
		)
	}

	@Test
	fun testEndArg() {
		val res = Array<List<Int>?>(3) { null }
		res[0] = findLessThan(
			u101, -17.5, 0, 6
		)
		res[1] = findLessThan(
			u101, -17.5, 0, 2
		)
		res[2] = findLessThan(
			u101, -17.5, 0, 105
		)
		assertEquals(
			listOf(0, 1, 2), res[0])
		assertEquals(
			listOf(0, 1), res[1])
		assertEquals(
			listOf(0, 1, 2), res[2])
	}

	@Test
	fun testSublistSearch() {
		val res = Array<List<Int>?>(2) { null }
		res[0] = findLessThan(
			u101, -17.5, 1, 5
		)
		res[1] = findLessThan(
			u101.reversed(), -17.5, 95, 100
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
			u101, -17.5, 99, 99
		)
		res[1] = findLessThan(
			u101, -17.5, 99, 98
		)
		assertEquals(
			listOf(0, 0),
			res.map { it?.size }
		)
	}

	@RepeatedTest(2)
	fun testShuffledList() {
		val shuffledList = u101.shuffled()
		val result = findLessThan(
			shuffledList, -15.0
		)
		assertEquals(
			5, result.size)
		assertEquals(
			(-20 .. -16).toList(),
			result.map { shuffledList[it].roundToInt() }.sorted()
		)
	}

}
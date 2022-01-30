package mathtools.numbers.listtypes.doubles

import mathtools.numbers.listtypes.DoubleList.findOutOfBounds
import mathtools.numbers.listtypes.ListNumberTypes.convertToDouble
import mathtools.numbers.statistics.StatisticsTestResources.largeByteList
import mathtools.numbers.statistics.StatisticsTestResources.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DoubleList's FindOutOfBounds function */
class DoubleListFindOutOfBoundsTest {

	private val u101 = convertToDouble(uniform101)
	private val large120 = convertToDouble(largeByteList)

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
		assertEquals(
			large120.indices.toList(),
			findOutOfBounds(
				large120, 120.1, 120.1
			)
		)
		assertEquals(
			large120.size - 100_000,
			findOutOfBounds(
				large120, 120.0, 120.0
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
	fun testLarge120ExcludeBottom10() {
		val result = findOutOfBounds(
			large120, 110.0, 125.0
		)
		assertEquals(50_000, result.size)
		for (i in 0 until 50_000)
			assertEquals(
				100.0 + (i / 5_000),
				large120[result[i]]
			)
	}

}
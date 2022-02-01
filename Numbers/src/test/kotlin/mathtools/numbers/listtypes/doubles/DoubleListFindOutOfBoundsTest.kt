package mathtools.numbers.listtypes.doubles

import mathtools.numbers.listtypes.DoubleList.findOutOfBounds
import mathtools.numbers.listtypes.NumberListConversion.toDouble
import mathtools.numbers.testdata.UniformTestDataSource.uniform101
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

}
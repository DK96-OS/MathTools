package mathtools.numbers.listtypes.longs

import mathtools.lists.LongList.findOutOfRange
import mathtools.lists.NumberListConversion.toLong
import mathtools.numbers.testdata.UniformTestDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DoubleList's findOutOfRange function */
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

}
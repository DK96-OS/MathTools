package mathtools.numbers.listtypes.longs

import mathtools.numbers.listtypes.LongList.findOutOfRange
import mathtools.numbers.listtypes.NumberListConversion.toLong
import mathtools.numbers.testdata.LargeTestDataSource
import mathtools.numbers.testdata.UniformTestDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DoubleList's findOutOfRange function */
class LongListFindOutOfRangeTest {

	private val u101 = toLong(uniform101)
	private val data = LargeTestDataSource()
	private val large120 = toLong(data.large120)

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
		assertEquals(
			large120.size - 20_000,
			findOutOfRange(
				large120, 121L..121
			).size
		)
		assertEquals(
			large120.size - 100_000,
			findOutOfRange(
				large120, 120L ..120L
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
	fun testLarge120ExcludeBottom10() {
		val result = findOutOfRange(
			large120, 110L .. 125L
		)
		assertEquals(50_000, result.size)
		for (i in 0 until 50_000)
			assertEquals(
				100L + (i / 5_000),
				large120[result[i]]
			)
	}

}
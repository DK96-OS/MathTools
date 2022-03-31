package mathtools.lists.longs

import mathtools.lists.LongList.removeByIndices
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing LongList RemoveByIndices Function
 * @author DK96-OS : 2022 */
class LongListRemoveByIndicesTest {

	@Test
	fun testEmptyLists() {
		assertEquals(
			0, removeByIndices(
				emptyList<Long>().toMutableList(), emptyList()
			).size
		)
		assertEquals(
			0, removeByIndices(
				mutableListOf(5L), emptyList()
			).size
		)
		assertEquals(
			0, removeByIndices(
				mutableListOf<Long>(), listOf(5)
			).size
		)
	}

	@Test
	fun testMonotonicIncreasingIndices() {
		val data = toLong(uniform101)
		val indices = listOf(
			4, 10, 20, 30
		)
		assertEquals(
			listOf(-16L, -10L, 0L, 10L),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testMonotonicDecreasingIndices() {
		val data = toLong(uniform101)
		val indices = listOf(
			30, 20, 10, 4
		)
		assertEquals(
			listOf(10L, 0L, -10L, -16L),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testUnsortedIndices() {
		val data = toLong(uniform101)
		val indices = listOf(
			50, 20, 40, 80, 10
		)
		assertEquals(
			listOf(30L, 0L, 20L, 60L, -10L),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testIndexOutOfBounds() {
		val data = toLong(uniform101)
		val indices = listOf(
			10, 20, 105
		)
		assertEquals(
			listOf(-10L, 0L),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testInvalidIndices() {
		val data = mutableListOf(400L, 500L, Long.MAX_VALUE)
		val indices = listOf(-1, 400)
		assertEquals(
			0, removeByIndices(data, indices))
	}

}
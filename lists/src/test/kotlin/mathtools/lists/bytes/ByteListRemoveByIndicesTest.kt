package mathtools.lists.bytes

import mathtools.lists.ByteList.removeByIndices
import mathtools.lists.NumberListConversion.toByte
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing IntList RemoveByIndices Function
 * @author DK96-OS : 2022 */
class ByteListRemoveByIndicesTest {

	@Test
	fun testEmptyLists() {
		assertEquals(
			0, removeByIndices(
				emptyList<Byte>().toMutableList(), emptyList()
			).size
		)
		assertEquals(
			0, removeByIndices(
				mutableListOf(5), emptyList()
			).size
		)
		assertEquals(
			0, removeByIndices(
				mutableListOf(), listOf(5)
			).size
		)
	}

	@Test
	fun testMonotonicIncreasingIndices() {
		val data = toByte(uniform101)
		val indices = listOf(
			4, 10, 20, 30
		)
		assertEquals(
			listOf<Byte>(-16, -10, 0, 10),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testMonotonicDecreasingIndices() {
		val data = toByte(uniform101)
		val indices = listOf(
			30, 20, 10, 4
		)
		assertEquals(
			listOf<Byte>(10, 0, -10, -16),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testUnsortedIndices() {
		val data = toByte(uniform101)
		val indices = listOf(
			50, 20, 40, 80, 10
		)
		assertEquals(
			listOf<Byte>(30, 0, 20, 60, -10),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testIndexOutOfBounds() {
		val data = toByte(uniform101)
		val indices = listOf(
			10, 20, 105
		)
		assertEquals(
			listOf<Byte>(-10, 0),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testInvalidIndicesProvided() {
		val data = toByte(uniform101)
		assertEquals(
			emptyList<Int>(),
			removeByIndices(data, listOf(-1, 300))
		)
		// No items removed
		assertEquals(
			uniform101.size, data.size)
	}

}
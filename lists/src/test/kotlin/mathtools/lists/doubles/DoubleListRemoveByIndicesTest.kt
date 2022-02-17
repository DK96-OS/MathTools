package mathtools.lists.doubles

import mathtools.lists.DoubleList.removeByIndices
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DoubleList RemoveByIndices Function
 * @author DK96-OS : 2022 */
class DoubleListRemoveByIndicesTest {

	@Test
	fun testEmptyLists() {
		assertEquals(
			0, removeByIndices(
				emptyList<Double>().toMutableList(), emptyList()
			).size
		)
		assertEquals(
			0, removeByIndices(
				mutableListOf(5.0), emptyList()
			).size
		)
		assertEquals(
			0, removeByIndices(
				mutableListOf<Double>(), listOf(5)
			).size
		)
	}

	@Test
	fun testMonotonicIncreasingIndices() {
		val data = toDouble(uniform101)
		val indices = listOf(
			4, 10, 20, 30
		)
		assertEquals(
			listOf(-16.0, -10.0, 0.0, 10.0),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testMonotonicDecreasingIndices() {
		val data = toDouble(uniform101)
		val indices = listOf(
			30, 20, 10, 4
		)
		assertEquals(
			listOf(10.0, 0.0, -10.0, -16.0),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testUnsortedIndices() {
		val data = toDouble(uniform101)
		val indices = listOf(
			50, 20, 40, 80, 10
		)
		assertEquals(
			listOf(30.0, 0.0, 20.0, 60.0, -10.0),
			removeByIndices(data, indices)
		)
	}

	@Test
	fun testIndexOutOfBounds() {
		val data = toDouble(uniform101)
		val indices = listOf(
			10, 20, 105
		)
		assertEquals(
			listOf(-10.0, 0.0),
			removeByIndices(data, indices)
		)
	}

}
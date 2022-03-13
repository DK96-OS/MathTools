package mathtools.lists.arrays

import mathtools.lists.arrays.ShortArrayExtensions.clear
import mathtools.lists.arrays.ShortArrayExtensions.isNonZero
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

/** Testing ShortArray methods
 * @author DK96-OS : 2022 */
class ShortArrayExtensionsTest {

	private val zero: Short = (0).toShort()

	@Test
	fun testClearArray() {
		val array = ShortArray(8) { it.toShort() }
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
		//
		array[3] = -9
		array[5] = 2
		//
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
	}

	@Test
	fun testClearArrayEmpty() {
		val array = ShortArray(8)
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
	}

	@Test
	fun testNonZero() {
		val array = ShortArray(8)
		assertFalse(array.isNonZero())
		Arrays.fill(array, 5)
		assertTrue(array.isNonZero())
		array[6] = 0
		assertFalse(array.isNonZero())
	}

}
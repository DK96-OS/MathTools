package mathtools.arrays

import mathtools.arrays.IntArrayExtensions.clear
import mathtools.arrays.IntArrayExtensions.isNonZero
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.Arrays

/** Testing IntArray methods
 * @author DK96-OS : 2022 */
class IntArrayExtensionsTest {

	private val zero: Int = 0

	@Test
	fun testClearArray() {
		val array = IntArray(8) { it }
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
		val array = IntArray(8)
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
	}

	@Test
	fun testNonZero() {
		val array = IntArray(8)
		assertFalse(array.isNonZero())
		Arrays.fill(array, 5)
		assertTrue(array.isNonZero())
		array[6] = zero
		assertFalse(array.isNonZero())
	}

}
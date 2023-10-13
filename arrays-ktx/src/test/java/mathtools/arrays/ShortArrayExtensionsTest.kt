package mathtools.arrays

import mathtools.arrays.ShortArrayExtensions.clear
import mathtools.arrays.ShortArrayExtensions.isNonZero
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.Arrays

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
package mathtools.arrays

import mathtools.arrays.ByteArrayExtensions.clear
import mathtools.arrays.ByteArrayExtensions.isNonZero
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.Arrays

/** Testing ByteArray methods
 * @author DK96-OS : 2022 */
class ByteArrayExtensionsTest {

	private val zero: Byte = (0).toByte()

	@Test
	fun testClearArray() {
		val array = ByteArray(8) { it.toByte() }
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
		val array = ByteArray(8)
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
	}

	@Test
	fun testNonZero() {
		val array = ByteArray(8)
		assertFalse(array.isNonZero())
		Arrays.fill(array, 5)
		assertTrue(array.isNonZero())
		array[6] = 0
		assertFalse(array.isNonZero())
	}

}
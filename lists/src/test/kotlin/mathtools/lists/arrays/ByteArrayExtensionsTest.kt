package mathtools.lists.arrays

import mathtools.lists.arrays.ByteArrayExtensions.clear
import mathtools.lists.arrays.ByteArrayExtensions.isNonZero
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

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
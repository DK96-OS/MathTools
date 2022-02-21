package mathtools.lists.arrays

import mathtools.lists.arrays.ByteArrayExtensions.clear
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing ByteArray methods
 * @author DK96-OS : 2022 */
class ByteArrayExtensionsTest {

	private val zero: Byte = (0).toByte()

	@Test
	fun testClearArray() {
		val array = ByteArray(20) { it.toByte() }
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
		//
		array[3] = -9
		array[15] = 2
		array[18] = -0
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
	}

	@Test
	fun testClearArrayEmpty() {
		val array = ByteArray(20)
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
	}

}
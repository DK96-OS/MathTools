package mathtools.lists.arrays

import mathtools.lists.arrays.LongArrayExtensions.clear
import mathtools.lists.arrays.LongArrayExtensions.isNonZero
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

/** Testing LongArray methods
 * @author DK96-OS : 2022 */
class LongArrayExtensionsTest {

	private val zero: Long = 0L

	@Test
	fun testClearArray() {
		val array = LongArray(8) { it.toLong() }
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
		val array = LongArray(8)
		array.clear()
		for (i in array.indices)
			assertEquals(zero, array[i])
	}

	@Test
	fun testNonZero() {
		val array = LongArray(8)
		assertFalse(array.isNonZero())
		Arrays.fill(array, 5)
		assertTrue(array.isNonZero())
		array[6] = zero
		assertFalse(array.isNonZero())
	}

}
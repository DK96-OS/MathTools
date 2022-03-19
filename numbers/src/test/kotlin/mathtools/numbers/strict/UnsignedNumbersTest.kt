package mathtools.numbers.strict

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing Unsigned Number Types
 * @author DK96-OS : 2022 */
class UnsignedNumbersTest {

	@Test
	fun testUByteConversions() {
		val positiveByte: Byte = 127
		val negativeByte = 128.toByte()
		assertEquals(
			127, positiveByte.toInt())
		assertEquals(
			-128, negativeByte.toInt())
		assertEquals(
			-128, negativeByte.toUByte().toByte().toInt())
		//
		val nByte2 = (-120).toByte()
		assertEquals("-120", nByte2.toString())
		val uByte2 = nByte2.toUByte()
		val uByte2Result = 128 + (128 + nByte2)
		assertEquals(
			uByte2.toString(), uByte2Result.toString())
		assertEquals(
			"136", uByte2.toInt().toString())
	}

}
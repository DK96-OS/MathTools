package mathtools.numbers.format

import mathtools.numbers.format.StringPackager.packBytes
import mathtools.numbers.format.StringPackager.packShort
import mathtools.numbers.format.StringPackager.packUShort
import mathtools.numbers.format.StringPackager.unpackByte0
import mathtools.numbers.format.StringPackager.unpackByte1
import mathtools.numbers.format.StringPackager.unpackBytes
import mathtools.numbers.format.StringPackager.unpackShort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/** Testing StringPackager functions of Integers
 * @author DK96-OS : 2022 */
class StringPackagerIntTest {

	@Test
	fun testPositiveBytes() {
		//
		for (b0Int in 0 .. Byte.MAX_VALUE step 4) {
			val b0 = b0Int.toByte()
			//
			for (b1Int in 0 .. Byte.MAX_VALUE step 4) {
				val b1 = b1Int.toByte()
				val packed = packBytes(b0, b1)
				// Check individual byte unpacking
				assertEquals(
					b0, unpackByte0(packed))
				assertEquals(
					b1, unpackByte1(packed))
				// Check pair of bytes unpacked
				val unpackedPair = unpackBytes(packed)
				assertEquals(
					b0, unpackedPair.first)
				assertEquals(
					b1, unpackedPair.second)
			}
		}
	}

	@Test
	fun testNegativeBytes() {
		for (b0Int in Byte.MIN_VALUE until 0 step 4) {
			val b0 = b0Int.toByte()
			for (b1Int in Byte.MIN_VALUE until 0 step 4) {
				val b1 = b1Int.toByte()
				val packed = packBytes(b0, b1)
				// Check individual byte unpacking
				assertEquals(
					b0, unpackByte0(packed))
				assertEquals(
					b1, unpackByte1(packed))
				// Check pair function
				val unpackedPair = unpackBytes(packed)
				assertEquals(
					b0, unpackedPair.first)
				assertEquals(
					b1, unpackedPair.second)
			}
		}
	}

	@Test
	fun testPositiveShorts() {
		for (sInt in 0 .. Short.MAX_VALUE step 500) {
			val s = sInt.toShort()
			val uShort = sInt.toUShort()
			val packed = packShort(s)
			val uPacked = packUShort(uShort)
			// Both versions should be the same
			assertEquals(
				packed.code, uPacked.code)
			//
			assertEquals(
				s, unpackShort(packed))
			assertEquals(
				uShort, unpackShort(uPacked).toUShort())
		}
	}

	@Test
	fun testNegativeShorts() {
		for (sInt in Short.MIN_VALUE until 0 step 500) {
			val s = sInt.toShort()
			val uShort = sInt.toUShort()
			val packed = packShort(s)
			val uPacked = packUShort(uShort)
			// Both versions should be the same
			assertEquals(
				packed.code, uPacked.code)
			//
			assertEquals(
				s, unpackShort(packed))
			assertEquals(
				uShort, unpackShort(uPacked).toUShort())
		}
	}

	@Test
	fun testShortUniqueness() {
		val map = java.util.HashMap<Char, Boolean>()
		for (sInt in Short.MIN_VALUE .. Short.MAX_VALUE) {
			val packed = packShort(sInt.toShort())
			assertNull(
				map[packed])
			map[packed] = true
		}
		assertTrue(
			map[packShort(25)]!!)
		assertTrue(
			map[packShort(-25)]!!)
	}

}
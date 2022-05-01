package mathtools.numbers.format

import mathtools.numbers.format.StringPackager.packUShort
import mathtools.numbers.format.StringPackager.unpackShort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing StringPackager functions of Integers
 * @author DK96-OS : 2022 */
class StringPackagerShortTest {

	@Test
	fun testPositiveShorts() {
		for (sInt in 0 .. Short.MAX_VALUE step 500) {
			val s = sInt.toShort()
			val uShort = sInt.toUShort()
			val packed = NumberSerializer.putShort(s)
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
			val packed = NumberSerializer.putShort(s)
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

}
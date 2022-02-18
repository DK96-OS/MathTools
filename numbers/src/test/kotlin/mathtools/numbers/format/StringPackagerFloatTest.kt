package mathtools.numbers.format

import mathtools.numbers.format.StringPackager.packFloat
import mathtools.numbers.format.StringPackager.unpackFloat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing StringPackager functions of Float
 * @author DK96-OS : 2022 */
class StringPackagerFloatTest {

	@Test
	fun testFloat() {
		for (e in 0 until 128) {
			val exp = e.shl(23)
			for (m in 0 until 23) {
				val mBit = (1).shl(m)
				val f = Float.fromBits(exp + mBit)
				val packed = packFloat(f)
				//
				assertEquals(2, packed.length)
				assertEquals(f, unpackFloat(packed[0], packed[1]))
			}
		}
	}

}
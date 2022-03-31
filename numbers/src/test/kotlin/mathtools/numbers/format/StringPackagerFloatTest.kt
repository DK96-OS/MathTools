package mathtools.numbers.format

import mathtools.numbers.format.StringPackager.packFloat
import mathtools.numbers.format.StringPackager.packFloats
import mathtools.numbers.format.StringPackager.unpackFloat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

/** Testing StringPackager functions of Float
 * @author DK96-OS : 2022 */
class StringPackagerFloatTest {

	@Test
	fun testFloat() {
		//
		for (e in 0 until 128) {
			val exp = e.shl(23)
			//
			for (m in 0 until 23) {
				val mBit = (1).shl(m)
				//
				val f = Float.fromBits(exp + mBit)
				val packed = packFloat(f)
				//
				assertEquals(
					2, packed.length)
				assertEquals(
					f, unpackFloat(packed[0], packed[1]))
			}
		}
	}

	@Test
	fun testMultipleFloats() {
		val inputs = FloatArray(100) { Random.nextFloat() }
		val packed = packFloats(*inputs)
		//
		assertEquals(
			2 * inputs.size, packed.length)
		//
		for (i in inputs.indices) {
			val strIndex = i * 2
			//
			assertEquals(
				inputs[i],
				unpackFloat(
					packed[strIndex],
					packed[strIndex + 1]
				)
			)
		}
	}

	@Test
	fun testPackFloatsSingleInput() {
		//
		for (i in 0 until 10) {
			val randF = Random.nextFloat()
			val packed = packFloats(randF)
			//
			assertEquals(
				2, packed.length)
			assertEquals(
				randF,
				unpackFloat(
					packed[0],
					packed[1]
				)
			)
		}
	}

}
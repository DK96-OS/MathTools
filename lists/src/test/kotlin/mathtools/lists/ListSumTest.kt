package mathtools.lists

import mathtools.lists.NumberListConversion.toByte
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.NumberListConversion.toFloat
import mathtools.lists.NumberListConversion.toInt
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.NumberListConversion.toShort
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/** Testing the ListSum function
 * @author DK96-OS : 2022 */
class ListSumTest {

	@Test
	fun testSumOfBytes() {
		val data = toByte(uniform101)
		val sum = listSum(data)
		assertEquals(
			3030.0, sum
		)
	}

	@Test
	fun testSumOfShorts() {
		val data = toShort(uniform101)
		val sum = listSum(data)
		assertEquals(
			3030.0, sum
		)
	}

	@Test
	fun testSumOfInts() {
		val data = toInt(uniform101)
		val sum = listSum(data)
		assertEquals(
			3030.0, sum
		)
	}

	@Test
	fun testSumOfLongs() {
		val data = toLong(uniform101)
		val sum = listSum(data)
		assertEquals(
			3030.0, sum
		)
	}

	@Test
	fun testSumOfFloats() {
		val data = toFloat(uniform101)
		val sum = listSum(data)
		assertEquals(
			3030.0, sum
		)
	}

	@Test
	fun testSumOfDoubles() {
		val data = toDouble(uniform101)
		val sum = listSum(data)
		assertEquals(
			3030.0, sum
		)
	}

	@Test
	fun testInvalidType() {
		assertThrows<IllegalArgumentException> {
			listSum(listOf(object: Number() {
				override fun toByte(): Byte = 1
				override fun toShort(): Short = 1
				override fun toInt(): Int = 1
				override fun toLong(): Long = 1
				override fun toFloat(): Float = 1f
				override fun toDouble(): Double = 1.0
				override fun toChar(): Char = '1'
			}))
		}
	}

}
package mathtools.lists.bytes

import mathtools.lists.ByteList.largeSum
import mathtools.lists.NumberListConversion.toByte
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

/** Testing [ByteList] LargeSum function
 * @author DK96-OS : 2022 */
class ByteListLargeSumTest {

	private val limit: Byte = (Byte.MAX_VALUE / 2).toByte()
	private val bigLimit = BigInteger.valueOf(limit.toLong())

	@Test
	fun testU101() {
		val data = toByte(uniform101)
		assertEquals(
			BigInteger.valueOf(3030),
			largeSum(data)
		)
	}

	@Test
	fun testMaxIntValues() {
		val data = mutableListOf<Byte>()
		for (i in 0 until 50)
			data.add(Byte.MAX_VALUE)
		assertEquals(
			BigInteger.valueOf(50L * Byte.MAX_VALUE),
			largeSum(data)
		)
	}

	@Test
	fun testSmallLists() {
		assertEquals(
			BigInteger.ZERO, largeSum(emptyList()))
		//
		assertEquals(
			bigLimit, largeSum(listOf(limit)))
		//
		assertEquals(
			bigLimit.multiply(BigInteger.TWO),
			largeSum(listOf(limit, limit))
		)
	}

	@Test
	fun testNegativeValues() {
		val value: Byte = (-(limit + 1) / 2).toByte()
		val array = Array(8) { value }
		//
		assertEquals(
			BigInteger.valueOf(value.toLong()).multiply(
				BigInteger.valueOf(8)
			),
			largeSum(array.toList())
		)
	}

}
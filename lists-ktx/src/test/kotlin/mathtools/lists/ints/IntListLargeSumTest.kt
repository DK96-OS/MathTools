package mathtools.lists.ints

import mathtools.lists.IntList.largeSum
import mathtools.lists.NumberListConversion.toInt
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

/** Testing the IntList LargeSum function
 * @author DK96-OS : 2022 */
class IntListLargeSumTest {

	private val limit = Integer.MAX_VALUE / 2
	private val bigLimit = BigInteger.valueOf(limit.toLong())

	@Test
	fun testU101() {
		val data = toInt(uniform101)
		assertEquals(
			BigInteger.valueOf(3030), largeSum(data)
		)
	}

	@Test
	fun testMaxIntValues() {
		val data = mutableListOf<Int>()
		for (i in 0 until 5000)
			data.add(Int.MAX_VALUE)
		assertEquals(
			BigInteger.valueOf(5000L * Int.MAX_VALUE), largeSum(data)
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
			bigLimit.multiply(BigInteger.valueOf(2)),
			largeSum(listOf(limit, limit))
		)
	}

	@Test
	fun testNegativeValues() {
		val value = -(limit + 1) / 2
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
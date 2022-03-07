package mathtools.lists.longs

import mathtools.lists.LongList.largeSum
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

/** Testing the LongList LargeSum function
 * @author DK96-OS : 2022 */
class LongListLargeSumTest {

	private val limit = Long.MAX_VALUE / 2
	private val bigLimit = BigInteger.valueOf(limit)

	@Test
	fun testU101() {
		val data = toLong(uniform101)
		assertEquals(
			BigInteger.valueOf(3030), largeSum(data)
		)
	}

	@Test
	fun testMaxIntValues() {
		val data = mutableListOf<Long>()
		for (i in 0 until 5000)
			data.add(Long.MAX_VALUE)
		var maxLong = BigInteger.valueOf(Long.MAX_VALUE)
		maxLong *= BigInteger.valueOf(5000)
		assertEquals(
			maxLong, largeSum(data)
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
		val value = -(limit + 1) / 2
		val array = Array(8) { value }
		//
		assertEquals(
			BigInteger.valueOf(value).multiply(BigInteger.valueOf(8)),
			largeSum(array.toList())
		)
	}

}
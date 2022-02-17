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

}
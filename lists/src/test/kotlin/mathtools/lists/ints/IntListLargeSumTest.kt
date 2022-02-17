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

}
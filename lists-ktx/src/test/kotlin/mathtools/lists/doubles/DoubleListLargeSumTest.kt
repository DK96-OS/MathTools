package mathtools.lists.doubles

import mathtools.lists.DoubleList
import mathtools.lists.DoubleList.largeSum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

/** Testing [DoubleList.largeSum] method
 * @author DK96-OS : 2022
 */
class DoubleListLargeSumTest {

	@Test
	fun testEmptyList() {
		assertEquals(
			BigDecimal.ZERO, largeSum(emptyList())
		)
	}

	@Test
	fun testSingleValuedList() {
		val input: MutableList<Double> = ArrayList()
		var value = 1.0
		input.add(value)
		assertEquals(
			BigDecimal.valueOf(1.0), largeSum(input)
		)
		// Try a second value
		value = 33.56
		input.clear()
		input.add(value)
		assertEquals(
			BigDecimal.valueOf(value), largeSum(input)
		)
	}

	@Test
	fun testMaxDouble() {
		val max = Double.MAX_VALUE
		val bigMax = BigDecimal.valueOf(max)
		assertEquals(
			0, bigMax.compareTo(
				largeSum(listOf(max))
			)
		)
		//
		val list: MutableList<Double> = ArrayList()
		for (i in 0 .. 9) list.add(Double.MAX_VALUE)
		assertEquals(
			0, bigMax.multiply(BigDecimal.TEN).compareTo(
				largeSum(list)
			)
		)
	}

	@Test
	fun testNegativeValues() {
		val max = -Double.MAX_VALUE
		val bigMax = BigDecimal.valueOf(max)
		assertEquals(
			0, bigMax.compareTo(
				largeSum(listOf(max))
			)
		)
		val list: MutableList<Double> = ArrayList()
		for (i in 0 .. 9) list.add(max)
		assertEquals(
			0, bigMax.multiply(BigDecimal.TEN).compareTo(
				largeSum(list)
			)
		)
	}
}
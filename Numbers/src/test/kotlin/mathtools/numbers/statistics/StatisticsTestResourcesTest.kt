package mathtools.numbers.statistics

import mathtools.numbers.statistics.StatisticsTestResources.largeByteList
import mathtools.numbers.statistics.StatisticsTestResources.largeShortList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatisticsTestResourcesTest {

	@Test
	fun testLargeByteArray() {
		val counter = IntArray(128)
		largeByteList.forEach { counter[it.toInt()]++ }
		for (i in 0 .. 99)
			assertEquals(0, counter[i])
		for (i in 100 .. 119)
			assertEquals(5_000, counter[i])
		assertEquals(
			100_000, counter[120]
		)
		for (i in 121 .. 125)
			assertEquals(20_000, counter[i])
		for (i in 126 .. 127)
			assertEquals(0, counter[i])
	}

	@Test
	fun testLargeShortArray() {
		val counter = IntArray(45)
		largeShortList.forEach { counter[it - 31996]++ }
		for (i in 0 until 4)
			assertEquals(2500, counter[i])
		assertEquals(10_000, counter[4])
		for (i in 5 until 45)
			assertEquals(250, counter[i])
	}

}
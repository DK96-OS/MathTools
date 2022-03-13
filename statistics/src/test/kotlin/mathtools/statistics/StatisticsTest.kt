package mathtools.statistics

import com.google.common.math.Stats
import mathtools.statistics.Statistics.calculateMean
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Test the Statistics functions
 * @author DK96-OS : 2022 */
class StatisticsTest {

	@Test
	fun testMeanEmptyList() {
		assertEquals(0.0, calculateMean(emptyList()))
	}

	@Test
	fun testMeanSingleValue() {
		assertEquals(
			8.0, calculateMean(listOf<Byte>(8)))
		assertEquals(
			8.0, calculateMean(listOf<Short>(8)))
		assertEquals(
			8.0, calculateMean(listOf(8)))
		assertEquals(
			8.0, calculateMean(listOf(8L)))
		// Decimals
		assertEquals(
			8.2, calculateMean(listOf(8.2f)), 0.000001)
		assertEquals(
			8.2, calculateMean(listOf(8.2)), 0.000001)
	}

	@Test
	fun testMeanSmallList() {
		val list = mutableListOf(100f, 110f, 190f, 200f)
		assertEquals(
			150.0, calculateMean(list), 0.0001)
		assertEquals(
			52.281, Statistics.calculateSDev(list), 0.001)
	}

	@Test
	fun testMeanNegativeValues() {
		val list = mutableListOf(-100f, -110f, -190f, -200f)
		assertEquals(
			-150.0, calculateMean(list), 0.0001)
		assertEquals(
			52.281, Statistics.calculateSDev(list), 0.001)
	}

	@Test
	fun testCompareGuava() {
		val list = mutableListOf(100f, 110f, 190f, 200f)
		//
		val mean = Stats.meanOf(list)
		val stats = Stats.of(list)
		assertEquals(150.0, mean)
		assertEquals(
			52.0,
			stats.sampleStandardDeviation(),
			1.0
		)
	}
 
}
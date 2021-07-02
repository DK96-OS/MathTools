package mathtools.numbers.statistics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.RepeatedTest

/** Test the Statistics functions */
class StatisticsTest {
  
    @Tag("fast")
	@Test fun checkStatisticsOfList() {
		val list = mutableListOf(100f, 110f, 190f, 200f)
		assertEquals(150f, Statistics.calculateMeanFloat(list), 0.01f)
		assertEquals(52f, Statistics.calculateStandardDevFloat(list), 1f)
	}
  
 	@RepeatedTest(3) fun testOneIn2To9Probability() {
		val N = 100_000L
		assertEquals(50f, checkPercentage(2, N), 0.5f)
		assertEquals(33.33f, checkPercentage(3, N), 0.5f)
		assertEquals(25.00f, checkPercentage(4, N), 0.5f)
		assertEquals(20.00f, checkPercentage(5, N), 0.5f)
		assertEquals(16.67f, checkPercentage(6, N), 0.5f)
		assertEquals(14.28f, checkPercentage(7, N), 0.5f)
		assertEquals(12.50f, checkPercentage(8, N), 0.5f)
		assertEquals(11.11f, checkPercentage(9, N), 0.5f)
	}
	
	@RepeatedTest(3) fun testOneIn20To100Probability() {
		val N = 100_000L
		assertEquals(5.0f, checkPercentage(20, N), 0.4f)
		assertEquals(4.0f, checkPercentage(25, N), 0.4f)
		assertEquals(2.5f, checkPercentage(40, N), 0.35f)
		assertEquals(2.0f, checkPercentage(50, N), 0.35f)
		assertEquals(1.0f, checkPercentage(100, N), 0.35f)
	}
	
	@Tag("slow")
	@RepeatedTest(3) fun testOneIn500Distribution() {
		val measurements = measureOneIn(500, 20_000L)
		val mean = Statistics.calculateMeanFloat(measurements)
		val sdev = Statistics.calculateStandardDevFloat(measurements, mean)
		assertEquals(0.2f, mean, 0.015f)
		assertEquals(0.0015f, sdev, 0.0009f)
	}
  
    @Tag("slow")
	@RepeatedTest(2) fun testOneIn5000Distribution() {
		val measurements = measureOneIn(5000, 10_000L, 8)
		val mean = Statistics.calculateMeanFloat(measurements)
		val sdev = Statistics.calculateStandardDevFloat(measurements, mean)
		assertEquals(0.02f, mean, 0.002f)
		assertEquals(0.000_3f, sdev, 0.000_29f)
		println("Standard Deviation: $sdev")
	}
  
  /** Runs the Statistics OneIn( x ) function N times.
      * N must end in a zero; it must be integer divisible by 10   */
	private fun checkPercentage(x: Int, N: Long): Float {
		var trueCount = 0
		for (i in 0 until N) if (Statistics.oneIn(x)) trueCount++
		return trueCount * 100f / N
	}
  
	/** Create a list of Measurements for OneIn output Statistics
	 * @param x The input to oneIn.
	 * @param nFactor A factor in determining the number of trials per measurement
	 * @param measurements The number of times to measure the percentage independently */
	private fun measureOneIn(
		x: Int, nFactor: Long = 25_000L, 
		measurements: Int = 10,
	): List<Float> {
		val newList = ArrayList<Float>(measurements)
		for (n in 0 until measurements)
			newList.add(checkPercentage(x, x * nFactor))
		return newList
	}
 
}

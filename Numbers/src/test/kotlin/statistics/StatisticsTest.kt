import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.math.roundToInt

/** Test the Statistics functions */
class StatisticsTest {
  
  @Test fun checkStatisticsOfList() {
    val list = mutableListOf(100f, 110f, 190f, 200f)
		assertEquals(150f, Statistics.calculateMeanFloat(list), 0.01f)
		assertEquals(52f, Statistics.calculateStandardDevFloat(list), 1f)
  }
  
 	@Test fun checkOneInProbability() {
		val N = 200000L
		assertEquals(33.33f, checkPercentage(3, N), 0.3f)
		assertEquals(25.00f, checkPercentage(4, N), 0.3f)
		assertEquals(20.00f, checkPercentage(5, N), 0.3f)
		assertEquals(16.67f, checkPercentage(6, N), 0.3f)
		assertEquals(14.28f, checkPercentage(7, N), 0.3f)
		assertEquals(12.50f, checkPercentage(8, N), 0.3f)
		assertEquals(11.11f, checkPercentage(9, N), 0.3f)
    
      // Check for variability
		// for (i in 0 until 5) println("${checkPercentage(9, N)}")	
      // Variability decreases as x++
    
		assertEquals(5.0f, checkPercentage(20, N), 0.1f)
		assertEquals(4.0f, checkPercentage(25, N), 0.1f)
		assertEquals(2.5f, checkPercentage(40, N), 0.1f)
		assertEquals(2.0f, checkPercentage(50, N), 0.1f)
		assertEquals(1.0f, checkPercentage(100, N), 0.1f)	
	}
  
	@Test fun testOneInProbabilityHighInputStatistics() {
		var measurements = measureOneIn(200)
		var mean = Statistics.calculateMeanFloat(measurements)
		var sdev = Statistics.calculateStandardDevFloat(measurements, mean)
		assertEquals(0.5f, mean, 0.001f)
		println(sdev)
			// One In 500
		measurements = measureOneIn(500, 30_000L)
		mean = Statistics.calculateMeanFloat(measurements)
		sdev = Statistics.calculateStandardDevFloat(measurements, mean)
		assertEquals(0.2f, mean, 0.002f)
		println(sdev)
			// One In 1000
		measurements = measureOneIn(1000, 30_000L)
		mean = Statistics.calculateMeanFloat(measurements)
		sdev = Statistics.calculateStandardDevFloat(measurements, mean)
		assertEquals(0.1f, mean, 0.001f)
		println(sdev)
	}
  
	@Test fun testOneInProbabilityHighInputVariability2() {
		val measurements = measureOneIn(5000, 10_000L, 30) 		// One In 5000
		val mean = Statistics.calculateMeanFloat(measurements)
		val sdev = Statistics.calculateStandardDevFloat(measurements, mean)
		assertEquals(0.02f, mean, 0.000_1f)
		assertEquals(0.000_4f, sdev, 0.000_3f)
		println(sdev)
	}
  
  /** Runs the Statistics OneIn( x ) function N times */
	private fun checkPercentage(x:Int, N:Long): Float {
		var trueCount = 0
		for (i in 0 until N) if (Statistics.oneIn(x)) trueCount++
		return trueCount * 100f / N
	}
  
	/** Create a list of Measurements for additional Statistics
	 * @param x The input to oneIn.
	 * @param nFactor A factor in determining the number of trials per measurement
	 * @param measurements The number of times to measure the percentage independently */
	private fun measureOneIn(x:Int, nFactor:Long = 30_000L, measurements:Int = 20)
	: List<Float> = Array(measurements) {checkPercentage(x, x * nFactor)}.toList()
 
}

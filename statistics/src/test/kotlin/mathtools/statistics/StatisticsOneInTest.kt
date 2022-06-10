package mathtools.statistics

import mathtools.statistics.Statistics.oneIn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

/** Testing [Statistics] oneIn function
 * @author DK96-OS : 2022 */
class StatisticsOneInTest {

    @Test
    fun testOne() {
        for (i in 0 until 10)
            assertTrue(oneIn(1))
    }

    @Test
    fun testInvalidInputs() {
        assertFalse(
            oneIn(0)
        )
        assertFalse(
            oneIn(-1)
        )
    }

    @RepeatedTest(3)
    fun testOneIn2To9Probability() {
        val n = 100_000L
        assertEquals(
            50f, checkPercentage(2, n), 0.5f)
        assertEquals(
            33.33f, checkPercentage(3, n), 0.5f)
        assertEquals(
            25.00f, checkPercentage(4, n), 0.5f)
        assertEquals(
            20.00f, checkPercentage(5, n), 0.5f)
        assertEquals(
            16.67f, checkPercentage(6, n), 0.5f)
        assertEquals(
            14.28f, checkPercentage(7, n), 0.5f)
        assertEquals(
            12.50f, checkPercentage(8, n), 0.5f)
        assertEquals(
            11.11f, checkPercentage(9, n), 0.5f)
    }

    @RepeatedTest(3)
    fun testOneIn20To100Probability() {
        val n = 100_000L
        assertEquals(
            5.0f, checkPercentage(20, n), 0.4f)
        assertEquals(
            4.0f, checkPercentage(25, n), 0.4f)
        assertEquals(
            2.5f, checkPercentage(40, n), 0.35f)
        assertEquals(
            2.0f, checkPercentage(50, n), 0.35f)
        assertEquals(
            1.0f, checkPercentage(100, n), 0.35f)
    }

    @Tag("slow")
    @RepeatedTest(3)
    fun testOneIn500Distribution() {
        val measurements = measureOneIn(500, 20_000L)
        val mean = Statistics.calculateMean(measurements)
        val sDev = Statistics.calculateSDev(measurements, mean)
        assertEquals(
            0.2, mean, 0.015)
        assertEquals(
            0.0015, sDev, 0.0009)
    }

    @Tag("slow")
    @RepeatedTest(2)
    fun testOneIn5000Distribution() {
        val measurements = measureOneIn(5000, 10_000L, 8)
        val mean = Statistics.calculateMean(measurements)
        val sDev = Statistics.calculateSDev(measurements, mean)
        assertEquals(
            0.02, mean, 0.002)
        assertEquals(
            0.000_3, sDev, 0.000_29)
        println("Standard Deviation: $sDev")
    }

    /** Runs the Statistics OneIn( x ) function N times.
     * N must end in a zero; it must be integer divisible by 10   */
    private fun checkPercentage(x: Int, N: Long): Float {
        var trueCount = 0L
        for (i in 0 until N)
            if (oneIn(x)) trueCount++
        return trueCount * 100f / N
    }

    /** Create a list of Measurements for OneIn output Statistics
     * @param x The input to oneIn.
     * @param nFactor A factor in determining the number of trials per measurement
     * @param measurements The number of times to measure the percentage independently */
    private fun measureOneIn(
        x: Int, nFactor: Long = 25_000L,
        measurements: Int = 10,
    ) : List<Float> = Array(measurements) {
        checkPercentage(x, x * nFactor)
    }.toList()

}
package mathtools.statistics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing various data sets */
class DistributionStatsTest {

    @Test
    fun testUniformDistribution() {
        val uniData = listOf(
            25f, 26f, 27f, 28f, 29f, 30f, 31f, 32f, 33f, 34f, 35f
        )
        DistributionStats.process(uniData)!!.run {
            assertEquals(30.0, mean, 0.000_001)
            assertEquals(3.31, standardDeviation, 0.01)
            assertEquals(25.0, min)
            assertEquals(35.0, max)
            assertEquals(
                30.0 + 3.31 * 3,
                valueAtDeviation(3.0),
                0.03
            )
        }
    }

    @Test
    fun testMountainDistribution() {
        val mtnData = arrayListOf<Long>()
        for (i in 1 .. 100)
            repeat(i) { mtnData.add(i.toLong()) }
        // Count up to 199, while decreasing the number added
        for (i in 101 .. 199)
            repeat(200 - i) { mtnData.add(i.toLong()) }
        //
        DistributionStats.process(mtnData)!!.run {
            assertEquals(100.0, mean, 0.001)
            assertEquals(40.82, standardDeviation, 0.01)
            assertEquals(1.0, min)
            assertEquals(199.0, max)
            assertEquals(
                100.0 + 40.82 * 3,
                valueAtDeviation(3.0),
                0.03
            )
        }
    }

    @Test
    fun testSkewDistribution() {
        val skewData = arrayListOf<Long>()
        for (i in 1 .. 19)
            repeat(i) { skewData.add(i.toLong()) }
        //
        DistributionStats.process(skewData)!!.run {
            assertEquals(13.0, mean, 0.001)
            assertEquals(4.59, standardDeviation, 0.01)
            assertEquals(1.0, min)
            assertEquals(19.0, max)
            assertEquals(
                13.0 + 4.59 * 3,
                valueAtDeviation(3.0),
                0.03
            )
        }
    }

}
package mathtools.numbers.statistics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

/** Testing various data sets */
class DistributionCharacteristicsTest {

    @Test
    fun testUniformDistribution() {
        val uniData = listOf(
            25f, 26f, 27f, 28f, 29f, 30f, 31f, 32f, 33f, 34f, 35f
        )
        DistributionCharacteristics.process(uniData)!!.run {
            assertEquals(30.0, mean, 0.1)
            assertEquals(3.0, standardDeviation, 1.0)
            assertEquals(25.0, min)
            assertEquals(35.0, max)
            assertEquals(null, outliers)
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
        DistributionCharacteristics.process(mtnData)!!.run {
            assertEquals(100.0, mean, 0.1)
            assertEquals(40.0, standardDeviation, 2.0)
            assertEquals(1.0, min)
            assertEquals(199.0, max)
            assertEquals(null, outliers)
        }
    }

    @Test
    fun testSkewDistribution() {
        val skewData = arrayListOf<Long>()
        for (i in 1 .. 19)
            repeat(i) { skewData.add(i.toLong()) }
        //
        DistributionCharacteristics.process(skewData)!!.run {
            assertEquals(13.0, mean, 0.1)
            assertEquals(5.0, standardDeviation, 0.5)
            assertEquals(1.0, min)
            assertEquals(19.0, max)
            assertEquals(null, outliers)
        }
    }

    @Test
    fun testEmptyList() {
        val emptyList = listOf<Long>()
        assertEquals(null, DistributionCharacteristics.process(emptyList))
    }

}

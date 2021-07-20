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
        val dc = DistributionCharacteristics.process(uniData)
        assertEquals(true, dc != null)
        assertEquals(30.0, dc!!.mean, 0.1)
        assertEquals(3.0, dc.standardDeviation, 1.0)
        assertEquals(null, dc.outliers)
    }

    @Test
    fun testMountainDistribution() {
        val mtnData = arrayListOf<Long>()
        for (i in 1 .. 100)
            repeat(i) { mtnData.add(i.toLong()) }
        for (i in 101 .. 199)
            repeat(200 - i) { mtnData.add(i.toLong()) }
        //
        val dc = DistributionCharacteristics.process(mtnData)
        assertEquals(true, dc != null)
        assertEquals(100.0, dc!!.mean, 0.1)
        assertEquals(40.0, dc.standardDeviation, 2.0)
        assertEquals(null, dc.outliers)
    }

    @Test
    fun testSkewDistribution() {
        val skewData = arrayListOf<Long>()
        for (i in 1 .. 19)
            repeat(i) { skewData.add(i.toLong()) }
        //
        val dc = DistributionCharacteristics.process(skewData)
        assertEquals(true, dc != null)
        assertEquals(13.0, dc!!.mean, 0.1)
        assertEquals(5.0, dc.standardDeviation, 0.5)
        assertEquals(null, dc.outliers)
    }

    @Test
    fun testEmptyList() {
        val emptyList = listOf<Long>()
        assertEquals(null, DistributionCharacteristics.process(emptyList))
    }

}

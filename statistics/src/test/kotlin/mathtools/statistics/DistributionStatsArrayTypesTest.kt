package mathtools.statistics

import mathtools.statistics.DistributionStats.Companion.process
import mathtools.statistics.testdata.UniformTestDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Testing DistributionCharacteristics on Number Array types */
class DistributionStatsArrayTypesTest {

    private lateinit var byteArray: ByteArray
    private lateinit var shortArray: ShortArray
    private lateinit var intArray: IntArray
    private lateinit var longArray: LongArray
    private lateinit var floatArray: FloatArray
    private lateinit var doubleArray: DoubleArray

    @BeforeEach
    fun setup() {
        /*  The range -128 to 127 is common to all Number types.
            Mean value of 30 with a uniform spread of 50 on each side.*/
        byteArray = ByteArray(101) { (it - 20).toByte() }
        shortArray = ShortArray(101) { (it - 20).toShort() }
        intArray = IntArray(101) { it - 20 }
        longArray = LongArray(101) { (it - 20).toLong() }
        floatArray = FloatArray(101) { (it - 20).toFloat() }
        doubleArray = DoubleArray(101) { (it - 20).toDouble() }
    }

    @Test
    fun testEquivalence() {
        assertEquals(byteArray.size, uniform101.size)
        for (i in byteArray.indices)
            assertEquals(
                byteArray[i],
                uniform101[i]
            )
        assertEquals(shortArray.size, uniform101.size)
        for (i in shortArray.indices)
            assertEquals(
                shortArray[i],
                uniform101[i].toShort()
            )
        assertEquals(intArray.size, uniform101.size)
        for (i in intArray.indices)
            assertEquals(
                intArray[i],
                uniform101[i].toInt()
            )
    }

    @Test
    fun testArrayCharacteristics() {
        verify(process(byteArray))
        verify(process(shortArray))
        verify(process(intArray))
        verify(process(longArray))
        verify(process(floatArray))
        verify(process(doubleArray))
    }

    private fun verify(dc: DistributionStats?) {
        assertEquals(true, dc != null)
        // Property Values
        assertEquals(-20.0, dc!!.min)
        assertEquals(80.0, dc.max)
        assertEquals(30.0, dc.mean)
        assertEquals(29.3, dc.standardDeviation, 0.002)
        assertEquals(101, dc.count)
        // Methods
        assertEquals(
            30 + 29.3 * 2,
            dc.valueAtDeviation(2.0),
            0.005
        )
        assertEquals(
            30 - 29.3 * 2,
            dc.valueAtDeviation(-2.0),
            0.005
        )
    }

}

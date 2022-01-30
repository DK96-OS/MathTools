package mathtools.numbers.statistics

import mathtools.numbers.statistics.DistributionCharacteristics.Companion.process
import mathtools.numbers.statistics.StatisticsTestResources.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**  */
class DistributionCharacteristicsArrayTypesTest {

    lateinit var byteArray: ByteArray
    lateinit var shortArray: ShortArray
    lateinit var intArray: IntArray
    lateinit var longArray: LongArray
    lateinit var floatArray: FloatArray
    lateinit var doubleArray: DoubleArray

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
                uniform101[i].toByte()
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

    private fun verify(dc: DistributionCharacteristics?) {
        assertEquals(true, dc != null)
        // Property Values
        assertEquals(-20.0, dc!!.min)
        assertEquals(80.0, dc.max)
        assertEquals(30.0, dc.mean)
        assertEquals(29.3, dc.standardDeviation, 0.002)
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
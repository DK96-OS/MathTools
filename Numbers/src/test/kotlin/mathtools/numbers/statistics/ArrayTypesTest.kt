package mathtools.numbers.statistics

import mathtools.numbers.statistics.DistributionCharacteristics.Companion.process
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

/**  */
class ArrayTypesTest {

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
        assertEquals(-20.0, dc!!.min)
        assertEquals(80.0, dc.max)
        assertEquals(30.0, dc.mean)
        assertEquals(29.3, dc.standardDeviation, 0.002)
        assertEquals(null, dc.outliers)
    }

}

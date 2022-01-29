package mathtools.numbers.statistics

import mathtools.numbers.listtypes.listSum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Testing the Number types supported by Statistics */
class NumberTypeTest {

    /** The most restrictive Number Type is used in mapping to other Types */
    lateinit var dataArray: ByteArray
    lateinit var dataList: List<Byte>

    @BeforeEach
    fun setup() {
        /*  The range -128 to 127 is common to all Number types.
            Target a mean value of 30 with a uniform spread of 50 on each side.*/
        dataArray = ByteArray(101) { (it - 20).toByte() }
        dataList = dataArray.toList()
    }

    @Test
    fun testSum() {
        // The sum of these numbers is:
        // -20 + -19 + .. 79 + 80
        // Cancel negative, positive pairs
        // = 21 + 22 + .. + 79 + 80
        // Group the constant 20 out of all 60 remaining values
        // = (20 * 60) + 1 + 2 + 3 + .. + 59 + 60
        // Group pairs from 1 to 59 into 60s.
        // = (1200) + 60 + (1 + 59) + (2 + 58) + .. + (29 + 31) + 30
        // There are 29 pairs and a 30 left over. Simplify
        // = 1290 + (29 * 60)
        // = 1290 + 1200 + 540
        // = 3030
        assertEquals(3030.0, listSum(dataList.map { it.toFloat() }))
        assertEquals(3030.0, listSum(dataList.map { it.toDouble() }))
        assertEquals(3030.0, listSum(dataList.map { it.toLong() }))
        assertEquals(3030.0, listSum(dataList.map { it.toInt() }))
        assertEquals(3030.0, listSum(dataList.map { it.toShort() }))
        assertEquals(3030.0, listSum(dataList))
    }

    @Test
    fun testCharacteristics() {
        /** Ensure that the outcomes of all Types are equal on the same data */
        fun verify(numbers: List<Number>) {
            DistributionCharacteristics.process(numbers)!!.run {
                assertEquals(-20.0, min)
                assertEquals(80.0, max)
                assertEquals(30.0, mean)
                assertEquals(29.3, standardDeviation, 0.002)
            }
        }
        verify(dataList)
        verify(dataList.map { it.toFloat() })
        verify(dataList.map { it.toDouble() })
        verify(dataList.map { it.toLong() })
        verify(dataList.map { it.toInt() })
        verify(dataList.map { it.toShort() })
    }

}

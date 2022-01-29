package mathtools.numbers.statistics

import mathtools.numbers.listtypes.ListNumberTypes.convertToDouble
import mathtools.numbers.listtypes.ListNumberTypes.convertToFloat
import mathtools.numbers.listtypes.ListNumberTypes.convertToInt
import mathtools.numbers.listtypes.ListNumberTypes.convertToLong
import mathtools.numbers.listtypes.ListNumberTypes.convertToShort
import mathtools.numbers.listtypes.listSum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Testing the Number types supported by Statistics */
class NumberTypeTest {

    /** The most restrictive Number Type is used in mapping to other Types */
    lateinit var dataArray: ByteArray
    lateinit var dataList: List<Byte>

    lateinit var dataArray2: ByteArray
    lateinit var dataList2: List<Byte>

    @BeforeEach
    fun setup() {
        /* The range -128 to 127 is common to all Number types.
           Target a mean value of 30 with a uniform spread of 50 on each side.*/
        dataArray = ByteArray(101) { (it - 20).toByte() }
        dataList = dataArray.toList()
        /* Target a mean value of 120 with most values below */
        dataArray2 = ByteArray(300_000) {
            when (it) {
                in 0 until 100_000 -> 120
                in 100_000 until 200_000 ->
                    (100 + (it - 100_000) / 10_000).toByte()
                else ->
                    (121 + (it - 200_000) / 20_000).toByte()
            }
        }
        dataList2 = dataArray2.toList()
    }

    @Test
    fun testSum() {
        // The sum of these numbers is:
        // -20 + -19 + .. 79 + 80
        // Cancel (negative, positive) pairs, now:
        // = 21 + 22 + .. + 79 + 80
        // Group the constant 20 out of all 60 remaining values:
        // = (20 * 60) + 1 + 2 + 3 + .. + 59 + 60
        // Group pairs from 1 to 59 into 60s:
        // = (1200) + 60 + (1 + 59) + (2 + 58) + .. + (29 + 31) + 30
        // There are 29 pairs and a 30 left over. Simplify:
        // = 1290 + (29 * 60) = 1290 + 1200 + 540 = 3030
        assertEquals(3030.0, listSum(convertToFloat(dataList)))
        assertEquals(3030.0, listSum(convertToDouble(dataList)))
        assertEquals(3030.0, listSum(convertToLong(dataList)))
        assertEquals(3030.0, listSum(convertToInt(dataList)))
        assertEquals(3030.0, listSum(convertToShort(dataList)))
        assertEquals(3030.0, listSum(dataList))
        // Array Equivalence
        assertEquals(3030, dataArray.sum())
        // Is an Array Sum function necessary...
    }

    @Test
    fun testDistributionCharacteristics() {
        /**  Assertion sub-routine
         * The outcome of each Type should be equal,
         * and to some known precision */
        fun verify(dc: DistributionCharacteristics?) {
            dc!!.run {
                assertEquals(-20.0, min)
                assertEquals(80.0, max)
                assertEquals(30.0, mean)
                assertEquals(
                    29.3, standardDeviation,
                    0.002
                )
            }
        }
        verify(DistributionCharacteristics.process(dataList))
        verify(DistributionCharacteristics.process(dataArray))
        verify(DistributionCharacteristics.process(
            convertToFloat(dataList)
        ))
        verify(DistributionCharacteristics.process(
            convertToDouble(dataList)
        ))
        verify(DistributionCharacteristics.process(
            convertToLong(dataList)
        ))
        verify(DistributionCharacteristics.process(
            convertToInt(dataList)
        ))
        verify(DistributionCharacteristics.process(
            convertToShort(dataList)
        ))
    }

}

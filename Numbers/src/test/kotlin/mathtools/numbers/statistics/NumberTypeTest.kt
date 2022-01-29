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
                    (100 + (it - 100_000) / 5_000).toByte()
                in 200_000 until 300_000 ->
                    (121 + (it - 200_000) / 20_000).toByte()
                else -> throw IllegalArgumentException()
            }
        }
        dataList2 = dataArray2.toList()
    }

    @Test
    fun testDataArraySetup() {
        val counter = IntArray(128)
        dataArray2.forEach { counter[it.toInt()]++ }
        assertEquals(0, counter[99])
        assertEquals(5_000, counter[100])
        assertEquals(5_000, counter[101])
        assertEquals(5_000, counter[102])
        assertEquals(5_000, counter[118])
        assertEquals(5_000, counter[119])
        assertEquals(100_000, counter[120])
        assertEquals(20_000, counter[121])
        assertEquals(20_000, counter[122])
        assertEquals(20_000, counter[123])
        assertEquals(20_000, counter[124])
        assertEquals(20_000, counter[125])
        assertEquals(0, counter[126])
    }

    @Test
    fun testSum() {
        /** Data List 1
         * The sum of these numbers is:
         * -20 + -19 + .. 79 + 80
         * Cancel (negative, positive) pairs, now:
         * = 21 + 22 + .. + 79 + 80
         * Group the constant 20 out of all 60 remaining values:
         * = (20 * 60) + 1 + 2 + 3 + .. + 59 + 60
         * Group pairs from 1 to 59 into 60s:
         * = (1200) + 60 + (1 + 59) + (2 + 58) + .. + (29 + 31) + 30
         * There are 29 pairs and a 30 left over. Simplify:
         * = 1290 + (29 * 60) = 1290 + 1200 + 540 = 3030
         */
        assertEquals(3030.0, listSum(convertToFloat(dataList)))
        assertEquals(3030.0, listSum(convertToDouble(dataList)))
        assertEquals(3030.0, listSum(convertToLong(dataList)))
        assertEquals(3030.0, listSum(convertToInt(dataList)))
        assertEquals(3030.0, listSum(convertToShort(dataList)))
        assertEquals(3030.0, listSum(dataList))
        // Array Equivalence
        assertEquals(3030, dataArray.sum())
        // Is an Array Sum function necessary...
        /** Data List 2
         * uniform(100 .. 119) * 100k + (120 * 100k) + uniform(121..125) * 100k
         * Split off 100 from all terms:
         * = 100 * 300k + u(1..19) * 95k + 20 * 100k + u(21..25) * 100k
         * Use average values of each uniformly increasing set
         * = 30M + (10 * 95k) + 2M + (22.5 * 100k)
         * Simplify:
         * = 32M + 950k + 2.3M = 35.25M
         */
        assertEquals(3.525E7, listSum(convertToFloat(dataList2)))
        assertEquals(3.525E7, listSum(convertToDouble(dataList2)))
        assertEquals(3.525E7, listSum(convertToLong(dataList2)))
        assertEquals(3.525E7, listSum(convertToInt(dataList2)))
        assertEquals(3.525E7, listSum(convertToShort(dataList2)))
        assertEquals(3.525E7, listSum(dataList2))
        assertEquals(3.525E7, dataArray2.sum().toDouble())
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

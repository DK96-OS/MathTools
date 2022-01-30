package mathtools.numbers.statistics

import mathtools.numbers.listtypes.ListNumberTypes.convertToByte
import mathtools.numbers.listtypes.ListNumberTypes.convertToDouble
import mathtools.numbers.listtypes.ListNumberTypes.convertToFloat
import mathtools.numbers.listtypes.ListNumberTypes.convertToInt
import mathtools.numbers.listtypes.ListNumberTypes.convertToLong
import mathtools.numbers.listtypes.ListNumberTypes.convertToShort
import mathtools.numbers.listtypes.listSum
import mathtools.numbers.statistics.DistributionCharacteristics.Companion.process
import mathtools.numbers.testdata.LargeTestDataSource
import mathtools.numbers.testdata.UniformTestDataSource.uniform101
import mathtools.numbers.testdata.UniformTestDataSource.uniform101DC
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the Number types supported by Statistics */
class NumberTypeTest {

    private val data: LargeTestDataSource = LargeTestDataSource()

    /** The key to this Test is to run a stats function on many types
     * and compare the results between types */
    private inline fun runOnAllTypes(
        numbers: List<Number>,
        level: Int = 0,
        verify: (dc: DistributionCharacteristics?) -> Unit,
    ) {
        if (level < 1)
            verify(
                process(
                convertToByte(numbers)
            )
            )
        if (level < 2)
            verify(
                process(
                convertToShort(numbers)
            )
            )
        if (level < 3)
            verify(
                process(
                convertToInt(numbers)
            )
            )
        if (level < 4)
            verify(
                process(
                convertToLong(numbers)
            )
            )
        if (level < 5)
            verify(
                process(
                convertToFloat(numbers)
            )
            )
        verify(
            process(
            convertToDouble(numbers)
        )
        )
    }

    /** Run a function on multiple list types, selected by level:
     * Level 0: All
     * Level 1: No Byte
     * Level 2: No Byte, Short
     * Level 3: No Byte, Short, Int
     * Level 4: No Byte, Short, Int, Long
     * Level 5: No Byte, Short, Int, Long, Float */
    private inline fun runOnListTypes(
        numbers: List<Number>,
        level: Int = 0,
        run: (data: List<Number>) -> Unit
    ) {
        if (level < 1) run(convertToByte(numbers))
        if (level < 2) run(convertToShort(numbers))
        if (level < 3) run(convertToInt(numbers))
        if (level < 4) run(convertToLong(numbers))
        if (level < 5) run(convertToFloat(numbers))
        run(convertToDouble(numbers))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testListSumFunction() {
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
        runOnListTypes(uniform101) {
            assertEquals(3030.0, listSum(it))
        }
        /** Data List 2
         * uniform(100 .. 119) * 100k + (120 * 100k) + uniform(121..125) * 100k
         * Split off 100 from all terms:
         * = 100 * 300k + u(1..19) * 95k + 20 * 100k + u(21..25) * 100k
         * Use average values of each uniformly increasing set
         * = 30M + (10 * 95k) + 2M + (22.5 * 100k)
         * Simplify:
         * = 32M + 950k + 2.3M = 35.25M
         */
        runOnListTypes(data.large120) {
            assertEquals(3.525E7, listSum(it))
        }
        /** Data List 3
         * Todo: Calculate Sum algebraically */
        runOnListTypes(data.large32000, 1) {
            assertEquals(9.6018E8, listSum(it))
        }
    }

    @Test
    fun testArraySumFunction() {
        assertEquals(
            3030, uniform101.toTypedArray().sum()
        )
        assertEquals(
            3.525E7, data.large120.toTypedArray().sum().toDouble()
        )
        assertEquals(
            9.6018E8, convertToLong(data.large32000).toTypedArray().sum().toDouble()
        )
    }

    @Test
    fun testUniform101Stats() {
        assertEquals(uniform101DC, process(uniform101))
        runOnAllTypes(uniform101, 1) {
            assertEquals(uniform101DC, it)
        }
    }

    @Test
    fun testLargeByteStats() {
        assert(data.large120DC == process(data.large120))
        runOnAllTypes(data.large120, 1) {
            assert(data.large120DC == it)
        }
    }

    private fun verifyLargeShort(dc: DistributionCharacteristics?) {
        dc!!.run {
            assertEquals(31996.0, min)
            assertEquals(32040.0, max)
            assertEquals(32006.0, mean)
            assertEquals(
                12.288_410_535_993_526, standardDeviation,
                0.000_000_000_000_001
            )
        }
    }

    @Test
    fun testLargeShortStats() {
        verifyLargeShort(
            process(data.large32000)
        )
        runOnAllTypes(data.large32000, 2) { verifyLargeShort(it) }
    }

}
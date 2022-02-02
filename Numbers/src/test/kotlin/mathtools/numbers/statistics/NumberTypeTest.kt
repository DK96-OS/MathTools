package mathtools.numbers.statistics

import mathtools.lists.NumberListConversion.toByte
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.NumberListConversion.toFloat
import mathtools.lists.NumberListConversion.toInt
import mathtools.lists.NumberListConversion.toLong
import mathtools.lists.NumberListConversion.toShort
import mathtools.lists.listSum
import mathtools.numbers.statistics.DistributionCharacteristics.Companion.process
import mathtools.numbers.testdata.LargeTestDataSource.large123
import mathtools.numbers.testdata.LargeTestDataSource.large123DC
import mathtools.numbers.testdata.LargeTestDataSource.large123Sum
import mathtools.numbers.testdata.LargeTestDataSource.large32760
import mathtools.numbers.testdata.LargeTestDataSource.large32760DC
import mathtools.numbers.testdata.LargeTestDataSource.large32760Sum
import mathtools.numbers.testdata.UniformTestDataSource.uniform101
import mathtools.numbers.testdata.UniformTestDataSource.uniform101DC
import mathtools.numbers.testdata.UniformTestDataSource.uniform101Sum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the Number types supported by Statistics */
class NumberTypeTest {

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
        if (level < 1) run(toByte(numbers))
        if (level < 2) run(toShort(numbers))
        if (level < 3) run(toInt(numbers))
        if (level < 4) run(toLong(numbers))
        if (level < 5) run(toFloat(numbers))
        run(toDouble(numbers))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testListSumFunction() {
        runOnListTypes(uniform101) {
            assertEquals(uniform101Sum, listSum(it))
        }
        runOnListTypes(large123) {
            assertEquals(large123Sum, listSum(it))
        }
        runOnListTypes(large32760, 1) {
            assertEquals(large32760Sum, listSum(it))
        }
    }

    @Test
    fun testUniform101Stats() {
        assertEquals(uniform101DC, process(uniform101))
        runOnListTypes(uniform101, 1) {
            assertEquals(uniform101DC, process(it))
        }
    }

    @Test
    fun testLargeByteStats() {
        assert(large123DC == process(large123))
        runOnListTypes(large123, 1) {
            assert(large123DC == process(it))
        }
    }

    @Test
    fun testLargeShortStats() {
        assert(large32760DC == process(large32760))
        runOnListTypes(large32760, 2) {
            assert(large32760DC == process(it))
        }
    }

}
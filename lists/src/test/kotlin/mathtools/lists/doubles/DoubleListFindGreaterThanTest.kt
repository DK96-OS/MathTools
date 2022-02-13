package mathtools.lists.doubles

import mathtools.lists.DoubleList.findGreaterThan
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.doubles.DoubleListTestResources.largeList
import mathtools.lists.doubles.DoubleListTestResources.largeListFactor
import mathtools.lists.doubles.DoubleListTestResources.maxValueList
import mathtools.lists.doubles.DoubleListTestResources.medList
import mathtools.lists.doubles.DoubleListTestResources.medListFactor
import mathtools.lists.doubles.DoubleListTestResources.nanValueList
import mathtools.lists.doubles.DoubleListTestResources.negativeInfiniteList
import mathtools.lists.doubles.DoubleListTestResources.positiveInfiniteList
import mathtools.lists.doubles.DoubleListTestResources.singleItemList
import mathtools.lists.doubles.DoubleListTestResources.smallList
import mathtools.lists.doubles.DoubleListTestResources.smallListFactor
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

/** Testing the Greater Than function for Double typed Lists */
class DoubleListFindGreaterThanTest {

    private val u101 = toDouble(uniform101)

    /** Greater Than function finds the right number of
     * elements in the small, medium, and large lists
     */
    @RepeatedTest(2)
    fun testFindGreaterThanRandomized() {
        var result = findGreaterThan(
            smallList.shuffled(),
            smallListFactor * 8 - 0.0001
        )
        assertEquals(3, result.size)
        result = findGreaterThan(
            medList.shuffled(),
            medListFactor * 900
        )
        assertEquals(100, result.size)
        result = findGreaterThan(
            largeList.shuffled(),
            largeListFactor * 120_000
        )
        assertEquals(30_000, result.size)
    }

    /** Greater Than works on single item and empty lists */
    @Test
    fun testFindGreaterThanSingleItem() {
        var result = findGreaterThan(
            singleItemList, 5.0
        )
        assertEquals(emptyList<Double>(), result)
        result = findGreaterThan(
            singleItemList, 4.9999999
        )
        assertEquals(1, result.size)
        assertEquals(0, result[0])
        // Empty List
        result = findGreaterThan(
            emptyList(), 4.999
        )
        assertEquals(emptyList<Double>(), result)
    }

    /** Greater Than works with the Max Value */
    @Test
    fun testFindGreaterThanMaxValue() {
        var result = findGreaterThan(
            maxValueList, Double.MAX_VALUE
        )
        assertEquals(emptyList<Double>(), result)
        result = findGreaterThan(
            maxValueList, Double.MAX_VALUE * 0.99999999
        )
        assertEquals(1, result.size)
        assertEquals(0, result[0])
    }

    /** Greater Than ignores Non Numerical elements */
    @Test
    fun testFindGreaterThanNaN() {
        var result = findGreaterThan(
            nanValueList, 3.9
        )
        assertEquals(1, result.size)
        assertEquals(4, result[0])
        result = findGreaterThan(
            nanValueList, 1.9
        )
        assertEquals(2, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
    }

    /** Greater Than finds positive infinity, not negative */
    @Test
    fun testFindGreaterThanInfinity() {
        var result = findGreaterThan(
            positiveInfiniteList, 3.9
        )
        assertEquals(2, result.size)
        assertEquals(3, result[0])
        assertEquals(4, result[1])
        // Negative Infinity
        result = findGreaterThan(
            negativeInfiniteList, 3.9
        )
        assertEquals(1, result.size)
        assertEquals(4, result[0])
    }

    @Test
    fun testStartArg() {
        val result1 = findGreaterThan(
            u101, 78.5, 90
        )
        val result2 = findGreaterThan(
            u101, 78.5, 100
        )
        assertEquals(listOf(99, 100), result1)
        assertEquals(listOf(100), result2)
    }

    @Test
    fun testEndArg() {
        val result1 = findGreaterThan(
            u101, 78.5, 0, 101
        )
        val result2 = findGreaterThan(
            u101, 78.5, 0, 100
        )
        assertEquals(listOf(99, 100), result1)
        assertEquals(listOf(99), result2)
    }

    @Test
    fun testSublistSearch() {
        val result1 = findGreaterThan(
            u101, 77.5, 99, 101
        )
        val result2 = findGreaterThan(
            u101, 77.5, 99, 100
        )
        assertEquals(listOf(99, 100), result1)
        assertEquals(listOf(99), result2)
    }

    @Test
    fun testBadIndexArgs() {
        val results = Array<List<Int>?>(3) { null }
        results[0] = findGreaterThan(
            u101, 77.5, 99, 99
        )
        results[1] = findGreaterThan(
            u101, 77.5, 99, 98
        )
        results[2] = findGreaterThan(
            u101, 77.5, -1, 98
        )
        assertEquals(
            listOf(0, 0, 0),
            results.map { it?.size }
        )
    }

}
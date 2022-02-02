package mathtools.lists.doubles

import mathtools.lists.DoubleList
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

/** Testing the Greater Than function for Double typed Lists */
class DoubleListGreaterThanTest {

    /** Greater Than function finds the right number of
     * elements in the small, medium, and large lists
     */
    @RepeatedTest(2)
    fun testFindGreaterThanRandomized() {
        var result = DoubleList.findGreaterThan(
            smallList.shuffled(),
            smallListFactor * 8 - 0.0001
        )
        assertEquals(3, result.size)
        result = DoubleList.findGreaterThan(
            medList.shuffled(),
            medListFactor * 900
        )
        assertEquals(100, result.size)
        result = DoubleList.findGreaterThan(
            largeList.shuffled(),
            largeListFactor * 120_000
        )
        assertEquals(30_000, result.size)
    }

    /** Greater Than works on single item and empty lists */
    @Test
    fun testFindGreaterThanSingleItem() {
        var result = DoubleList.findGreaterThan(
            singleItemList, 5.0
        )
        assertEquals(emptyList<Double>(), result)
        result = DoubleList.findGreaterThan(
            singleItemList, 4.9999999
        )
        assertEquals(1, result.size)
        assertEquals(0, result[0])
        // Empty List
        result = DoubleList.findGreaterThan(
            emptyList(), 4.999
        )
        assertEquals(emptyList<Double>(), result)
    }

    /** Greater Than works with the Max Value */
    @Test
    fun testFindGreaterThanMaxValue() {
        var result = DoubleList.findGreaterThan(
            maxValueList, Double.MAX_VALUE
        )
        assertEquals(emptyList<Double>(), result)
        result = DoubleList.findGreaterThan(
            maxValueList, Double.MAX_VALUE * 0.99999999
        )
        assertEquals(1, result.size)
        assertEquals(0, result[0])
    }

    /** Greater Than ignores Non Numerical elements */
    @Test
    fun testFindGreaterThanNaN() {
        var result = DoubleList.findGreaterThan(
            nanValueList, 3.9
        )
        assertEquals(1, result.size)
        assertEquals(4, result[0])
        result = DoubleList.findGreaterThan(
            nanValueList, 1.9
        )
        assertEquals(2, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
    }

    /** Greater Than finds positive infinity, not negative */
    @Test
    fun testFindGreaterThanInfinity() {
        var result = DoubleList.findGreaterThan(
            positiveInfiniteList, 3.9
        )
        assertEquals(2, result.size)
        assertEquals(3, result[0])
        assertEquals(4, result[1])
        // Negative Infinity
        result = DoubleList.findGreaterThan(
            negativeInfiniteList, 3.9
        )
        assertEquals(1, result.size)
        assertEquals(4, result[0])
    }

}
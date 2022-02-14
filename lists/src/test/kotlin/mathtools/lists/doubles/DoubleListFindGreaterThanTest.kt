package mathtools.lists.doubles

import mathtools.lists.DoubleList.findGreaterThan
import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.doubles.DoubleListTestResources.maxValueList
import mathtools.lists.doubles.DoubleListTestResources.nanValueList
import mathtools.lists.doubles.DoubleListTestResources.negativeInfiniteList
import mathtools.lists.doubles.DoubleListTestResources.positiveInfiniteList
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.math.roundToInt

/** Testing the Greater Than function for Double typed Lists */
class DoubleListFindGreaterThanTest {

    private val u101 = toDouble(uniform101)

    @Test
    fun testSingleItem() {
        assertEquals(
            0, findGreaterThan(
                listOf(5.0), 5.0
            ).size
        )
        assertEquals(
            listOf(0), findGreaterThan(
                listOf(5.0), 4.9999999
            )
        )
    }

    @Test
    fun testEmptyList() {
        assertEquals(
            0, findGreaterThan(
                emptyList(), 4.999
            ).size
        )
    }

    @Test
    fun testMaxValue() {
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

    @Test
    fun testNaN() {
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

    /** Finds positive infinity, not negative */
    @Test
    fun testInfinity() {
        val positiveResult = findGreaterThan(
            positiveInfiniteList, 3.9
        )
        assertEquals(2, positiveResult.size)
        assertEquals(3, positiveResult[0])
        assertEquals(4, positiveResult[1])
        // Negative Infinity
        val negativeResult = findGreaterThan(
            negativeInfiniteList, 3.9
        )
        assertEquals(1, negativeResult.size)
        assertEquals(4, negativeResult[0])
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

    @RepeatedTest(2)
    fun testRandomized() {
        val result = findGreaterThan(
            u101.shuffled(), 75.0
        )
        assertEquals(5, result.size)
        assertEquals(
            (76..80).toList(),
            result.map { u101[it].roundToInt() }.sorted()
        )
    }

}
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

/** Testing the Greater Than function for Double typed Lists
 * @author DK96-OS : 2022 */
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
        val res = Array<List<Int>?>(2) { null }
        res[0] = findGreaterThan(
            maxValueList, Double.MAX_VALUE
        )
        res[1] = findGreaterThan(
            maxValueList, Double.MAX_VALUE * 0.99999999
        )
        assertEquals(emptyList<Double>(), res[0])
        assertEquals(listOf(0), res[1])
    }

    @Test
    fun testNaN() {
        val res = Array<List<Int>?>(2) { null }
        res[0] = findGreaterThan(
            nanValueList, 3.9
        )
        res[1] = findGreaterThan(
            nanValueList, 1.9
        )
        assertEquals(listOf(4), res[0])
        assertEquals(listOf(2, 4), res[1])
    }

    /** Finds positive infinity, not negative */
    @Test
    fun testInfinity() {
        assertEquals(
            listOf(3, 4), findGreaterThan(
                positiveInfiniteList, 3.9
            )
        )
        assertEquals(
            listOf(4), findGreaterThan(
                negativeInfiniteList, 3.9
            )
        )
    }

    @Test
    fun testStartArg() {
        val res = Array<List<Int>?>(2) { null }
        res[0] = findGreaterThan(
            u101, 78.5, 90
        )
        res[1] = findGreaterThan(
            u101, 78.5, 100
        )
        assertEquals(listOf(99, 100), res[0])
        assertEquals(listOf(100), res[1])
    }

    @Test
    fun testEndArg() {
        val res1 = findGreaterThan(
            u101, 78.5, 0, 105
        )
        val res2 = findGreaterThan(
            u101, 78.5, 0, 100
        )
        assertEquals(listOf(99, 100), res1)
        assertEquals(listOf(99), res2)
    }

    @Test
    fun testSublistSearch() {
        val res = Array<List<Int>?>(2) { null }
        res[0] = findGreaterThan(
            u101, 77.5, 95, 100
        )
        res[1] = findGreaterThan(
            u101.reversed(), 77.5, 1, 5
        )
        assertEquals(listOf(98, 99), res[0])
        assertEquals(listOf(1, 2), res[1])
    }

    @Test
    fun testBadIndexArgs() {
        val res = Array<List<Int>?>(3) { null }
        res[0] = findGreaterThan(
            u101, 77.5, 99, 99
        )
        res[1] = findGreaterThan(
            u101, 77.5, 99, 98
        )
        res[2] = findGreaterThan(
            u101, 77.5, -1, 98
        )
        assertEquals(
            listOf(0, 0, 0),
            res.map { it?.size }
        )
    }

    @RepeatedTest(2)
    fun testShuffledList() {
        val shuffledList = u101.shuffled()
        val res = findGreaterThan(
            shuffledList, 75.0
        )
        assertEquals(5, res.size)
        assertEquals(
            (76..80).toList(),
            res.map { shuffledList[it].roundToInt() }.sorted()
        )
    }

}
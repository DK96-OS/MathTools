package mathtools.lists

import java.math.BigDecimal

/** Functions of Double typed Lists
 * @author DK96-OS : 2022 */
object DoubleList {

    /** Find indices of elements greater than the limit
     * @param list A List of values to search in
     * @param limit The lowest value that will be ignored
     * @param start The start of the range of indices to check (inclusive)
     * @param end The end of the range of indices to check (exclusive)
     * @return Indices of elements above limit */
    fun findGreaterThan(
        list: List<Double>,
        limit: Double,
        start: Int = 0,
        end: Int = list.size,
    ) : List<Int> {
        if (start < 0 || list.isEmpty()
            || limit == Double.POSITIVE_INFINITY
            || limit.isNaN()
        ) return emptyList()
        // If end is greater than list size, use list size
        val lastIndex = if (list.size < end)
            list.size - 1 else end - 1
        var indices: ArrayList<Int>? = null
        for (idx in start .. lastIndex) {
            if (list[idx] > limit) {
                if (indices == null)
                    indices = arrayListOf(idx)
                else
                    indices.add(idx)
            }
        }
        return indices ?: emptyList()
    }

    /** Find indices of elements less than the limit
     * @param list A List of values to search in
     * @param limit The greatest value that will be ignored
     * @param start The start of the range of indices to check (inclusive)
     * @param end The end of the range of indices to check (exclusive)
     * @return Indices of elements below limit */
    fun findLessThan(
        list: List<Double>,
        limit: Double,
        start: Int = 0,
        end: Int = list.size,
    ) : List<Int> {
        if (start < 0 || list.isEmpty()
            || limit == Double.NEGATIVE_INFINITY
            || limit.isNaN()
        ) return emptyList()
        // If end is greater than list size, use list size
        val lastIndex = if (list.size < end)
            list.size - 1 else end - 1
        var indices: ArrayList<Int>? = null
        for (idx in start .. lastIndex) {
            if (list[idx] < limit) {
                if (indices == null)
                    indices = arrayListOf(idx)
                else
                    indices.add(idx)
            }
        }
        return indices ?: emptyList()
    }

    /** Find indices of elements outside of the specified boundaries
     * @param list A List of values to search in
     * @param lowerBound The lower limit of the boundary
     * @param upperBound The upper limit of the boundary
     * @param start The start of the range of indices to check (inclusive)
     * @param end The end of the range of indices to check (exclusive)
     * @return Indices of elements out of bounds */
    fun findOutOfBounds(
        list: List<Double>,
        lowerBound: Double,
        upperBound: Double,
        start: Int = 0,
        end: Int = list.size,
    ) : List<Int> {
        if (start < 0 || list.isEmpty()
            || lowerBound.isNaN()
            || upperBound.isNaN()
            || lowerBound > upperBound
        ) return emptyList()
        // If end is greater than list size, use list size
        val lastIndex = if (list.size < end)
            list.size - 1 else end - 1
        var indices: ArrayList<Int>? = null
        for (idx in start .. lastIndex) {
            val item = list[idx]
            if (item < lowerBound || upperBound < item) {
                if (indices == null)
                    indices = arrayListOf(idx)
                else
                    indices.add(idx)
            }
        }
        return indices ?: emptyList()
    }

    /** Remove and return doubles from a mutable list at given indices
     * @param list The list of values to remove from
     * @param indices The list of indices to be removed
     * @return List of removed items, in same order as indices */
    fun removeByIndices(
        list: MutableList<Double>,
        indices: List<Int>,
    ) : List<Double> {
        if (list.isEmpty() || indices.isEmpty())
            return emptyList()
        val valid = indices.filter { it in list.indices }
        if (valid.isEmpty())
            return emptyList()
        val result = valid.map { list[it] }
        val sorted = valid.sortedDescending()
        for (i in sorted.indices)
            list.removeAt(sorted[i])
        return result
    }

    /** Calculate a large sum
     * @param list The values to add in the sum
     * @return A BigDecimal containing the sum of all values */
    fun largeSum(
        list: List<Double>,
    ) : BigDecimal {
        var sum: BigDecimal = BigDecimal.ZERO
        for (idx in list.indices) {
            val next = list[idx]
            if (next != 0.0)
                sum = sum.add(BigDecimal.valueOf(next))
        }
        return sum
    }

}
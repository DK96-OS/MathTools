package mathtools.lists

import java.math.BigInteger

/** Functions of Long typed Lists
 * @author DK96-OS : 2022 */
object LongList {

    /** Find all elements greater than the limit
     * @param list A List of values to search in
     * @param limit The lowest value that will be ignored
     * @param start The start of the range of indices to check (inclusive)
     * @param end The end of the range of indices to check (exclusive)
     * @return Indices of elements above limit */
    fun findGreaterThan(
        list: List<Long>,
        limit: Long,
        start: Int = 0,
        end: Int = list.size,
    ) : List<Int> {
        if (start < 0 || list.isEmpty()
            || limit == Long.MAX_VALUE
        ) return emptyList()
        // If end is greater than list size, use list size
        val lastIndex = if (list.size < end)
            list.size - 1 else end - 1
        var indices: ArrayList<Int>? = null
        for (idx in start .. lastIndex)
            if (list[idx] > limit) {
                if (indices == null)
                    indices = arrayListOf(idx)
                else
                    indices.add(idx)
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
        list: List<Long>,
        limit: Long,
        start: Int = 0,
        end: Int = list.size,
    ) : List<Int> {
        if (start < 0 || list.isEmpty() || limit == Long.MIN_VALUE)
            return emptyList()
        // If end is greater than list size, use list size
        val lastIndex = if (list.size < end)
            list.size - 1 else end - 1
        var indices: ArrayList<Int>? = null
        for (idx in start .. lastIndex)
            if (list[idx] < limit) {
                if (indices == null)
                    indices = arrayListOf(idx)
                else
                    indices.add(idx)
            }
        return indices ?: emptyList()
    }

    /** Find indices of elements outside of the given range
     * @param list A List of values to search in
     * @param range The range of values to ignore
     * @param start The start of the range of indices to check (inclusive)
     * @param end The end of the range of indices to check (exclusive)
     * @return Indices of elements out of range */
    fun findOutOfRange(
        list: List<Long>,
        range: LongRange,
        start: Int = 0,
        end: Int = list.size,
    ) : List<Int> {
        if (start < 0 || list.isEmpty()
            || range.first > range.last
            || range.first == Long.MIN_VALUE
            && range.last == Long.MAX_VALUE
        ) return emptyList()
        // If end is greater than list size, use list size
        val lastIndex = if (list.size < end)
            list.size - 1 else end - 1
        // Container for discovered indices
        var indices: ArrayList<Int>? = null
        if (range.first == range.last) {
            for (idx in start .. lastIndex) {
                val item = list[idx]
                if (item != range.first)
                    if (indices == null)
                        indices = arrayListOf(idx)
                    else
                        indices.add(idx)
            }
        } else {
            for (idx in start .. lastIndex) {
                val item = list[idx]
                if (item < range.first || range.last < item) {
                    if (indices == null)
                        indices = arrayListOf(idx)
                    else
                        indices.add(idx)
                }
            }
        }
        return indices ?: emptyList()
    }

    /** Remove and return items from the mutable list at the given indices */
    fun removeByIndices(
        list: MutableList<Long>,
        indices: List<Int>,
    ) : List<Long> = when {
        indices.isEmpty() -> emptyList()
        else -> ArrayList<Long>().apply {
            for (i in indices.size - 1 downTo 0) {
                val listIndex = indices[i]
                if (listIndex in list.indices)
                    add(list.removeAt(listIndex))
            }
        }
    }

    /** Calculate a large sum */
    fun largeSum(
        list: List<Long>,
    ) : BigInteger {
        val limit: Long = Long.MAX_VALUE / 2
        var sum: BigInteger = BigInteger.ZERO
        var minorSum = 0L
        for (idx in list.indices) {
            val next = list[idx]
            when {
                // Next is over limit, just add
                next >= limit -> sum += BigInteger.valueOf(next)
                // Minor sum below limit, safe to add next
                minorSum < limit -> minorSum += next
                else -> {
                    val trySum = minorSum + next
                    // Check for overflow
                    if (trySum > minorSum) { // No overflow
                        minorSum = trySum
                    } else {
                        sum += BigInteger.valueOf(minorSum)
                        minorSum = next
                    }
                }
            }
        }
        if (minorSum != 0L)
            sum += BigInteger.valueOf(minorSum)
        return sum
    }

}
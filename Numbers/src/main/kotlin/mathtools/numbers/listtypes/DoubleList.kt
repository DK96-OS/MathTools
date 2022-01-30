package mathtools.numbers.listtypes

import java.math.BigDecimal

/** Functions of Double typed Lists
 * Developed by DK96-OS : 2022 */
object DoubleList {

    /** Find indices of elements greater than the limit
     * @return Indices of elements above limit */
    fun findGreaterThan(
        list: List<Double>,
        limit: Double,
        start: Int = 0,
    ) : List<Int> {
        if (start < 0 || list.isEmpty())
            return emptyList()
        var indices: ArrayList<Int>? = null
        for (idx in start until list.size) {
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
     * @return Indices of elements below limit */
    fun findLessThan(
        list: List<Double>,
        limit: Double,
        start: Int = 0,
    ) : List<Int> {
        if (start < 0 || list.isEmpty())
            return emptyList()
        var indices: ArrayList<Int>? = null
        for (idx in start until list.size) {
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
     * @return Indices of elements out of bounds */
    fun findOutOfBounds(
        list: List<Double>,
        lowerBound: Double,
        upperBound: Double,
        start: Int = 0
    ) : List<Int> {
        if (start < 0 || list.isEmpty() || lowerBound > upperBound)
            return emptyList()
        var indices: ArrayList<Int>? = null
        for (idx in start until list.size) {
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

    /** Remove and return items from the mutable list at the given indices */
    fun removeByIndices(
        list: MutableList<Double>,
        indices: List<Int>,
    ) : List<Double> = when {
        indices.isEmpty() -> emptyList()
        else -> ArrayList<Double>().apply {
            for (i in indices.size - 1 downTo 0) {
                val listIndex = indices[i]
                if (listIndex in list.indices)
                    add(list.removeAt(listIndex))
            }
        }
    }

    /** Calculate a large sum.
     * Note: Result may depend on order of elements in list */
    fun largeSum(
        list: List<Double>,
    ) : BigDecimal {
        val limit: Double = Double.MAX_VALUE / 2
        var sum: BigDecimal = BigDecimal.ZERO
        var minorSum = 0.0
        for (idx in list.indices) {
            val next = list[idx]
            when {
                // Next is over limit, just add
                next >= limit -> sum += BigDecimal.valueOf(next)
                // Minor sum below limit, safe to add next
                minorSum < limit -> minorSum += next
                else -> {
                    val trySum = minorSum + next
                    // Check for overflow
                    if (trySum > minorSum) { // No overflow
                        minorSum = trySum
                    } else {
                        sum += BigDecimal.valueOf(minorSum)
                        minorSum = next
                    }
                }
            }
        }
        if (minorSum > 0)
            sum += BigDecimal.valueOf(minorSum)
        return sum
    }

}
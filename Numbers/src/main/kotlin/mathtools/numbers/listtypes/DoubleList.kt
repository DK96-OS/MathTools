package mathtools.numbers.listtypes

import java.math.BigDecimal

/** Functions of Double typed Lists */
object DoubleList {

    /** Find all elements greater than the limit */
    fun findGreaterThan(
        list: List<Double>,
        limit: Double,
        start: Int = 0,
    ) : List<Int>? {
        if (start < 0 || list.isEmpty()) return null
        val indices = ArrayList<Int>(4)
        for (idx in start until list.size) {
            if (list[idx] > limit) indices.add(idx)
        }
        return indices.ifEmpty { null }
    }

    /** Find all elements less than the limit */
    fun findLessThan(
        list: List<Double>,
        limit: Double,
        start: Int = 0,
    ) : List<Int>? {
        if (start < 0 || list.isEmpty()) return null
        val indices = ArrayList<Int>(4)
        for (idx in start until list.size) {
            if (list[idx] < limit) indices.add(idx)
        }
        return indices.ifEmpty { null }
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
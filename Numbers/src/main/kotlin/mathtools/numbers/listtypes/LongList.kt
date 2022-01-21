package mathtools.numbers.listtypes

import java.math.BigInteger

/** Functions of Long typed Lists */
object LongList {

    /** Find all elements greater than the limit */
    fun findGreaterThan(
        list: List<Long>,
        limit: Long,
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
        list: List<Long>,
        limit: Long,
        start: Int = 0,
    ) : List<Int>? {
        if (start < 0 || list.isEmpty()) return null
        val indices = ArrayList<Int>(4)
        for (idx in start until list.size) {
            if (list[idx] < limit) indices.add(idx)
        }
        return indices.ifEmpty { null }
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
        if (minorSum > 0)
            sum += BigInteger.valueOf(minorSum)
        return sum
    }

}
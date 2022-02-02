package mathtools.lists

import java.math.BigInteger
import kotlin.math.roundToLong

/** Functions of Integer typed Lists
 * Developed by DK96-OS : 2022 */
object IntList {

	/** Find all elements greater than the limit */
	fun findGreaterThan(
		list: List<Int>,
		limit: Int,
		start: Int = 0,
	) : List<Int> {
		if (start < 0 || list.isEmpty() || limit == Int.MAX_VALUE)
			return emptyList()
		var indices: ArrayList<Int>? = null
		for (idx in start until list.size)
			if (list[idx] > limit) {
				if (indices == null)
					indices = arrayListOf(idx)
				else
					indices.add(idx)
			}
		return indices ?: emptyList()
	}

	/** Find indices of elements less than the limit */
	fun findLessThan(
		list: List<Int>,
		limit: Int,
		start: Int = 0,
	) : List<Int> {
		if (start < 0 || list.isEmpty() || limit == Int.MIN_VALUE)
			return emptyList()
		var indices: ArrayList<Int>? = null
		for (idx in start until list.size)
			if (list[idx] < limit) {
				if (indices == null)
					indices = arrayListOf(idx)
				else
					indices.add(idx)
			}
		return indices ?: emptyList()
	}

	/** Find indices of elements outside of the given range */
	fun findOutOfRange(
		list: List<Int>,
		range: IntRange,
		start: Int = 0,
	) : List<Int> {
		if (start < 0 || list.isEmpty()
		    || range.first == Int.MIN_VALUE && range.last == Int.MAX_VALUE
		) return emptyList()
		var indices: ArrayList<Int>? = null
		for (idx in start until list.size)
			if (list[idx] !in range) {
				if (indices == null)
					indices = arrayListOf(idx)
				else
					indices.add(idx)
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
		list: List<Int>,
	) : BigInteger {
		val limit: Long = (Long.MAX_VALUE * 0.96f).roundToLong()
		var sum: BigInteger = BigInteger.ZERO
		var minorSum = 0L
		for (idx in list.indices) {
			val next = list[idx]
			when {
				// Minor sum below limit, safe to add next
				minorSum < limit -> minorSum += next
				else -> {
					val trySum: Long = minorSum + next
					// Check for overflow
					if (trySum > minorSum) minorSum = trySum // No overflow
					else {
						sum += BigInteger.valueOf(minorSum)
						minorSum = next.toLong()
					}
				}
			}
		}
		if (minorSum != 0L)
			sum += BigInteger.valueOf(minorSum)
		return sum
	}

}
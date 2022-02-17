package mathtools.lists

import java.math.BigInteger
import kotlin.math.roundToLong

/** Functions of Integer typed Lists
 * @author DK96-OS : 2022 */
object IntList {

	/** Find all elements greater than the limit
	 * @param list A List of values to search in
	 * @param limit The lowest value that will be ignored
	 * @param start The start of the range of indices to check (inclusive)
	 * @param end The end of the range of indices to check (exclusive)
	 * @return Indices of elements above limit */
	fun findGreaterThan(
		list: List<Int>,
		limit: Int,
		start: Int = 0,
		end: Int = list.size,
	) : List<Int> {
		if (start < 0 || list.isEmpty() || limit == Int.MAX_VALUE)
			return emptyList()
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
		list: List<Int>,
		limit: Int,
		start: Int = 0,
		end: Int = list.size,
	) : List<Int> {
		if (start < 0 || list.isEmpty() || limit == Int.MIN_VALUE)
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
		list: List<Int>,
		range: IntRange,
		start: Int = 0,
		end: Int = list.size,
	) : List<Int> {
		if (start < 0 || list.isEmpty()
		    || range.first > range.last
		    || range.first == Int.MIN_VALUE
		    && range.last == Int.MAX_VALUE
		) return emptyList()
		// If end is greater than list size, use list size
		val lastIndex = if (list.size < end)
			list.size - 1 else end - 1
		var indices: ArrayList<Int>? = null
		for (idx in start .. lastIndex)
			if (list[idx] !in range) {
				if (indices == null)
					indices = arrayListOf(idx)
				else
					indices.add(idx)
			}
		return indices ?: emptyList()
	}

	/** Remove and return ints from a mutable list at given indices
	 * @param list The list of values to remove from
	 * @param indices The list of indices to be removed
	 * @return List of removed items, in same order as indices */
	fun removeByIndices(
		list: MutableList<Int>,
		indices: List<Int>,
	) : List<Int> {
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
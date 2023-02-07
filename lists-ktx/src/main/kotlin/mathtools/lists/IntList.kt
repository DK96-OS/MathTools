package mathtools.lists

import java.math.BigInteger

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
		if (list.isEmpty()
			|| limit == Int.MAX_VALUE
		) return emptyList()
		// If start is smaller than zero, use zero
		val firstIndex = if (0 > start)
			0 else start
		// If end is greater than list size, use list size
		val lastIndex = if (list.size < end)
			list.size - 1 else end - 1
		//
		var indices: ArrayList<Int>? = null
		for (idx in firstIndex .. lastIndex)
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
		if (list.isEmpty()
			|| limit == Int.MIN_VALUE
		) return emptyList()
		// If start is smaller than zero, use zero
		val firstIndex = if (0 > start)
			0 else start
		// If end is greater than list size, use list size
		val lastIndex = if (list.size < end)
			list.size - 1 else end - 1
		var indices: ArrayList<Int>? = null
		for (idx in firstIndex .. lastIndex)
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
		if (list.isEmpty()
		    || range.first > range.last
		    || range.first == Int.MIN_VALUE
		    && range.last == Int.MAX_VALUE
		) return emptyList()
		// If start is smaller than zero, use zero
		val firstIndex = if (0 > start)
			0 else start
		// If end is greater than list size, use list size
		val lastIndex = if (list.size < end)
			list.size - 1 else end - 1
		//
		var indices: ArrayList<Int>? = null
		for (idx in firstIndex .. lastIndex)
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
		if (list.size < 2) return when (list.size) {
			1 -> BigInteger.valueOf(list[0].toLong())
			else -> BigInteger.ZERO
		}
		val buffer = BigIntSumBuffer()
		for (i in list) buffer.add(i)
		return buffer.sum
	}

}
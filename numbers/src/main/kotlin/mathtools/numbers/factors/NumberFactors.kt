package mathtools.numbers.factors

/** Functions that Factor Numbers
 * @author DK96-OS : 2022 */
object NumberFactors {

	/** Determines whether a number contains a factor of 2
	 * @param number the Number to check
	 * @return True if number is divisible by 2 */
	fun isProductOf2(
		number: Long,
	) : Boolean {
		val lowest = number.takeLowestOneBit()
		// If the lowest one bit is greater than 1, it is even
		return lowest != 0L && lowest > 1
	}

	/** Determines whether a number contains a factor of 5
	 * @param number the Number to check
	 * @return True if the number is divisible by 5 */
	fun isProductOf5(
		number: Long
	) : Boolean {
		val nStr = number.toString()
		val length = nStr.length - 1
		val s = nStr[length].digitToInt()
		return s == 5 || s == 0 && length > 0
	}

	/** Divides a factor from a number repeatedly
	 * @param factor The factor to divide with
	 * @param number The composite number to be reduced if it contains the factor
	 * @return The reduced or unreduced composite number */
	fun divideOutFactor(
		factor: Int,
		number: Long,
	) : Long {
		// Convert factor to Long once
		val lFactor = when {
			// These factors are invalid, or won't do anything
			factor in -1 .. 1 -> return number
			// Invert negative factors
			factor < 0 -> -factor.toLong()
			else -> factor.toLong()
		}
		// Divide and check
		var reduced = number / lFactor
		return if (reduced * lFactor == number) {
			// Try reducing by another factor
			var newReduced = reduced / lFactor
			while (newReduced * lFactor == reduced && reduced !in -1 .. 1) {
				reduced = newReduced
				newReduced = reduced / lFactor
			}
			reduced
		} else number
	}

}
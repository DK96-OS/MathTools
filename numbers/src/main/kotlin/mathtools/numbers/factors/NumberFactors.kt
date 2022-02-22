package mathtools.numbers.factors

/** Functions that Factor Numbers
 * @author DK96-OS : 2022 */
object NumberFactors {

	@Deprecated(
		"Moved to BitFactors class",
		ReplaceWith("isProductOf2", "mathtools.numbers.factors.BitFactoring"),
		level = DeprecationLevel.WARNING
	)
	/** Determines whether a number contains a factor of 2
	 * @param number the Number to check
	 * @return True if number is divisible by 2 */
	fun isProductOf2(
		number: Long,
	) : Boolean = BitFactoring.isProductOf2(number)

	@Deprecated(
		"Moved to BitFactors class",
		ReplaceWith("isProductOf2", "mathtools.numbers.factors.BitFactoring"),
		level = DeprecationLevel.WARNING
	)
	/** Determines whether a number contains a factor of 2
	 * @param number the Number to check
	 * @return True if number is divisible by 2 */
	fun isProductOf2(
		number: Int,
	) : Boolean = BitFactoring.isProductOf2(number)

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

	/** Count the power of a factor in a composite number.
	 *  Ignores negative signs.
	 * @param factor The factor to check for
	 * @param composite The number to check in
	 * @return The power of factor in the composite */
	fun countFactor(
		factor: Int,
		composite: Long,
	) : Int {
		// validate factor
		if (factor < 0) return countFactor(-factor, composite)
		else if (factor == 0 || factor == 1) return 0
		// validate composite
		if (composite < 0) return countFactor(factor, -composite)
		else if (composite == 0L || composite == 1L) return 0
		// cast to long once
		val longF = factor.toLong()
		var remainder = composite / longF
		// check
		val reverse = remainder * longF
		if (reverse < composite) return 0
		// iterate
		var counter = 1
		while (remainder > 1 && remainder % longF == 0L) {
			remainder /= longF
			counter++
		}
		return counter
	}

}
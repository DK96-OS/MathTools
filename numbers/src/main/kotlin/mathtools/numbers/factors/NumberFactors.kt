package mathtools.numbers.factors

/** Functions that Factor Numbers
 * @author DK96-OS : 2022 */
object NumberFactors {

	@Deprecated("Moved to BitFactoring",
		ReplaceWith("BitFactoring.isProductOf2(number)",
			"mathtools.numbers.factors.BitFactoring"),
		level = DeprecationLevel.ERROR
	)
	/** Determines whether a number contains a factor of 2
	 * @param number the Number to check
	 * @return True if number is divisible by 2 */
	fun isProductOf2(
		number: Long,
	) : Boolean = BitFactoring.isProductOf2(number)

	@Deprecated("Moved to BitFactoring",
		ReplaceWith("BitFactoring.isProductOf2(number)",
			"mathtools.numbers.factors.BitFactoring"),
		level = DeprecationLevel.ERROR
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
	@Deprecated("Moved to Factoring",
		ReplaceWith("Factoring.divideOutFactor(number, factor)")
	)
	fun divideOutFactor(
		factor: Int,
		number: Long,
	) : Long = Factoring.divideOutFactor(number, factor)

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
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

}
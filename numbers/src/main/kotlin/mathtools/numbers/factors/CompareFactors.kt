package mathtools.numbers.factors

/** Functions for comparing factors of numbers
 * @author DK96-OS : 2022 */
object CompareFactors {

	/** Find the Greatest Common Divisor of two integers.
	 * @param n1 One of the integers to check
	 * @param n2 One of the integers to check
	 * @return The Greatest Common Divisor, or 1 if either number is zero */
	fun gcd(
		n1: Int,
		n2: Int,
	) : Int {
		if (n1 == 0 || n2 == 0) return 1
		if (n1 < 0) return gcd(-n1, n2)
		if (n2 < 0) return gcd(n1, -n2)
		if (n1 == 1 || n2 == 1) return 1
		if (n1 == n2) return n1
		return if (n1 < n2)
			innerGCD(n2, n1)
		else
			innerGCD(n1, n2)
	}

	/** Assume both numbers are valid inputs, a is greater than b */
	private fun innerGCD(a: Int, b: Int): Int {
		val remainder = a % b
		return if (remainder == 0)
			b else innerGCD(b, remainder)
	}

	/** Find the Lowest Common Multiple of two integers.
	 *  Ignores negative signs, will always return a positive value.
	 * @param n1 One of the integers to compare
	 * @param n2 One of the integers to compare
	 * @return The Lowest Common Multiple, or 1 if either number is zero */
	fun lcm(
		n1: Int,
		n2: Int,
	) : Long {
		if (n1 == 0 || n2 == 0) return 1
		if (n1 < 0) return lcm(-n1, n2)
		if (n2 < 0) return lcm(n1, -n2)
		return when {
			n1 == 1 -> n2.toLong()
			n2 == 1 || n1 == n2 -> n1.toLong()
			else -> {
				val gcd = if (n1 > n2)
					innerGCD(n1, n2) else innerGCD(n2, n1)
				return n1 * n2.toLong() / gcd
			}
		}
	}

}
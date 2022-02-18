package mathtools.numbers.factors

import mathtools.numbers.factors.NumberFactors.isProductOf2

/** Functions that operate on 32-bit Integers
 * @author DK96-OS : 2020 - 2022 */
object IntOperations {

	/** Perform Base 10 shift up to Int Max value.
	 *  If an overflow occurs, Int MaxValue is returned
	 *  Negative shifts are allowed
	 * @param x The value to shift
	 * @param e The power of base 10 applied
	 * @return The shifted value, or MaxValue if known that the shift is too large */
	fun tenShift(
		x: Int,
		e: Int,
	) : Int = when {
		e == 0 -> x
		x == 0 -> 0
		x < 0 -> -tenShift(-x, e)
		e < 0 -> when {
			e < -9 -> 0
			e < -3 -> tenShift(x / 10_000, e + 4)
			e == -3 -> x / 1000
			e == -2 -> x / 100
			else -> x / 10
		}
		e > 9 -> Int.MAX_VALUE
		else -> {
			// Protect against overflow using Long
			val testShift = when (e) {
				1 -> x * 10L
				2 -> x * 100L
				3 -> x * 1000L
				else -> x * 10_000L
			}
			if (testShift <= Int.MAX_VALUE) {
				if (e < 5) testShift.toInt()
				else tenShift(testShift.toInt(), e - 4)
			} else Int.MAX_VALUE
		}
	}

	/** Compute an exponent, while checking for Integer Overflow
	 * Negative Powers are not allowed - will be returned
	 * @param x The base value of the exponent
	 * @param power The power of the exponent
	 * @return The product, and the remaining power that would cause an overflow */
	fun exponent(
		x: Int,
		power: Int,
	) : Pair<Int, Int> {
		when {
			power < 0 -> return x to power
			power == 0 -> return 1 to 0
			x < 2 -> return when (x) {
				1 -> 1 to 0
				0 -> 0 to 0
				-1 -> when {
					isProductOf2(power) -> 1 to 0
					else -> -1 to 0
				}
				else -> {
					val (product, remaining) = exponent(-x, power)
					if (isProductOf2(power - remaining))
						product to remaining
					else
						-product to remaining
				}
			}
			power == 1 -> return x to 0
		}
		val longX = x.toLong()
		var product = longX * longX
		// check for overflow
		if (product <= x || Int.MAX_VALUE < product)
			return x to power
		if (power == 2)
			return product.toInt() to 0
		for (e in 2 until power) {
			val next = product * longX
			if (next <= x || Int.MAX_VALUE < next) {
				// Integer Overflow
				return product.toInt() to power - e
			}
			product = next
		}
		return product.toInt() to 0
	}

}
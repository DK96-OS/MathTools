package mathtools.numbers.format

import mathtools.numbers.factors.IntOperations
import kotlin.math.roundToInt

/** Handling Percentage Values and Formatting
 * @author DK96-OS : 2022 */
object Percentages {

	/** Format a float as a percent string with specific accuracy.
	 *  Valid outputs are between 0% and 100% - why it is called 'strict'
	 * @param f Should be within 0f and 1f, otherwise the closest value of 0% or 100% is returned.
	 * @param decimalsAllowed The number of decimal places to allow in the final string, 0 .. 6 */
	fun strictPercent(
		f: Float,
		decimalsAllowed: Byte = 0,
	) : String = when {
		f in 0f .. 1f -> if (decimalsAllowed <= (0).toByte())
			(f * 100).roundToInt().toString().plus("%")
		else if (decimalsAllowed in 1 .. 7) {
			val shift = IntOperations.tenShift(1, decimalsAllowed.toInt())
			val rounded = (f * 100 * shift).roundToInt().toFloat()
			(rounded / shift).toString().plus("%")
		} else throw IllegalArgumentException("Invalid Decimal count")
		f > 1f -> "100%"
		else -> "0%"
	}

}
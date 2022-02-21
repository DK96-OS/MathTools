package mathtools.numbers.format

import mathtools.numbers.factors.IntOperations.tenShift
import kotlin.math.roundToInt

/** Handling Percentage Values and Formatting
 * @author DK96-OS : 2022 */
object Percentages {

	/** Format a float as a percent string with specific accuracy.
	 *  Valid outputs are between 0% and 100% - why it is called 'strict'
	 *  Up to 5 decimal places are allowed.
	 * @param f Should be within 0f and 1f, otherwise the closest value of 0% or 100% is returned.
	 * @param decimalsAllowed The number of decimal places to allow in the final string, 0 .. 5 */
	fun strictPercent(
		f: Float,
		decimalsAllowed: Byte = 0,
	) : String = when {
		f in 0.0000001f .. 1f -> when {
			decimalsAllowed < 1 -> (f * 100).roundToInt()
			decimalsAllowed < 5 -> {
				val shift = tenShift(1, decimalsAllowed.toInt()).toFloat()
				(f * 100f * shift).roundToInt() / shift
			}
			else -> {
				val shift = tenShift(1, 5).toFloat()
				(f * 100f * shift).roundToInt() / shift
			}
		}.toString().plus("%")
		f > 1f -> "100%"
		else -> "0%"
	}

}
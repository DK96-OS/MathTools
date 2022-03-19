package mathtools.numbers.format;

import static mathtools.numbers.format.Rounding.round;

import javax.annotation.Nonnull;

/** Handling Percentage Values and Formatting
 * @author DK96-OS : 2022 */
public final class Percentages {

	/** Format a float as a percent string with specific accuracy.
	 *  Valid outputs are between 0% and 100% - why it is called 'strict'
	 *  Up to 5 decimal places are allowed.
	 *  If the number of decimals is non-zero, the string will always end in ".0%"
	 * @param number Should be within 0 and 1, otherwise the closest value of 0% or 100% is returned.
	 * @param decimalsAllowed The number of decimal places to allow in the final string, 0 .. 5
	 * 	 * @return A string containing a percentage formatted number */
	@Nonnull
	public static String strictPercent(
		final float number,
		final byte decimalsAllowed
	) {
		if (0.000_000_1f <= number && 1 > number)
			if (1 > decimalsAllowed)
				return Math.round(number * 100) + "%";
			else
				return round(number * 100, decimalsAllowed) + "%";
		else
			return (1f <= number) ? "100%" : "0%";
	}

	/** Percent with no decimals
	 * @param number The number to format, should be within 0 and 1
	 * @return A string containing a percentage formatted number */
	@Nonnull
	public static String strictPercent(
		final float number
	) {
		if (0.000_000_1f <= number && 1 > number)
			return Math.round(number * 100) + "%";
		else
			return (1f <= number) ? "100%" : "0%";
	}

}
package mathtools.numbers.format;

import static mathtools.numbers.factors.IntOperations.tenShift;

import javax.annotation.Nonnull;

/** Handling Percentage Values and Formatting
 * @author DK96-OS : 2022 */
public final class Percentages {

	/** Format a float as a percent string with specific accuracy.
	 *  Valid outputs are between 0% and 100% - why it is called 'strict'
	 *  Up to 5 decimal places are allowed.
	 * @param f Should be within 0f and 1f, otherwise the closest value of 0% or 100% is returned.
	 * @param decimalsAllowed The number of decimal places to allow in the final string, 0 .. 5 */
	@Nonnull
	public static String strictPercent(
		final float f,
		final byte decimalsAllowed
	) {
		if (0.0000001f <= f && f <= 1)
			return adjust(f * 100f, decimalsAllowed) + "%";
		else if (f > 1f) return "100%";
		else return "0%";
	}

	/** Percent with no decimals */
	@Nonnull
	public static String strictPercent(
		final float f
	) {
		return strictPercent(f, (byte) 0);
	}

	private static int adjust(
		final float number,
		final int decimals
	) {
		if (decimals < 1)
			return Math.round(number);
		else if (decimals < 5) {
			float shift = tenShift(
				1, decimals
			);
			return (int) (Math.round(number * shift) / shift);
		} else {
			float shift = tenShift(1, 5);
			return (int) (Math.round(number * shift) / shift);
		}
	}

}
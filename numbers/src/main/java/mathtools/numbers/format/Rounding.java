package mathtools.numbers.format;

/** Rounding various Number Types
 * @author DK96-OS : 2022 */
public final class Rounding {

	/** Truncate a float to some number of decimal places
	 * @param number The number to be rounded
	 * @param decimals The number of decimals to keep. Maximum 5, Minimum 0.
	 * @return A Floating point number that is rounded
	 */
	public static float round(
		final float number,
		final int decimals
	) {
		// Less than one decimals is covered by Math.round
		if (1 > decimals) return Math.round(number);
		// Shift by a multiple of 10
		final float shift;
		switch (decimals) {
			case 1: shift = 10; break;
			case 2: shift = 100; break;
			case 3: shift = 1000; break;
			case 4: shift = 10000; break;
			// The maximum reliable number of decimals is 5
			default: shift = 100000; break;
		}
		return Math.round(number * shift) / shift;
	}

	private Rounding() {}

}
package mathtools.numbers.factors;

/** Functions that Factor Numbers
 * @author DK96-OS : 2022 */
public final class NumberFactors {

	/** Determines whether a number contains a factor of 5
	 * @param number the Number to check
	 * @return True if the number is divisible by 5 */
	public static boolean isProductOf5(
		final long number
	) {
		final String nStr = Long.toString(number);
		final int length = nStr.length() - 1;
		final int d = Character.digit(nStr.charAt(length), 10);
		return 5 == d || 0 == d && 0 < length;
	}

	/** Count the power of a factor in a composite number.
	 *  Ignores negative signs.
	 * @param factor The factor to check for
	 * @param composite The number to check in
	 * @return The power of factor in the composite */
	public static int countFactor(
		final int factor,
		final long composite
	) {
		// validate factor
		if (0 > factor)
			return countFactor(-factor, composite);
		else if (2 > factor)
			return 0;
		// validate composite
		else if (0L > composite)
			return countFactor(factor, -composite);
		else if (2 > composite)
			return 0;
		else {
			final long longF = factor;    // cast to long once
			long remainder = composite / longF;
			// check by multiplication
			final long reverse = remainder * longF;
			// Assume always less than if fails
			if (reverse < composite)
				return 0;
			else {
				int counter = 1;
				for(; 1L < remainder && 0L == remainder % longF; ++counter) {
					remainder /= longF;
				}
				return counter;
			}
		}
	}

	private NumberFactors() {}

}
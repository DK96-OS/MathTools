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
		return d == 5 || d == 0 && length > 0;
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
		if (factor < 0)
			return countFactor(-factor, composite);
		else if (factor < 2)
			return 0;
		// validate composite
		else if (composite < 0L)
			return countFactor(factor, -composite);
		else if (composite < 2)
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
				for(; remainder > 1L && remainder % longF == 0L; ++counter) {
					remainder /= longF;
				}
				return counter;
			}
		}
	}

	private NumberFactors() {}

}
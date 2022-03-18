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

	private NumberFactors() {}

}
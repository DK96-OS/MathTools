package mathtools.numbers.factors;

/** Functions for comparing factors of numbers
 * @author DK96-OS : 2022 */
public final class CompareFactors {

	/** Find the Greatest Common Divisor of two integers.
	 * @param n1 One of the integers to check
	 * @param n2 One of the integers to check
	 * @return The Greatest Common Divisor, or 1 if either number is zero */
	public static int gcd(
		final int n1,
		final int n2
	) {
		if (0 == n1 || 0 == n2) return 1;
		if (0 > n1) return gcd(-n1, n2);
		if (0 > n2) return gcd(n1, -n2);
		if (1 == n1 || 1 == n2) return 1;
		if (n1 == n2) return n1;
		return (n1 < n2) ? innerGCD(n2, n1) : innerGCD(n1, n2);
	}

	/** Assume both numbers are valid inputs, a is greater than b */
	private static int innerGCD(
		final int a,
		final int b
	) {
		final int remainder = a % b;
		return (0 == remainder) ? b : innerGCD(b, remainder);
	}

	/** Find the Lowest Common Multiple of two integers.
	 *  Ignores negative signs, will always return a positive value.
	 * @param n1 One of the integers to compare
	 * @param n2 One of the integers to compare
	 * @return The Lowest Common Multiple, or 1 if either number is zero */
	public static long lcm(
		final int n1,
		final int n2
	) {
		if (0 == n1 || 0 == n2) return 1;
		if (0 > n1) return lcm(-n1, n2);
		if (0 > n2) return lcm(n1, -n2);
		if (1 == n1) return n2;
		if (1 == n2 || n1 == n2) return n1;
		//
		final int gcd = (n1 > n2) ?
			innerGCD(n1, n2) : innerGCD(n2, n1);
		return n1 * ((long) n2) / gcd;
	}

	private CompareFactors() {}

}
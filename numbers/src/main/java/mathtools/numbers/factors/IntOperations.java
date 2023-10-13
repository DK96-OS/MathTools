package mathtools.numbers.factors;

import com.google.common.math.IntMath;

import javax.annotation.Nonnull;

import mathtools.pairs.IntPair;

/** Functions that operate on 32-bit Integers
 * @author DK96-OS : 2020 - 2022 */
public final class IntOperations {

	/** Perform Base 10 shift up to Int Max value.
	 *  If an overflow occurs, Int MaxValue is returned
	 *  Negative shifts are allowed
	 * @param x The value to shift
	 * @param e The power of base 10 applied
	 * @return The shifted value, or MaxValue if known that the shift is too large */
	public static int tenShift(
		final int x,
		final int e
	) {
		if (0 == e)
			return x;
		else if (0 == x)
			return 0;
		else if (0 > x)
			return -tenShift(-x, e);
		else if (9 < e)
			return Integer.MAX_VALUE;
		else if (0 > e) {
			if (-9 > e) return 0;
			else return
				(-3 > e ? tenShift(x / 10000, e + 4) :
					(-3 == e ? x / 1000 :
						(-2 == e ? x / 100 : x / 10)
					)
				);
		} else {
			final long testShift;
			switch(e) {
				case 1:
					testShift = (long)x * 10L;
					break;
				case 2:
					testShift = (long)x * 100L;
					break;
				case 3:
					testShift = (long)x * 1000L;
					break;
				default:
					testShift = (long)x * 10000L;
			}
			if ((long) Integer.MAX_VALUE >= testShift)
				return (5 > e ? (int)testShift :
					tenShift((int)testShift, e - 4)
				);
			else
				return Integer.MAX_VALUE;
		}
	}

	/** Tries to evaluate an exponential term within 32-bit signed int
	 *  If the term cannot be evaluated, the number of powers remaining are returned as well
	 * @param base The base factor of the exponent.
	 * @param power The power of the exponent.
	 * @return A pair of integers. If the second int is zero, the first is the evaluated term. If
	the second int is non-zero, this is the power on the exponent that has yet to be evaluated. * */
	@Nonnull
	public static IntPair exponent(
		final int base,
		int power
	) {
		if (0 > power)
			return new IntPair(base, power);
		else if (0 == power)
			return new IntPair(1, 0);
		else if (2 > base)
			return negativeExponentBase(base, power);
		else if (1 == power)
			return new IntPair(base, 0);
		else {
			int product = base;
			power--;
			try {
				product = IntMath.checkedMultiply(product, base);
				power--;
				for (; 0 < power; --power) {
					product = IntMath.checkedMultiply(product, base);
				}
				return new IntPair(product, 0);
			} catch (final ArithmeticException e) {
				return new IntPair(product, power);
			}
		}
	}

	/** Handles certain exponent function cases
	 * @param x The base factor of the exponent. Is 1, 0, or negative.
	 * @param power The power of the exponent. Is greater than 0.
	 * @return A pair of integer values */
	@Nonnull
	private static IntPair negativeExponentBase(
		final int x,
		final int power
	) {
		switch (x) {
			case -1: if (BitFactoring.isProductOf2(power))
				return new IntPair(1, 0);
			else
				return new IntPair(-1, 0);
			case 0:
				return new IntPair(0, 0);
			case 1:
				return new IntPair(1, 0);
			default:
				// X is negative, need to flip
				final IntPair result = exponent(-x, power);
				// Check the results for additional processing
				int product = result.getFirst();
				final int remaining = result.getSecond();
				// If the difference is odd, the product will be negative
				if (!BitFactoring.isProductOf2(power - remaining))
					product = -product;
				return new IntPair(product, remaining);
		}
	}

	private IntOperations() {}

}
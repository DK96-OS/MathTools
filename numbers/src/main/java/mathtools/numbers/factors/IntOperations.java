package mathtools.numbers.factors;

import javax.annotation.Nonnull;

import mathtools.numbers.structs.IntPair;

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

	@Nonnull
	public static IntPair exponent(
		final int x,
		final int power
	) {
		if (0 > power)
			return new IntPair(x, power);
		else if (0 == power)
			return new IntPair(1, 0);
		else if (2 > x) switch(x) {
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
		} else if (1 == power)
			return new IntPair(x, 0);
		else {
			final long longX = x;
			long product = longX * longX;
			// check for overflow
			if (product > longX && (long)Integer.MAX_VALUE >= product) {
				if (2 != power) {
					for (int e = 2; e < power; ++e) {
						final long next = product * longX;
						if (next <= longX || (long) Integer.MAX_VALUE < next) {
							// Integer Overflow
							return new IntPair((int) product, power - e);
						}
						product = next;
					}
				}
				return new IntPair((int) product, 0);
			} else
				return new IntPair(x, power);
		}
	}

	private IntOperations() {}

}
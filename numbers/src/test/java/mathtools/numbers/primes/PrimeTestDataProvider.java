package mathtools.numbers.primes;

import com.google.common.primitives.Ints;

import java.util.List;

import javax.annotation.Nonnull;

import mathtools.arrays.IntArrayExt;

/** Provides Prime Number Data for Tests */
public final class PrimeTestDataProvider {

	/** Get a list of primes up to 251 */
	@Nonnull
	public static List<Integer> getPrimes251() {
		return IntArrayExt.toList(
			primes251
		);
	}

	private static final int[] primes251 = {
		2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
		73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
		151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227,
		229, 233, 239, 241, 251
	};

	private static final int[] primes257To349 = {
		257, 263, 269, 271, 277, 281, 283, 293,
		307, 311, 313, 317, 331, 337, 347, 349
	};

	private static final int[] primes353To509 = {
		353, 359, 367, 373, 379, 383, 389, 397,
		401, 409, 419, 421, 431, 433, 439, 443,
		449, 457, 461, 463, 467, 479, 487, 491,
		499, 503, 509
	};

	/** Get a list of primes up to 349 */
	@Nonnull
	public static List<Integer> getPrimes349() {
		return IntArrayExt.toList(
			Ints.concat(
				primes251, primes257To349
			)
		);
	}

	@Nonnull
	public static List<Integer> getPrimes509() {
		final int[] primes349 = Ints.concat(
			primes251, primes257To349
		);
		return IntArrayExt.toList(
			Ints.concat(
				primes349, primes353To509
			)
		);
	}

}
package mathtools.numbers.primes.validate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.numbers.factors.BitFactoring;
import mathtools.numbers.primes.PrimeCacheInterface;

/** For finding and checking for prime numbers
 * @author DK96-OS : 2022 */
public final class PrimeValidation {

	/** Determine if a number is prime.
	 * Zero and one are prime.
	 * Negative numbers are treated as positive.
	 * @param number The number to check for prime status
	 * @param cache the PrimeCache Interface to use to obtain Prime Numbers
	 * @return Whether the number is a prime, as far as this cache can check
	 */
	public static boolean isPrime(
		final int number,
		@Nonnull final PrimeCacheInterface cache
	) {
		// Get the boundary of the Static Cache
		final int maxStaticPrime = StaticPrimes.getStaticPrime(
			StaticPrimes.MAX_INDEX
		);
		// handle negative numbers and positives in static cache range
		if (number < maxStaticPrime) {
			// Check for small numbers
			if (4 > number) {
				// In prime region centered on zero
				if (-4 < number)
					return true;
				// Protect against min value
				else if (Integer.MIN_VALUE == number)
					return false;
				// Flip sign to positive
				else
					return isPrime(
						-number, cache
					);
			}
			// If it is prime, it will be in the static array
			return StaticPrimes.containsNumber(number);
		} else if (number == maxStaticPrime)
			return true;
		// check the bits for an even number
		if (BitFactoring.isProductOf2(number))
			return false;
		// check all primes in static array
		if (!staticIsPrime(number))
			return false;
		// There is a break condition in static prime check
		else if (maxStaticPrime * maxStaticPrime > number)
			return true;
		// check all primes in the cache
		try {
			return cacheIsPrime(number, cache);
		} catch (final IllegalArgumentException e) {
			// If not prime, has large prime factors
			throw new IllegalArgumentException(
				String.format(
					"Number %d is too large for this cache",
					number
				)
			);
		}
	}

	/** Find the next prime equal to or greater than testNum.
	 *  This will assume that Cache contains all primes up to, but not including testNum (if it
	 *  is prime).
	 * @param testNum The first number to test for prime status
	 * @param cache The prime number cache to use
	 * @return The next prime, or null if the cache is not large enough to find the next prime
	 * @throws IllegalArgumentException If testNum is a product of 2, or less than 5
	 */
	@Nullable
	public static Integer findPrime(
		int testNum,
		@Nonnull final PrimeCacheInterface cache
	) throws IllegalArgumentException {
		// If this function isn't being used properly, throw Exception
		if (5 > testNum || BitFactoring.isProductOf2(testNum))
			throw new IllegalArgumentException(
				String.format(
					"Invalid findPrime method argument: %d", testNum
				)
			);
		// Additional conditions on this method may include:
		// - testNum should be greater than the maximum static prime
		//      Otherwise, not optimized
		for (;
		     testNum <= cache.getMaxPrime();
		     testNum += 2
		) {
			// The public isPrime method does input validation
			//    An optimization would skip validation, use private method
			if (isPrime(testNum, cache))
				return testNum;
		}
		return null;
	}

	/** Checks the factors in the static array.
	 *  Note: Skips checking if 2 is a factor, use [BitFactoring] for 2
	 * @param number The number to check for prime status
	 * @return True if none of the primes in static array are factors
	 */
	static boolean staticIsPrime(
		final int number
	) {
		// In the range [-3, 3] all are prime
		if (4 > number)
			return -4 < number || staticIsPrime(-number);
		// Check static array for factor
		int prevPrime = 2;  // Assume 2 has already been checked
		int primeIndex = 1;
		for (;
		     StaticPrimes.MAX_INDEX >= primeIndex;
		     primeIndex++
		) {
			final byte testPrime = StaticPrimes.getStaticPrime(
				primeIndex
			);
			// If Number can be divided evenly by test Prime
			if (0 == number % testPrime)
				return false;
			// Square Root break condition
			if (testPrime * prevPrime > number)
				return true;
			prevPrime = testPrime;
		}
		return true;
	}

	/** Checks the factors in the cache
	 *  Note: Assumes the static array has already been checked
	 * @param number The number to check for prime status
	 * @param cache The prime number cache to use
	 * @return False if number is definitely not prime, true if prime
	 * @throws IllegalArgumentException If the Number is too high to validate with this cache
	 */
	private static boolean cacheIsPrime(
		final int number,
		@Nonnull final PrimeCacheInterface cache
	) throws IllegalArgumentException {
		// Start at the end of the static array
		int prevPrime = StaticPrimes.getStaticPrime(
			StaticPrimes.MAX_INDEX
		);
		int primeIndex = StaticPrimes.MAX_INDEX + 1;
		// Check cached values
		for (;
		     primeIndex <= cache.getMaxIndex();
		     ++primeIndex
		) {
			final int testPrime = cache.getPrime(
				primeIndex
			);
			// If Number can be divided evenly by test Prime
			if (0 == number % testPrime)
				return false;
			// Square Root break condition
			if (testPrime * prevPrime > number)
				return true;
			prevPrime = testPrime;
		}
		// Need to test more primes to be sure, but cache is exhausted
		throw new IllegalArgumentException(
			"Number is too high for this cache"
		);
	}

	private PrimeValidation() {}

}
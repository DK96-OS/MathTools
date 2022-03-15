package mathtools.numbers.primes;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.numbers.factors.BitFactoring;

/** For finding and checking for prime numbers
 * @author DK96-OS : 2022 */
public final class PrimeChecker {

	private PrimeChecker() {}

	/** Determine if a number is prime. Zero and one are prime.
	 * @param number The number to check for prime status
	 * @return Whether the number is a prime, as far as this cache can check */
	public static boolean isPrime(
		final int number,
		@Nonnull final PrimeCacheInterface cache
	) {
		// handle small and negative numbers
		if (number <= initArray[15]) {
			// Check for small numbers
			if (number < 4) {
				// In prime region centered on zero
				if (number > -4) return true;
				// Protect against min value
				else if (number == Integer.MIN_VALUE) return false;
				// Flip sign to positive
				else return isPrime(-number, cache);
			}
			// If it is prime, it will be in the static array
			final int searchKey = Arrays.binarySearch(
				// Primes at index 0, 1 are covered by branches above
				initArray, 2, 15, (byte) number
			);
			return initArray[searchKey] == number;
		}
		// check the bits for an even number
		if (BitFactoring.isProductOf2(number)) return false;
		// check all primes in static array
		if (!staticIsPrime(number)) return false;
		// check all primes in the cache
		try {
			return cacheIsPrime(number, cache);
		} catch (IllegalArgumentException e) {
			// If not prime, has large prime factors
			return true;
		}
	}

	/** Find the next prime
	 * @param testNum The starting point for looking for new primes
	 * @param cache The prime number cache to use
	 * @return The next prime, or null if the cache is not large enough to find the next prime */
	@Nullable
	static Integer findPrime(
		int testNum,
		@Nonnull final PrimeCacheInterface cache
	) {
		// If this function isn't being used properly, return null
		if (testNum < 5 || BitFactoring.isProductOf2(testNum)) return null;
		//
		for (; testNum <= cache.getMaxPrime(); testNum += 2) {
			if (isPrime(testNum, cache)) return testNum;
		}
		return null;
	}

	/** The first 16 useful primes */
	private static final byte[] initArray = new byte[]{
		2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
	};

	/** Get a prime number from the static array.
	 *  The static array contains primes from 2 to 53.
	 * @param index The prime number index. Must be less than 16.
	 * @return A prime number from a static array */
	static int getStaticPrime(
		final int index
	) throws ArrayIndexOutOfBoundsException {
		return initArray[index];
	}

	/** Checks the factors in the static array.
	 *  Note: Skips checking if 2 is a factor, use [BitFactoring] for 2
	 * @param number The number to check for prime status
	 * @return True if none of the primes in static array are factors */
	static boolean staticIsPrime(
		final int number
	) {
		// In the range [-3, 3] all are prime
		if (number < 4) return number > -4 || staticIsPrime(-number);
		// Check static array for factor
		int prevPrime = 2;  // Assume 2 has already been checked
		for (int i = 1; i < 16; i++) {
			final byte testPrime = initArray[i];
			if (number % testPrime == 0) return false;
			//
			if (testPrime * prevPrime > number) return true;
			prevPrime = testPrime;
		}
		return true;
	}

	/** Checks the factors in the cache
	 *  Note: Assumes the static array has already been checked
	 * @param number The number to check for prime status
	 * @param cache The prime number cache to use
	 * @return False if number is definitely not prime, true if prime */
	private static boolean cacheIsPrime(
		final int number,
		@Nonnull final PrimeCacheInterface cache
	) {
		// Start at the end of the static array
		int prevPrime = initArray[15];
		// Check cached values
		for (int i = 16; i <= cache.getMaxIndex(); i++) {
			final int testPrime = cache.getPrime(i);
			if (number % testPrime == 0) return false;
			if (testPrime * prevPrime > number) return true;
			prevPrime = testPrime;
		}
		// Need to test more primes to be sure, but cache is exhausted
		throw new IllegalArgumentException("Number is too high for this cache");
	}

}
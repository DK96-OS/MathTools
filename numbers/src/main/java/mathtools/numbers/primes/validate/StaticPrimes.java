package mathtools.numbers.primes.validate;

import mathtools.lists.arrays.ByteArrayExt;

/** An organized static structure for containing small prime numbers
 * @author DK96-OS : 2022 */
public final class StaticPrimes {

	/** The index of the largest prime number in this cache */
	public static final byte MAX_INDEX = 15;

	/** The first 16 useful primes */
	private static final byte[] initArray = {
		2, 3, 5, 7, 11, 13, 17, 19,
		23, 29, 31, 37, 41, 43, 47, 53,
	};

	/** Obtain a Prime from the Static Array.
	 * The static array contains primes from 2 to 53.
	 * @param index The index of the prime number, from 0 to StaticPrimeCache MAX_INDEX
	 * @return The Prime Number at the requested index
	 * @throws ArrayIndexOutOfBoundsException If the index is not within the static array bounds
	 */
	public static byte getStaticPrime(
		final int index
	) throws ArrayIndexOutOfBoundsException {
		return initArray[index];
	}

	/** Determine if a number is present in the cache
	 * @param number The number to search for
	 * @return Whether the number is present or not */
	public static boolean containsNumber(
		final int number
	) {
		final byte maxPrime = initArray[MAX_INDEX];
		// Compare with the highest value in the array
		if (number > maxPrime)
			return false;
		else if (number == maxPrime)
			return true;
		// If it is prime, it will be in the static array
		//todo: Use a logarithmic algorithm, this is linear (but limited to 16 operations)
		final int targetIndex = ByteArrayExt.findTargetValueN(
			initArray,
			(byte) number,
			1
		);
		// Non-negative index means the value is in the array
		return -1 < targetIndex;
	}

}
package mathtools.numbers.primes;

/** An organized static structure for containing small prime numbers
 * @author DK96-OS : 2022 */
final class StaticPrimes {

	/** The index of the largest prime number in this cache */
	static final byte MAX_INDEX = 15;

	/** The first 16 useful primes */
	private static final byte[] initArray = {
		2, 3, 5, 7, 11, 13, 17, 19,
		23, 29, 31, 37, 41, 43, 47, 53,
	};

	/** Obtain a Prime from the Static Array.
	 * The static array contains primes from 2 to 53.
	 * @param index The index of the prime number, from 0 to StaticPrimeCache MAX_INDEX
	 * @return The Prime Number at the requested index.
	 * @throws ArrayIndexOutOfBoundsException If the index is not within the static array bounds
	 */
	static byte getStaticPrime(
		final int index
	) throws ArrayIndexOutOfBoundsException {
		return initArray[index];
	}

	/** Determine if a number is present in the static prime array.
	 * This array contains the first 16 primes (2 to 53).
	 * @param number The number to search for.
	 * @return Whether the number is present or not.
	 */
	static boolean containsNumber(
		final int number
	) {
		final byte maxPrime = initArray[MAX_INDEX];
		// Compare with the highest value in the array
		if (number > maxPrime)
			return false;
		else if (number == maxPrime)
			return true;
		// If it is prime, it will be in the static array
		// Use a binary-search-like algorithm
		int searchIndex;
		final int lastIndex;
		final byte mid = initArray[7];
		// Split Array in Half using sorted property
		if (number < mid) {
			searchIndex = 0;
			lastIndex = 7;
		} else if (number > mid) {
			searchIndex = 8;
			lastIndex = 15;
		} else
			return true;
		// Traverse half of the array
		for (; searchIndex <= lastIndex; ++searchIndex) {
			final byte value = initArray[searchIndex];
			if (value == number)
				return true;
			if (value > number)
				return false;
		}
		return false;
	}

	private StaticPrimes() {}

}
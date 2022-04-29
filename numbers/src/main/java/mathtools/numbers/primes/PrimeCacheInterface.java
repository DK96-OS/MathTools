package mathtools.numbers.primes;

/** Provides an ordered set of prime numbers
 * @author DK96-OS : 2022 */
public interface PrimeCacheInterface {

	/** Obtain the highest index that this cache can contain
	 * @return An integer, the index of the highest prime number */
	int getMaxIndex();

	/** Obtain the highest prime that this cache can contain
	 * @return An integer, the highest prime number */
	int getMaxPrime();

	/** Request a prime number by it's index
	 * @param index The index of the requested prime number
	 * @return The prime number at the given index */
	int getPrime(int index);

	/** Obtain the index of the largest prime that is in the cache
	 * @return An integer, the index of the current highest prime number in the cache */
	int getHighestCachedIndex();

	/** Clear saved prime numbers in this cache */
	void clear();

}
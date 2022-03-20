package mathtools.numbers.primes.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static mathtools.numbers.primes.PrimeChecker.isPrime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeTestDataProvider;

/** Testing [BytePrimeCache]
 * @author DK96-OS : 2022 */
public class BytePrimeCacheTest {

	private final List<Integer> primeList = PrimeTestDataProvider.getPrimes251();

	private BytePrimeCache cache;

	@BeforeEach
	void testSetup() { cache = new BytePrimeCache(); }

	@Test
	void testInitialization() {
		// The first 16 prime numbers are always available
		assertEquals(
			15, cache.getHighestCachedIndex());
		// Even if the cache is cleared
		cache.clear();
		assertEquals(
			15, cache.getHighestCachedIndex());
	}

	@Test
	void testGetPrime() {
		for (int i = 0; i < primeList.size(); ++i) {
			final int prime = primeList.get(i);
			assertEquals(
				prime, cache.getPrime(i),
				String.format("Failed getPrime(%d) = %d", i, prime)
			);
			cache.clear();
		}
	}

	@Test
	void testIsPrimeAscending() {
		final int lastPrime = primeList.get(primeList.size() - 1);
		// From 2 to the highest prime in the cache
		for (int n = 2; n <= lastPrime; ++n)
			assertEquals(
				primeList.contains(n),
				isPrime(n, cache),
				String.format("Failed to identify: %d", n)
			);
	}

	@Test
	void testIsPrimeDescending() {
		final int lastPrime = primeList.get(primeList.size() - 1);
		// From the highest prime in the cache, down to 2
		for (int n = lastPrime; 2 <= n; --n) {
			assertEquals(
				primeList.contains(n),
				isPrime(n, cache),
				String.format("Failed to identify: %d", n)
			);
			cache.clear();
		}
	}

	@Test
	void testGetPrimeDecreasing() {
		for (int i = cache.getMaxIndex(); 0 <= i; --i)
			assertEquals(
				primeList.get(i), cache.getPrime(i)
			);
	}

	@Test
	void testPrimeTooLargeForCache() {
		assertThrows(
			IllegalArgumentException.class,
			() -> cache.getPrime(cache.getMaxIndex() + 5)
		);
	}

}
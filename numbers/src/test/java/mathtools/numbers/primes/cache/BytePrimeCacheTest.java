package mathtools.numbers.primes.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import kotlin.random.Random;
import kotlin.ranges.IntRange;
import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeTestDataProvider;
import mathtools.numbers.primes.validate.PrimeValidation;

/** Testing [BytePrimeCache]
 * @author DK96-OS : 2022 */
public final class BytePrimeCacheTest {

	private final List<Integer> primeList = PrimeTestDataProvider.getPrimes251();

	private BytePrimeCache cache;

	@BeforeEach
	public void testSetup() {
		cache = new BytePrimeCache();
	}

	@Test
	public void testInitialization() {
		// The first 16 prime numbers are always available
		assertEquals(
			15,
			cache.getHighestCachedIndex()
		);
		// Even if the cache is cleared
		cache.clear();
		assertEquals(
			15,
			cache.getHighestCachedIndex()
		);
	}

	@Test
	public void testGetPrime() {
		int primeIndex = 0;
		for (;
			primeIndex < primeList.size();
			++primeIndex
		) {
			final int prime = primeList.get(primeIndex);
			assertEquals(
				prime,
				cache.getPrime(primeIndex),
				String.format(
					"Failed getPrime(%d) = %d",
					primeIndex,
					prime
				)
			);
			cache.clear();
		}
	}

	@Test
	public void testGetPrimeDecreasing() {
		int i = cache.getMaxIndex();
		for (;
			0 <= i;
			--i
		) assertEquals(
			primeList.get(i),
			cache.getPrime(i)
		);
	}

	@Test
	public void testGetPrimeNearMax() {
		//
		final int maxMinus1 = cache.getPrime(
			cache.getMaxIndex() - 1
		);
		assertTrue(
			PrimeValidation.isPrime(maxMinus1, cache));
	}

	@Test
	public void testPrimeTooLargeForCache() {
		assertThrows(
			IllegalArgumentException.class,
			() -> cache.getPrime(cache.getMaxIndex() + 5)
		);
	}

	@RepeatedTest(3)
	public void testRandomAccess() {
		final IntRange testIndexRange = new IntRange(
			10, cache.getMaxIndex()
		);
		for (int i = 0; 200 > i; ++i) {
			for (int j = 0; j < cache.getMaxIndex(); ++j) {
				final int rand = Random.Default.nextInt(
					testIndexRange.getStart(),
					testIndexRange.getEndInclusive() + 1
				);
				assertEquals(
					primeList.get(rand),
					cache.getPrime(rand)
				);
			}
			cache.clear();
		}
	}

	@ParameterizedTest
	@ValueSource(
		ints = { 1, 2, 3, 5, 6, 8, 9, 12 }
	)
	public void testExpansionRates(
		final int rate
	) {
		final int maxIndex = cache.getMaxIndex();
		for (
			int i = 0;
			i < maxIndex;
			i += rate
		) assertEquals(
			primeList.get(i),
			cache.getPrime(i)
		);
	}

	@Test
	public void testIsPrimeAscending() {
		final int lastPrime = primeList.get(
			primeList.size() - 1
		);
		// From 2 to the highest prime in the cache
		for (
			int n = 2;
			n <= lastPrime;
			++n
		) assertEquals(
			primeList.contains(n),
			PrimeValidation.isPrime(n, cache),
			String.format(
				"Failed to identify: %d", n
			)
		);
	}

	@Test
	public void testIsPrimeDescending() {
		final int lastPrime = primeList.get(
			primeList.size() - 1
		);
		// From the highest prime in the cache, down to 2
		int n = lastPrime;
		for (;
		     2 <= n;
		     --n
		) {
			assertEquals(
				primeList.contains(n),
				PrimeValidation.isPrime(n, cache),
				String.format(
					"Failed to identify: %d", n
				)
			);
			cache.clear();
		}
	}

}
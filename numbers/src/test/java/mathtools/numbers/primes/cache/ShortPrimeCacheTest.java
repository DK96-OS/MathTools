package mathtools.numbers.primes.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import kotlin.random.Random;
import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeTestDataProvider;
import mathtools.numbers.primes.ShortPrimeCache;
import mathtools.pairs.IntPair;

/** Testing [ShortPrimeCache] class
 * @author DK96-OS : 2022 */
public final class ShortPrimeCacheTest {

	private final List<Integer> primeList = PrimeTestDataProvider.getPrimes509();

	private final int maxPrimeIndex = primeList.size() - 1;

	private ShortPrimeCache cache = null;

	@BeforeEach
	public void testSetup() {
		cache = new ShortPrimeCache();
	}

	@Test
	public void testGetPrimeDecreasing() {
		for (
			int i = maxPrimeIndex;
			0 <= i;
			--i
		) {
			assertEquals(
				primeList.get(i),
				cache.getPrime(i)
			);
			cache.clear();
		}
	}

	@Test
	public void testSpecificPrimes() {
		assertEquals(
			547, cache.getPrime(100));
		assertEquals(
			661, cache.getPrime(120));
		assertEquals(
			811, cache.getPrime(140));
		assertEquals(
			947, cache.getPrime(160));
		assertEquals(
			1087, cache.getPrime(180));
		assertEquals(
			1229, cache.getPrime(200));
		assertEquals(
			1993, cache.getPrime(300));
		assertEquals(
			2131, cache.getPrime(320));
		assertEquals(
			2293, cache.getPrime(340));
		assertEquals(
			2437, cache.getPrime(360));
		assertEquals(
			2621, cache.getPrime(380));
		assertEquals(
			2749, cache.getPrime(400));
		assertEquals(
			5801, cache.getPrime(760));
		assertEquals(
			7001, cache.getPrime(900));
		assertEquals(
			7211, cache.getPrime(920));
		assertEquals(
			7727, cache.getPrime(980));
	}

	@Test
	public void testIsPrime() {
		int n = 2;
		for (;
		     n < primeList.get(maxPrimeIndex);
		     ++n
		) assertEquals(
			primeList.contains(n),
			cache.isPrime(n)
		);
	}

	@Test
	public void testIsPrimeCacheClear() {
		for (
			int n = primeList.get(maxPrimeIndex);
			2 <= n;
			--n
		) {
			cache.clear();
			assertEquals(
				primeList.contains(n),
				cache.isPrime(n)
			);
		}
	}

	@Test
	public void testLargeIndices() {
		final List<IntPair> testData = Lists.newArrayList(
			new IntPair(1000, 7927),
			new IntPair(2000, 17393),
			new IntPair(3000, 27457),
			new IntPair(3511, 32749)
		);
		for (final IntPair pair : testData) {
			assertEquals(
				pair.getSecond(),
				cache.getPrime(pair.getFirst())
			);
		}
	}

	@Test
	public void testPrimeTooLargeForCache() {
		assertThrows(
			IllegalStateException.class,
			() -> cache.getPrime(cache.getMaxIndex() + 5)
		);
	}

	@ParameterizedTest
	@ValueSource(ints = {
		1, 2, 3, 5, 6, 8, 10, 15
	})
	public void testExpansionRates(
		final int rate
	) {
		for (
			int i = 0;
			maxPrimeIndex >= i;
			i += rate
		) assertEquals(
			primeList.get(i),
			cache.getPrime(i)
		);
	}

	@RepeatedTest(2)
	public void testRandomAccess() {
		for (
			int i = 0;
			200 > i;
			++i
		) {
			final int rand = Random.Default.nextInt(
				1, 70
			);
			assertEquals(
				primeList.get(rand),
				cache.getPrime(rand)
			);
			cache.clear();
		}
	}

	@RepeatedTest(2)
	public void testRandomIsPrime() {
		final int maxIndex = cache.getMaxIndex();
		for (
			int x = 0;
			20 > x;
			++x
		) {
			cache.clear();
			for (
				int i = 0;
				100 > i;
				++i
			) {
				final int randIndex = Random.Default.nextInt(
					BytePrimeCache.MAX_INDEX + 1,
					maxIndex
				);
				final int rand = cache.getPrime(randIndex);
				assertTrue(
					cache.isPrime(rand));
			}
		}
	}

}
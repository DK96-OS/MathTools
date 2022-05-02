package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider;

/** Testing [PrimeNumberTools] DivideOutSmallPrimes method
 * @author DK96-OS : 2022 */
public final class PrimeNumberToolsDivideOutSmallPrimesTest {

	private final PrimeCacheInterface cache = new BytePrimeCache();

	@Test
	public void testInvalidProduct() {
		assertNull(
			divideOutSmallPrimes(
				1, 7, cache));
		assertNull(
			divideOutSmallPrimes(
				0, 7, cache));
		assertNull(
			divideOutSmallPrimes(
				-1, 7, cache));
	}

	@Test
	public void testInvalidMaxPrime() {
		assertNull(
			divideOutSmallPrimes(
				20, 1, cache));
		assertNull(
			divideOutSmallPrimes(
				20, 0, cache));
		assertNull(
			divideOutSmallPrimes(
				20, -1, cache));
	}

	@Test
	public void testEqualInputs() {
		assertNull(
			divideOutSmallPrimes(
				7, 7, cache));
		assertNull(
			divideOutSmallPrimes(
				20, 20, cache));
		assertNull(
			divideOutSmallPrimes(
				Integer.MAX_VALUE, Integer.MAX_VALUE, cache));
	}

	@Test
	public void testMaxValueMaxPrime() {
		assertNull(
			divideOutSmallPrimes(
				901, Long.MAX_VALUE, cache
			));
	}

	@Test
	public void testCacheOverflow() {
		assertNull(
			divideOutSmallPrimes(
				1_107_729, Long.MAX_VALUE, cache
			));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testDivideOutSmallPrimes100(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertEquals(
			25, divideOutSmallPrimes(
				100, 2, cache));
		assertEquals(
			25, divideOutSmallPrimes(
				100, 3, cache));
		assertEquals(
			25, divideOutSmallPrimes(
				100, 4, cache));
		assertNull(
			divideOutSmallPrimes(
				100, 5, cache));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testDivideOutSmallPrimes77(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertEquals(
			11, divideOutSmallPrimes(
				77, 7, cache));
		assertEquals(
			11, divideOutSmallPrimes(
				77, 9, cache));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testDivideOutSmallPrimes53(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertEquals(
			53, divideOutSmallPrimes(
				53, 43, cache));
		assertNull(
			divideOutSmallPrimes(
				53, 53, cache));
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider.class)
	public void testDivideOutSmallPrimesNegative(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertNull(
			divideOutSmallPrimes(
				-50, 43, cache));
		assertNull(
			divideOutSmallPrimes(
				-100, 5, cache));
	}

	/** Call divideOutSmallPrimes in PrimeNumberTools
	 */
	@Nullable
	private static Long divideOutSmallPrimes(
		final long product,
		final long maxPrime,
		@Nonnull final PrimeCacheInterface cache
	) {
		return PrimeNumberTools.INSTANCE.divideOutSmallPrimes(
			product,
			maxPrime,
			cache
		);
	}

}
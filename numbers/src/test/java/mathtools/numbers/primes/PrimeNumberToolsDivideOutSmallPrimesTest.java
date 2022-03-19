package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.annotation.Nonnull;

import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider;

/** Testing [PrimeNumberTools] DivideOutSmallPrimes method
 * @author DK96-OS : 2022 */
public final class PrimeNumberToolsDivideOutSmallPrimesTest {

	private final PrimeNumberTools I = PrimeNumberTools.INSTANCE;

	final PrimeCacheInterface cache = new BytePrimeCache();

	@Test
	void testInvalidProduct() {
		assertNull(
			I.divideOutSmallPrimes(1, 7, cache));
		assertNull(
			I.divideOutSmallPrimes(0, 7, cache));
		assertNull(
			I.divideOutSmallPrimes(-1, 7, cache));
	}

	@Test
	void testInvalidMaxPrime() {
		assertNull(
			I.divideOutSmallPrimes(20, 1, cache));
		assertNull(
			I.divideOutSmallPrimes(20, 0, cache));
		assertNull(
			I.divideOutSmallPrimes(20, -1, cache));
	}

	@Test
	void testEqualInputs() {
		assertNull(
			I.divideOutSmallPrimes(7, 7, cache));
		assertNull(
			I.divideOutSmallPrimes(20, 20, cache));
		assertNull(
			I.divideOutSmallPrimes(Integer.MAX_VALUE, Integer.MAX_VALUE, cache));
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider.class)
	void testDivideOutSmallPrimes(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertEquals(
			25, I.divideOutSmallPrimes(100, 2, cache));
		assertEquals(
			25, I.divideOutSmallPrimes(100, 3, cache));
		assertEquals(
			25, I.divideOutSmallPrimes(100, 4, cache));
		assertNull(
			I.divideOutSmallPrimes(100, 5, cache));
		//
		assertEquals(
			11, I.divideOutSmallPrimes(77, 7, cache));
		assertEquals(
			11, I.divideOutSmallPrimes(77, 9, cache));
		assertEquals(
			17, I.divideOutSmallPrimes(68, 13, cache));
		//
		assertNull(
			I.divideOutSmallPrimes(50, 43, cache));
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider.class)
	void testDivideOutSmallPrimesNegative(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertNull(I.divideOutSmallPrimes(-50, 43, cache));
		assertNull(I.divideOutSmallPrimes(-100, 5, cache));
	}

}
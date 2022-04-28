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

	private final PrimeNumberTools INST = PrimeNumberTools.INSTANCE;

	private final PrimeCacheInterface cache = new BytePrimeCache();

	@Test
	public void testInvalidProduct() {
		assertNull(
			INST.divideOutSmallPrimes(
				1, 7, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				0, 7, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				-1, 7, cache)
		);
	}

	@Test
	public void testInvalidMaxPrime() {
		assertNull(
			INST.divideOutSmallPrimes(
				20, 1, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				20, 0, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				20, -1, cache)
		);
	}

	@Test
	public void testEqualInputs() {
		assertNull(
			INST.divideOutSmallPrimes(
				7, 7, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				20, 20, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				Integer.MAX_VALUE, Integer.MAX_VALUE, cache)
		);
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testDivideOutSmallPrimes100(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertEquals(
			25,
			INST.divideOutSmallPrimes(
				100, 2, cache)
		);
		assertEquals(
			25,
			INST.divideOutSmallPrimes(
				100, 3, cache)
		);
		assertEquals(
			25,
			INST.divideOutSmallPrimes(
				100, 4, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				100, 5, cache)
		);
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testDivideOutSmallPrimes77(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertEquals(
			11,
			INST.divideOutSmallPrimes(
				77, 7, cache)
		);
		assertEquals(
			11,
			INST.divideOutSmallPrimes(
				77, 9, cache)
		);
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testDivideOutSmallPrimes53(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertEquals(
			53,
			INST.divideOutSmallPrimes(
				53, 43, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				53, 53, cache)
		);
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider.class)
	public void testDivideOutSmallPrimesNegative(
		@Nonnull final PrimeCacheInterface cache
	) {
		assertNull(
			INST.divideOutSmallPrimes(
				-50, 43, cache)
		);
		assertNull(
			INST.divideOutSmallPrimes(
				-100, 5, cache)
		);
	}

}
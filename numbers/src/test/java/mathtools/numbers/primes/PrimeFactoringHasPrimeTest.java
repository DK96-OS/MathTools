package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.primes.PrimeFactoring.hasPrimeAbove;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider;

/** Testing [PrimeFactoring] hasPrimeAbove method
 * @author DK96-OS : 2018 - 2022 */
public final class PrimeFactoringHasPrimeTest {

	private final PrimeCacheInterface cache = new BytePrimeCache();

	@Test
	public void testHasPrimeGreaterLimit() {
		assertFalse(
			hasPrimeAbove(29, 29, cache));
		assertFalse(
			hasPrimeAbove(29, 31, cache));
	}

	@Test
	public void testHasPrimeNegativeLimit() {
		assertFalse(
			hasPrimeAbove(29, -29, cache));
		assertFalse(
			hasPrimeAbove(29, -31, cache));
	}

	@ParameterizedTest
	@ArgumentsSource(
		PrimeCacheArgumentProvider.class
	)
	public void testHasPrimeLimit(
		final PrimeCacheInterface cache
	) {
		assertFalse(
			hasPrimeAbove(40, 11, cache));
		assertFalse(
			hasPrimeAbove(39, 13, cache));
		//
		assertTrue(
			hasPrimeAbove(34, 11, cache));
		assertTrue(
			hasPrimeAbove(39, 11, cache));
	}

	@Test
	public void testNumberMinValue() {
		assertFalse(
			hasPrimeAbove(Long.MIN_VALUE, 50,cache));
		assertFalse(
			hasPrimeAbove(301, Long.MIN_VALUE, cache));
		assertFalse(
			hasPrimeAbove(Long.MIN_VALUE, Long.MIN_VALUE, cache));
	}

	@Test
	public void testNumberMaxValue() {
		assertTrue(
			hasPrimeAbove(Long.MAX_VALUE, 50, cache));
		assertFalse(
			hasPrimeAbove(301, Long.MAX_VALUE, cache));
		assertFalse(
			hasPrimeAbove(Long.MAX_VALUE, Long.MAX_VALUE, cache));
	}

}
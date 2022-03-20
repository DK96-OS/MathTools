package mathtools.numbers.primes.checker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.primes.PrimeChecker.isPrime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeCacheInterface;

/** Testing [PrimeChecker] IsPrime Method
 * @author DK96-OS : 2022 */
public final class PrimeCheckerIsPrimeTest {

	private final PrimeCacheInterface cache = new BytePrimeCache();

	@Test
	void testPrimes() {
		assertTrue(isPrime(0, cache));
		assertTrue(isPrime(1, cache));
		assertTrue(isPrime(3, cache));
		assertTrue(isPrime(5, cache));
		assertTrue(isPrime(7, cache));
	}

	@ParameterizedTest
	@ValueSource(ints = { 4, 6, 8, 10, 12, 57, 63, 72, 102, 105, 215 })
	void testNonPrimes(final int nonPrime) {
		assertFalse(
			isPrime(nonPrime, cache));
	}

	@Test
	void testNegativePrimes() {
		assertTrue(isPrime(-1, cache));
		assertTrue(isPrime(-2, cache));
		assertTrue(isPrime(-3, cache));
		assertTrue(isPrime(-11, cache));
	}

	@Test
	void testNegativeNonPrimes() {
		assertFalse(isPrime(-4, cache));
		assertFalse(isPrime(-8, cache));
		assertFalse(isPrime(-15, cache));
	}

	@Test
	void testMinValue() {
		// Since MinValue is a multiple of 2, it will be correctly categorized
		assertFalse(
			isPrime(Integer.MIN_VALUE, cache));
	}

	@Test
	void testMaxValue() {
		// MaxValue is out of bounds for the BytePrime Cache
		assertThrows(IllegalArgumentException.class,
			() -> isPrime(Integer.MAX_VALUE, cache));
	}

}
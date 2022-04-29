package mathtools.numbers.primes.validate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.numbers.primes.validate.PrimeValidation.isPrime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeCacheInterface;

/** Testing [PrimeValidation] IsPrime Method
 * @author DK96-OS : 2022 */
public final class PrimeValidationIsPrimeTest {

	private final PrimeCacheInterface cache = new BytePrimeCache();

	@ParameterizedTest
	@ValueSource(
		ints = { 0, 1, 2, 3, 7, 19, 47, 53, 61, 73, 101, 251, 7793 }
	)
	public void testPrimes(
		final int prime
	) {
		assertTrue(
			isPrime(prime, cache));
	}

	@ParameterizedTest
	@ValueSource(
		ints = { 4, 6, 8, 10, 12, 57, 63, 72, 102, 105, 215 }
	)
	public void testNonPrimes(
		final int nonPrime
	) {
		assertFalse(
			isPrime(nonPrime, cache));
	}

	@Test
	public void testNegativePrimes() {
		assertTrue(
			isPrime(-1, cache));
		assertTrue(
			isPrime(-2, cache));
		assertTrue(
			isPrime(-3, cache));
		assertTrue(
			isPrime(-11, cache));
	}

	@Test
	public void testNegativeNonPrimes() {
		assertFalse(
			isPrime(-4, cache));
		assertFalse(
			isPrime(-8, cache));
		assertFalse(
			isPrime(-15, cache));
		assertFalse(
			isPrime(-2000, cache));
	}

	@Test
	public void testMinValue() {
		// Since MinValue is a multiple of 2, it will be correctly categorized
		assertFalse(
			isPrime(Integer.MIN_VALUE, cache));
	}

	@Test
	public void testMaxValue() {
		// MaxValue is out of bounds for the BytePrime Cache
		assertThrows(
			IllegalArgumentException.class,
			() -> isPrime(Integer.MAX_VALUE, cache)
		);
	}

}
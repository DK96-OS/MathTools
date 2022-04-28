package mathtools.numbers.primes.validate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static mathtools.numbers.primes.validate.PrimeValidation.findPrime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import mathtools.numbers.primes.BytePrimeCache;
import mathtools.numbers.primes.PrimeCacheInterface;
import mathtools.numbers.primes.PrimeTestDataProvider;

/** Testing [PrimeChecker] FindPrime Static Method
 * @author DK96-OS : 2022 */
public final class PrimeValidationFindPrimeTest {

	private final PrimeCacheInterface byteCache = new BytePrimeCache();
	//private final PrimeCacheInterface shortCache = new ShortPrimeCache();

	private final List<Integer> primeList = PrimeTestDataProvider.getPrimes251();

	@ParameterizedTest
	@ValueSource(
		ints = { 7, 53, 73, 101, 103, 241 }
	)
	public void testNumberIsPrime(
		final int prime
	) {
		assertEquals(
			prime, findPrime(prime, byteCache));
	}

	/** Any product of 2 should cause findPrime to throw Exception, as even numbers are
	 * invalid */
	@ParameterizedTest
	@ValueSource(
		ints = { 2, 12, 2048 }
	)
	public void testExceptionProductOf2(
		final int number
	) {
		assertThrows(
			IllegalArgumentException.class,
			() -> findPrime(number, byteCache)
		);
	}

	@ParameterizedTest
	@ValueSource(
		ints = { -1, -55, -301, -2048, Integer.MIN_VALUE }
	)
	public void testExceptionNegative(
		final int number
	) {
		assertThrows(
			IllegalArgumentException.class,
			() -> findPrime(number, byteCache)
		);
	}

	/** These inputs are 2 less than a prime, but not prime themselves */
	@ParameterizedTest
	@ValueSource(
		ints = { 9, 15, 21, 51, 77, 99, 105 }
	)
	public void testBeforePrime(
		final int prime
	) {
		assertEquals(
			prime + 2,
			findPrime(prime, byteCache)
		);
	}

	@Test
	public void testFindPrimeSpecificCases() {
		assertEquals(
			251, findPrime(243, byteCache));
	}

	@Test
	public void testFindPrimeFromEmptyCache() {
		for (
			int idx = 2;
			idx < primeList.size();
			++idx
		) {
			final int prevPrime = primeList.get(idx - 1);
			final int expectedPrime = primeList.get(idx);
			assertEquals(
				expectedPrime,
				findPrime(prevPrime + 2, byteCache)
			);
			byteCache.clear();
		}
	}

	@Test
	public void testFindPrimeAboveMax() {
		assertNull(
			findPrime(253, byteCache));
		assertNull(
			findPrime(57599, byteCache));
		//
		assertNull(
			findPrime(57699, byteCache));
	}

}
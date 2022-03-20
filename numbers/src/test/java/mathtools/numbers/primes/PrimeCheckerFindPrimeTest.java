package mathtools.numbers.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static mathtools.numbers.primes.PrimeChecker.findPrime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/** Testing [PrimeChecker] FindPrime Static Method
 * @author DK96-OS : 2022 */
public final class PrimeCheckerFindPrimeTest {

	private final PrimeCacheInterface byteCache = new BytePrimeCache();
	//private final PrimeCacheInterface shortCache = new ShortPrimeCache();

	private final List<Integer> primeList = PrimeTestDataProvider.getPrimes251();

	@Test
	void testNumberProductOf2() {
		assertNull(findPrime(8, byteCache));
		assertNull(findPrime(12, byteCache));
		assertNull(findPrime(Integer.MIN_VALUE, byteCache));
	}

	@Test
	void testNumberNegative() {
		assertNull(findPrime(-1, byteCache));
		assertNull(findPrime(-55, byteCache));
		assertNull(findPrime(-301, byteCache));
	}

	@ParameterizedTest
	@ValueSource(ints = { 7, 53, 73, 101, 103 })
	void testOnPrime(final int prime) {
		assertEquals(
			prime, findPrime(prime, byteCache));
	}

	@ParameterizedTest
	@ValueSource(ints = { 9, 15, 27, 51, 77, 99, 105 })
	void testBeforePrime(final int prime) {
		assertEquals(
			prime + 2,
			findPrime(prime, byteCache)
		);
	}

	@Test
	void testFindPrimeFromEmptyCache() {
		for (int idx = 2; idx < primeList.size(); ++idx) {
			final int prevPrime = primeList.get(idx - 1);
			final int expectedPrime = primeList.get(idx);
			assertEquals(
				expectedPrime,
				findPrime(prevPrime + 2, byteCache)
			);
			byteCache.clear();
		}
	}

}
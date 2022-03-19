package mathtools.numbers.primes

import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

/** Testing [PrimeNumberTools] static functions
 * @author DK96-OS : 2018 - 2022 */
class PrimeNumberToolsTest {

    @ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
    fun testPrimeFactorLimitCheck(
		cache: PrimeCacheInterface
    ) {
		assertFalse(
			PrimeFactoring.hasPrimeAbove(40, 11, cache))
    	assertFalse(
			PrimeFactoring.hasPrimeAbove(39, 13, cache))
		//
    	assertTrue(
			PrimeFactoring.hasPrimeAbove(34, 11, cache))
    	assertTrue(
			PrimeFactoring.hasPrimeAbove(39, 11, cache))
    }

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testFirstPrimeAboveLimit(
		cache: PrimeCacheInterface
	) {
		assertNull(
			PrimeFactoring.firstPrimeAbove(5000, 71, cache))
		assertNull(
			PrimeFactoring.firstPrimeAbove(5000, 5, cache))
		assertEquals(
			5,
			PrimeFactoring.firstPrimeAbove(5000, 4, cache))
		assertEquals(
			5,
			PrimeFactoring.firstPrimeAbove(5000, 4, cache))
		assertEquals(
			7,
			PrimeFactoring.firstPrimeAbove(49, 6, cache))
		assertNull(
			PrimeFactoring.firstPrimeAbove(49, 7, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testFirstPrimeAboveLimitNegative(
		cache: PrimeCacheInterface
	) {
		// Ignore negative sign on product
		assertNull(
			PrimeFactoring.firstPrimeAbove(-5000, 71, cache))
		assertEquals(
			5,
			PrimeFactoring.firstPrimeAbove(-5000, 4, cache))
		assertEquals(
			7,
			PrimeFactoring.firstPrimeAbove(-49, 6, cache))
		// Null when limit is negative
		assertNull(
			PrimeFactoring.firstPrimeAbove(-5000, -4, cache))
		assertNull(
			PrimeFactoring.firstPrimeAbove(-49, -6, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testFirstPrimeAboveLimitInvalid(
		cache: PrimeCacheInterface
	) {
		// Null when product is 1 or zero
		assertNull(
			PrimeFactoring.firstPrimeAbove(-1, 5, cache))
		assertNull(
			PrimeFactoring.firstPrimeAbove(0, 5, cache))
		assertNull(
			PrimeFactoring.firstPrimeAbove(1, 5, cache))
	}

}
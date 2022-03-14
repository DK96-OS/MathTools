package mathtools.numbers.primes

import mathtools.numbers.primes.PrimeNumberTools.divideOutSmallPrimes
import mathtools.numbers.primes.PrimeNumberTools.reduceByPrimeRange
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
		cache: PrimeCacheBase
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
		cache: PrimeCacheBase
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
		cache: PrimeCacheBase
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
		cache: PrimeCacheBase
	) {
		// Null when product is 1 or zero
		assertNull(
			PrimeFactoring.firstPrimeAbove(-1, 5, cache))
		assertNull(
			PrimeFactoring.firstPrimeAbove(0, 5, cache))
		assertNull(
			PrimeFactoring.firstPrimeAbove(1, 5, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testReduceByPrimes(
		cache: PrimeCacheBase
	) {
		assertEquals(
			2, reduceByPrimeRange(1..2, 30, cache))
		assertEquals(
			4, reduceByPrimeRange(1..5, 60, cache))
		assertEquals(
			12, reduceByPrimeRange(2..2, 60, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testReduceByPrimesNegative(
		cache: PrimeCacheBase
	) {
		assertEquals(
			-2, reduceByPrimeRange(1..2, -30, cache))
		assertEquals(
			-4, reduceByPrimeRange(1..2, -60, cache))
		assertEquals(
			-12, reduceByPrimeRange(2..3, -60, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testReduceByPrimesIndivisibleProduct(
		cache: PrimeCacheBase
	) {
		assertNull(
			reduceByPrimeRange(1..3, -1, cache))
		assertNull(
			reduceByPrimeRange(1..3, 0, cache))
		assertNull(
			reduceByPrimeRange(1..3, 1, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testReduceByPrimesInvalidRange(
		cache: PrimeCacheBase
	) {
		// Negative valued ranges are ignored
		assertNull(
			reduceByPrimeRange(-3..-1, -30, cache))
		// The invalid region of the range is ignored
		assertNull(
			reduceByPrimeRange(-3..3, -60, cache))
		// Invalid region is ignored
		assertEquals(
			-15, reduceByPrimeRange(-1..0, -60, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testReduceByPrimesReversedRange(
		cache: PrimeCacheBase
	) {
		assertEquals(
			4, reduceByPrimeRange(2..1, 60, cache))
		assertEquals(
			-4, reduceByPrimeRange(2..1, -60, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testDivideOutSmallPrimes(
		cache: PrimeCacheBase
	) {
		assertEquals(
			25, divideOutSmallPrimes(100, 2, cache))
		assertEquals(
			25, divideOutSmallPrimes(100, 3, cache))
		assertEquals(
			25, divideOutSmallPrimes(100, 4, cache))
		assertNull(
			divideOutSmallPrimes(100, 5, cache))
		//
		assertEquals(
			11, divideOutSmallPrimes(77, 7, cache))
		assertEquals(
			11, divideOutSmallPrimes(77, 9, cache))
		assertEquals(
			17, divideOutSmallPrimes(68, 13, cache))
		//
		assertNull(
			divideOutSmallPrimes(50, 43, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testDivideOutSmallPrimesNegative(
		cache: PrimeCacheBase
	) {
		assertNull(divideOutSmallPrimes(-50, 43, cache))
		assertNull(divideOutSmallPrimes(-100, 5, cache))
	}

}
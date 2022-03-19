package mathtools.numbers.primes

import mathtools.numbers.primes.PrimeNumberTools.reduceByPrimeRange
import mathtools.numbers.primes.cache.PrimeCacheArgumentProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

/** Testing [PrimeNumberTools] ReduceByPrimeRange method
 * @author DK96-OS : 2022 */
class PrimeNumberToolsReduceByPrimeRangeTest {

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testReduceByPrimesInvalidRange(
		cache: PrimeCacheInterface
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
		cache: PrimeCacheInterface
	) {
		assertEquals(
			4, reduceByPrimeRange(2..1, 60, cache))
		assertEquals(
			-4, reduceByPrimeRange(2..1, -60, cache))
	}

	@ParameterizedTest
	@ArgumentsSource(PrimeCacheArgumentProvider::class)
	fun testReduceByPrimesIndivisibleProduct(
		cache: PrimeCacheInterface
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
	fun testReduceByPrimesNegative(
		cache: PrimeCacheInterface
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
	fun testReduceByPrimes(
		cache: PrimeCacheInterface
	) {
		assertEquals(
			2, reduceByPrimeRange(1..2, 30, cache))
		assertEquals(
			4, reduceByPrimeRange(1..5, 60, cache))
		assertEquals(
			12, reduceByPrimeRange(2..2, 60, cache))
	}

}
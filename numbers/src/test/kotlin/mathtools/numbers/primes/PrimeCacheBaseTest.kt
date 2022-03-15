package mathtools.numbers.primes

import mathtools.numbers.primes.PrimeChecker.findPrime
import mathtools.numbers.primes.PrimeChecker.isPrime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/** Testing the PrimeCacheBase Abstract class
 * @author DK96-OS : 2022 */
class PrimeCacheBaseTest {

	@Test
	fun testIsPrime() {
		val cache: PrimeCacheInterface = BytePrimeCache()
		assertTrue(
			isPrime(1, cache))
		assertTrue(
			isPrime(2, cache))
		assertTrue(
			isPrime(3, cache))
		assertTrue(
			isPrime(3, cache))
		assertFalse(
			isPrime(4, cache))
		assertTrue(
			isPrime(5, cache))
		for (i in 3 .. cache.maxIndex step 2) assertTrue(
			isPrime(cache.getPrime(i), cache))
	}

	@Test
	fun testIsPrimeNegative() {
		val cache: PrimeCacheInterface = BytePrimeCache()
		assertTrue(
			isPrime(-3, cache))
		assertFalse(
			isPrime(-4, cache))
		assertTrue(
			isPrime(-5, cache))
		for (i in 3 until cache.maxIndex step 4) assertTrue(
			isPrime(-cache.getPrime(i), cache))
	}

	@Test
	fun testIsPrimeMaxProducts() {
		val cache: PrimeCacheInterface = BytePrimeCache()
		assertTrue(
			isPrime(251, cache))
		val maxMinus1 = cache.getPrime(cache.maxIndex - 1)
		assertTrue(
			isPrime(maxMinus1, cache))
		//
		for (i in 0 .. 20) assertFalse(
			isPrime(251 * cache.getPrime(i), cache))
	}

	@Test
	fun testFindPrime() {
		val cache: PrimeCacheInterface = BytePrimeCache()
		assertEquals(
			17, findPrime(15, cache))
		assertEquals(
			23, findPrime(21, cache))
		assertEquals(
			241, findPrime(241, cache))
		assertEquals(
			251, findPrime(243, cache))
	}

	@Test
	fun testFindPrimeAboveMax() {
		val cache: PrimeCacheInterface = BytePrimeCache()
		assertNull(
			findPrime(253, cache))
		assertNull(
			findPrime(57599, cache))
		//
		assertNull(
			findPrime(57699, cache))
	}

	@Test
	fun testFindPrimeNegative() {
		val cache: PrimeCacheInterface = BytePrimeCache()
		assertNull(
			findPrime(-7, cache))
		assertNull(
			findPrime(-2, cache))
		assertNull(
			findPrime(-1, cache))
	}

}
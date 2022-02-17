package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the PrimeCacheBase Abstract class
 * @author DK96-OS : 2022 */
class PrimeCacheBaseTest {

	@Test
	fun testIsPrime() {
		val cache: PrimeCacheBase = BytePrimeCache()
		assertEquals(true, cache.isPrime(1))
		assertEquals(true, cache.isPrime(2))
		assertEquals(true, cache.isPrime(3))
		assertEquals(true, cache.isPrime(3))
		assertEquals(false, cache.isPrime(4))
		assertEquals(true, cache.isPrime(5))
		for (i in 3 .. cache.maxIndex step 2)
			assertEquals(true, cache.isPrime(cache.getPrime(i)))
	}

	@Test
	fun testIsPrimeNegative() {
		val cache: PrimeCacheBase = BytePrimeCache()
		assertEquals(true, cache.isPrime(-3))
		assertEquals(false, cache.isPrime(-4))
		assertEquals(true, cache.isPrime(-5))
		for (i in 3 until cache.maxIndex step 4)
			assertEquals(true, cache.isPrime(-cache.getPrime(i)))
	}

	@Test
	fun testIsPrimeMaxProducts() {
		val cache: PrimeCacheBase = BytePrimeCache()
		assertEquals(true, cache.isPrime(251))
		val maxMinus1 = cache.getPrime(cache.maxIndex - 1)
		assertEquals(true, cache.isPrime(maxMinus1))
		//
		for (i in 0 .. 20) {
			val prime = cache.getPrime(i)
			assertEquals(false, cache.isPrime(251 * prime))
		}
	}

	@Test
	fun testFindPrime() {
		val cache: PrimeCacheBase = BytePrimeCache()
		assertEquals(17, cache.findPrime(15))
		assertEquals(23, cache.findPrime(21))
		assertEquals(241, cache.findPrime(241))
		assertEquals(251, cache.findPrime(243))
	}

	@Test
	fun testFindPrimeAboveMax() {
		val cache: PrimeCacheBase = BytePrimeCache()
		assertEquals(null, cache.findPrime(253))
		assertEquals(null, cache.findPrime(57599))
		//
		assertEquals(null, cache.findPrime(57699))
	}

	@Test
	fun testFindPrimeNegative() {
		val cache: PrimeCacheBase = BytePrimeCache()
		assertEquals(null, cache.findPrime(-7))
		assertEquals(null, cache.findPrime(-2))
		assertEquals(null, cache.findPrime(-1))
	}

}
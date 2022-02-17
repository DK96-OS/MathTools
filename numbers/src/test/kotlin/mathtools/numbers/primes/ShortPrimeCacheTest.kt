package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Testing the Short PrimeCache class, for primes up to 32767 */
class ShortPrimeCacheTest {
	
	private val primeList: List<Int> = arrayListOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
    	71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 
    	139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
    	211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277,
    	281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349,  // Index 69
    )
    /* These are the indices for a selection of numbers in the prime list:
    	assertEquals(199, primeList[45])
    	assertEquals(251, primeList[53])
    	assertEquals(349, primeList[69])
    */

	@Test
	fun testIsPrime() {
		val cache = ShortPrimeCache()
		for (n in 2 until primeList.last())
			assertEquals(n in primeList, cache.isPrime(n))
		for (n in primeList.last() downTo 2) {
			cache.clear()
			assertEquals(n in primeList, cache.isPrime(n))
		}
	}
	
	@Test
	fun testGetPrimeDecreasing() {
		val cache = ShortPrimeCache()
		for (i in primeList.size - 1 downTo 0) {
			assertEquals(primeList[i], cache.getPrime(i))
			cache.clear()
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [1, 2, 3, 5, 6, 8, 10, 15])
	fun testExpansionRates(rate: Int) {
		val cache = ShortPrimeCache()
		for (i in 0 until primeList.size step rate)
			assertEquals(primeList[i], cache.getPrime(i))
	}
/*
	@Test fun testCacheExpansionSlow() {
		val cache = ShortPrimeCache()
		assertEquals(137, cache.getPrime(32))
		assertEquals(139, cache.getPrime(33))
		assertEquals(149, cache.getPrime(34))
		assertEquals(3, cache.arraySize)
		assertEquals(1, cache.queueSize)
		assertEquals(151, cache.getPrime(35))
		assertEquals(2, cache.queueSize)
		assertEquals(157, cache.getPrime(36))
		assertEquals(3, cache.queueSize)
		for (i in 37 .. 50)
			assertEquals(primeList[i], cache.getPrime(i))
	}
	@Test fun testCacheExpansionQuick() {
		val cache = ShortPrimeCache()
		assertEquals(3, cache.arraySize)
		assertEquals(0, cache.queueSize)
			// Expand the cache by 3
		assertEquals(157, cache.getPrime(36))
		assertEquals(3, cache.arraySize)
		assertEquals(3, cache.queueSize)
		assertEquals(149, cache.getPrime(34))
		assertEquals(139, cache.getPrime(33))
		assertEquals(151, cache.getPrime(35))
		for (i in 36 until 58 step 8)
			assertEquals(primeList[i], cache.getPrime(i))
	}
*/
	
	@RepeatedTest(4) 
	fun testRandomAccess() {
		val cache = ShortPrimeCache()
		val testIndexRange = 1 .. 69
		for (i in 0 until 200) {
			val rand = testIndexRange.random()
			assertEquals(primeList[rand], cache.getPrime(rand))
			cache.clear()
		}
		for (x in 0 until 20) {
			cache.clear()
			for (i in 0 until 100) {
				val rand = cache.getPrime(cache.shortIndexRange.random())
				assertEquals(true, cache.isPrime(rand))
			}
		}
	}

	@Test
	fun testPrimeTooLargeForCache() {
		val cache = ShortPrimeCache()
		assertThrows<IllegalStateException> {
			cache.getPrime(cache.maxIndex + 5)
		}
	}

}
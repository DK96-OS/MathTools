package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Testing the Short PrimeCache class, for primes up to 32767 */
class ShortPrimeCacheTest {
	
	private val primeList: List<Int> = PrimeTestDataProvider.getPrimes349()
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

	@Test
	fun testSpecificPrimes() {
		val cache = ShortPrimeCache()
		assertEquals(547, cache.getPrime(100))
		assertEquals(661, cache.getPrime(120))
		assertEquals(811, cache.getPrime(140))
		assertEquals(947, cache.getPrime(160))
		assertEquals(1087, cache.getPrime(180))
		assertEquals(1229, cache.getPrime(200))
		assertEquals(1993, cache.getPrime(300))
		assertEquals(2131, cache.getPrime(320))
		assertEquals(2293, cache.getPrime(340))
		assertEquals(2437, cache.getPrime(360))
		assertEquals(2621, cache.getPrime(380))
		assertEquals(2749, cache.getPrime(400))
		assertEquals(5801, cache.getPrime(760))
		assertEquals(7001, cache.getPrime(900))
		assertEquals(7211, cache.getPrime(920))
		assertEquals(7727, cache.getPrime(980))
	}

	@Test
	fun testShort() {
		println("Short: ${Short.MAX_VALUE}")
	}

	@Test
	fun testMaxIndex() {
		val cache = ShortPrimeCache()
		val testIndices = mutableListOf(
			1000, 2000, 3000, 3090, 3490,
			3510, 3511
		)
		for (i in testIndices) {
			val prime = cache.getPrime(i)
			println("Query($i) => $prime")
		}
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
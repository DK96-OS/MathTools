package data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Testing the Short PrimeCache class, for primes up to 32767 */
class ShortPrimeCacheTest {
	
	private val primeList: List<Int> = arrayListOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
    	71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 
    	139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
    	211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277,
    )

	@Test fun testCacheInitialization() {
		val cache = ShortPrimeCache()
		assertEquals(2, cache.getPrime(0))
		assertEquals(3, cache.getPrime(1))
		assertEquals(5, cache.getPrime(2))
		assertEquals(127, cache.getPrime(30))
		assertEquals(131, cache.getPrime(31))
	}

	@Test fun testByteCacheIntegration() {
		val cache = ShortPrimeCache()
		assertEquals(59, cache.getPrime(16))	// The first queue element
		assertEquals(53, cache.getPrime(15))	// Hardcoded Array element
		assertEquals(47, cache.getPrime(14))
		assertEquals(61, cache.getPrime(17))	// The 2nd queue element
		assertEquals(6, cache.byteCache.arraySize)	// 6 Hardcoded
		assertEquals(2, cache.byteCache.queueSize)
		assertEquals(3, cache.arraySize)		// No change to array
		assertEquals(0, cache.queueSize)	// No change to queue
	}
	
	@Test fun testIsPrime() {
		val cache = ShortPrimeCache()
		assertEquals(true, cache.isPrime(211))
		assertEquals(true, cache.isPrime(233))
		assertEquals(true, cache.isPrime(239))
		assertEquals(false, cache.isPrime(240))
		assertEquals(true, cache.isPrime(251))
		assertEquals(true, cache.isPrime(257))
	}
	
	@Test fun testGetPrimeIncreasing() {
		val cache = ShortPrimeCache()
		for (i in 32 until primeList.size) {
			println("CacheSize: ${cache.arraySize} + ${cache.queueSize}")
			assertEquals(primeList[i], cache.getPrime(i))
		}
	}
	
	@Test fun testGetPrimeDecreasing() {
		val cache = ShortPrimeCache()
		for (i in primeList.size -1 downTo 30) {
			println("CacheSize: ${cache.arraySize} + ${cache.queueSize}")
			assertEquals(primeList[i], cache.getPrime(i))
		}
	}

	@Test fun testCacheExpansionSlow() {
		val cache = ShortPrimeCache()
		assertEquals(131, cache.getPrime(31))
		assertEquals(137, cache.getPrime(32))
		assertEquals(139, cache.getPrime(33))
		assertEquals(149, cache.getPrime(34))
		assertEquals(151, cache.getPrime(35))
		for (i in 36 until 40) {
			val prime = cache.getPrime(i)
			println("idx: $i, prime=$prime")
		}
	}

	@Test fun testCacheExpansionQuick() {
		val cache = ShortPrimeCache()
		
		for (i in 36 until 68 step 9) {
			val prime = cache.getPrime(i)
			println("idx: $i, prime=$prime")
		}
	}
}

package data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Testing the Byte PrimeCache class, for primes less than 128 */
class BytePrimeCacheTest {

	private val primeList: List<Int> = arrayListOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
    	73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127
    )
    
    @Test fun testInitialization() {
    	val cache = BytePrimeCache()
    	assertEquals(6, cache.arraySize)
    	assertEquals(0, cache.queueSize)
    	assertEquals(true, cache.isPrime(31))
    	assertEquals(false, cache.isPrime(30))
    }
	
	@Test fun testGetPrimeIncreasing() {
		val cache = BytePrimeCache()
		for (i in 0 until primeList.size) 
			assertEquals(primeList[i], cache.getPrime(i))
	}
	
	@Test fun testGetPrimeDecreasing() {
		val cache = BytePrimeCache()
		assertEquals(47, cache.getPrime(14))
		assertEquals(43, cache.getPrime(13))
		for (i in 30 downTo 0) 
			assertEquals(primeList[i], cache.getPrime(i))
	}
	
	@Test fun testCacheExpansionSlow() {
		val cache = BytePrimeCache()
		// The first prime that is cached
		assertEquals(59, cache.getPrime(16))
		assertEquals(6, cache.arraySize)		// No change to array
		assertEquals(1, cache.queueSize)	// Added recent prime to queue
		// More calculations
		assertEquals(61, cache.getPrime(17))
		assertEquals(67, cache.getPrime(18))
		//	 Two more numbers have been added
		assertEquals(3, cache.queueSize)
		assertEquals(6, cache.arraySize)		// No change to array
		assertEquals(79, cache.getPrime(21))
		// 3 Just added
		assertEquals(6, cache.queueSize)
	}
	
	@Test fun testCacheExpansionQuick() {
		val cache = BytePrimeCache()
		// Require expanding by 6 primes
		assertEquals(79, cache.getPrime(21))
		assertEquals(6, cache.arraySize)		// No change to array
		assertEquals(6, cache.queueSize)	// Added recent prime to queue
		// Jump to a high prime
		assertEquals(127, cache.getPrime(30))
		assertEquals(12, cache.arraySize)	// Combined existing queued primes
		assertEquals(9, cache.queueSize)	// Just recently added
	}

}

package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Testing the Byte PrimeCache class, for primes less than 128 */
class BytePrimeCacheTest {

	private val primeList: List<Int> = arrayListOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
    	73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127
    )
    
    @Test fun testInitialization() {
    	val cache = BytePrimeCache()
    	assertEquals(4, cache.arraySize)
    	assertEquals(0, cache.queueSize)
    	assertEquals(true, cache.isPrime(31))
    	assertEquals(false, cache.isPrime(30))
    }
    
    @Test fun testFindPrime() {
    	val cache = BytePrimeCache()
    	assertEquals(7, cache.findPrime(7))
    	assertEquals(11, cache.findPrime(9))
    	assertEquals(11, cache.findPrime(11))
    	assertEquals(17, cache.findPrime(15))
    	assertEquals(19, cache.findPrime(19))
    	assertEquals(23, cache.findPrime(21))
    		//
    	assertEquals(41, cache.findPrime(39))
    	assertEquals(47, cache.findPrime(45))
    	assertEquals(101, cache.findPrime(99))
    }

    @Test fun testIsPrime() {
    	val cache = ShortPrimeCache()
		for (n in 2 .. primeList.last())
			assertEquals(n in primeList, cache.isPrime(n))
		for (n in primeList.last() downTo 2) {
			cache.clear()
			assertEquals(n in primeList, cache.isPrime(n))
		}
    }

	@Test fun testGetPrimeDecreasing() {
		val cache = BytePrimeCache()
		for (i in cache.maxIndex downTo 0) 
			assertEquals(primeList[i], cache.getPrime(i))
	}

	@Test fun testCacheExpansionSlow() {
		val cache = BytePrimeCache()
		// The first prime that is cached
		assertEquals(59, cache.getPrime(16))
		assertEquals(4, cache.arraySize)		// No change to array
		assertEquals(3, cache.queueSize)	// Add recent primes to queue
		// More calculations
		assertEquals(61, cache.getPrime(17))
		assertEquals(67, cache.getPrime(18))
		//	 Two more numbers have been added
		assertEquals(0, cache.queueSize)
		assertEquals(9, cache.arraySize)		// Array resized
		// 3 More added
		assertEquals(79, cache.getPrime(21))
		assertEquals(3, cache.queueSize)
		assertEquals(9, cache.arraySize)		// Array unchanged
	}
	
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 5, 6, 8, 9])
	fun testExpansionRates(rate: Int) {
		val cache = BytePrimeCache()
		for (i in 0 .. cache.maxIndex step rate)
			assertEquals(primeList[i], cache.getPrime(i))
	}
	
	@RepeatedTest(4) 
	fun testRandomAccess() {
		val cache = BytePrimeCache()
		val testIndexRange = 10 .. 30
		for (i in 0 until 200) {
			val rand = testIndexRange.random()
			assertEquals(primeList[rand], cache.getPrime(rand))
		}
		for (x in 0 until 20) {
			cache.clear()
			for (i in 0 until 100) {
				val rand = cache.getPrime(testIndexRange.random())
				assertEquals(true, rand in primeList)
			}
		}
	}

}

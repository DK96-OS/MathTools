package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Testing the Byte PrimeCache class, for primes less than 128 */
class BytePrimeCacheTest {

	private val primeList: List<Int> = listOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
    	73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
    	151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 
    	229, 233, 239, 241, 251,
    )

    @Test fun testInitialization() {
    	val cache = BytePrimeCache()
    	// The first 16 prime numbers are a;ways available
    	assertEquals(16, cache.arraySize)
    	assertEquals(0, cache.queueSize)
    	cache.clear()   	// Even if the cache is cleared
    	assertEquals(16, cache.arraySize)
    	assertEquals(0, cache.queueSize)
    }
    
    @Test fun testFindPrime() {
    	val cache = BytePrimeCache()
    	for (idx in 3 until primeList.size) {
    	    val prevPrime = primeList[idx - 1]
    	    val expectedPrime = primeList[idx]
    	    assertEquals(
    	        expectedPrime, cache.findPrime(prevPrime + 2))
    	    cache.clear()
    	}
    }

    @Test fun testIsPrime() {
    	val cache = BytePrimeCache()
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

	@Test
	fun testCacheQueue() {
		val cache = BytePrimeCache()
		val initialSize = cache.arraySize
		// The first prime beyond the initial list
		assertEquals(
		    primeList[initialSize], cache.getPrime(initialSize))
		// The array should not resize for one additional prime
		assertEquals(16, cache.arraySize)
		// The Queue should store this prime for now
		assertEquals(1, cache.queueSize)
		// Expansion, but only in the Queue
		val queueCapacity = 8
		val preCapacity = initialSize + queueCapacity - 1
		assertEquals(
		    primeList[preCapacity], cache.getPrime(preCapacity))
		// Check Array and Queue Sizes again
		assertEquals(16, cache.arraySize)
		assertEquals(queueCapacity, cache.queueSize)
		// Expand the array
		val newMaxIndex = preCapacity + 1
		assertEquals(
		    primeList[newMaxIndex], cache.getPrime(newMaxIndex))
		// After expansion, the Array contains the highest prime requested
		assertEquals(newMaxIndex + 1, cache.arraySize)
		assertEquals(0, cache.queueSize)    // And the Queue is empty
	}

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 5, 6, 8, 9, 12])
	fun testExpansionRates(rate: Int) {
		val cache = BytePrimeCache()
		for (i in 0 .. cache.maxIndex step rate)
			assertEquals(primeList[i], cache.getPrime(i))
	}
	
	@RepeatedTest(3)
	fun testRandomAccess() {
		val cache = BytePrimeCache()
		val testIndexRange = 10 .. cache.maxIndex
		repeat(200) {
			repeat(cache.maxIndex) {
			    val rand = testIndexRange.random()
			    assertEquals(primeList[rand], cache.getPrime(rand))
		    }
			cache.clear()
		}
	}
	
	@ParameterizedTest
	@ValueSource(ints = [2, 3, 5, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 22, 25, 26, 28, 30, 31, 33, 35, 40, 45, 50, 51, 52, 53])
	fun testTargetedAccess(target: Int) {
		val cache = BytePrimeCache()
		val prime = primeList[target]
		repeat(240_000) {
			assertEquals(prime, cache.getPrime(target))
		    cache.clear()
		}
	}
}

package mathtools.numbers.primes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Testing the Byte PrimeCache class, for primes less than 128 */
class BytePrimeCacheTest {
    companion object {
        val primeList: List<Int> = listOf(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
            151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227,
            229, 233, 239, 241, 251,
        )
    }

    @Test fun testInitialization() {
    	val cache = BytePrimeCache()
    	// The first 16 prime numbers are a;ways available
        assertEquals(0, cache.arraySize)
        assertEquals(0, cache.queueSize)
        cache.clear()   	// Even if the cache is cleared
        assertEquals(0, cache.arraySize)
        assertEquals(0, cache.queueSize)
        // Highest Index is from the static array
        assertEquals(15, cache.highestCachedIndex())
    }
    
    @Test fun testFindPrime() {
        val cache = BytePrimeCache()
        for (idx in 2 until primeList.size) {
            val prevPrime = primeList[idx - 1]
            val expectedPrime = primeList[idx]
            assertEquals(
                expectedPrime, cache.findPrime(prevPrime + 2))
            cache.clear()
        }
    }

    @Test
    fun testIsPrime() {
        val cache = BytePrimeCache()
        for (n in 2 .. primeList.last())
            assertEquals(
                n in primeList, cache.isPrime(n), "Failed to identify: $n")
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
        val initialSize = cache.highestCachedIndex() + 1
        // The first prime beyond the initial list is accessed
        assertEquals(
            primeList[initialSize], cache.getPrime(initialSize))
        // The array should not initialize for one prime
        assertEquals(0, cache.arraySize)
        // The Queue should store this prime for now
        assertEquals(1, cache.queueSize)
        // Expansion, but only in the Queue
        val queueCapacity = 8
        val preCapacity = initialSize + queueCapacity - 1
        assertEquals(
            primeList[preCapacity], cache.getPrime(preCapacity))
        // Expand the array
        val newMaxIndex = preCapacity + 1
        assertEquals(
            primeList[newMaxIndex], cache.getPrime(newMaxIndex))
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

    @Test
    fun testPrimeTooLargeForCache() {
        val cache = BytePrimeCache()
        assertThrows<IllegalStateException> {
            cache.getPrime(cache.maxIndex + 5)
        }
    }

    @Test
    fun testUByteConversions() {
        val positiveByte = 127
        val negativeByte = 128
        assertEquals(127, positiveByte.toByte().toInt())
        assertEquals(-128, negativeByte.toByte().toInt())
        assertEquals(-128, negativeByte.toUByte().toByte().toInt())
        //
        val nByte2 = (-120).toByte()
        assertEquals("-120", nByte2.toString())
        val uByte2 = nByte2.toUByte()
        val uByte2Result = 128 + (128 + nByte2)
        assertEquals(
            uByte2.toString(), uByte2Result.toString()
        )
        assertEquals("136", uByte2.toInt().toString())
    }

}
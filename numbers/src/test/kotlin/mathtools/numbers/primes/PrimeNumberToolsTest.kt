package mathtools.numbers.primes

import mathtools.numbers.primes.PrimeNumberTools.getFirstPrimeAboveLimit
import mathtools.numbers.primes.PrimeNumberTools.reduceByPrimes
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing the Prime Number tools object */
class PrimeNumberToolsTest {
	
	private val primeList: List<Int> = arrayListOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 
    	73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
    )

	@AfterEach fun tearDown() {
        PrimeNumberTools.clearCache()
	}

    @Test
    fun testGetPrimeIncreasingByOne() {
    	for (idx in primeList.indices)
        	assertEquals(primeList[idx], PrimeNumberTools.getPrime(idx))
    }
    
    @Test
    fun testGetPrimeDecreasingByOne() {
    	for (idx in primeList.size - 1 downTo 0)
    		assertEquals(primeList[idx], PrimeNumberTools.getPrime(idx))
    }

    @Test
    fun testPrimeFactorLimitCheck() {
    	assertEquals(false, PrimeNumberTools.checkForPrimeFactorAboveLimit(40, 11))
    	assertEquals(false, PrimeNumberTools.checkForPrimeFactorAboveLimit(39, 13))
    	
    	assertEquals(true, PrimeNumberTools.checkForPrimeFactorAboveLimit(34, 11))
    	assertEquals(true, PrimeNumberTools.checkForPrimeFactorAboveLimit(39, 11))
    }
    
    @Test
    fun obtainHighPrimeNumbers() {
    	for (i in 20 .. 3000 step 50)
    		println("Idx: $i, Prime: ${PrimeNumberTools.getPrime(i)}")
    }

	@Test
	fun testFirstPrimeAboveLimit() {
		assertEquals(null, getFirstPrimeAboveLimit(5000, 71))
		assertEquals(null, getFirstPrimeAboveLimit(5000, 5))
		assertEquals(5, getFirstPrimeAboveLimit(5000, 4))
		assertEquals(5, getFirstPrimeAboveLimit(5000, 4))
		assertEquals(7, getFirstPrimeAboveLimit(49, 6))
		assertEquals(null, getFirstPrimeAboveLimit(49, 7))
	}

	@Test
	fun testFirstPrimeAboveLimitNegative() {
		// Ignore negative sign on product
		assertEquals(null, getFirstPrimeAboveLimit(-5000, 71))
		assertEquals(5, getFirstPrimeAboveLimit(-5000, 4))
		assertEquals(7, getFirstPrimeAboveLimit(-49, 6))
		// Null when limit is negative
		assertEquals(null, getFirstPrimeAboveLimit(-5000, -4))
		assertEquals(null, getFirstPrimeAboveLimit(-49, -6))
	}

	@Test
	fun testReduceByPrimes() {
		assertEquals(2, reduceByPrimes(1..2, 30))
		assertEquals(4, reduceByPrimes(1..5, 60))
		assertEquals(12, reduceByPrimes(2..2, 60))
	}

	@Test
	fun testReduceByPrimesNegative() {
		assertEquals(-2, reduceByPrimes(1..2, -30))
		assertEquals(-4, reduceByPrimes(1..2, -60))
		assertEquals(-12, reduceByPrimes(2..3, -60))
	}

	@Test
	fun testReduceByPrimesInvalidRange() {
		assertEquals(null, reduceByPrimes(-3..-1, -30))
		assertEquals(null, reduceByPrimes(-3..5, -60))
		assertEquals(null, reduceByPrimes(-1..0, -60))
	}

}
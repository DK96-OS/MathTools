package mathtools.numbers.primes

import mathtools.numbers.primes.PrimeNumberTools.checkForPrimeFactorAboveLimit
import mathtools.numbers.primes.PrimeNumberTools.divideOutSmallPrimes
import mathtools.numbers.primes.PrimeNumberTools.getFirstPrimeAboveLimit
import mathtools.numbers.primes.PrimeNumberTools.reduceByPrimeRange
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/** Testing the Prime Number tools object */
class PrimeNumberToolsTest {
	
	private val primeList: List<Int> = arrayListOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 
    	73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
    )

	@AfterEach
	fun testCleanup() {
        PrimeNumberTools.clearCache()
	}

    @Test
    fun testGetPrimeIncreasingByOne() {
    	for (idx in primeList.indices)
        	assertEquals(
		        primeList[idx],
		        PrimeNumberTools.getPrime(idx)
	        )
    }
    
    @Test
    fun testGetPrimeDecreasingByOne() {
    	for (idx in primeList.size - 1 downTo 0)
    		assertEquals(
			    primeList[idx],
			    PrimeNumberTools.getPrime(idx)
		    )
    }

    @Test
    fun testPrimeFactorLimitCheck() {
    	assertFalse(
		    checkForPrimeFactorAboveLimit(40, 11))
    	assertFalse(
		    checkForPrimeFactorAboveLimit(39, 13))
    	//
    	assertTrue(
		    checkForPrimeFactorAboveLimit(34, 11))
    	assertTrue(
		    checkForPrimeFactorAboveLimit(39, 11))
    }

	@Test
	fun testFirstPrimeAboveLimit() {
		assertNull(
			getFirstPrimeAboveLimit(5000, 71))
		assertNull(
			getFirstPrimeAboveLimit(5000, 5))
		assertEquals(
			5, getFirstPrimeAboveLimit(5000, 4))
		assertEquals(
			5, getFirstPrimeAboveLimit(5000, 4))
		assertEquals(
			7, getFirstPrimeAboveLimit(49, 6))
		assertNull(
			getFirstPrimeAboveLimit(49, 7))
	}

	@Test
	fun testFirstPrimeAboveLimitNegative() {
		// Ignore negative sign on product
		assertNull(
			getFirstPrimeAboveLimit(-5000, 71))
		assertEquals(
			5, getFirstPrimeAboveLimit(-5000, 4))
		assertEquals(
			7, getFirstPrimeAboveLimit(-49, 6))
		// Null when limit is negative
		assertNull(
			getFirstPrimeAboveLimit(-5000, -4))
		assertNull(
			getFirstPrimeAboveLimit(-49, -6))
	}

	@Test
	fun testFirstPrimeAboveLimitInvalid() {
		// Null when product is 1 or zero
		assertNull(
			getFirstPrimeAboveLimit(-1, 5))
		assertNull(
			getFirstPrimeAboveLimit(0, 5))
		assertNull(
			getFirstPrimeAboveLimit(1, 5))
	}

	@Test
	fun testReduceByPrimes() {
		assertEquals(
			2, reduceByPrimeRange(1..2, 30))
		assertEquals(
			4, reduceByPrimeRange(1..5, 60))
		assertEquals(
			12, reduceByPrimeRange(2..2, 60))
	}

	@Test
	fun testReduceByPrimesNegative() {
		assertEquals(
			-2, reduceByPrimeRange(1..2, -30))
		assertEquals(
			-4, reduceByPrimeRange(1..2, -60))
		assertEquals(
			-12, reduceByPrimeRange(2..3, -60))
	}

	@Test
	fun testReduceByPrimesIndivisibleProduct() {
		assertNull(
			reduceByPrimeRange(1..3, -1))
		assertNull(
			reduceByPrimeRange(1..3, 0))
		assertNull(
			reduceByPrimeRange(1..3, 1))
	}

	@Test
	fun testReduceByPrimesInvalidRange() {
		// Negative valued ranges are ignored
		assertNull(
			reduceByPrimeRange(-3..-1, -30))
		// The invalid region of the range is ignored
		assertNull(
			reduceByPrimeRange(-3..3, -60))
		// Invalid region is ignored
		assertEquals(
			-15, reduceByPrimeRange(-1..0, -60))
	}

	@Test
	fun testReduceByPrimesReversedRange() {
		assertEquals(
			4, reduceByPrimeRange(2..1, 60))
		assertEquals(
			-4, reduceByPrimeRange(2..1, -60))
	}

	@Test
	fun testDivideOutSmallPrimes() {
		assertEquals(
			25, divideOutSmallPrimes(100, 2))
		assertEquals(
			25, divideOutSmallPrimes(100, 3))
		assertEquals(
			25, divideOutSmallPrimes(100, 4))
		assertNull(
			divideOutSmallPrimes(100, 5))
		//
		assertEquals(
			11, divideOutSmallPrimes(77, 7))
		assertEquals(
			11, divideOutSmallPrimes(77, 9))
		assertEquals(
			17, divideOutSmallPrimes(68, 13))
		//
		assertNull(
			divideOutSmallPrimes(50, 43))
	}

	@Test
	fun testDivideOutSmallPrimesNegative() {
		assertNull(
			divideOutSmallPrimes(-50, 43))
		assertNull(
			divideOutSmallPrimes(-100, 5))
	}

}
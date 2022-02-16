package mathtools.numbers.primes

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

}
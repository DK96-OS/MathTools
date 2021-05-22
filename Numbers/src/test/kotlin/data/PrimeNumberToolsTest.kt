package data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import data.PrimeNumberTools

/** Testing the Prime Number tools object */
class PrimeNumberToolsTest {
	
	private val primeList: List<Int> = arrayListOf<Int>(
    	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71
    )

	@AfterEach fun tearDown() {
        PrimeNumberTools.clearCache()
	}

    @Test fun testGetPrimeIncreasingByOne() {
    	for (idx in 0 until primeList.size)
        	assertEquals(primeList[idx], PrimeNumberTools.getPrime(idx)) 
    }
    
    @Test fun testGetPrimeDecreasingByOne() {
    	for (idx in primeList.size - 1 downTo 0)
    		assertEquals(primeList[idx], PrimeNumberTools.getPrime(idx))
    }
    
    @Test fun testReduceByFactor() {
    	assertEquals(2, PrimeNumberTools.reduceByFactor(5, 10))
    	assertEquals(8, PrimeNumberTools.reduceByFactor(3, 24))
    	assertEquals(1, PrimeNumberTools.reduceByFactor(5, 25))
    	assertEquals(1, PrimeNumberTools.reduceByFactor(25, 25))
    	assertEquals(25, PrimeNumberTools.reduceByFactor(10, 25))
    	assertEquals(18, PrimeNumberTools.reduceByFactor(5, 18))
    	assertEquals(64, PrimeNumberTools.reduceByFactor(5, 64))
    }
    
    @ParameterizedTest
    @ValueSource(ints = [2, 3, 5, 7, 11, 13, 17]) 
    fun testReduceByFactorLarge(factor: Int) {
    	assertEquals(353, PrimeNumberTools.reduceByFactor(factor, 353))
    	assertEquals(2909, PrimeNumberTools.reduceByFactor(factor, 2909))
    	assertEquals(7211, PrimeNumberTools.reduceByFactor(factor, 7211))
    	assertEquals(9013, PrimeNumberTools.reduceByFactor(factor, 9013))
    }
    
    @Test fun testPrimeFactorLimitCheck() {
    	assertEquals(false, PrimeNumberTools.checkForPrimeFactorAboveLimit(40, 11))
    	assertEquals(false, PrimeNumberTools.checkForPrimeFactorAboveLimit(39, 13))
    	
    	assertEquals(true, PrimeNumberTools.checkForPrimeFactorAboveLimit(34, 11))
    	assertEquals(true, PrimeNumberTools.checkForPrimeFactorAboveLimit(39, 11))
    }
    
    @Test fun obtainHighPrimeNumbers() {
    	for (i in 20 .. 3000 step 50)
    		println("Idx: $i, Prime: ${PrimeNumberTools.getPrime(i)}")
    }

}

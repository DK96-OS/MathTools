package data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import data.PrimeNumberTools

/** Testing the Prime Number tools object */
class PrimeNumberToolsTest {
	
	private lateinit var primeList: ArrayList<Int>

	@Before fun setup() {
		primeList = arrayListOf<Int>(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43
        )
	}

    @Test fun testGetPrime() {
        primeList.forEachIndexed {idx, prime ->
        	assertEquals(prime, PrimeNumberTools.getPrime(idx)) 
        }
    }
    
    @Test fun testCreateNewList() {
    	val newList = arrayListOf<Int>()
    	PrimeNumberTools.resizePrimeList(newList, 43)
    	for (i in 0 until primeList.size)
    		assertEquals(primeList[i], newList[i])
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

}

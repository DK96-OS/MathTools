package mathtools.numbers.primes

/** Common to Prime Number Caches */
abstract class PrimeCacheBase(
	/** The highest index that this Cache can store */
	val maxIndex: Int, 
	/** The maximum value that small data type arrays can hold */
	val maxValue: Int,
) {	
	/** The range of indexed prime numbers serviced by this cache */
	val indexRange: IntRange = 0 .. maxIndex

	/** Obtain the index of the highest prime number in the cache */
	abstract fun highestCachedIndex(): Int

	/** Request a prime number by it's index
	  * @param idx The index of the requested prime number
	  * @return The prime number at idx */
	abstract fun getPrime(idx: Int): Int

	/** Merge existing data structures, and find new primes
	  * @param add Try to find this many primes beyond highest cached index
	  * @return The highest prime that has been recently added */
	protected abstract fun consolidate(add: Int = 1): Int

	/** Clear saved prime numbers in this cache */
	abstract fun clear()

    /** Skip checking if 2 is a factor, assume number is odd */
    private fun quickIsPrime(number: Int): Boolean {
        for (i in 1 .. maxIndex - 1 step 2) {
            val testPrime1 = getPrime(i + 1)
            if (number == testPrime1) return true
            if (number % testPrime1 == 0) return false
            val testPrime0 = getPrime(i)
            if (number == testPrime0) return true
            if (number % testPrime0 == 0) return false
            // Factor limit condition
            if (testPrime1 * testPrime0 > number) return true
        }
        // When the last index was skipped because of step 2
        return if (maxIndex % 2 == 0)
            number % getPrime(maxIndex) != 0 else true
    }

    /** Determine if this number is prime */
    fun isPrime(number: Int): Boolean {
        if (number in 0 .. 3) return true
        if (number < 0) return isPrime(-number)
        if (number % 2 == 0) return false
        // Check current cache size, and maximum prime number
        var cacheIndex = highestCachedIndex()
        var highestPrime = getPrime(cacheIndex)
        // Check factor break condition
        var maxProduct = highestPrime * getPrime(cacheIndex - 1)
        if (maxProduct > number) return quickIsPrime(number)
        if (maxProduct == number) return false
        // Expand the cache, assume more primes will be required
        var availablePrimes = (maxIndex - cacheIndex).coerceAtMost(24)
        while (availablePrimes > 0) { 	// Can be expanded
            highestPrime = consolidate(availablePrimes)
            maxProduct = highestPrime * getPrime(cacheIndex - 1)
            if (maxProduct > number) return quickIsPrime(number)
            if (maxProduct == number) return false
            // The given number is still larger than the break condition
            availablePrimes = (maxIndex - cacheIndex).coerceAtMost(48)
        }
        throw IllegalStateException("Inconsistent State")
    }

    /** Find the next prime using the given number as a starting point
      * @param testNum The first number to test for prime status */
    internal fun findPrime(testNum: Int): Int? {
        for (n in testNum .. maxValue step 2)
            if (quickIsPrime(n)) return n
        return null
    }
}

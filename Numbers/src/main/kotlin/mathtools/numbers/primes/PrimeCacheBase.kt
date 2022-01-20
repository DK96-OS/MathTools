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
        var prevPrime = 2L
        for (i in 1 .. 15) {
            val testPrime = initArray[i]
            if (number % testPrime == 0) return false
            if (testPrime * prevPrime > number) return true
            prevPrime = testPrime.toLong()
        }
        for (i in 16 .. indexRange.last) {
            val testPrime = getPrime(i)
            if (number % testPrime == 0) return false
            if (testPrime * prevPrime > number) return true
            prevPrime = testPrime.toLong()
        }
        return true
    }

    /** Determine if this number is prime.
     * Zero is considered prime */
    fun isPrime(number: Int): Boolean {
        when {
            // Basic Primes: 0, 1, 2, 3
            number in 0 .. 3 -> return true
            // Invert the number if it is negative
            number < 0 -> return isPrime(-number)
            // Check if it is in the initial prime array
            number <= initArray.last() -> {
                return number.toByte() in initArray
            }
            // Check if it is even
            number % 2 == 0 -> return false
        }
        // Check current cache size, and maximum prime number
        val cacheIndex = highestCachedIndex()
        var highestPrime = getPrime(cacheIndex)
        // Check factor break condition
        var maxProduct = highestPrime * getPrime(cacheIndex - 1)
        if (maxProduct > number) return quickIsPrime(number)
        if (maxProduct == number) return false
        // Potential Improvement: Check static primes before expanding cache
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

    companion object {
        /** The first 16 useful primes */
        internal val initArray: ByteArray = byteArrayOf(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
        )
    }
}

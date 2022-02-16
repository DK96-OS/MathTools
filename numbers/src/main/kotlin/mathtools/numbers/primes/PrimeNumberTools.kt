package mathtools.numbers.primes

import mathtools.numbers.factors.NumberFactors.divideOutFactor

/** Container for PrimeNumber functions
 * @author DK96-OS : 2018 - 2021 */
object PrimeNumberTools {

	/** This master prime cache provides a reference for other sets */
	private val shortPrimes = ShortPrimeCache()		// Short Max Value: 32767

	/** Obtain a prime number by it's index, starting at index 0 -> 2 */
	fun getPrime(idx: Int): Int = when (idx) {
		in shortPrimes.indexRange -> try {
			shortPrimes.getPrime(idx)
		} catch (e: OutOfMemoryError) {
			shortPrimes.clear()
			-1
		}
		else -> throw IllegalArgumentException(
			"Prime Number too high for given cache.")
	}

	/** Clear all cached prime numbers */
	fun clearCache() { shortPrimes.clear()}

    /** Whether this Product/Prime contains a prime number greater than the limit
     * @param product The product to test
     * @param limit The maximum prime number allowed */
	fun checkForPrimeFactorAboveLimit(product: Long, limit: Long)
	: Boolean = if (limit < product) {
		var primeIdx = 0
		var checkPrime = getPrime(primeIdx)
		var composite = product
		while (limit in checkPrime until composite) {
			composite = divideOutFactor(checkPrime, composite)
			checkPrime = getPrime(++primeIdx)
		}
		composite > limit
    } else false

    /** Obtain lowest Prime Number Factor that is greater than the limit
     * @param product The product to search in
     * @param limit The maximum prime factor that is allowed
     * @return The smallest prime factor above the limit, or null  */
    fun getFirstPrimeAboveLimit(product: Long, limit: Long): Long? {
        var primeIdx = 0
        var testPrime = getPrime(primeIdx)
        var composite = product
        while (limit in testPrime until composite) {
            composite = divideOutFactor(testPrime, composite)
            testPrime = getPrime(++primeIdx)
        }
        while (testPrime in (limit + 1) .. composite) {
            if (composite % testPrime == 0L) return testPrime.toLong()
            else testPrime += 2
        }
        return null
    }

	/** Try to divide the product by all primes in the index range.
	 * @param primeIndexRange The range of prime number indices to try
	 * @param product Assumed to be a product of prime numbers
	 * @return Remaining product after all divisions attempted, or null if 1 */
	fun reduceByPrimes(primeIndexRange: IntRange, product: Long): Long? {
		var composite: Long = product
		for (primeIdx in primeIndexRange) {
			val testPrime = getPrime(primeIdx)
			composite = divideOutFactor(testPrime, composite)
			if (composite < testPrime) break
		}
		return if (composite <= 1L) null else composite
	}
	
    /** Remove any prime factors less than or equal to the maximum
	 * @param product Assumed product of a set of prime numbers
	 * @param maxPrime The maximum prime factor to be removed */
	fun reduceByPrimes(product: Long, maxPrime: Long): Long? {
		if (product <= maxPrime || maxPrime <= 1) return null
		var composite: Long = divideOutFactor(2, product)
		var primeIdx = 1
		var testPrime: Int = 3	// getPrime(idx) currently returns Int
		while (
			testPrime <= composite &&
			testPrime <= maxPrime && primeIdx <= shortPrimes.maxIndex
		) {
			testPrime = getPrime(primeIdx++)
			composite = divideOutFactor(testPrime, composite)
		}
		return if (composite <= 1L || composite <= testPrime)
			null else composite
	}
	
}
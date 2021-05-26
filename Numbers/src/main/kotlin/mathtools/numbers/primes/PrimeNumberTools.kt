package mathtools.numbers.primes

/** Container for PrimeNumber functions */
object PrimeNumberTools {

	/** This master prime cache provides a reference for other sets */
	private val shortPrimes = ShortPrimeCache()		// Short Max Value: 32767

	/** Obtain a prime number by it's index, starting at index 0 -> 2 */
	fun getPrime(idx: Int): Int = when (idx) {
		in shortPrimes.indexRange -> shortPrimes.getPrime(idx)
		//todo: Use special mathematical condition to enable computing higher prime numbers from the cache without storing them
		else -> throw IllegalArgumentException("Prime Number too high for given cache.")
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
        	composite = reduceByFactor(checkPrime, composite)
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
            composite = reduceByFactor(testPrime, composite)
            testPrime = getPrime(++primeIdx)
        }
        while (testPrime in (limit + 1) .. composite) {
            if (composite % testPrime == 0L) return testPrime.toLong()
            else testPrime += 2
        }
        return null
    }

    /** Reduces the composite by the given factor repeatedly
     * @param factor The factor to reduce
     * @param composite The composite number to be reduced if it contains factor
     * @return The reduced or unreduced composite number */
    fun reduceByFactor(factor: Int, composite: Long)
    : Long = if (composite % factor == 0L) {
        var reduced = composite / factor
        while (reduced % factor == 0L) { reduced /= factor }
        reduced
    } else composite
    
}

package data

/** Container for PrimeNumber functions */
object PrimeNumberTools {

    /** Updates a list of prime numbers, to contain all primes up to the maximum
     * @param maxPrime The maximum possible number that may be added to the list if prime */
    fun ArrayList<Int>.updatePrimeNumberList(maxPrime: Long) {
        var tryAddingMore = true
        if (isEmpty()) {
            add(2)
            add(3)
        } else while (last() > maxPrime) {
            tryAddingMore = false
            removeLast()
        }
        if (tryAddingMore) {
            var testN = last() + 2
            var mayBePrime = true
            while (testN <= maxPrime) {
                for (primeIdx in 1 until size) {
                    val prime = get(primeIdx)
                    if (testN % prime == 0) {
                        mayBePrime = false
                        break
                    } else if (prime > testN / get(primeIdx - 1)) break
                }
                if (mayBePrime) add(testN) else mayBePrime = true
                testN += 2
            }
        }
    }

    /** Whether this Product/Prime contains a prime number greater than the limit
     * @param product The product to test
     * @param limit The maximum prime number allowed */
    fun checkForPrimeFactorAboveLimit(product: Long, limit: Long)
    : Boolean = if (limit < product) {
        var composite = product
        while (composite % 2 == 0L && composite > limit) 
        	composite /= 2
        if (limit >= 3) 
        	while (composite % 3 == 0L && composite > limit) 
        		composite /= 3
        if (limit >= 5) 
        	while (composite % 5 == 0L && composite > limit) 
        		composite /= 5
        if (composite <= limit) false else {
            var primeCounter = 7L
            while (limit in primeCounter until composite) {
                while (composite % primeCounter == 0L) 
                	composite /= primeCounter
                primeCounter += 2
                if (primeCounter.toString().endsWith('5')) 
                	primeCounter += 2
            }
            composite > limit
        }
    } else false

    /** Obtain lowest Prime Number Factor that is greater than the limit
     * @param product The product to search in
     * @param limit The maximum prime factor that is allowed
     * @return The smallest prime factor above the limit, or null  */
    fun getFirstPrimeAboveLimit(product: Long, limit: Long): Long? {
        var composite = if (limit >= 2) reduceByFactor(2, product)
        else throw IllegalArgumentException()
        var primeCounter = 3
        while (limit in primeCounter until composite) { // Safe to remove
            val reduced = reduceByFactor(primeCounter, composite)
            if (reduced < composite) composite = reduced
            primeCounter += 2
        }
        while (primeCounter in (limit + 1) .. composite) {
            if (composite % primeCounter == 0L) return primeCounter.toLong()
            else primeCounter += 2
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

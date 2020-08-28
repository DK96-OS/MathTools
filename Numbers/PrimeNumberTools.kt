
/** Container for PrimeNumber functions */
object PrimeNumberTools {

    /** Creates a list of prime numbers up to the maximum provided
     * Checks odd numbers if they can be divided by the primes accumulated
     * @param maxPrime The maximum possible number that will be added to the list */
    fun createPrimeNumberList(maxPrime: Long): ArrayList<Int> {
        val primeNumbers = arrayListOf(2, 3, 5, 7)
        var testN = primeNumbers[primeNumbers.size - 1] + 4
        var mayBePrime = true
        var upperPrimeLimit: Int  // Upper limit on the second factor
        while (testN <= maxPrime) {
            upperPrimeLimit = testN / 2    // Increment pattern eliminates 2
            for (pIndex in 1 until primeNumbers.size) {
                val prime = primeNumbers[pIndex]
                if (prime > upperPrimeLimit)
                    break
                else if (testN % prime != 0)
                    upperPrimeLimit = testN / prime
                else {
                    mayBePrime = false
                    break
                }
            }
            if (mayBePrime) primeNumbers.add(testN) else mayBePrime = true
            testN += 2
        }
        return primeNumbers
    }

    /** Determines whether this Product/Prime contains a prime number greater than the limit
     * @param product The product to test
     * @param limit The maximum prime number allowed */
    fun checkForPrimeFactorAboveLimit(product: Long, limit: Long)
        : Boolean = if (limit < product) {
        var composite = product
        while (composite % 2 == 0L && composite > limit) composite /= 2
        if (limit >= 3) while (composite % 3 == 0L && composite > limit) composite /= 3
        if (limit >= 5) while (composite % 5 == 0L && composite > limit) composite /= 5
        if (composite <= limit) false else {
            var primeCounter = 7L
            while (limit in primeCounter until composite) {
                while (composite % primeCounter == 0L) composite /= primeCounter
                primeCounter += 2
                if (primeCounter.toString().endsWith('5')) primeCounter += 2
            }
            composite > limit
        }
    } else false

    /** Obtains the lowest Prime Number Factor that is greater than the limit or null
     * @param product The product to search in
     * @param limit The maximum prime factor that is allowed
     * @return The smallest prime factor above the limit, or null  */
    fun getFirstPrimeAboveLimit(product: Long, limit: Long): Long? {
        var composite = product
        while (composite % 2 == 0L) composite /= 2
        if (limit >= 3)
            while (composite % 3 == 0L && composite > limit) composite /= 3
        else if (composite % 3 == 0L) 
            return 3
        if (limit >= 5)
            while (composite % 5 == 0L && composite > limit) composite /= 5
        else if (composite % 5 == 0L) 
            return 5
        if (limit < composite) {
            var primeCounter = 7L
            while (limit in primeCounter until composite) {
                if (composite % primeCounter == 0L) composite /= primeCounter
                else primeCounter += 2
            }
            if (limit < composite) while (primeCounter <= composite) {
                if (composite % primeCounter == 0L) return primeCounter else primeCounter++
            }
        }
        return null
    }

}


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

    /** Checks for a Prime Number Factor in product that is greater than the limit
     * @param product The product to search in
     * @param limit The maximum prime factor that is allowed
     * @return The smallest prime factor above the limit, or null  */
    fun getFirstPrimeAboveLimit(product: Long, limit: Long): Long? {
        var remainingFactors = product
        while (remainingFactors % 2 == 0L) remainingFactors /= 2  //Discard all 2 values
        if (limit < remainingFactors) { //Is there a potential factor greater than the remainder
            var primeCounter = 3L  //Need to remove all primes below or equal to the limit
            while (limit in primeCounter until remainingFactors) {
                if (remainingFactors % primeCounter == 0L)
                    remainingFactors /= primeCounter    //Throw away
                else
                    primeCounter += 2    //Increment
            }
            if (limit < remainingFactors) {
                while (primeCounter <= remainingFactors) {
                    if (remainingFactors % primeCounter == 0L) return primeCounter
                    else primeCounter++
                }
            }
        }
        return null
    }
    
}

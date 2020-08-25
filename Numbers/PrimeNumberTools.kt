
/** Container for PrimeNumber functions */
object PrimeNumberTools {

    /** Creates a list of prime numbers up to the maximum provided
     * Checks odd numbers if they can be divided by the primes accumulated
     * @param maxPrime The maximum possible number that will be added to the list */
    fun createPrimeNumberList(maxPrime: Long): ArrayList<Int> {
        val primeNumbers = arrayListOf(2, 3, 5, 7)
        var testN = primeNumbers[primeNumbers.size - 1] + 2
        var mayBePrime = true
        while (testN <= maxPrime) {
            for (pIndex in 1 until primeNumbers.size) { // Checks division by all primes 3 and up
                if (testN % primeNumbers[pIndex] == 0) { // Successful division
                    mayBePrime = false
                    break
                }
            }
            if (mayBePrime) primeNumbers.add(testN) else mayBePrime = true
            testN += 2
        }
        return primeNumbers
    }
    
}

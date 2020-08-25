import kotlin.random.Random

/** Generator that limits the Prime numbers involved in a product
  * Developed by DK96-OS 2018 - 2020 */
class ProductGenerator(
    /** The range to generate products in */
    range: LongRange,
    /** The maximum prime number that is allowed in the product */
    maxPrime: Long
) 
    : Generator {

    var product: Long = 1L
        private set

    var range: LongRange = range
        private set
    var maxPrime: Long = maxPrime
        private set

    private val rng = Random(System.currentTimeMillis())

    init { generate() }

    /** Updates the generator parameters, and runs again
     * @param rng The new product range
     * @param maxPrime The highest prime number allowed, null makes the generator pointless */
    fun setParametersAndRegenerate(rng: LongRange, maxPrime: Long) {
        range = rng
        this.maxPrime = maxPrime
        generate()
    }

    override fun generate() {
        product = range.random(rng)
        if (maxPrime < product) {
            var primeAboveLimit = getFirstPrimeAboveLimit(product, maxPrime)
            while (primeAboveLimit != null) {
                product /= primeAboveLimit
                product *= (primeAboveLimit - if (Math.random() > 0.5) 1 else -1)
                primeAboveLimit = getFirstPrimeAboveLimit(product, maxPrime)
            }
        }
    }

    /** Checks for a Prime Number Factor in product that is greater than the limit
     * @param product The product to search in
     * @param limit The maximum prime factor that is allowed
     * @return The smallest prime factor above the limit, or null  */
    private fun getFirstPrimeAboveLimit(product: Long, limit: Long): Long? {
        var remainingFactors = product
        while (remainingFactors % 2 == 0L) remainingFactors /= 2  // Discard all 2 values
        if (limit < remainingFactors) { // Is there a potential factor greater than the remainder
            var primeCounter = 3L  // Need to remove all primes below or equal to the limit
            while (limit in primeCounter until remainingFactors) {
                if (remainingFactors % primeCounter == 0L)
                    remainingFactors /= primeCounter    // Throw away
                else primeCounter += 2   // Increment
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

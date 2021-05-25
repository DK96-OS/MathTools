package generators

import kotlin.random.Random
import data.PrimeNumberTools

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

    init {
        if (range.first < 2 || range.last - range.first < 3 || maxPrime < 2)
            throw IllegalArgumentException()
        generate()
    }

    /** Updates the generator parameters, and runs again
     * @param rng The new product range
     * @param limit The highest prime number allowed */
    fun setParametersAndRegenerate(rng: LongRange, limit: Long) {
        if (rng.first < 2 || rng.last - rng.first < 3 || maxPrime < 2)
            throw IllegalArgumentException()
        range = rng
        maxPrime = limit
        generate()
    }

    override fun generate() {
        var invalid = true
        while (invalid) {
            product = range.random()
            invalid = if (maxPrime < product)
                PrimeNumberTools.checkForPrimeFactorAboveLimit(product, maxPrime) else false
        }
    }

}

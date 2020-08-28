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
        product = range.random()
        if (maxPrime < product) {
            var primeAboveLimit = PrimeNumberTools.getFirstPrimeAboveLimit(product, maxPrime)
            while (primeAboveLimit != null) {
                product /= primeAboveLimit
                product *= (primeAboveLimit - if (Math.random() > 0.5) 1 else 2)
                primeAboveLimit = PrimeNumberTools.getFirstPrimeAboveLimit(product, maxPrime)
            }
        }
    }

}

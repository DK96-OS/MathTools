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
            var primeAboveLimit = PrimeNumberTools.getFirstPrimeAboveLimit(product, maxPrime)
            while (primeAboveLimit != null) {
                product /= primeAboveLimit
                product *= (primeAboveLimit - if (Math.random() > 0.5) 1 else 2)
                primeAboveLimit = PrimeNumberTools.getFirstPrimeAboveLimit(product, maxPrime)
            }
        }
    }

}

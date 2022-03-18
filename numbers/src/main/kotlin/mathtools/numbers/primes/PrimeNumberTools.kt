package mathtools.numbers.primes

import mathtools.numbers.factors.BitFactoring.isProductOf2
import mathtools.numbers.factors.Factoring.divideOutFactor

/** Container for PrimeNumber functions
 * @author DK96-OS : 2018 - 2022 */
object PrimeNumberTools {

    /** Whether this Product/Prime contains a prime number greater than the limit
     * @param product The product to test
     * @param limit The maximum prime number allowed */
	@Deprecated(
			message = "Moved to PrimeFactoring",
			replaceWith = ReplaceWith(
					"PrimeFactoring.hasPrimeAbove(product, limit, cache)",
					"mathtools.numbers.primes.PrimeFactoring"
			),
			level = DeprecationLevel.WARNING
	)
	fun checkForPrimeFactorAboveLimit(
	    product: Long,
	    limit: Long,
		cache: PrimeCacheInterface,
    ) : Boolean = PrimeFactoring.hasPrimeAbove(product, limit, cache)

    /** Obtain lowest Prime Number Factor that is greater than the limit
     * @param product The product to search in
     * @param limit The maximum prime factor that is allowed
     * @return The smallest prime factor above the limit, or null  */
	@Deprecated(
		message = "Moved to PrimeFactoring",
		replaceWith = ReplaceWith(
			"PrimeFactoring.firstPrimeAbove(product, limit, cache)",
			"mathtools.numbers.primes.PrimeFactoring"
		),
		level = DeprecationLevel.WARNING
	)
	fun getFirstPrimeAboveLimit(
	    product: Long,
	    limit: Long,
		cache: PrimeCacheInterface,
    ) : Long? = PrimeFactoring.firstPrimeAbove(product, limit, cache)

	/** Try to divide the product by all primes in the index range.
	 * @param primeIndexRange The range of prime number indices to try
	 * @param product Assumed to be a product of prime numbers
	 * @return Remaining product after all divisions attempted, or null if 1 */
	fun reduceByPrimeRange(
		primeIndexRange: IntRange,
		product: Long,
		cache: PrimeCacheInterface,
	) : Long? {
		// check arguments
		when {
			// range is reversed
			primeIndexRange.last < primeIndexRange.first ->
				return reduceByPrimeRange(
					primeIndexRange.last .. primeIndexRange.first,
					product,
					cache
				)
			// range starts below 0
			primeIndexRange.first < 0 -> {
				if (primeIndexRange.last < 0) return null
				// If range ends in valid region, reduce in valid region
				return reduceByPrimeRange(
					0 .. primeIndexRange.last,
					product,
					cache
				)
			}
			// Product is indivisible
			product in -1 .. 1 -> return null
			// Product is negative
			product < 0 -> {
				val result = reduceByPrimeRange(
					primeIndexRange, -product, cache
				)
				return if (result != null) -result else null
			}
		}
		// Do reduction
		var composite: Long = product
		for (primeIdx in primeIndexRange) {
			val testPrime = cache.getPrime(primeIdx)
			composite = divideOutFactor(composite, testPrime)
			// If the composite is less than the test factor, done
			if (composite < testPrime) break
		}
		return if (composite <= 1L)
			null else composite
	}
	
    /** Remove any prime factors less than or equal to the maximum
	 * @param product Assumed product of a set of prime numbers
	 * @param maxPrime The maximum prime factor to be removed
     * @return A number containing only primes greater than maxPrime, or null */
	fun divideOutSmallPrimes(
	    product: Long,
	    maxPrime: Long,
		cache: PrimeCacheInterface,
    ) : Long? {
	    // check arguments
	    when {
			maxPrime < 2 -> return null
			product in -1 .. 1 -> return null
		    product < 0 -> return divideOutSmallPrimes(
				-product, maxPrime, cache
			)
		    product <= maxPrime -> return null
		}
	    // check efficiently for factors of 2
		var composite: Long = if (isProductOf2(product))
			divideOutFactor(2, product) else product
	    // check next prime factor
		var primeIdx = 1
		var testPrime = 3	// getPrime(idx) currently returns Int
	    while (
			testPrime <= composite
			&& testPrime <= maxPrime
			&& primeIdx < cache.maxIndex
		) {
			composite = divideOutFactor(composite, testPrime)
		    // Validate the next prime before overwriting
		    val nextPrime = cache.getPrime(++primeIdx)
		    if (nextPrime >= composite) break
		    testPrime = nextPrime
		}
		// If composite became 1, it was completely divided
	    return if (composite <= 1L || composite <= testPrime)
			null else composite
	}
	
}
package data

/** Container for PrimeNumber functions */
object PrimeNumberTools {


    /** Updates a list of prime numbers, to contain all primes up to the maximum
     * @param maxPrime The maximum possible number that may be added to the list if prime */
    fun resizePrimeList(list: ArrayList<Int>, maxPrime: Int) {
        if (list.size > 0 && list.last() > maxPrime) {
            list.removeLast()
            while (list.last() > maxPrime) list.removeLast()
        } else {
            var counter = list.size
		    var nextPrime = getPrime(counter)
		    try {
		    	while (nextPrime <= maxPrime) {
		    		list.add(nextPrime)
		    		nextPrime = getPrime(++counter)
		    	}
		    } catch(e: Exception) {println("Prime Number Exception: $e")}
        }
    }	

	/** This master prime set provides a reference for other sets to build from */
	private val masterPrimeSet = ArrayDeque<Int>()
	private const val setStart = 10
	private const val setLimit = 180
	
	/** Obtain a prime number by it's index, starting at index 0 -> 2 */
	fun getPrime(idx: Int): Int = when (idx) {
		in setStart .. setStart + setLimit -> {
			val diff = idx - setStart
			if (diff < masterPrimeSet.size) 
				masterPrimeSet[diff]
			else {
				increaseMasterPrimeSet(diff + 1 - masterPrimeSet.size)
				masterPrimeSet[diff]
			}
		}
		0 -> 2
		in 1..3 -> 1 + 2 * idx
		in 4..5 -> 3 + 2 * idx
		in 6..7 -> 5 + 2 * idx
		8 -> 23
		9 -> 29
		// If more constants are added, update the setStart constant
		else -> throw IllegalArgumentException("Prime Number too high")
	}

	/** Add primes to the end of the set 
		* @param n The number of primes to add to the master set */
	private fun increaseMasterPrimeSet(n: Int) {
		val initIndex = setStart + masterPrimeSet.size - 1	// Index of highest prime
		if (initIndex + n > setStart + setLimit) 
			throw IllegalArgumentException("Cannot increase set beyond limit")
		var primeCounter = 0
		var testN = getPrime(initIndex) + 2
		var mayBePrime = true
		while (primeCounter < n) {
			var prevPrime = 2
			for (i in 1 until initIndex) {
				val prime = getPrime(i)
				if (testN % prime == 0) {
					mayBePrime = false
					break
				} else if (prime > testN.toFloat() / prevPrime) break
				prevPrime = prime
			}
			if (!mayBePrime) mayBePrime = true else {
				masterPrimeSet.add(testN)
				primeCounter++
			}
			testN += 2	// Always increment by 2 to stay odd numbered
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

package mathtools.numbers.primes

/** Common to Prime Number Caches */
abstract class PrimeCacheBase(
	/** The highest index that this Cache can store */
	val maxIndex: Int, 
	/** The maximum value that small data type arrays can hold */
	val maxValue: Int,
) {	
	/** The range of indexed prime numbers serviced by this cache */
	val indexRange: IntRange = 0 .. maxIndex

	/** Obtain the index of the highest prime number in the cache */
	abstract fun highestCachedIndex(): Int

	/** Obtain the Prime Number at the given index */
	abstract fun getPrime(idx: Int): Int

	/** Merge the Cache data structures, while adding new primes */
	protected abstract fun consolidate(add: Int = 1): Int

	/** Clear saved prime numbers in this cache */
	abstract fun clear()

	/** Skip checking if 2 is a factor, assume that number is odd */
	protected fun quickIsPrime(number: Int): Boolean {
		var prevPrime = 2L
		for (i in 1 .. indexRange.last) {
			val testPrime = getPrime(i)
			if (number % testPrime == 0) return false
			if (testPrime * prevPrime > number) break
			prevPrime = testPrime.toLong()
		}
		return true
	}

	/** Determine if this number is a prime */
	fun isPrime(number: Int): Boolean {
		if (number in 0 .. 3) return true
		if (number < 0) return isPrime(-number)
		if (number % 2 == 0) return false
		if (number > maxValue * 23L)
			throw IllegalArgumentException("Use a bigger cache")
		var cacheIndex = highestCachedIndex()		// check cache size
		var highestPrime = getPrime(cacheIndex)
		if (highestPrime * 23L >= number 
			|| highestPrime * getPrime(cacheIndex - 1) >= number
			) return quickIsPrime(number)
		var availablePrimes = (maxIndex - cacheIndex).coerceAtMost(16)
		if (availablePrimes > 0) { 	// Can be expanded
			highestPrime = consolidate(availablePrimes)
			if (highestPrime * getPrime(cacheIndex - 1) >= number) 
				return quickIsPrime(number)
			cacheIndex = highestCachedIndex()
			availablePrimes = (maxIndex - cacheIndex).coerceAtMost(32)
			while (availablePrimes > 0 && highestPrime * 23L <= number) {
				highestPrime = consolidate(availablePrimes)
				if (highestPrime * getPrime(cacheIndex - 1) >= number) 
					return quickIsPrime(number)
				cacheIndex = highestCachedIndex()
				availablePrimes = (maxIndex - cacheIndex).coerceAtMost(32)
			}
			if (highestPrime * getPrime(cacheIndex - 1) >= number)
				return quickIsPrime(number)
		}
		throw IllegalArgumentException("Use a bigger cache")
	}

	/** Find the next prime using the given number as a starting point
		* @param testNum The first number to test for prime status */
	internal fun findPrime(testNum: Int): Int? {
		for (n in testNum .. maxValue step 2)
		    if (quickIsPrime(n)) return n
		return null
	}
}

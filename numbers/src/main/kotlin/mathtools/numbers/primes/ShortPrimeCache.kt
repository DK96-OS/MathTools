package mathtools.numbers.primes

/** An array-based cache for Prime Numbers in the 16-bit integer range.
 * @author DK96-OS : 2021 - 2022
 */
open class ShortPrimeCache
	: PrimeCacheInterface {

	/** Rely on BytePrimeCache for smallest primes.
	 */
	private val byteCache = BytePrimeCache()
	
	/** The range of indexed prime numbers served by this cache.
	 */
	private val shortIndexRange
		: IntRange = BytePrimeCache.MAX_INDEX + 1 .. maxIndex

	/** Storage for short primes */
	private var shortArray: ShortArray = shortArrayOf(
		521, 523, 541, 547, 557, 563, 569, 571,
    )
	private val shortQueue = ArrayDeque<Short>(12)	

	private val arraySize: Int get() = shortArray.size
	private val queueSize: Int get() = shortQueue.size

	override fun getMaxIndex(): Int = MAX_INDEX

	override fun getMaxPrime(): Int = MAX_PRIME

	override fun getHighestCachedIndex()
		: Int = BytePrimeCache.MAX_INDEX + shortArray.size + shortQueue.size

	override fun getPrime(idx: Int): Int {
		val shortIndex = idx - shortIndexRange.first
		if (shortIndex < 0)
			return byteCache.getPrime(idx)
			// First check the Array
		if (shortIndex < arraySize)
			return shortArray[shortIndex].toInt()
			// Check the Queue
		val overflow = shortIndex - arraySize
		if (overflow < queueSize)
			return shortQueue[overflow].toInt()
			// Expand the Cache
		if (idx <= maxIndex) {
			val primesRequired = idx - highestCachedIndex
			if (extendCache(primesRequired)) {
				return getPrime(idx)
			}
		}
		throw IllegalStateException("Unable to obtain Prime")
	}

	/** Determine if this is a prime number.
	 * @param number The number to test for Prime status.
	 * @return True, if the number is Prime.
	 *  */
	fun isPrime(number: Int)
		: Boolean = PrimeValidation.isPrime(number, this)

	private fun extendCache(
		addPrimes: Int
	) : Boolean {
		var primesRequired = addPrimes
		// If there are more than 12 items in the temporary queue
		return if (primesRequired + queueSize > 12) {
			consolidate(primesRequired)
			true
		} else {	// Look for primes, append to queue
			var prevPrime = (
				shortQueue.lastOrNull() ?: shortArray.last()
			).toInt()
			var testN = prevPrime + 2
			while (primesRequired > 0) {
				val prime = PrimeValidation.findPrime(
					testN, this
				) ?: break
				shortQueue.addLast(prime.toShort())
				testN = prime + if (prime - prevPrime == 2) 4 else 2
				prevPrime = prime
				primesRequired--
			}
			primesRequired == 0	// The correct amount of primes found
		}
	}

	private fun consolidate(add: Int) {
		//if (add <= 0) throw IllegalArgumentException()
		val prevSize = arraySize + queueSize
		if (prevSize + add > maxIndex + 1)
			return
		val oldArray = shortArray
		var prev = (shortQueue.lastOrNull() ?: oldArray.last()).toInt()
		shortArray = ShortArray(prevSize + add) {
			if (it < oldArray.size) oldArray[it]
			else if (it < prevSize) shortQueue.removeFirst()
			else {
				val newPrime = PrimeValidation.findPrime(
					prev + 2, this
				) ?: throw IllegalStateException("Next Prime not found")
				prev = newPrime
				newPrime.toShort()
			}
		}
	}

	override fun clear() {
		shortQueue.clear()
		shortArray = shortArrayOf(
			521, 523, 541, 547, 557, 563, 569, 571,
		)
		byteCache.clear()
	}

	companion object {
		/** The largest index that can be stored.
		 */
		const val MAX_INDEX: Int = 3511

		/** The largest prime value that can be stored.
		 */
		const val MAX_PRIME: Int = 32749
	}
}
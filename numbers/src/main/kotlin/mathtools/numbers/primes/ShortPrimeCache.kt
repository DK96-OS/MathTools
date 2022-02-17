package mathtools.numbers.primes

/** A cache for small prime numbers, within Short range
   *  Developed by DK96-OS : 2021 */
open class ShortPrimeCache : PrimeCacheBase(
	maxIndex = 3020, 
	maxValue = Short.MAX_VALUE.toInt(),
) {
	/** Rely on BytePrimeCache for smallest primes */
	private val byteCache = BytePrimeCache()
	
	/** The range of indexed prime numbers serviced by this cache
		*  note: maximum index must be less than the index of 32767.  */
	val shortIndexRange: IntRange = byteCache.maxIndex + 1 .. maxIndex

	/** Storage for short primes */
	private var shortArray: ShortArray = shortArrayOf(
	    257, 263, 269, 271, 277, 281, 283, 293,
    )
	private val shortQueue = ArrayDeque<Short>(12)	

	private val arraySize: Int get() = shortArray.size
	private val queueSize: Int get() = shortQueue.size

	override fun highestCachedIndex()
	: Int = byteCache.maxIndex + shortArray.size + shortQueue.size

	override fun getPrime(idx: Int): Int {
		val shortIndex = idx - shortIndexRange.first
		if (shortIndex < 0) return byteCache.getPrime(idx)
			// First check the Array
		if (shortIndex < arraySize) return shortArray[shortIndex].toInt()
			// Check the Queue
		val overflow = shortIndex - arraySize
		if (overflow < queueSize) return shortQueue[overflow].toInt()
			// Expand the Cache
		if (idx in indexRange && extendCache(idx)) return getPrime(idx)
		throw IllegalStateException("Unable to obtain Prime")
	}

	private fun extendCache(toIndex: Int)
	: Boolean = if (toIndex in shortIndexRange) {
		var primesRequired = toIndex  - highestCachedIndex()
		if (primesRequired <= 0) false
		else if (primesRequired + queueSize > 12)
			consolidate(primesRequired) > 0
		else {	// Look for primes, append to queue
			var prevPrime = (
				shortQueue.lastOrNull() ?: shortArray.last()).toInt()
			var testN = prevPrime + 2
			while (primesRequired > 0) {
				val prime = findPrime(testN) ?: break
				shortQueue.addLast(prime.toShort())
				testN = prime + if (prime - prevPrime == 2) 4 else 2
				prevPrime = prime
				primesRequired--
			}
			primesRequired == 0	// The correct amount of primes found
		}
	} else false

	override fun consolidate(add: Int): Int {
		if (add <= 0) throw IllegalArgumentException()
		val prevSize = arraySize + queueSize
		if (prevSize + add > indexRange.last + 1) return -1
		val oldArray = shortArray
		var prev = (shortQueue.lastOrNull() ?: oldArray.last()).toInt()
		shortArray = ShortArray(prevSize + add) {
			if (it < oldArray.size) oldArray[it]
			else if (it < prevSize) shortQueue.removeFirst()
			else {
				val newPrime = findPrime(prev + 2)
				        ?: throw IllegalStateException("Next Prime not found")
				prev = newPrime
				newPrime.toShort()
			}
		}
		return shortArray.last().toInt()
	}

	override fun clear() {
		shortQueue.clear()
		shortArray = shortArrayOf(
		    257, 263, 269, 271, 277, 281, 283, 293,
		)
		byteCache.clear()
	}
}
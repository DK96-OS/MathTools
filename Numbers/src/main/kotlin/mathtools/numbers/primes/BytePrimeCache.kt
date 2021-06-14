package mathtools.numbers.primes

/** A cache for really small prime numbers within the Byte range
   *  Developed by DK96-OS : 2021 */
open class BytePrimeCache : PrimeCacheBase(
	maxIndex = 30, 
	maxValue = Byte.MAX_VALUE.toInt(),
) {
	private var byteArray: ByteArray = byteArrayOf(31, 37, 41, 43)
	private val byteQueue = ArrayDeque<Byte>(4)
	
	internal val arraySize: Int get() = byteArray.size
	internal val queueSize: Int get() = byteQueue.size
	
	override fun highestCachedIndex(): Int = 9 + arraySize + queueSize
	
	/** Get a prime number by it's index */
	override fun getPrime(idx: Int)
	: Int = when (val arrayIndex = idx - 10) {
		in byteArray.indices -> byteArray[arrayIndex].toInt()
		in -10 until 0 -> when (idx) {
			0 -> 2
			in 1..3 -> 1 + 2 * idx			// 3, 5, 7
			in 4..5 -> 3 + 2 * idx			// 11, 13
			in 6..7 -> 5 + 2 * idx			// 17, 19
			else -> 6 * (idx - 4) - 1	// 23, 29
		}
		else -> {
			val overflow = arrayIndex - arraySize
			when {
				overflow < queueSize -> byteQueue[overflow].toInt()
				extendCache(idx) -> getPrime(idx)
				else -> throw IllegalStateException(
					"Cannot get prime at index: $idx")
			}
		}
	}
	
	/** Find new primes and add to the cache, up to the given index */
	private fun extendCache(toIndex: Int)
	: Boolean = if (toIndex in indexRange) {
		var primesRequired = toIndex - highestCachedIndex()
		if (primesRequired <= 0) false
		else if (primesRequired > 3 || queueSize + primesRequired > 4)
			consolidate(primesRequired) > 0
		else {		// Add to queue
			var prevPrime = (
				byteQueue.lastOrNull() ?: byteArray.last()).toInt()
			var testN = prevPrime + 2
			while (primesRequired > 0) {
				val prime = findPrime(testN) ?: break
				byteQueue.addLast(prime.toByte())
				testN = prime + if (prime - prevPrime	 == 2) 4 else 2
				prevPrime = prime
				primesRequired--
			}
			primesRequired == 0	// The correct amount of primes found
		}
	} else false

	override fun consolidate(add: Int): Int {
		val prevSize = arraySize + queueSize
		if (prevSize + add > indexRange.last + 1) return -1
		val oldArray = byteArray
		if (add == 0) byteArray = ByteArray(prevSize) {
			if (it < oldArray.size) oldArray[it] else byteQueue.removeFirst()
		} else {
			var prev = (byteQueue.lastOrNull() ?: oldArray.last()).toInt()
			byteArray = ByteArray(prevSize + add) {
				when {
					it < oldArray.size -> oldArray[it]
					it < prevSize -> byteQueue.removeFirst()
					else -> {
						val newPrime = findPrime(prev + 2)
							?: throw IllegalStateException("Next prime not found")
						prev = newPrime
						newPrime.toByte()
					}
				}
			}
		}
		return byteArray.last().toInt()
	}
	
	override fun clear() {
		byteQueue.clear()
		byteArray = byteArrayOf(31, 37, 41, 43)
	}
}

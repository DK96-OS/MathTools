package mathtools.numbers.primes

/** An array-based cache for Prime Numbers in the 8-bit integer range
 * @author DK96-OS : 2021 */
open class BytePrimeCache : PrimeCacheBase(
	maxIndex = MAX_INDEX,
	maxValue = MAX_PRIME,
) {
	private var byteArray: ByteArray? = null
	private val byteQueue = ArrayList<UByte>(8)

	internal val arraySize: Int get() = byteArray?.size ?: 0
	internal val queueSize: Int get() = byteQueue.size

    override fun highestCachedIndex()
		: Int = 15 + (byteArray?.size ?: 0) + byteQueue.size

    override fun getPrime(idx: Int): Int = when {
        idx < 16 -> initArray[idx].toInt()
        byteArray != null -> {
            val arrayIndex = idx - 16
            val arraySize = byteArray!!.size
            if (arrayIndex < arraySize) {
                val raw = byteArray!![arrayIndex].toInt()
                if (raw > 0) raw else raw + 256
            } else {
                val queueIndex = arrayIndex - arraySize
                if (queueIndex < byteQueue.size) byteQueue[queueIndex].toInt()
                else if (extendCache(idx)) getPrime(idx)
                else
                    throw IllegalStateException("Could not get prime at: $idx")
            }
        }
        idx - 16 in byteQueue.indices -> byteQueue[idx - 16 - arraySize].toInt()
        extendCache(idx) -> getPrime(idx)
        else -> throw IllegalStateException("Could not get prime at: $idx")
    }

	/** Find new primes and add to the cache, up to the given index
	  * @param toIndex The Index to extend the cache to */
	private fun extendCache(toIndex: Int)
	: Boolean = if (toIndex in indexRange) {
		var primesRequired = toIndex - highestCachedIndex()
		if (primesRequired <= 0) false
		else if (byteQueue.size + primesRequired > 8)
		    consolidate(primesRequired) > 0
		else {		// Add to queue
			var prevPrime = getPrime(highestCachedIndex())
			var testN = prevPrime + 2
			while (primesRequired > 0) {
				val prime = findPrime(testN) ?: break
				if (prime > maxValue) break
				byteQueue.add(prime.toUByte())
				testN = prime + if (prime - prevPrime == 2) 4 else 2
				prevPrime = prime
				primesRequired--
			}
			primesRequired == 0	// The correct amount of primes found
		}
	} else false

	override fun consolidate(add: Int): Int {
		if (add <= 0) throw IllegalArgumentException()
		val prevSize = arraySize + byteQueue.size
		if (prevSize + add > indexRange.last + 1) return -1
		val oldArray = byteArray
		var prev: Int = getPrime(highestCachedIndex())
		byteArray = ByteArray(prevSize + add) {
			when {
				it < (oldArray?.size ?: 0) -> oldArray!![it]
				it < prevSize -> byteQueue.removeFirst().toByte()
				else -> {
					prev = findPrime(prev + 2)
					       ?: throw IllegalStateException("Next prime not found")
					prev.toByte()
				}
			}
		}
		val highestPrime = byteArray!!.last()
		return if (highestPrime > -1)
			highestPrime.toInt() else 256 + highestPrime
	}

    override fun clear() {
        byteQueue.clear()
        byteArray = null
    }

	companion object {
		/** The largest index that can be stored */
		const val MAX_INDEX: Int = 53
		/** The largest prime value that can be stored */
		const val MAX_PRIME: Int = 251
	}
}
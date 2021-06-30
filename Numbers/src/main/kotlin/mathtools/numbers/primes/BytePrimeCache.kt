package mathtools.numbers.primes

/** A Lookup Table for Prime Numbers within the UByte Range, up to 251
   *  Developed by DK96-OS : 2021 */
open class BytePrimeCache : PrimeCacheBase(
	maxIndex = 53,  // This is the Index of the prime number 251
	maxValue = UByte.MAX_VALUE.toInt(),
) {
	private var byteArray: ByteArray = byteArrayOf(
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
    )
	private val byteQueue = ArrayDeque<UByte>(8)

	internal val arraySize: Int get() = byteArray.size
	internal val queueSize: Int get() = byteQueue.size

    override fun highestCachedIndex()
	: Int = -1 + byteArray.size + byteQueue.size

    override fun getPrime(idx: Int): Int = when {
        idx in byteArray.indices -> byteArray[idx].toUByte().toInt()
        idx <= highestCachedIndex() -> byteQueue[idx - arraySize].toInt()
        extendCache(idx) -> getPrime(idx)
        else -> throw IllegalStateException("Could not get prime at: $idx")
    }

	/** Find new primes and add to the cache, up to the given index
	  * @param toIndex The Index to extend the cache to */
	private fun extendCache(toIndex: Int)
	: Boolean = if (toIndex in indexRange) {
		var primesRequired = toIndex - highestCachedIndex()
		if (primesRequired <= 0) false
		else if (queueSize + primesRequired > 8)
		    consolidate(primesRequired) > 0
		else {		// Add to queue
			var prevPrime = (
				byteQueue.lastOrNull() ?: byteArray.last().toUByte()
			).toInt()
			var testN = prevPrime + 2
			while (primesRequired > 0) {
				val prime = findPrime(testN) ?: break
				if (prime > maxValue) break
				byteQueue.addLast(prime.toUByte())
				testN = prime + if (prime - prevPrime == 2) 4 else 2
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
			if (it < oldArray.size) oldArray[it]
			else byteQueue.removeFirst().toByte()
		} else {
			var prev: Int = (
			    byteQueue.lastOrNull() ?: oldArray.last().toUByte()
			 ).toInt()
			byteArray = ByteArray(prevSize + add) {
				when {
					it < oldArray.size -> oldArray[it]
					it < prevSize -> byteQueue.removeFirst().toByte()
					else -> {
						prev = findPrime(prev + 2)
							?: throw IllegalStateException("Next prime not found")
						if (prev > Byte.MAX_VALUE)
						    prev.toUByte().toByte() else prev.toByte()
					}
				}
			}
		}
		return byteArray.last().toUByte().toInt()
	}

    override fun clear() {
        byteQueue.clear()
        byteArray = byteArrayOf(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
        )
    }
}

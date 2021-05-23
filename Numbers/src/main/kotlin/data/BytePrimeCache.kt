package data

/** A cache for really small prime numbers within the Byte range 
   *  Developed by DK96-OS : 2021 */
open class BytePrimeCache {
	
	/** The range of indexed prime numbers serviced by this cache
		*  note: maximum index must be less than the index of 32767.  */
	val byteIndexRange: IntRange = 0 .. 30

	private var byteArray: ByteArray = byteArrayOf(31, 37, 41, 43, 47, 53)
	private val byteQueue = ArrayDeque<Byte>()
	
	internal val arraySize: Int get() = byteArray.size
	internal val queueSize: Int get() = byteQueue.size
	
	/** Get a prime number by it's index */
	open fun getPrime(idx: Int): Int = when (val arrayIndex = idx - 10) {
		in byteArray.indices -> byteArray[arrayIndex].toInt()
		in -10 until 0 -> when (idx) {
			0 -> 2
			in 1..3 -> 1 + 2 * idx			// 3, 5, 7
			in 4..5 -> 3 + 2 * idx			// 11, 13
			in 6..7 -> 5 + 2 * idx			// 17, 19
			else -> 6 * (idx - 4) - 1		// 23, 29
		}
		else -> {
			val arrayOverflow = arrayIndex - byteArray.size
			if (arrayOverflow < byteQueue.size) {
				val result = byteQueue[arrayOverflow].toInt()
				if (byteQueue.size > 7) combineCache()
				result
			} else if (extendCache(idx))
				byteQueue.last().toInt() 	// Newest numbers end up in the queue
			else
				throw IllegalStateException("Unable to calculate prime at index: $idx")
		}
	}
	
	/** Find new primes and add to the cache, up to the given index */
	private fun extendCache(toIndex: Int)
	: Boolean = if (toIndex in byteIndexRange) {
		var newPrimesRequired = toIndex - 9 - arraySize - queueSize
			// Resize Array if there are enough primes to justify
		if (queueSize > 9 || newPrimesRequired > 2 && queueSize >= 5)
			combineCache()
			// Start looking for primes
		var prevPrime = (
			byteQueue.lastOrNull() ?: byteArray.lastOrNull() ?: 29).toInt()
		var testN = prevPrime + 2
		while (newPrimesRequired > 0 && testN <= Byte.MAX_VALUE) {
			if (quickIsPrime(testN)) {
				byteQueue.addLast(testN.toByte())
				val primeDiff = testN - prevPrime		// Consecutive difference
				prevPrime = testN
				testN += if (primeDiff == 2) 4 else 2		// Primes come in pairs
				newPrimesRequired--
			} else testN += 2
		}
		newPrimesRequired == 0	// The correct amount of primes found
	} else false
	
	/** Skip checking if 2 is a factor, assume this number will be odd */
	protected open fun quickIsPrime(number: Int): Boolean {
		var prevPrime = 2
		for (i in 1 until byteIndexRange.last) {
			val testPrime = getPrime(i)
			if (number % testPrime == 0) return false
			if (testPrime > number.toFloat() / prevPrime) break
			prevPrime = testPrime
		}
		return true
	}
	
	/** Determine if this number is a prime */
	internal fun isPrime(number: Int): Boolean {
		if (number in 1 until 3) return true
		if (number < 0) return isPrime(-number)
		if (number % 2 == 0) return false
		//todo: check the cache size before calling
		return quickIsPrime(number)
	}
	
	/** Merge the Queue into the Array */
	private fun combineCache() {
		val oldArray = byteArray
		val arraySize = oldArray.size
		byteArray = ByteArray(arraySize + byteQueue.size) {
			if (it < arraySize) oldArray[it] else byteQueue.removeFirst()
		}
	}
	
	internal fun getArray(): ByteArray {
		combineCache()
		return byteArray
	}
	
}

package data

/** A cache for really small prime numbers within the Byte range 
   *  Developed by DK96-OS : 2021 */
open class BytePrimeCache : PrimeCacheInterface {

	/** Bytes are less than the index of 127.  */
	override val indexRange: IntRange = 0 .. 30
	override val maxValue = 127

	private var byteArray: ByteArray = byteArrayOf(31, 37, 41, 43, 47, 53)
	private val byteQueue = ArrayDeque<Byte>()
	
	internal val arraySize: Int get() = byteArray.size
	internal val queueSize: Int get() = byteQueue.size
	
	/** Get a prime number by it's index */
	override fun getPrime(idx: Int): Int = when (val arrayIndex = idx - 10) {
		in byteArray.indices -> byteArray[arrayIndex].toInt()
		in -10 until 0 -> when (idx) {
			0 -> 2
			in 1..3 -> 1 + 2 * idx			// 3, 5, 7
			in 4..5 -> 3 + 2 * idx			// 11, 13
			in 6..7 -> 5 + 2 * idx			// 17, 19
			else -> 6 * (idx - 4) - 1	// 23, 29
		}
		else -> {
			val arrayOverflow = arrayIndex - arraySize
			if (arrayOverflow < queueSize) {
				val result = byteQueue[arrayOverflow].toInt()
				if (queueSize > 7) consolidate(0)
				result
			} else if (extendCache(idx)) getPrime(idx)
			else
				throw IllegalStateException("Cannot get prime at index: $idx")
		}
	}
	
	/** Find new primes and add to the cache, up to the given index */
	private fun extendCache(toIndex: Int)
	: Boolean = if (toIndex in indexRange) {
		var newPrimesRequired = toIndex - 9 - arraySize - queueSize
			// Resize Array if there are enough primes to justify
		if (queueSize > 9 || newPrimesRequired > 8 ||
				newPrimesRequired > 2 && queueSize >= 4
		) {
			if (newPrimesRequired < 0) throw IllegalArgumentException()
			consolidate(newPrimesRequired)
			newPrimesRequired = 0
		} else {	// Just add to queue
			var prevPrime = (
				byteQueue.lastOrNull() ?: byteArray.last()).toInt()
			var testN = prevPrime + 2
			while (newPrimesRequired > 0) {
				val prime = findPrime(prevPrime, testN)
				if (prime == null) break
				byteQueue.addLast(prime.toByte())
				val primeDiff = prime - prevPrime		// Consecutive difference
				prevPrime = prime
				testN = prime + if (primeDiff == 2) 4 else 2
				newPrimesRequired--
			}
		}
		newPrimesRequired == 0	// The correct amount of primes found
	} else false

	override fun consolidate(add: Int): Int {
		val oldArray = byteArray
		val prevSize = arraySize + queueSize
		if (prevSize + add > indexRange.last + 1) 
			throw IllegalArgumentException("Invalid expansion parameter")
		if (add == 0) byteArray = ByteArray(prevSize) {
			if (it < oldArray.size) oldArray[it] else byteQueue.removeFirst()
		} else {
			var prev = (byteQueue.lastOrNull() ?: oldArray.last()).toInt()
			byteArray = ByteArray(prevSize + add) {
				if (it < oldArray.size) oldArray[it] 
				else if (it < prevSize) byteQueue.removeFirst()
				else {
					val newPrime = findPrime(prev)
					if (newPrime == null)
						throw IllegalStateException("Next prime not found")
					prev = newPrime
					newPrime.toByte()
				}
			}
		}
		return byteArray.last().toInt()
	}
	
	override fun clear() {
		byteQueue.clear()
		byteArray = byteArrayOf(31, 37, 41, 43, 47, 53)
	}
}

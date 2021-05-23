package data

/** A cache for small prime numbers, within Short range 
   *  Developed by DK96-OS : 2021 */
open class ShortPrimeCache : PrimeCacheInterface {

	override val indexRange = 0 until 3020
	override val maxValue = 32767
	
	/** Rely on BytePrimeCache for smallest primes */
	internal val byteCache = BytePrimeCache()
	
	/** The range of indexed prime numbers serviced by this cache
		*  note: maximum index must be less than the index of 32767.  */
	val shortIndexRange: IntRange = byteCache.indexRange.last + 1 .. 3020

	/** Storage for short primes */
	private var shortArray: ShortArray = shortArrayOf(131, 137, 139)
	private val shortQueue = ArrayDeque<Short>()

	internal val arraySize: Int get() = shortArray.size
	internal val queueSize: Int get() = shortQueue.size

	override fun getPrime(idx: Int): Int {
		if (idx in byteCache.indexRange) return byteCache.getPrime(idx)
		val shortIndex = idx - shortIndexRange.start
			// First check the Array
		if (shortIndex < arraySize) return shortArray[shortIndex].toInt()
			// Check if the Queue has the prime
		val overflow = shortIndex - arraySize
		if (overflow < queueSize) return shortQueue[overflow].toInt()
			// Extend the Queue
		if (extendCache(idx)) return getPrime(idx)
		else
			throw IllegalStateException("Unable to extend Prime cache!")
	}

	private fun extendCache(toIndex: Int)
	: Boolean = if (toIndex in shortIndexRange) {
		var newPrimesRequired = 1 + toIndex  - shortIndexRange.start - arraySize - queueSize
		if (queueSize > 9 || newPrimesRequired > 8 ||
			newPrimesRequired > 2 && queueSize >= 4
		) {
			if (newPrimesRequired < 0) throw IllegalArgumentException()
			consolidate(newPrimesRequired)
			true
		} else {	// Look for primes, append to queue
			var prevPrime = (
				shortQueue.lastOrNull() ?: shortArray.last()).toInt()
			var testN = prevPrime + 2
			while (newPrimesRequired > 0) {
				val prime = findPrime(prevPrime, testN)
				if (prime == null) break
				shortQueue.addLast(prime.toShort())
				val primeDiff = prime - prevPrime		// Consecutive difference
				prevPrime = prime
				testN = prime + if (primeDiff == 2) 4 else 2	// Primes in pairs
				newPrimesRequired--
			}
			newPrimesRequired == 0	// The correct amount of primes found
		}
	} else false

	override fun consolidate(add: Int): Int {
		val oldArray = shortArray
		val prevSize = oldArray.size + queueSize
		if (prevSize + add > indexRange.last + 1)
			throw IllegalArgumentException("Invalid expansion parameter")
		if (add == 0) shortArray = ShortArray(prevSize) {
			if (it < oldArray.size) oldArray[it] else shortQueue.removeFirst()
		} else {
			var prev = (shortQueue.lastOrNull() ?: oldArray.last()).toInt()
			shortArray = ShortArray(prevSize + add) {
				if (it < oldArray.size) oldArray[it]
				else if (it < prevSize) shortQueue.removeFirst()
				else {
					val newPrime = findPrime(prev)
					if (newPrime == null)
						throw IllegalStateException("Next Prime not found")
					prev = newPrime
					newPrime.toShort()
				}
			}
		}
		return shortArray.last().toInt()
	}
	
	override fun clear() {
		shortQueue.clear()
		shortArray = shortArrayOf(131, 137, 139)
		byteCache.clear()
	}
}

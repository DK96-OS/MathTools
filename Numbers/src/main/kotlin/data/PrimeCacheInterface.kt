package data

/** Common to Prime Number Caches */
interface PrimeCacheInterface {

	/** The range of indexed prime numbers serviced by this cache */
	val indexRange: IntRange
	
	val maxValue: Int
	
	/** Check if this index is in the cache's range */
	fun inRange(idx: Int): Boolean = idx in indexRange
	
	/** Obtain the Prime Number at the given index */
	fun getPrime(idx: Int): Int

	/** Merge the Cache data structures */
	fun consolidate(add: Int = 1): Int

	/** Clear saved prime numbers in this cache */
	fun clear()
	
	/** Skip checking if 2 is a factor, assume that number is odd */
	open fun quickIsPrime(number: Int): Boolean {
		var prevPrime = 2
		for (i in 1 until indexRange.last) {
			val testPrime = getPrime(i)
			if (number % testPrime == 0) return false
			if (testPrime > number.toFloat() / prevPrime) {
				val ratio = number.toFloat() / prevPrime
				println("IsPrime BREAK on $number : $testPrime > $ratio")
				break
			}
			prevPrime = testPrime
		}
		return true
	}
		
	/** Determine if this number is a prime */
	open fun isPrime(number: Int): Boolean {
		if (number in 1 until 3) return true
		if (number < 0) return isPrime(-number)
		if (number % 2 == 0) return false
		//todo: check the cache size before calling
		return quickIsPrime(number)
	}
	
	/** Try to find the next prime */
	fun findPrime(prevPrime: Int, testNum: Int? = null): Int? {
		var testN = if (testNum != null && 
				testNum == 4 + prevPrime || testNum == 2 + prevPrime)
			testNum else prevPrime + 2
		while (testN <= maxValue) {
			if (quickIsPrime(testN)) return testN else testN += 2
		}
		return null
	}
}

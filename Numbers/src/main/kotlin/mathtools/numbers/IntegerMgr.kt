package mathtools.numbers

/** Data Structure for an integer that may travel within a fixed range
 * Developed by DK96-OS : 2019 - 2021 */
class IntegerMgr(
    number: Int,
    max: Int,
    min: Int = 1,
    var isIncreasing: Boolean = true
) {
    val range: IntRange = min .. max
    
    var number: Int = number
    	private set

    /** Increases or Decreases by 1 based on isIncreasing */
    fun increment(): Int {
        if (isIncreasing) {
            if (number >= range.last) {
                isIncreasing = false
                number--
            } else number++
        } else {
            if (number <= range.first) {
                isIncreasing = true
                number++
            } else number--
        }
        return number
    }
    
    /** Returns the current value, then increments */
    fun getThenIncrement(): Int {
        val n0 = number
        increment()
        return n0
    }

    /** Randomly select any number in the given range or  */
    fun randomize(initRange: IntRange? = null) {
        number = initRange?.random() ?: range.random()
    }

    /** Set the current value exactly, or to the closest end of the range. */
    fun trySetN(n: Int) {
    	number = n.coerceIn(range.first, range.last)
    }

    override fun toString(): String = number.toString()

    operator fun plus(b: IntegerMgr) = number + b.number
    operator fun minus(b: IntegerMgr) = number - b.number
    operator fun times(b: IntegerMgr) = number * b.number
    operator fun div(b: IntegerMgr) = number / b.number

    operator fun plus(i: Int) = number + i
    operator fun minus(i: Int) = number - i
    operator fun times(i: Int) = number * i
    operator fun div(i: Int) = number / i

    operator fun plus(f: Float) = number + f
    operator fun minus(f: Float) = number - f
    operator fun times(f: Float) = number * f
    operator fun div(f: Float) = number / f

    operator fun compareTo(i: Int) = number - i
}

package mathtools.numbers.strict

/** Data Structure for integer restricted to fixed range
 * @author DK96-OS : 2019 - 2022 */
open class RestrictedInt(
    number: Int,
    max: Int,
    min: Int = 1,
    isIncreasing: Boolean = true
) {
    val range: IntRange = when {
        min <= max -> min .. max
        else -> max .. min
    }

    var value: Int = number.coerceIn(range)
        private set

    var isIncreasing: Boolean = isIncreasing
        private set

    constructor(
        number: Int,
        range: IntRange,
        isIncreasing: Boolean = true,
    ) : this(number, range.last, range.first, isIncreasing)

    /** Increases or Decreases by 1 based on state */
    open fun inc(): Int {
        if (isIncreasing) {
            if (value >= range.last) {
                isIncreasing = false
                value--
            } else value++
        } else {
            if (value <= range.first) {
                isIncreasing = true
                value++
            } else value--
        }
        return value
    }

    /** Returns the current value, then increments */
    open fun getAndInc(): Int {
        val n0 = value
        inc()
        return n0
    }

    /** Randomly select any number in the given range or  */
    open fun randomize(initRange: IntRange? = null): Int {
        value = when {
            initRange == null -> range.random()
            initRange.first > initRange.last -> range.random()
            initRange.first in range -> {
                if (initRange.last in range)
                    initRange.random()
                else
                    (initRange.first .. range.last).random()
            }
            initRange.last in range ->
                (range.first .. initRange.last).random()
            else -> range.random()
        }
        return value
    }

    /** Set the current value exactly, or to the closest end of the range. */
    open fun trySetN(n: Int) {
        value = n.coerceIn(range.first, range.last)
    }

    override fun toString(): String = value.toString()

    operator fun plus(b: RestrictedInt): Int = value + b.value
    operator fun minus(b: RestrictedInt): Int = value - b.value
    operator fun times(b: RestrictedInt): Int = value * b.value
    operator fun div(b: RestrictedInt) = value / b.value

    operator fun plus(b: RestrictedDigit): Int = value + b.digit
    operator fun minus(b: RestrictedDigit): Int = value - b.digit
    operator fun times(b: RestrictedDigit): Int = value * b.digit
    operator fun div(b: RestrictedDigit): Int = value / b.digit

    operator fun plus(i: Int): Int = value + i
    operator fun minus(i: Int): Int = value - i
    operator fun times(i: Int): Int = value * i
    operator fun div(i: Int): Int = value / i

    operator fun plus(f: Float): Float = value + f
    operator fun minus(f: Float): Float = value - f
    operator fun times(f: Float): Float = value * f
    operator fun div(f: Float): Float = value / f

    operator fun compareTo(i: Int): Int = value - i
}
package mathtools.numbers.strict

import kotlin.random.Random

/** A digit that counts up to a maximum value, and then resets to minimum
 * @author DK96-OS : 2019 - 2022 */
class RestrictedDigit(
    digit: Byte = 1,
    minimumValue: Byte = 0,
    maximumValue: Byte = 9,
) {
    val minimum: Byte = minimumValue.coerceIn(0, 9)

    val maximum: Byte = maximumValue.coerceAtLeast(minimum)

    var digit: Byte = digit.coerceIn(minimum, maximum)
        private set

    /** Increments the digit. Returns whether the loop has reset */
    fun inc(): Boolean = if (digit >= maximum) {
        digit = minimum
        true
    } else {
        digit++
        false
    }

    fun randomize(): Byte {
        digit = Random.nextInt(minimum.toInt(), maximum + 1).toByte()
        return digit
    }

    override fun toString(): String = digit.toString()

    operator fun plus(i: Int): Int = digit + i
    operator fun minus(i: Int): Int = digit - i
    operator fun times(i: Int): Int = digit * i
    operator fun div(i: Int): Int = digit / i

    operator fun plus(f: Float): Float = digit + f
    operator fun minus(f: Float): Float = digit - f
    operator fun times(f: Float): Float = digit * f
    operator fun div(f: Float): Float = digit / f

    operator fun compareTo(i: Int): Int = digit - i

}
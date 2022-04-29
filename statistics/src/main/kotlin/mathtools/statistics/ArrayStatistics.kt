package mathtools.statistics

import kotlin.math.sqrt

/* The Standard Deviation functions */

/** Calculate the standard deviation of an 8 bit Byte Array
 * @param array The Array containing the data
 * @param mean The Mean (average) value of the data in the Array
 * @return A Double representing the Standard Deviation
 */
fun calculateSDev(
    array: ByteArray,
    mean: Double,
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toDouble() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 16 bit Short Array
 * @param array The Array containing the data
 * @param mean The Mean (average) value of the data in the Array
 * @return A Double representing the Standard Deviation
 */
fun calculateSDev(
    array: ShortArray,
    mean: Double
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toDouble() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 32 bit Integer Array
 * @param array The Array containing the data
 * @param mean The Mean (average) value of the data in the Array
 * @return A Double representing the Standard Deviation
 */
fun calculateSDev(
    array: IntArray,
    mean: Double
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toDouble() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 64 bit Long Array
 * @param array The Array containing the data
 * @param mean The Mean (average) value of the data in the Array
 * @return A Double representing the Standard Deviation
 */
fun calculateSDev(
    array: LongArray,
    mean: Double
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toDouble() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 32 bit Float Array
 * @param array The Array containing the data
 * @param mean The Mean (average) value of the data in the Array
 * @return A Double representing the Standard Deviation
 */
fun calculateSDev(
    array: FloatArray,
    mean: Double
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i] - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 64 bit Double Array
 * @param array The Array containing the data
 * @param mean The Mean (average) value of the data in the Array
 * @return A Double representing the Standard Deviation
 */
fun calculateSDev(
    array: DoubleArray,
    mean: Double
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i] - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}
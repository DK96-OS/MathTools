package mathtools.statistics

import kotlin.math.sqrt

/* This file contains Statistics functions for Number Arrays
*/
/* The Mean value functions */

/** Calculate the mean value of a 64 bit Double Array */
fun calculateMean(array: DoubleArray)
    : Double = if (array.isEmpty())
    0.0
else array.sum() / array.size

/** Calculate the mean value of a 32 bit Float Array */
fun calculateMean(array: FloatArray)
    : Double = if (array.isEmpty())
    0.0
else array.sum().toDouble() / array.size
    // todo: Use double to count the sum


/* The Standard Deviation functions */

/** Calculate the standard deviation of an 8 bit Byte Array */
fun calculateSDev(
    array: ByteArray, mean: Double = ArrayStatistics.calculateMean(array)
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toFloat() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 16 bit Short Array */
fun calculateSDev(
    array: ShortArray, mean: Double = ArrayStatistics.calculateMean(array)
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toFloat() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 32 bit Int Array */
fun calculateSDev(
    array: IntArray, mean: Double = ArrayStatistics.calculateMean(array)
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toFloat() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 64 bit Long Array */
fun calculateSDev(
    array: LongArray,
    mean: Double = ArrayStatistics.calculateMean(array)
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i].toDouble() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 32 bit Float Array */
fun calculateSDev(
    array: FloatArray, mean: Double = calculateMean(array)
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i] - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 64 bit Double Array */
fun calculateSDev(
    array: DoubleArray, mean: Double = calculateMean(array)
) : Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    for (i in array.indices) {
        val deviation = array[i] - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}
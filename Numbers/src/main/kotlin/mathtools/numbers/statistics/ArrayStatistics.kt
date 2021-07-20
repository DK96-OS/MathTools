package mathtools.numbers.statistics

/* This file contains Statistics functions for Number Arrays */


/* The Mean value functions */

/** Calculate the mean value of an 8 bit Byte Array */
fun calculateMean(array:ByteArray)
: Double = if (array.isEmpty()) 
    0.0 else array.sum().toDouble() / array.size

/** Calculate the mean value of a 16 bit Short Array */
fun calculateMean(array:ShortArray)
: Double = if (array.isEmpty()) 
    0.0 else array.sum().toDouble() / array.size

/** Calculate the mean value of a 32 bit Integer Array */
fun calculateMean(array:IntArray)
: Double = if (array.isEmpty()) 
    0.0 else array.sum().toDouble() / array.size

/** Calculate the mean value of a 64 bit Long Array */
fun calculateMean(array:LongArray)
: Double = if (array.isEmpty()) 
    0.0 else array.sum().toDouble() / array.size

/** Calculate the mean value of a 64 bit Double Array */
fun calculateMean(array:DoubleArray)
: Double = if (array.isEmpty()) 
    0.0 else array.sum() / array.size

/** Calculate the mean value of a 32 bit Float Array */
fun calculateMean(array:FloatArray)
: Double = if (array.isEmpty()) 
    0.0 else array.sum().toDouble() / array.size


/* The Standard Deviation functions */

/** Calculate the standard deviation of an 8 bit Byte Array */
fun calculateSDev(
    array:ByteArray, mean:Double = calculateMean(array))
: Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0f
    var deviation = 0f
    val meanFloat = mean.toFloat()
    for (i in array.indices) {
        deviation = array[i].toFloat() - meanFloat
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum.toDouble() / (array.size - 1))
}

/** Calculate the standard deviation of a 16 bit Short Array */
fun calculateSDev(
    array:ShortArray, mean:Double = calculateMean(array))
: Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0f
    var deviation = 0f
    val meanFloat = mean.toFloat()
    for (i in array.indices) {
        deviation = array[i].toFloat() - meanFloat
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum.toDouble() / (array.size - 1))
}

/** Calculate the standard deviation of a 32 bit Int Array */
fun calculateSDev(
    array:IntArray, mean:Double = calculateMean(array))
: Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    var deviation = 0.0
    for (i in array.indices) {
        deviation = array[i].toDouble() - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}


/** Calculate the standard deviation of a 64 bit Long Array */
fun calculateSDev(
    array:LongArray, mean:Double = calculateMean(array))
: Double {
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
    array:FloatArray, mean:Double = calculateMean(array))
: Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    val meanFloat = mean.toFloat()
    for (i in array.indices) {
        val deviation = (array[i] - meanFloat).toDouble()
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

/** Calculate the standard deviation of a 64 bit Double Array */
fun calculateSDev(
    array:DoubleArray, mean:Double = calculateMean(array))
: Double {
    if (array.size < 3) return 0.0
    var varianceSum = 0.0
    var deviation = 0.0
    for (i in array.indices) {
        deviation = array[i] - mean
        varianceSum += deviation * deviation
    }
	return sqrt(varianceSum / (array.size - 1))
}

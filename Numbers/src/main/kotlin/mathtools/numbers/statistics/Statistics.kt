package mathtools.numbers.statistics

import kotlin.math.sqrt

/** Key Statistics Functions */
object Statistics {

	/** Return true once every x times this function is called */
	fun oneIn(x:Int): Boolean = when {
		x > 1 -> Math.random() < 0.99999999f / x
		x == 1 -> true
		else -> throw IllegalArgumentException()
	}

	/** Sums the numbers in the list and divides by the count */
	fun calculateMeanLong(list:List<Long>)
	: Float = if (list.isEmpty()) 0f else list.sum() / list.size.toFloat()

	fun calculateMeanFloat(list:List<Float>)
	: Float = if (list.isEmpty()) 0f else list.sum() / list.size.toFloat()

    inline fun <T : Number> calculateMean(list:List<T>)
    : Double = if (list.isEmpty()) 0.0 else listSum(list) / list.size

    inline fun <T : Number> calculateSDev(
        list:List<T>, mean:Double = calculateMean(list))
    : Double {
        if (list.size <3) return 0.0
        var varianceSum = 0.0
        for (i in list.indices) {
            val dev = list[i].toDouble() - mean
            varianceSum += dev * dev
        }
        return sqrt(varianceSum / (list.size - 1))
    }

	/** Compute the Standard Deviation for a list of Long */
	fun calculateStandardDeviation(
		list:List<Long>, mean:Float = calculateMeanLong(list)) 
    : Float {
		if (list.size < 3) return mean
		val varianceSum = list.sumOf {
			val dev = it.toDouble() - mean
			dev * dev
		}
		return sqrt(varianceSum / (list.size - 1)).toFloat()
	}

	/** Compute the Standard Deviation for a list of Float */
	fun calculateStandardDevFloat(
		list:List<Float>, mean:Float = calculateMeanFloat(list)) 
    : Float {
		val nMinus1 = list.size - 1
		if (nMinus1 < 2) return mean
		val varianceSum = list.sumOf {
			val dev = it.toDouble() - mean
			dev * dev
		}
		return sqrt(varianceSum / nMinus1).toFloat()
	}

    /** Determine which side of the mean most values are found */
    fun calculateSkewLong(
        list:List<Long>, mean:Float, sDev:Float) {}

}

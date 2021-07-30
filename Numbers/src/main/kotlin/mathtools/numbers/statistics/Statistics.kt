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

    fun <T : Number> calculateMean(list:List<T>)
    : Double = if (list.isEmpty()) 0.0 else listSum(list) / list.size

    fun <T : Number> calculateSDev(
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

	/** Sums the numbers in the list and divides by the count */
	@Deprecated(
	    message = "Use the common List Number functions",
	    replaceWith = ReplaceWith("calculateMean(list)"),
	    level = DeprecationLevel.WARNING)
	fun calculateMeanLong(list:List<Long>)
	: Float = if (list.isEmpty()) 0f else list.sum() / list.size.toFloat()

    @Deprecated(
	    message = "Use the common List Number functions",
        replaceWith = ReplaceWith("calculateMean(list)"),
        level = DeprecationLevel.WARNING)
	fun calculateMeanFloat(list:List<Float>)
	: Float = if (list.isEmpty()) 0f else list.sum() / list.size.toFloat()

	/** Compute the Standard Deviation for a list of Long */
	@Deprecated(
	    message = "Use the common List Number functions",
	    replaceWith = ReplaceWith("calculateSDev"),
        level = DeprecationLevel.WARNING)
	fun calculateStandardDeviation(
		list:List<Long>, mean:Float = calculateMean(list).toFloat()) 
    : Float = calculateSDev(list, mean.toDouble()).toFloat()

	/** Compute the Standard Deviation for a list of Float */
	@Deprecated(
	    message = "Use the common List Number functions",
	    replaceWith = ReplaceWith("calculateSDev"),
	    level = DeprecationLevel.WARNING)
	fun calculateStandardDevFloat(
		list:List<Float>, mean:Float = calculateMean(list).toFloat()) 
    : Float = calculateSDev(list, mean.toDouble()).toFloat()

}

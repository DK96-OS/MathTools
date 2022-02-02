package mathtools.numbers.statistics

import mathtools.numbers.listtypes.listSum
import kotlin.math.sqrt

/** Key Statistics Functions
 * Developed by DK96-OS : 2022 */
object Statistics {

	/** Return true once every x times this function is called */
	fun oneIn(
		x: Int
	) : Boolean = when {
		x > 1 -> Math.random() < 0.9999999f / x
		x == 1 -> true
		else -> throw IllegalArgumentException()
	}

	/** Calculate the mean (average) value of the elements in a Number List */
    fun <T : Number> calculateMean(
	    list: List<T>
    ) : Double = when (list.size) {
		0 -> 0.0
	    1 -> list[0].toDouble()
	    in 2 .. 50 -> listSum(list) / list.size
	    else -> // Prefer large sum
		    listSum(list) / list.size
    }

	/** Calculate the standard deviation of the elements in a Number List
	 * @param list The elements of a distribution
	 * @param mean The mean value of the distribution. Uses calculateMean() as default */
    fun <T : Number> calculateSDev(
        list: List<T>,
        mean: Double = calculateMean(list)
    ) : Double {
        if (list.size < 2) return 0.0
        var varianceSum = 0.0
        for (i in list.indices) {
            val dev = list[i].toDouble() - mean
            varianceSum += dev * dev
        }
        return sqrt(varianceSum / (list.size - 1))
    }

	/* Deprecated Functions */

	/** Sums the numbers in the list and divides by the count */
	@Deprecated(
	    message = "Use the common List Number functions",
	    replaceWith = ReplaceWith("calculateMean(list)"),
	    level = DeprecationLevel.ERROR)
	fun calculateMeanLong(
		list: List<Long>
	) : Float = if (list.isEmpty()) 0f else list.sum() / list.size.toFloat()

    @Deprecated(
	    message = "Use the common List Number functions",
        replaceWith = ReplaceWith("calculateMean(list)"),
        level = DeprecationLevel.ERROR)
	fun calculateMeanFloat(list:List<Float>)
	: Float = if (list.isEmpty()) 0f else list.sum() / list.size.toFloat()

	/** Compute the Standard Deviation for a list of Long */
	@Deprecated(
	    message = "Use the common List Number functions",
	    replaceWith = ReplaceWith("calculateSDev"),
        level = DeprecationLevel.ERROR)
	fun calculateStandardDeviation(
		list:List<Long>, mean:Float = calculateMean(list).toFloat()) 
    : Float = calculateSDev(list, mean.toDouble()).toFloat()

	/** Compute the Standard Deviation for a list of Float */
	@Deprecated(
	    message = "Use the common List Number functions",
	    replaceWith = ReplaceWith("calculateSDev"),
	    level = DeprecationLevel.ERROR)
	fun calculateStandardDevFloat(
		list:List<Float>, mean:Float = calculateMean(list).toFloat()) 
    : Float = calculateSDev(list, mean.toDouble()).toFloat()

}

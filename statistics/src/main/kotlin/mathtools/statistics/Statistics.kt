package mathtools.statistics

import mathtools.lists.listSum
import kotlin.math.sqrt

/** Key Statistics Functions
 * @author DK96-OS : 2022 */
object Statistics {

	/** Return true once every x times this function is called */
	@Deprecated(
		"Use Probability class"
	)
	fun oneIn(
		x: Int,
	) : Boolean = if (1 < x)
		x * Math.random() < 0.9999999f
	else
		1 == x

	/** Calculate the mean (average) value of the elements in a Number List
	 * 	 */
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

}
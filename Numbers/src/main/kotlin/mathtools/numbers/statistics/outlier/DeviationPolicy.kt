package mathtools.numbers.statistics.outlier

import mathtools.lists.DoubleList
import mathtools.lists.LongList
import mathtools.numbers.statistics.DistributionCharacteristics
import kotlin.math.roundToLong

/** An outlier policy that considers an element an outlier
 * when it's value is outside a maximum number of standard deviations
 * from the mean value of the distribution.
 * Developed by DK96-OS : 2022
 * @param maxDeviations the number of standard deviations from the mean
 * @param upperOutliers check outliers greater than the mean
 * @param lowerOutliers check outliers less than the mean
 */
class DeviationPolicy(
    val maxDeviations: Double,
    val upperOutliers: Boolean = true,
    val lowerOutliers: Boolean = true,
) : OutlierPolicy {

    init {
        if (maxDeviations <= 0.0
            || maxDeviations.isInfinite()
            || maxDeviations.isNaN()
            || maxDeviations == Double.MAX_VALUE
            || maxDeviations == Double.MIN_VALUE
        ) throw IllegalArgumentException(
            "Max Deviations must be finite and greater than zero"
        )
        if (!(upperOutliers || lowerOutliers))
            throw IllegalArgumentException(
                "Select a region to search for outliers"
            )
    }

    /** Obtain the outlier boundary values */
    private val DistributionCharacteristics.bounds
        : Pair<Double, Double> get() {
        val upperBound = valueAtDeviation(maxDeviations)
        val lowerBound = valueAtDeviation(-maxDeviations)
        return lowerBound to upperBound
    }

    override fun removeOutliersDouble(
        mutableList: MutableList<Double>,
        distribution: DistributionCharacteristics,
        maxOutliers: UByte,
    ) : List<Double> {
        if (maxOutliers < 1u) return emptyList()
        return when {
            upperOutliers && lowerOutliers -> {
                val (lowerBound, upperBound) = distribution.bounds
                val outlierIdx = DoubleList.findOutOfBounds(
                    mutableList, lowerBound, upperBound
                )
                val nOutlierOverflow = outlierIdx.size - maxOutliers.toInt()
                if (nOutlierOverflow < 1)
                    DoubleList.removeByIndices(mutableList, outlierIdx)
                else
                    DoubleList.removeByIndices(
                        mutableList, outlierIdx.sortedBy {
                            val number = mutableList[it] - distribution.mean
                            if (number >= 0) number else -number
                        }.drop(nOutlierOverflow)
                            .sorted()
                    )
            }
            upperOutliers -> {
                val outlierIdx = DoubleList.findGreaterThan(
                    mutableList,
                    distribution.valueAtDeviation(maxDeviations)
                )
                val nOutlierOverflow = outlierIdx.size - maxOutliers.toInt()
                if (nOutlierOverflow < 1)
                    DoubleList.removeByIndices(mutableList, outlierIdx)
                else
                    DoubleList.removeByIndices(
                        mutableList, outlierIdx.sortedBy {
                            val number = mutableList[it] - distribution.mean
                            if (number >= 0) number else -number
                        }.drop(nOutlierOverflow)
                            .sorted()
                    )
            }
            lowerOutliers -> {
                val outlierIdx = DoubleList.findLessThan(
                    mutableList,
                    distribution.valueAtDeviation(-maxDeviations)
                )
                val nOutlierOverflow = outlierIdx.size - maxOutliers.toInt()
                if (nOutlierOverflow < 1)
                    DoubleList.removeByIndices(mutableList, outlierIdx)
                else
                    DoubleList.removeByIndices(
                        mutableList, outlierIdx.sortedBy {
                            val number = mutableList[it] - distribution.mean
                            if (number >= 0) number else -number
                        }.drop(nOutlierOverflow)
                            .sorted()
                    )
            }
            else -> emptyList()
        }
    }

    override fun removeOutliersLong(
        mutableList: MutableList<Long>,
        distribution: DistributionCharacteristics,
        maxOutliers: UByte
    ) : List<Long> {
	    if (maxOutliers < 1u) return emptyList()
        return when {
            upperOutliers && lowerOutliers -> {
                val (b0, b1) = distribution.bounds
                val outlierIdx = LongList.findOutOfRange(
                    mutableList, b0.roundToLong() .. b1.roundToLong()
                )
                val nOutlierOverflow = outlierIdx.size - maxOutliers.toInt()
                if (nOutlierOverflow < 1)
                    LongList.removeByIndices(mutableList, outlierIdx)
                else
                    LongList.removeByIndices(
                        mutableList, outlierIdx.sortedBy {
                            val number = mutableList[it] - distribution.mean
                            if (number >= 0) number else -number
                        }.drop(nOutlierOverflow)
                            .sorted()
                    )
            }
            upperOutliers -> {
                val outlierIdx = LongList.findGreaterThan(
                    mutableList,
                    distribution.valueAtDeviation(maxDeviations).roundToLong()
                )
                val nOutlierOverflow = outlierIdx.size - maxOutliers.toInt()
                if (nOutlierOverflow < 1)
                    LongList.removeByIndices(mutableList, outlierIdx)
                else
                    LongList.removeByIndices(
                        mutableList, outlierIdx.sortedBy {
                            val number = mutableList[it] - distribution.mean
                            if (number >= 0) number else -number
                        }.drop(nOutlierOverflow)
                            .sorted()
                    )
            }
            lowerOutliers -> {
                val outlierIdx = LongList.findLessThan(
                    mutableList,
                    distribution.valueAtDeviation(-maxDeviations).roundToLong()
                )
                val nOutlierOverflow = outlierIdx.size - maxOutliers.toInt()
                if (nOutlierOverflow < 1)
                    LongList.removeByIndices(mutableList, outlierIdx)
                else
                    LongList.removeByIndices(
                        mutableList, outlierIdx.sortedBy {
                            val number = mutableList[it] - distribution.mean
                            if (number >= 0) number else -number
                        }.drop(nOutlierOverflow)
                            .sorted()
                    )
            }
            else -> throw IllegalStateException()
        }
    }

    override fun identifyOutliers(
        array: DoubleArray,
        distribution: DistributionCharacteristics
    ) : List<Int> = when {
        upperOutliers && lowerOutliers -> {
            val (lowerBound, upperBound) = distribution.bounds
	        array.mapIndexed { index, d ->
		        if (d < lowerBound || upperBound < d) index else null
	        }.filterNotNull()
        }
        upperOutliers -> {
            val upperBound = distribution.valueAtDeviation(maxDeviations)
	        array.mapIndexed { index, d ->
		        if (upperBound < d) index else null
	        }.filterNotNull()
        }
        lowerOutliers -> {
            val lowerBound = distribution.valueAtDeviation(-maxDeviations)
	        array.mapIndexed { index, d ->
		        if (d < lowerBound) index else null
	        }.filterNotNull()
        }
        else -> throw IllegalStateException()
    }

    override fun identifyOutliers(
        array: LongArray,
        distribution: DistributionCharacteristics
    ) : List<Int> = when {
        upperOutliers && lowerOutliers -> {
            val (b0, b1) = distribution.bounds
            val lowerBound = b0.roundToLong()
            val upperBound = b1.roundToLong()
	        array.mapIndexed { index, d ->
		        if (d < lowerBound || upperBound < d) index else null
	        }.filterNotNull()
        }
        upperOutliers -> {
            val upperBound = distribution.valueAtDeviation(maxDeviations)
	        array.mapIndexed { index, d ->
		        if (upperBound < d) index else null
	        }.filterNotNull()
        }
        lowerOutliers -> {
            val lowerBound = distribution.valueAtDeviation(-maxDeviations)
	        array.mapIndexed { index, d ->
		        if (d < lowerBound) index else null
	        }.filterNotNull()
        }
        else -> throw IllegalStateException()
    }

}
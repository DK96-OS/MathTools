package mathtools.numbers.statistics.outlier

import mathtools.numbers.listtypes.DoubleList
import mathtools.numbers.listtypes.LongList
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
class StandardDeviationPolicy(
    val maxDeviations: Double,
    val upperOutliers: Boolean = true,
    val lowerOutliers: Boolean = true,
) : OutlierPolicy {

    override fun removeOutliersDouble(
        mutableList: MutableList<Double>,
        distribution: DistributionCharacteristics,
        maxOutliers: UByte,
    ) : List<Double> = when {
        maxOutliers == (0u).toUByte() -> emptyList()
        upperOutliers && lowerOutliers -> {
            val outlierIdx = DoubleList.findOutOfBounds(
                mutableList,
                distribution.valueAtDeviation(maxDeviations),
                distribution.valueAtDeviation(-maxDeviations)
            )
            if (outlierIdx.size <= maxOutliers.toInt())
                DoubleList.removeByIndices(mutableList, outlierIdx)
            else
                TODO()
        }
        upperOutliers -> {
            val outlierIdx = DoubleList.findGreaterThan(
                mutableList, distribution.valueAtDeviation(maxDeviations)
            )
            if (outlierIdx.size <= maxOutliers.toInt())
                DoubleList.removeByIndices(mutableList, outlierIdx)
            else
                TODO()
        }
        lowerOutliers -> {
            val outlierIdx = DoubleList.findLessThan(
                mutableList, distribution.valueAtDeviation(-maxDeviations)
            )
            if (outlierIdx.size <= maxOutliers.toInt())
                DoubleList.removeByIndices(mutableList, outlierIdx)
            else
	            TODO()
        }
        else -> emptyList()
    }

    override fun removeOutliersLong(
        mutableList: MutableList<Long>,
        distribution: DistributionCharacteristics,
        maxOutliers: UByte
    ) : List<Long> = when {
        maxOutliers == (0u).toUByte() -> emptyList()
        upperOutliers && lowerOutliers -> {
            val lowerBound = distribution.valueAtDeviation(maxDeviations)
                .roundToLong()
            val upperBound = distribution.valueAtDeviation(-maxDeviations)
                .roundToLong()
            val outlierIdx = LongList.findOutOfRange(
                mutableList,
                lowerBound..upperBound
            )
            if (outlierIdx.size <= maxOutliers.toInt())
                LongList.removeByIndices(mutableList, outlierIdx)
            else
	            TODO()
        }
        upperOutliers -> {
            val outlierIdx = LongList.findGreaterThan(
                mutableList,
                distribution.valueAtDeviation(maxDeviations).roundToLong()
            )
            if (outlierIdx.size <= maxOutliers.toInt())
                LongList.removeByIndices(mutableList, outlierIdx)
            else
				TODO()
        }
        lowerOutliers -> {
            val outlierIdx = LongList.findLessThan(
                mutableList,
                distribution.valueAtDeviation(-maxDeviations).roundToLong()
            )
            if (outlierIdx.size <= maxOutliers.toInt())
                LongList.removeByIndices(mutableList, outlierIdx)
            else
				TODO()
        }
        else -> emptyList()
    }

    override fun identifyOutliers(
        array: DoubleArray,
        distribution: DistributionCharacteristics
    ) : List<Int> {
        TODO()
    }

    override fun identifyOutliers(
        array: LongArray,
        distribution: DistributionCharacteristics
    ) : List<Int> {
        TODO()
    }

}
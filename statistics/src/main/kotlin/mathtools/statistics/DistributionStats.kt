package mathtools.statistics

import mathtools.statistics.Statistics.calculateMean
import mathtools.statistics.Statistics.calculateSDev

/** The key statistical parameters of a distribution.
 * @author DK96-OS : 2022
 */
class DistributionStats internal constructor(
    val mean: Double,
    val standardDeviation: Double,
    val min: Double,
    val max: Double,
    val count: Int,
) {

    constructor(
        mean: Double,
        sDev: Double,
        min: Number,
        max: Number,
        count: Int,
    ) : this(mean, sDev, min.toDouble(), max.toDouble(), count)

    /** Calculate the value at a specific number of deviations from mean
     */
    fun valueAtDeviation(nDevs: Double)
        : Double = mean + nDevs * standardDeviation

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DistributionStats

        if (mean != other.mean) return false
        if (standardDeviation != other.standardDeviation) return false
        if (min != other.min) return false
        if (max != other.max) return false
        if (count != other.count) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mean.hashCode()
        result = 31 * result + standardDeviation.hashCode()
        result = 31 * result + min.hashCode()
        result = 31 * result + max.hashCode()
        result = 31 * result + count
        return result
    }

    override fun toString(): String = buildString {
        append("Mean: $mean, ")
        append("SD: $standardDeviation, ")
        append("Min: $min, ")
        append("Max: $max, ")
        append("Count: $count")
    }

    companion object {
        
        /** Obtain the [DistributionStats] of a Number List.
         * @return A DistributionStats instance, or null if list is empty.
         */
        fun <T : Number> process(
            list: List<T>
        ) : DistributionStats? {
            when (list.size) {
                0 -> return null
                1 -> {
                    val first = list[0]
                    return DistributionStats(
                        first.toDouble(), 0.0, first, first, 1
                    )
                }
            }
            val mean = calculateMean(list)
            val sDev = calculateSDev(list, mean)
            when (list.first()) {
                is Float -> {
                    val typedList = list as List<Float>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!,
                        typedList.size,
                    )
                }
                is Double -> {
                    val typedList = list as List<Double>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!! as Number,
                        typedList.maxOrNull()!!,
                        typedList.size,
                    )
                }
                is Long -> {
                    val typedList = list as List<Long>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!,
                        typedList.size,
                    )
                }
                is Int -> {
                    val typedList = list as List<Int>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!,
                        typedList.size,
                    )
                }
                is Short -> {
                    val typedList = list as List<Short>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!,
                        typedList.size,
                    )
                }
                is Byte -> {
                    val typedList = list as List<Byte>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!,
                        typedList.size,
                    )
                }
                else -> throw IllegalArgumentException(
                    "Unsupported Number Type"
                )
            }
        }

        /** Obtain the [DistributionStats] of an array */
        fun process(array: ByteArray)
            : DistributionStats? = DistributionStatistics.process(array)

        /** Obtain the [DistributionStats] of an array */
        fun process(array: ShortArray)
            : DistributionStats? = DistributionStatistics.process(array)

        /** Obtain the [DistributionStats] of an array */
        fun process(array: IntArray)
            : DistributionStats? = DistributionStatistics.process(array)

        /** Obtain the [DistributionStats] of an array */
        fun process(array: LongArray)
            : DistributionStats? = DistributionStatistics.process(array)

        /** Obtain the [DistributionStats] of an array */
        fun process(array: FloatArray)
            : DistributionStats? = DistributionStatistics.process(array)

        /** Obtain the [DistributionStats] of an array */
        fun process(array: DoubleArray)
            : DistributionStats? = DistributionStatistics.process(array)
    }

}

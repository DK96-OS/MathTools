package mathtools.statistics

import mathtools.statistics.Statistics.calculateMean
import mathtools.statistics.Statistics.calculateSDev

/** The key statistical parameters of a distribution
 * Developed by DK96-OS : 2022 */
class DistributionStats internal constructor(
    val mean: Double,
    val standardDeviation: Double,
    val min: Double,
    val max: Double,
) {

    constructor(
        mean: Double, sDev: Double, min: Number, max: Number,
    ) : this(mean, sDev, min.toDouble(), max.toDouble())

    /** Calculate the value at a specific number of deviations from mean */
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

        return true
    }

    override fun hashCode(): Int {
        var result = mean.hashCode()
        result = 31 * result + standardDeviation.hashCode()
        result = 31 * result + min.hashCode()
        result = 31 * result + max.hashCode()
        return result
    }

    override fun toString(): String = buildString {
        append("Mean: $mean, ")
        append("SD: $standardDeviation, ")
        append("Min: $min, ")
        append("Max: $max")
    }

    companion object {
        /** Determine the DistributionCharacteristics of the given List */
        inline fun <reified T : Number> process(
            list: List<T>
        ) : DistributionStats? {
            when (list.size) {
                0 -> return null
                1 -> {
                    val first = list[0]
                    return DistributionStats(
                        first.toDouble(), 0.0, first, first
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
                        typedList.maxOrNull()!!
                    )
                }
                is Double -> {
                    val typedList = list as List<Double>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!! as Number,
                        typedList.maxOrNull()!!
                    )
                }
                is Long -> {
                    val typedList = list as List<Long>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                is Int -> {
                    val typedList = list as List<Int>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                is Short -> {
                    val typedList = list as List<Short>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                is Byte -> {
                    val typedList = list as List<Byte>
                    return DistributionStats(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                else -> throw IllegalArgumentException(
                    "Invalid Data Type Received: ${T::class.java.name}")
            }
        }

        fun process(array: ByteArray)
            : DistributionStats? = if (array.size <= 2)
            null
        else DistributionStats(
	        ArrayStatistics.calculateMean(array), calculateSDev(array),
	        array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: ShortArray)
            : DistributionStats? = if (array.size <= 2)
            null
        else DistributionStats(
            ArrayStatistics.calculateMean(array), calculateSDev(array),
            array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: IntArray)
            : DistributionStats? = if (array.size <= 2)
            null
        else DistributionStats(
	        calculateMean(array), calculateSDev(array),
	        array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: LongArray)
            : DistributionStats? = if (array.size <= 2)
            null
        else DistributionStats(
	        calculateMean(array), calculateSDev(array),
	        array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: FloatArray)
            : DistributionStats? = if (array.size <= 2)
            null
        else DistributionStats(
	        calculateMean(array), calculateSDev(array),
	        array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: DoubleArray)
            : DistributionStats? = if (array.size <= 2)
            null
        else DistributionStats(
	        calculateMean(array), calculateSDev(array),
	        array.minOrNull()!!, array.maxOrNull()!!
        )
    }
}
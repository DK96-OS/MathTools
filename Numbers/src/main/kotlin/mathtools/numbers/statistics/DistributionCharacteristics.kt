package mathtools.numbers.statistics

import mathtools.numbers.statistics.Statistics.calculateMean
import mathtools.numbers.statistics.Statistics.calculateSDev

/** The key statistical parameters of a distribution
 * Developed by DK96-OS : 2022 */
class DistributionCharacteristics internal constructor(
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

        other as DistributionCharacteristics

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
        ) : DistributionCharacteristics? {
            when (list.size) {
                0 -> return null
                1 -> {
                    val first = list[0]
                    return DistributionCharacteristics(
                        first.toDouble(), 0.0, first, first
                    )
                }
            }
            val mean = calculateMean(list)
            val sDev = calculateSDev(list, mean)
            when (list.first()) {
                is Float -> {
                    val typedList = list as List<Float>
                    return DistributionCharacteristics(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                is Double -> {
                    val typedList = list as List<Double>
                    return DistributionCharacteristics(
                        mean, sDev,
                        typedList.minOrNull()!! as Number,
                        typedList.maxOrNull()!!
                    )
                }
                is Long -> {
                    val typedList = list as List<Long>
                    return DistributionCharacteristics(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                is Int -> {
                    val typedList = list as List<Int>
                    return DistributionCharacteristics(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                is Short -> {
                    val typedList = list as List<Short>
                    return DistributionCharacteristics(
                        mean, sDev,
                        typedList.minOrNull()!!,
                        typedList.maxOrNull()!!
                    )
                }
                is Byte -> {
                    val typedList = list as List<Byte>
                    return DistributionCharacteristics(
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
            : DistributionCharacteristics? = if (array.size <= 2)
            null
        else DistributionCharacteristics(
            calculateMean(array), calculateSDev(array),
            array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: ShortArray)
            : DistributionCharacteristics? = if (array.size <= 2)
            null
        else DistributionCharacteristics(
            calculateMean(array), calculateSDev(array),
            array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: IntArray)
            : DistributionCharacteristics? = if (array.size <= 2)
            null
        else DistributionCharacteristics(
            calculateMean(array), calculateSDev(array),
            array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: LongArray)
            : DistributionCharacteristics? = if (array.size <= 2)
            null
        else DistributionCharacteristics(
            calculateMean(array), calculateSDev(array),
            array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: FloatArray)
            : DistributionCharacteristics? = if (array.size <= 2)
            null
        else DistributionCharacteristics(
            calculateMean(array), calculateSDev(array),
            array.minOrNull()!!, array.maxOrNull()!!
        )

        fun process(array: DoubleArray)
            : DistributionCharacteristics? = if (array.size <= 2)
            null
        else DistributionCharacteristics(
            calculateMean(array), calculateSDev(array),
            array.minOrNull()!!, array.maxOrNull()!!
        )
    }
}

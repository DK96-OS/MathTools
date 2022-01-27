package mathtools.numbers.statistics

import mathtools.numbers.statistics.Statistics.calculateMean
import mathtools.numbers.statistics.Statistics.calculateSDev

/** The key statistical parameters of a distribution */
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

    companion object {
        /** Determine the DistributionCharacteristics of the given List */
        inline fun <reified T : Number> process(
            list: List<T>
        ) : DistributionCharacteristics? {
            if (list.size <= 2) return null
            val mean = calculateMean(list)
            val sDev = calculateSDev(list, mean)
            when (list.first()) {
                is Float -> {
                    val list = list as List<Float>
                    return DistributionCharacteristics(
                        mean, sDev, list.minOrNull()!!, list.maxOrNull()!!)
                }
                is Double -> {
                    val list = list as List<Double>
                    return DistributionCharacteristics(
                        mean, sDev,
                        list.minOrNull()!! as Number,
                        list.maxOrNull()!!
                    )
                }
                is Long -> {
                    val list = list as List<Long>
                    return DistributionCharacteristics(
                        mean, sDev, list.minOrNull()!!, list.maxOrNull()!!)
                }
                is Int -> {
                    val list = list as List<Int>
                    return DistributionCharacteristics(
                        mean, sDev, list.minOrNull()!!, list.maxOrNull()!!)
                }
                is Short -> {
                    val list = list as List<Short>
                    return DistributionCharacteristics(
                        mean, sDev, list.minOrNull()!!, list.maxOrNull()!!)
                }
                is Byte -> {
                    val list = list as List<Byte>
                    return DistributionCharacteristics(
                        mean, sDev, list.minOrNull()!!, list.maxOrNull()!!)
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

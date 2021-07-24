package mathtools.numbers.statistics

/** Functions involved in characterizing distributions */
data class DistributionCharacteristics(
    val mean: Double,
    val standardDeviation: Double,
    val min: Double,
    val max: Double,
    val outliers: ArrayList<Double>? = null,
) {

    constructor(
        mean: Double, sDev: Double, min: Number, max: Number,
        outliers: ArrayList<Double>? = null)
    : this(mean, sDev, min.toDouble(), max.toDouble(), outliers)

    companion object {
        /** Determine the DistributionCharacteristics of the given List */
        inline fun <reified T : Number> process(list: List<T>)
        : DistributionCharacteristics? {
            if (list.size <= 2) return null
            val mean = Statistics.calculateMean(list)
            val sDev = Statistics.calculateSDev(list, mean)
                // Remove Outliers
                // Determine skewedness
            when (list.first()) {
                is Float -> {
                    val list = list as List<Float>
                    return DistributionCharacteristics(
                        mean, sDev, list.minOrNull()!!, list.maxOrNull()!!)
                }
                is Double -> {
                    val list = list as List<Double>
                    return DistributionCharacteristics(
                        mean, sDev, list.minOrNull()!!, list.maxOrNull()!!)
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
    }
}

package mathtools.numbers.statistics

/** Functions involved in characterizing distributions */
data class DistributionCharacteristics(
    val mean: Double,
    val standardDeviation: Double,
    val min: Double,
    val max: Double,
    val outliers: ArrayList<Float>? = null,
) {
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
                    val min = list.minOrNull()!!.toDouble()
                    val max = list.maxOrNull()!!.toDouble()
                    return DistributionCharacteristics(mean, sDev, min, max)
                }
                is Double -> {
                    val list = list as List<Double>
                    val min = list.minOrNull()!!.toDouble()
                    val max = list.maxOrNull()!!.toDouble()
                    return DistributionCharacteristics(mean, sDev, min, max)
                }
                is Long -> {
                    val list = list as List<Long>
                    val min = list.minOrNull()!!.toDouble()
                    val max = list.maxOrNull()!!.toDouble()
                    return DistributionCharacteristics(mean, sDev, min, max)
                }
                is Int -> {
                    val list = list as List<Int>
                    val min = list.minOrNull()!!.toDouble()
                    val max = list.maxOrNull()!!.toDouble()
                    return DistributionCharacteristics(mean, sDev, min, max)
                }
                is Short -> {
                    val list = list as List<Short>
                    val min = list.minOrNull()!!.toDouble()
                    val max = list.maxOrNull()!!.toDouble()
                    return DistributionCharacteristics(mean, sDev, min, max)
                }
                is Byte -> {
                    val list = list as List<Byte>
                    val min = list.minOrNull()!!.toDouble()
                    val max = list.maxOrNull()!!.toDouble()
                    return DistributionCharacteristics(mean, sDev, min, max)
                }
                else -> throw IllegalArgumentException(
                    "Invalid Data Type Received: ${T::class.java.name}")
            }
        }
    }
}

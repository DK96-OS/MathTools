package mathtools.numbers.statistics

/** Functions involved in characterizing distributions */
data class DistributionCharacteristics(
    val mean: Float,
    val standardDeviation: Float,
    val outliers: ArrayList<Float>? = null,
) {
    companion object {
        inline fun <reified T> process(list: List<T>): DistributionCharacteristics? {
            if (list.size <= 2) return null
            if (list.first() is Float) {
                val list = list as List<Float>
                val mean = Statistics.calculateMeanFloat(list)
                val sDev = Statistics.calculateStandardDevFloat(list, mean)
                // Remove Outliers
                // Determine skewedness
                return DistributionCharacteristics(mean, sDev)
            } else if (list.first() is Long) {
                val list = list as List<Long>
                val mean = Statistics.calculateMeanLong(list)
                val sDev = Statistics.calculateStandardDeviation(list, mean)
                // Remove Outliers
                // Determine skewedness
                return DistributionCharacteristics(mean, sDev)
            } else {
                throw IllegalArgumentException("Invalid Data Type Received: ${T::class.java.name}")
            }
        }
    }
}

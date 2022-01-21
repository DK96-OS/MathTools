package mathtools.numbers.statistics

/** Outlier scanning functions
* Developed by DK96-OS : 2021 */
object Outliers {

    /** Build a list of outliers
      * Developed by DK96-OS : 2021 */
    fun scanOutliers(
        dc: DistributionCharacteristics, sDevLimit: Double) 
    : ArrayList<Double>? {
        val upperList = upperOutliers(dc, sDevLimit)
        val lowerList = lowerOutliers(dc, sDevLimit)
        return when {
            upperList != null -> if (lowerList == null) 
                upperList
            else upperList.apply { addAll(lowerList) }
            lowerList != null -> lowerList
            else -> null
        }
    }

    /** Build a list of outliers above the given limit 
      *  Developed by DK96-OS : 2021 */
    fun upperOutliers(
        dc: DistributionCharacteristics, sDevLimit: Double)
    : ArrayList<Double>? {
        val upperLimit = dc.mean + sDevLimit * dc.standardDeviation
        return if (dc.max > upperLimit) {
            val list = arrayListOf(dc.max)
            //
            list
        } else null
    }

    /** Build a list of outliers below the given limit
      * Developed by DK96-OS : 2021 */
    fun lowerOutliers(
        dc: DistributionCharacteristics, sDevLimit: Double)
    : ArrayList<Double>? {
        val lowerLimit = dc.mean - sDevLimit * dc.standardDeviation
        return if (dc.min < lowerLimit) {
            val list = arrayListOf(dc.min)
            //
            list
        } else null
    }

}


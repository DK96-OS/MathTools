package mathtools.statistics.outlier

import mathtools.statistics.DistributionStats

/** Describes the way that outliers are identified
 * Developed by DK96-OS : 2022 */
interface OutlierPolicy {

    /* Find and Remove Outliers from a Mutable List */

    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list */
    fun removeOutliersDouble(
	    mutableList: MutableList<Double>,
	    distribution: DistributionStats,
	    maxOutliers: UByte = 2u,
    ) : List<Double>

    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list
    fun removeOutliersFloat(
        mutableList: MutableList<Float>,
        distribution: DistributionCharacteristics,
        maxOutliers: UByte = 2u,
    ) : List<Float>
    */
    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list */
    fun removeOutliersLong(
	    mutableList: MutableList<Long>,
	    distribution: DistributionStats,
	    maxOutliers: UByte = 2u,
    ) : List<Long>

    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list
    fun removeOutliersInt(
        mutableList: MutableList<Int>,
        distribution: DistributionCharacteristics,
        maxOutliers: UByte = 2u,
    ) : List<Int>
    */
    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list
    fun removeOutliersShort(
        mutableList: MutableList<Short>,
        distribution: DistributionCharacteristics,
        maxOutliers: UByte = 2u,
    ) : List<Short>
    */

    /* Identify the outliers in an unsorted Array */

    /** Find the outliers in the unsorted Array
     * @return The indices of the outliers, or empty list */
    fun identifyOutliers(
	    array: DoubleArray,
	    distribution: DistributionStats,
    ) : List<Int>

    /** Find the outliers in the unsorted Array
     * @return The indices of the outliers, or empty list
    fun identifyOutliers(
        array: FloatArray,
        distribution: DistributionCharacteristics,
    ) : List<Int>
    */

    /** Find the outliers in the unsorted Array
     * @return The indices of the outliers, or empty list */
    fun identifyOutliers(
	    array: LongArray,
	    distribution: DistributionStats,
    ) : List<Int>

    /** Find the outliers in the unsorted Array
     * @return The indices of the outliers, or empty list
    fun identifyOutliers(
        array: IntArray,
        distribution: DistributionCharacteristics,
    ) : List<Int>
    */
    /** Find the outliers in the unsorted Array
     * @return The indices of the outliers, or empty list
    fun identifyOutliers(
        array: ShortArray,
        distribution: DistributionCharacteristics,
    ) : List<Int>
    */

    /* Determine whether the outer elements are outliers */

    /** Check if the endpoint elements are outliers.
     * Assumes that array is sorted
      */
    //fun checkEndpointOutlier()

}
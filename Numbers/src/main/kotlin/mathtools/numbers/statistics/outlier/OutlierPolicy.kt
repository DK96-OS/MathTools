package mathtools.numbers.statistics.outlier

import mathtools.numbers.statistics.DistributionCharacteristics

/** Describes the way that outliers are identified */
interface OutlierPolicy {

    /* Find and Remove Outliers from a Mutable List */

    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list */
    fun removeOutliersDouble(
        mutableList: MutableList<Double>,
        maxOutliers: UShort,
        distribution: DistributionCharacteristics,
    ) : List<Double>

    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list
    fun removeOutliersFloat(
        mutableList: MutableList<Float>,
        maxOutliers: UShort,
        distribution: DistributionCharacteristics,
    ) : List<Float>
    */
    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list */
    fun removeOutliersLong(
        mutableList: MutableList<Long>,
        maxOutliers: UShort,
        distribution: DistributionCharacteristics,
    ) : List<Long>

    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list
    fun removeOutliersInt(
        mutableList: MutableList<Int>,
        maxOutliers: UShort,
        distribution: DistributionCharacteristics,
    ) : List<Int>
    */
    /** Remove outliers from the unsorted list
     * @return The outliers that were removed, or empty list
    fun removeOutliersShort(
        mutableList: MutableList<Short>,
        maxOutliers: UShort,
        distribution: DistributionCharacteristics,
    ) : List<Short>
    */

    /* Identify the outliers in an unsorted Array */

    /** Find the outliers in the unsorted Array
     * @return The indices of the outliers, or empty list */
    fun identifyOutliers(
        array: DoubleArray,
        distribution: DistributionCharacteristics,
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
        distribution: DistributionCharacteristics,
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
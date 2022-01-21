package mathtools.numbers.listtypes.doubles

object DoubleListResources {

    internal const val smallListFactor = 100.101
    /** A list with 10 elements */
    internal val smallList: MutableList<Double>
        get() = Array(10) {
            smallListFactor * (it + 1)
        }.toMutableList()

    internal const val medListFactor = 200.500543
    /** A list with 1000 elements */
    internal val medList: MutableList<Double>
        get() = Array(1_000) {
            medListFactor * (it + 1)
        }.toMutableList()

    internal const val largeListFactor = 5_000_00_000.500543
    /** A list with 150_000 elements */
    internal val largeList: MutableList<Double>
        get() = Array(150_000) {
            largeListFactor * (it + 1)
        }.toMutableList()

    /** A list containing a single element 5.0 */
    internal val singleItemList: MutableList<Double>
        get() = mutableListOf(5.0)

    /** A list containing the max Double value */
    internal val maxValueList: MutableList<Double>
        get() = Array(5) {
            Double.MAX_VALUE * (1 - (it / 10))
        }.toMutableList()

    /** A list containing NaN value */
    internal val nanValueList: MutableList<Double>
        get() = Array(5) {
            if (it == 3)
                Double.NaN
            else
                it.toDouble()
        }.toMutableList()

    /** A list containing positive infinity */
    internal val positiveInfiniteList: MutableList<Double>
        get() = Array(5) {
            if (it == 3)
                Double.POSITIVE_INFINITY
            else
                it.toDouble()
        }.toMutableList()

    /** A list containing negative infinity */
    internal val negativeInfiniteList: MutableList<Double>
        get() = Array(5) {
            if (it == 3)
                Double.NEGATIVE_INFINITY
            else
                it.toDouble()
        }.toMutableList()

}
package mathtools.lists.doubles

/** Provider of Double List data samples for testing
 * @author DK96-OS : 2022 */
object DoubleListTestResources {

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

    /** A list containing the max Double value */
    internal val maxValueList: MutableList<Double>
        get() = Array(5) {
            Double.MAX_VALUE * (1 - (it / 10.0))
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
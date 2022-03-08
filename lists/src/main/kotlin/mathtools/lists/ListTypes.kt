package mathtools.lists

/** Sum up the numbers in the list
 * Use with caution, overflows are not checked in most cases. */
@Deprecated(
    message = "Incomplete and imprecise function. For casual users.",
    level = DeprecationLevel.WARNING
)
fun <T : Number> listSum(
    list: List<T>
) : Double = when (list.firstOrNull()) {
    is Double -> (list as List<Double>).sum()
    is Float -> {
        val floatList = list as List<Float>
        var sum = 0.0
        for (i in floatList.indices) sum += floatList[i]
        sum
    }
    is Long ->
        LongList.largeSum(list as List<Long>).toDouble()
    is Int ->
        IntList.largeSum(list as List<Int>).toDouble()
    is Short -> {
        val shortList = list as List<Short>
        var sum = 0L
        for (i in shortList.indices) sum += shortList[i]
        sum.toDouble()
    }
    is Byte -> {
        val byteList = list as List<Byte>
        var sum = 0L
        for (i in byteList.indices) sum += byteList[i]
        sum.toDouble()
    }
    else -> throw IllegalArgumentException("Unsupported Type")
}
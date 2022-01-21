package mathtools.numbers.listtypes

/** Sum up the numbers in the list
 * Use with caution, overflows are not checked. */
fun <T : Number> listSum(list: List<T>)
: Double = when (list.firstOrNull()) {
    is Double -> (list as List<Double>).sum()
    is Float -> {
        val floatList = list as List<Float>
        var sum = 0.0
        for (i in floatList.indices)
            sum += floatList[i].toDouble()
        sum
    }
    is Long -> (list as List<Long>).sum().toDouble()
    is Int -> {
        val intList = list as List<Int>
        var sum = 0L
        for (i in intList.indices) sum += intList[i]
        sum.toDouble()
    }
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
    else -> 0.0
}

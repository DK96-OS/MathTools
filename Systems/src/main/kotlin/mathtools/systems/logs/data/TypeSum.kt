package mathtools.systems.logs.data

/** The sum of events of a given type */
class TypeSum(
    val type: Short,
    count: Int
) {
    var count: Int = count
        internal set
}

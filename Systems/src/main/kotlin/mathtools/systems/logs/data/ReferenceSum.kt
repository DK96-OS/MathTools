package mathtools.systems.logs.data

/** Log summary for a particular reference */
class ReferenceSum(
    val type: Short,
    val referenceId: Long,
    count: Int,
) {
    var count: Int = count
        internal set
}

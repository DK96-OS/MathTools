package mathtools.systems.logs.data

/** A Log event data point */
class LogData(
    val dateTime: Long,
    val type: Short,
    val referenceId: Long,
    count: Short = 1,
) {
    var count: Short = count
        internal set
}

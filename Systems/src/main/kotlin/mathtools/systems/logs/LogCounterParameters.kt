package mathtools.systems.logs

/** Customize Log Counter behaviour */
class LogCounterParameters(
    internal val dateTimeRange: LongRange,
) {
    
    /** Determine whether the date is within the relevant range */
    internal fun isValid(date: Long): Boolean = date in dateTimeRange
    
}

package mathtools.systems.measurement.electrical

/** Single Measurement Data for Electrical experiment 
 * Developed by DK96-OS : 2021 */
data class ElectricalMeasureData(
	/** Measurement start time, to nanosecond accuracy */
    val startTime: Long,
	/** End time of the measurement, to nanosecond accuracy */
    val endTime: Long,
    /** The average power consumption during the measurement */
    val averagePower: Double,
    /** The average voltage across the resistance */
    val averageVoltage: Double,
) {
    /** The duration that the measurements are averaged over */
    val timeSpan: Long = endTime - startTime
}

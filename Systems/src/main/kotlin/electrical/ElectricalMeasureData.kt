package electrical

import measurement.MeasurementData
import kotlin.math.sqrt

/** Single Measurement Data for Electrical experiment 
 * Developed by DK96-OS : 2021 */
data class ElectricalMeasureData(
    val startTime: Long,
    val endTime: Long,
    /** The average power consumption during the measurement */
    val averagePower: Double,
    /** The average voltage across the resistance */
    val averageVoltage: Double,
) : MeasurementData {
    /** The duration that the measurements are averaged over */
    val timeSpan: Long = endTime - startTime
    /** Estimating electrical Current from Power and Voltage */
    val averageCurrent: Double = sqrt(averagePower / averageVoltage)
}

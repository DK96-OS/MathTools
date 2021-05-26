package mathtools.systems.measurement.electrical

import mathtools.systems.measurement.MeasurementResult

/** The result of a series of identical Electrical measurements
 * Developed by DK96-OS : 2021 */
class ElectricalMeasurementResult(
    override val id: String,
    /** The amount of energy consumed */
    val energy: Double,
    /** The duration of the measurement in nanoseconds */
    val timeDuration: Long,
    /** The amount of charge moved across the circuit load */
    val chargeMoved: Double
) : MeasurementResult(id)

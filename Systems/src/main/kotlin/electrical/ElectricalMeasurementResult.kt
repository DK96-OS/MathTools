package electrical

import measurement.MeasurementResult

/** The result of a series of identical Electrical measurements
 * Developed by DK96-OS : 2021 */
class ElectricalMeasurementResult(
    override val id: String,
    val energy: Double,
    val timeDuration: Long,
    val chargeMoved: Double
) : MeasurementResult(id)

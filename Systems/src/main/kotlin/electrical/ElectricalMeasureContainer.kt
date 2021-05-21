package electrical

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import measurement.MeasurementContainer

/** Measurement container for Electrical system
 * Developed by DK96-OS : 2021 */
class ElectricalMeasureContainer(
    override val id: String,
    nMeasurements: Int,
) : MeasurementContainer<
        ElectricalMeasureData, 
        ElectricalMeasurementResult
        >(id, nMeasurements) {

    override suspend fun complete(
        list: List<ElectricalMeasureData>
    ): ElectricalMeasurementResult = coroutineScope {
        val energy = async {
            list.sumOf { it.averagePower * it.timeSpan.toDouble() }
        }
        val totalTime = async { list.sumOf { it.timeSpan } }
        val chargeMoved = async {
            list.sumOf { it.averageCurrent * it.timeSpan }
        }
        ElectricalMeasurementResult(
            id, energy.await(), totalTime.await(), chargeMoved.await())
    }

}

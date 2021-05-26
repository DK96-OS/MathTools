package mathtools.systems.measurement.electrical

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import mathtools.systems.measurement.MeasurementContainer
import kotlin.math.sqrt

/** Measurement container for Electrical system
 * Developed by DK96-OS : 2021 */
class ElectricalMeasureContainer(
    override val id: String, nMeasurements: Int) 
: MeasurementContainer<ElectricalMeasureData, ElectricalMeasurementResult>
(id, nMeasurements) {

    override suspend fun complete(list: List<ElectricalMeasureData>)
    : ElectricalMeasurementResult = coroutineScope {
    	// Converting from nanoseconds to standard units
    	val convertTime = 1.0e-9
        val energy = async {
            list.sumOf { 
            	it.averagePower * it.timeSpan.toDouble() 
            } * convertTime
        }
        val totalTime = async { list.sumOf { it.timeSpan } }
        val chargeMoved = async {
            list.sumOf {
    			// Estimating electrical Current from Power and Voltage
            	val avgCurrent = sqrt(it.averagePower / it.averageVoltage)
            	avgCurrent * it.timeSpan
            } * convertTime
        }
        ElectricalMeasurementResult(
            id, energy.await(), totalTime.await(), chargeMoved.await())
    }

}

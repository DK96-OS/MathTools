package mathtools.systems.measurement.electrical

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import mathtools.systems.measurement.MeasurementQueue
import kotlin.math.sqrt

/** Electrical system Measurement processing
 * Developed by DK96-OS : 2021 */
class ElectricalMeasureQueue : MeasurementQueue<
	ElectricalParams, ElectricalMeasureData>() {

	val resultQueue = ArrayDeque<ElectricalMeasurementResult>()

    override suspend fun process(
    	params: ElectricalParams, 
    	data: MutableList<ElectricalMeasureData>
    ) { coroutineScope {
    	val convertTime = 1.0e-9 	// Converting from nanoseconds
        val energy = async {
            data.sumOf { 
            	it.averagePower * it.timeSpan.toDouble() 
            } * convertTime
        }
        val totalTime = async { data.sumOf { it.timeSpan } }
        val chargeMoved = async {
            data.sumOf {
    			// Estimating electrical Current from Power and Voltage
            	val avgCurrent = sqrt(it.averagePower / it.averageVoltage)
            	avgCurrent * it.timeSpan
            } * convertTime
        }
        resultQueue.addLast(ElectricalMeasurementResult(
            params.id, energy.await(), totalTime.await(), chargeMoved.await()
        ))
    } }

}

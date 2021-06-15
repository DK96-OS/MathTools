package mathtools.systems.measurement.electrical

import mathtools.systems.measurement.MQParams

/** Measurement parameters for Electrical system
 * Developed by DK96-OS : 2021 */
class ElectricalParams(
 	val id: String = "",
 	nDataPoints: Int = 10,
 	val power: Float = 1.5f,
 	val voltage: Float = 450f,
 ) : MQParams {
 
 	override val nDataPoints: Int  = nDataPoints
 
 }

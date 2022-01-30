package mathtools.numbers.statistics

import mathtools.numbers.statistics.DistributionCharacteristics.Companion.process
import kotlin.math.PI
import kotlin.math.cos

/** Data Resources for Statistics Tests
 * Developed by DK96-OS : 2022 */
object StatisticsTestResources {

	val precise101: List<Number> = FloatArray(101) {
		50f + ((it / 50f) - 1)
	}.toList()

	val twoPi = (2 * PI).toFloat()
	val twoPi101 = twoPi / 101f

	val wave101: List<Number> = FloatArray(101) {
		cos( twoPi101 * it)
	}.toList()

	val wave101DC = process(wave101)!!

}
package mathtools.numbers.statistics

import mathtools.numbers.statistics.DistributionCharacteristics.Companion.process
import kotlin.math.PI
import kotlin.math.cos

object StatisticsTestResources {

	val uniform101: List<Number> = ByteArray(101) {
		(it - 20).toByte()
	}.toList()

	val uniform101DC = process(uniform101)!!

	val uniform1001: List<Number>
		get() = ShortArray(1001) {
			(it - 200).toShort()
		}.toList()

	val uniform10001: List<Number>
		get() = ShortArray(10001) {
			(it - 2000).toShort()
		}.toList()

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
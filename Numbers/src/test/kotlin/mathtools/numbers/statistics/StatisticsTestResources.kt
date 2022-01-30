package mathtools.numbers.statistics

import mathtools.numbers.statistics.DistributionCharacteristics.Companion.process
import kotlin.math.PI
import kotlin.math.cos

object StatisticsTestResources {

	/* The range -128 to 127 is common to all Number types.
     * Target mean value of 30, uniform spread of 50 on each side. */
	private val u101Byte
		: ByteArray = ByteArray(101) {
		(it - 20).toByte()
		}
	val uniform101: List<Byte> = u101Byte.toList()

	val uniform101DC = DistributionCharacteristics(
		30.0, 29.300_170_647_967_224,
		-20.0, 80.0
	)

	/* Target a mean value of 120 with most values below */
	private val largeByteArray
		: ByteArray by lazy { ByteArray(300_000) {
		when (it) {
			in 0 until 100_000 -> 120
			in 100_000 until 200_000 ->
				(100 + (it - 100_000) / 5_000).toByte()
			in 200_000 until 300_000 ->
				(121 + (it - 200_000) / 20_000).toByte()
			else -> throw IllegalArgumentException()
		}
	} }
	/** A 300_000 element byte list with a mean 120,
	 * min value 100, max value 125 */
	val largeByteList: List<Byte> by lazy { largeByteArray.toList() }

	val largeByteDC = DistributionCharacteristics(
		120.0, 4.0, 100, 125
	)

	fun cloneLargeByteArray(): ByteArray = largeByteArray.clone()

	/* Mean value 63000 */
	private val largeShortArray
		: ShortArray by lazy { ShortArray(30_000) {
			when (it) {
				in 0 .. 9_999 -> (32_000).toShort()
				in 10_000 .. 19_999 ->
					(32_001 + (it - 10_000) / 250).toShort()
				in 20_000 until 30_000 ->
					(31_999 - (it - 20_000) / 2_500).toShort()
				else -> throw IllegalArgumentException("Invalid: $it")
			}
	} }
	val largeShortList: List<Short> by lazy { largeShortArray.toList() }

	val largeShortDC = DistributionCharacteristics(
		120.0, 4.0, 100, 125
	)

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
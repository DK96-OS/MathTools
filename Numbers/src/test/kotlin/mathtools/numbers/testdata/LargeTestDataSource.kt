package mathtools.numbers.testdata

import mathtools.numbers.statistics.DistributionCharacteristics

/** A Source for Large TestData lists
 * Developed by DK96-OS : 2022 */
class LargeTestDataSource {

	/** A 300_000 element byte list with a mean ~120,
	 * min value 100, max value 125 */
	val large120: List<Byte> by lazy { ByteArray(300_000) {
		when (it) {
			in 0 until 100_000 -> 120
			in 100_000 until 200_000 ->
				(100 + (it - 100_000) / 5_000).toByte()
			in 200_000 until 300_000 ->
				(121 + (it - 200_000) / 20_000).toByte()
			else -> throw IllegalArgumentException()
		}
	}.toList() }

	val large120DC = DistributionCharacteristics(
		117.5, 6.726823234918257,
		100, 125
	)

	/** A 30_000 element short list with mean ~32000 */
	val large32000: List<Short> by lazy { ShortArray(30_000) {
		when (it) {
			in 0 .. 9_999 -> (32_000).toShort()
			in 10_000 .. 19_999 ->
				(32_001 + (it - 10_000) / 250).toShort()
			in 20_000 until 30_000 ->
				(31_999 - (it - 20_000) / 2_500).toShort()
			else -> throw IllegalArgumentException("Invalid: $it")
		}
	}.toList() }

	val large32000DC = DistributionCharacteristics(
		120.0, 4.0, 100, 125
	)

}
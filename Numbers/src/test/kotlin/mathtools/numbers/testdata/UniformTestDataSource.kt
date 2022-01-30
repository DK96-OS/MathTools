package mathtools.numbers.testdata

import mathtools.numbers.statistics.DistributionCharacteristics

/** Uniform Data Collections
 * Developed by DK96-OS : 2022 */
object UniformTestDataSource {

	/* The range -128 to 127 is common to all Number types.
     * Target mean value of 30, uniform spread of 50 on each side. */
	val uniform101: List<Byte> by lazy {
		ByteArray(101) {
			(it - 20).toByte()
		}.toList()
	}

	val uniform101DC = DistributionCharacteristics(
		30.0, 29.300_170_647_967_224,
		-20.0, 80.0
	)

	val uniform32666: List<Short> by lazy {
		ShortArray(30_000) { // Short Max = 32767
			(32666 + (it / 1_000) - 15).toShort()
		}.toList()
	}

}
package mathtools.statistics.testdata

import mathtools.statistics.DistributionStats

/** Uniform Data Collections
 * Developed by DK96-OS : 2022 */
object UniformTestDataSource {

	/** Uniform 101
	 *  Mean value ~ 30, uniform spread of 50 on each side
	 *  The range -128 to 127 is common to all Number types.
	 *
	 * Sum Calculation:
	 * -20 + -19 + .. 79 + 80
	 * Cancel (negative, positive) pairs, now:
	 * = 21 + 22 + .. + 79 + 80
	 * Group the constant 20 out of all 60 remaining values:
	 * = (20 * 60) + 1 + 2 + 3 + .. + 59 + 60
	 * Group pairs from 1 to 59 into 60s:
	 * = (1200) + 60 + (1 + 59) + (2 + 58) + .. + (29 + 31) + 30
	 * There are 29 pairs and a 30 left over. Simplify:
	 * = 1290 + (29 * 60)
	 * = 1290 + 1200 + 540
	 * = 3030
	 */
	val uniform101: List<Byte> by lazy {
		ByteArray(101) {
			(it - 20).toByte()
		}.toList()
	}

	val uniform101DC = DistributionStats(
		30.0, 29.300_170_647_967_224,
		-20.0, 80.0
	)

	const val uniform101Sum: Double = 3030.0

}
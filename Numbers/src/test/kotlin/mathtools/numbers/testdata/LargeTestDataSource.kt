package mathtools.numbers.testdata

import mathtools.numbers.statistics.DistributionCharacteristics

/** A Source for Large TestData lists
 * Developed by DK96-OS : 2022 */
object LargeTestDataSource {

	/** Large 123 Data Set
	 *  A 30_000 element byte list with a mean ~123,
	 *  min value 120, max value 126
	 *  T = Top: 10_000 : 123
	 *  C = Mid: 5_000  : 122, 124
	 *  B = Bot: 2_500  : 120, 121, 125, 126
	 *
	 * Sum Calculation:
	 * (120 + 121 + 125 + 126)B + (122 + 124)C + 123T
	 * Simplify:
	 * = 492B + 246C + 123T
	 * */
	val large123: List<Byte> by lazy { ArrayList<Byte>(30_000).apply {
		for (i in 0 until 10_000) add(123)
		for (i in 0 until 5_000) {
			add(122)
			add(124)
		}
		for (i in 0 until 2_500) {
			add(120)
			add(121)
			add(125)
			add(126)
		}
	}.toList() }

	val large123DC = DistributionCharacteristics(
		123.0, 1.5811651830568505,
		120, 126
	)

	const val large123Sum: Double = 3.69E6

	/** Large Data Set - Causes 32-bit Integer overflow
	 *  A list with mean ~32760, Max 32763, Min 32757
	 *  T = Top : 32760
	 *  C = Mid : 32759, 32761
	 *  B = Bot : 32757, 32758, 32762, 32763
	 *
	 * Sum Calculation:
	 * = 32760 * T + (32759 + 32761) * C + 4(32760) * B
	 * = 32760 * (T + 2C + 4B)
	 * */
	val large32760: List<Short> by lazy { ArrayList<Short>(65_553).apply {
		for (i in 0 until 25_553) add(32760)
		for (i in 0 until 10_000) {
			add(32761)
			add(32759)
		}
		for (i in 0 until 5_000) {
			add(32762)
			add(32763)
			add(32758)
			add(32757)
		}
	}.toList() }

	val large32760DC = DistributionCharacteristics(
		32760.0, 1.5126994751837317,
		32757, 32763
	)

	/** Sum is greater than max value of 32 bit signed Integer value */
	const val large32760Sum: Double = 2.14751628E9

}
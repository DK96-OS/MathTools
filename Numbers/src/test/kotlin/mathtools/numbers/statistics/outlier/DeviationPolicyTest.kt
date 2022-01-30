package mathtools.numbers.statistics.outlier

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/** Testing DeviationPolicy class construction
 * Developed by DK96-OS : 2022 */
class DeviationPolicyTest {

	@Test
	fun testBadArguments() {
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(
				5.0,
				upperOutliers = false,
				lowerOutliers = false
			)
		}
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(0.0)
		}
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(-20.0)
		}
	}

	@Test
	fun testBadArgsInfiniteDeviations() {
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(Double.POSITIVE_INFINITY)
		}
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(Double.NEGATIVE_INFINITY)
		}
	}

	@Test
	fun testBadArgsSpecialDeviations() {
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(Double.NaN)
		}
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(Double.MIN_VALUE)
		}
		assertThrows<IllegalArgumentException> {
			DeviationPolicy(Double.MAX_VALUE)
		}
	}

}
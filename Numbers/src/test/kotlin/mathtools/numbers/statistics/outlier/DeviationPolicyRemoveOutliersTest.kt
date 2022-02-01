package mathtools.numbers.statistics.outlier

import mathtools.numbers.listtypes.NumberListConversion.toDouble
import mathtools.numbers.listtypes.NumberListConversion.toLong
import mathtools.numbers.statistics.outlier.DeviationPolicyTestResources.policy2SD
import mathtools.numbers.statistics.outlier.DeviationPolicyTestResources.policy2SDUpper
import mathtools.numbers.statistics.outlier.DeviationPolicyTestResources.policy3SD
import mathtools.numbers.testdata.LargeTestDataSource.large123
import mathtools.numbers.testdata.LargeTestDataSource.large123DC
import mathtools.numbers.testdata.UniformTestDataSource.uniform101
import mathtools.numbers.testdata.UniformTestDataSource.uniform101DC
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DeviationPolicy's RemoveOutliers method
 * Developed by DK96-OS : 2022 */
class DeviationPolicyRemoveOutliersTest {

	// Recreate the list each time, as it might be changed by RemoveOutliers
	private val u101Double: MutableList<Double>
		get() = toDouble(uniform101)
	private val u101Long: MutableList<Long>
		get() = toLong(uniform101)

	private val large120Double: MutableList<Double>
		get() = toDouble(large123)
	private val large120Long: MutableList<Long>
		get() = toLong(large123)

	@Test
	fun test2SDPolicyOnLarge120() {
		assertEquals(
			0, policy2SD.removeOutliersDouble(
				large120Double, large123DC, 10u
			).size
		)
		assertEquals(
			0, policy2SD.removeOutliersLong(
				large120Long, large123DC, 10u
			).size
		)
	}

	@Test
	fun test3SDPolicyOnUniform101() {
		assertEquals(
			0, policy3SD.removeOutliersDouble(
				u101Double, uniform101DC, 10u
			).size
		)
		assertEquals(
			0, policy3SD.removeOutliersLong(
				u101Long, uniform101DC, 10u
			).size
		)
	}

	@Test
	fun test2DUpperPolicyOnU101() {
		// All items are within 2 SD of mean
		assertEquals(
			0, policy2SDUpper.removeOutliersDouble(
				u101Double, uniform101DC, 10u
			).size
		)
		assertEquals(
			0, policy2SDUpper.removeOutliersLong(
				u101Long, uniform101DC, 10u
			).size
		)
	}

}
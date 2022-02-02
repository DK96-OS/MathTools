package mathtools.statistics.outlier

import mathtools.statistics.outlier.DeviationPolicyTestResources.policy1SD
import mathtools.statistics.outlier.DeviationPolicyTestResources.policy1SDLower
import mathtools.statistics.outlier.DeviationPolicyTestResources.policy1SDUpper
import mathtools.statistics.outlier.DeviationPolicyTestResources.policy2SD
import mathtools.statistics.outlier.DeviationPolicyTestResources.policy2SDLower
import mathtools.statistics.outlier.DeviationPolicyTestResources.policy2SDUpper
import mathtools.statistics.testdata.UniformTestDataSource.uniform101
import mathtools.statistics.testdata.UniformTestDataSource.uniform101DC
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Testing DeviationPolicy's IdentifyOutliers method
 * Developed by DK96-OS : 2022 */
class DeviationPolicyIdentifyOutliersTest {

	private val doubleArray = DoubleArray(uniform101.size) {
		uniform101[it].toDouble()
	}
	private val longArray = LongArray(uniform101.size) {
		uniform101[it].toLong()
	}

	@Test
	fun testNoOutliersDouble() {
		assertEquals(
			0, policy2SD.identifyOutliers(
				doubleArray, uniform101DC
			).size
		)
		assertEquals(
			0, policy2SDUpper.identifyOutliers(
				doubleArray, uniform101DC
			).size
		)
		assertEquals(
			0, policy2SDLower.identifyOutliers(
				doubleArray, uniform101DC
			).size
		)
	}

	@Test
	fun testNoOutliersLong() {
		assertEquals(
			0, policy2SD.identifyOutliers(
				longArray, uniform101DC
			).size
		)
		assertEquals(
			0, policy2SDUpper.identifyOutliers(
				longArray, uniform101DC
			).size
		)
		assertEquals(
			0, policy2SDLower.identifyOutliers(
				longArray, uniform101DC
			).size
		)
	}

	@Test
	fun test1SDDouble() {
		var result = policy1SD.identifyOutliers(
			doubleArray, uniform101DC
		)
		assertEquals(42, result.size)
		for (i in 0 until 21)
			assertEquals(i, result[i])
		for (i in 80 until 101)
			assertEquals(i, result[i - 80 + 21])
		//
		result = policy1SDUpper.identifyOutliers(
			doubleArray, uniform101DC
		)
		assertEquals(21, result.size)
		for (i in 80 until 101)
			assertEquals(i, result[i - 80])
		//
		result = policy1SDLower.identifyOutliers(
			doubleArray, uniform101DC
		)
		assertEquals(21, result.size)
		for (i in 0 until 21)
			assertEquals(i, result[i])
	}

	@Test
	fun test1SDLong() {
		var result = policy1SD.identifyOutliers(
			longArray, uniform101DC
		)
		assertEquals(42, result.size)
		for (i in 0 until 21)
			assertEquals(i, result[i])
		for (i in 80 until 101)
			assertEquals(i, result[i - 80 + 21])
		//
		result = policy1SDUpper.identifyOutliers(
			longArray, uniform101DC
		)
		assertEquals(21, result.size)
		for (i in 80 until 101)
			assertEquals(i, result[i - 80])
		//
		result = policy1SDLower.identifyOutliers(
			longArray, uniform101DC
		)
		assertEquals(21, result.size)
		for (i in 0 until 21)
			assertEquals(i, result[i])

	}

}
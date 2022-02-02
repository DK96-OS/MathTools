package mathtools.statistics.outlier

import mathtools.lists.NumberListConversion.toDouble
import mathtools.lists.NumberListConversion.toLong
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
import kotlin.math.roundToLong

/** Testing DeviationPolicy's RemoveOutliers method
 * Developed by DK96-OS : 2022 */
class DeviationPolicyRemoveOutliersTest {

	/** Test Cases:
	 *  1. Use Uniform Data Source for all tests - elements within 2 SD
	 *  2. If modifications to the list occur, it should be recreated
	 *  3. For coverage, use upper, lower and two-sided policies
	 *      Below maxOutlier Limit uses a function that is already tested.
	 *      Focus on cases where there are more outliers than the limit.
	 */

	@Test
	fun testPolicyOnDoubleUniformList() {
		val mutableList = toDouble(uniform101)
		// Every element in uniform 101 is within 2 SD
		assertEquals(
			0, policy2SD.removeOutliersDouble(
				mutableList, uniform101DC, 10u
			).size
		)
		assertEquals(uniform101.size, mutableList.size)
		assertEquals(
			0, policy2SDLower.removeOutliersDouble(
				mutableList, uniform101DC, 10u
			).size
		)
		assertEquals(uniform101.size, mutableList.size)
		assertEquals(
			0, policy2SDUpper.removeOutliersDouble(
				toDouble(uniform101), uniform101DC, 10u
			).size
		)
		assertEquals(uniform101.size, mutableList.size)
		// Outside of 1 SD, there are many elements
		val result1SD = policy1SD.removeOutliersDouble(
			mutableList, uniform101DC, 100u
		)
		assertEquals(42, result1SD.size)
		assertEquals(uniform101.size - 42, mutableList.size)
	}

	@Test
	fun testPolicyOnLongUniformList() {
		val mutableList = toLong(uniform101)
		assertEquals(
			0, policy2SD.removeOutliersLong(
				mutableList, uniform101DC, 10u
			).size
		)
		assertEquals(uniform101.size, mutableList.size)
		assertEquals(
			0, policy2SDLower.removeOutliersLong(
				mutableList, uniform101DC, 10u
			).size
		)
		assertEquals(uniform101.size, mutableList.size)
		assertEquals(
			0, policy2SDUpper.removeOutliersLong(
				mutableList, uniform101DC, 10u
			).size
		)
		assertEquals(uniform101.size, mutableList.size)
		// Outside of 1 SD, there are many elements
		val result1SD = policy1SD.removeOutliersLong(
			mutableList, uniform101DC, 100u
		)
		assertEquals(42, result1SD.size)
		assertEquals(uniform101.size - 42, mutableList.size)
	}

	@Test
	fun testOneOutlierDouble() {
		val boundary = uniform101DC.valueAtDeviation(2.5)
		val mutableList = toDouble(uniform101)
		mutableList.add(boundary)
		val removed = policy2SD.removeOutliersDouble(
			mutableList, uniform101DC, 1u
		)
		assertEquals(1, removed.size)
		assertEquals(boundary, removed[0])
		assertEquals(uniform101.size, mutableList.size)
	}

	@Test
	fun testOneOutlierLong() {
		val boundary = uniform101DC.valueAtDeviation(2.5).toLong()
		val mutableList = toLong(uniform101)
		mutableList.add(boundary)
		val removed = policy2SD.removeOutliersLong(
			mutableList, uniform101DC, 1u
		)
		assertEquals(1, removed.size)
		assertEquals(boundary, removed[0])
		assertEquals(uniform101.size, mutableList.size)
	}

	@Test
	fun testMaxOutliersLimitDouble() {
		val outlier0 = uniform101DC.valueAtDeviation(3.0)
		val outlier1 = uniform101DC.valueAtDeviation(-3.1)
		val mutableList = toDouble(uniform101).apply {
			add(outlier0)
			add(outlier1)
		}
		// Outlier 1 is further from mean, removed first
		val removed1 = policy2SD.removeOutliersDouble(
			mutableList, uniform101DC, 1u
		)
		assertEquals(outlier1, removed1[0])
		val removed0 = policy2SD.removeOutliersDouble(
			mutableList, uniform101DC, 1u
		)
		assertEquals(outlier0, removed0[0])
	}

	@Test
	fun testMaxOutliersLimitLong() {
		val outlier0 = uniform101DC.valueAtDeviation(3.0).roundToLong()
		val outlier1 = uniform101DC.valueAtDeviation(-3.5).roundToLong()
		val mutableList = toLong(uniform101)
		mutableList.add(outlier0)
		mutableList.add(outlier1)   // Outlier 1 is larger, removed first
		val removed1 = policy2SD.removeOutliersLong(
			mutableList, uniform101DC, 1u
		)
		assertEquals(outlier1, removed1[0])
		val removed0 = policy2SD.removeOutliersLong(
			mutableList, uniform101DC, 1u
		)
		assertEquals(outlier0, removed0[0])
	}

	@Test
	fun testOutlierLimitUpperPolicyDouble() {
		val data = toDouble(uniform101)
		val removed = policy1SDUpper.removeOutliersDouble(
			data, uniform101DC, 5u
		)
		assertEquals(uniform101.size - 5, data.size)
		assertEquals(5, removed.size)
		// Removed are sorted in descending order, in this case
		for (i in 0 until 5)
			assertEquals(80.0 - i, removed[i])
	}

	@Test
	fun testOutlierLimitUpperPolicyLong() {
		val data = toLong(uniform101)
		val removed = policy1SDUpper.removeOutliersLong(
			data, uniform101DC, 5u
		)
		assertEquals(uniform101.size - 5, data.size)
		assertEquals(5, removed.size)
		// Removed are sorted in descending order, in this case
		for (i in 0 until 5)
			assertEquals(80L - i, removed[i])
	}

	@Test
	fun testOutlierLimitLowerPolicyDouble() {
		val data = toDouble(uniform101)
		val removed = policy1SDLower.removeOutliersDouble(
			data, uniform101DC, 5u
		)
		assertEquals(uniform101.size - 5, data.size)
		assertEquals(5, removed.size)
		// Removed are sorted in descending order, in this case
		for (i in 0 until 5)
			assertEquals(-16.0 - i, removed[i])
	}

	@Test
	fun testOutlierLimitLowerPolicyLong() {
		val data = toLong(uniform101)
		val removed = policy1SDLower.removeOutliersLong(
			data, uniform101DC, 5u
		)
		assertEquals(uniform101.size - 5, data.size)
		assertEquals(5, removed.size)
		// Removed are sorted in descending order, in this case
		for (i in 0 until 5)
			assertEquals(-16L - i, removed[i])
	}

}
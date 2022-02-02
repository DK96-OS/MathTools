package mathtools.statistics.testdata

import mathtools.statistics.DistributionCharacteristics.Companion.process
import mathtools.statistics.testdata.LargeTestDataSource.large123
import mathtools.statistics.testdata.LargeTestDataSource.large123DC
import mathtools.statistics.testdata.LargeTestDataSource.large123Sum
import mathtools.statistics.testdata.LargeTestDataSource.large32760
import mathtools.statistics.testdata.LargeTestDataSource.large32760DC
import mathtools.statistics.testdata.LargeTestDataSource.large32760Sum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class TestDataSourceTest {

	@Test
	fun testLarge123Values() {
		val counter = IntArray(10)
		val offset = 120
		large123.forEach { counter[it - offset]++ }
		assertEquals(10_000, counter[123 - offset])
		assertEquals(5000, counter[122 - offset])
		assertEquals(5000, counter[124 - offset])
		for (i in 2 until 4) {
			assertEquals(2500, counter[123 + i - offset])
			assertEquals(2500, counter[123 - i - offset])
		}
	}

	@Test
	fun testLarge123DC() {
		assertEquals(large123DC, process(large123))
	}

	@Test
	fun testLarge123Sum() {
		assertEquals(large123Sum, large123.sum().toDouble())
	}

	@Test
	fun testLarge32760Values() {
		val counter = IntArray(10)
		val offset = 32757
		val mean = 32760
		large32760.forEach { counter[it - offset]++ }
		assertEquals(25_553, counter[mean - offset])
		assertEquals(10_000, counter[mean + 1 - offset])
		assertEquals(10_000, counter[mean - 1 - offset])
		for (i in 2 until 4) {
			assertEquals(5000, counter[mean + i - offset])
			assertEquals(5000, counter[mean - i - offset])
		}
	}

	@Test
	fun testLarge32760DC() {
		assertEquals(large32760DC, process(large32760))
	}

	@Test
	fun testLarge32760Sum() {
		assert(
			large32760Sum > Int.MAX_VALUE.toDouble()
		)
		assertNotEquals(
			large32760Sum, large32760.sum().toDouble()
		)
	}

}
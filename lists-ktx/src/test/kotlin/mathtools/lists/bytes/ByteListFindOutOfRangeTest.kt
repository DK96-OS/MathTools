package mathtools.lists.bytes

import mathtools.lists.ByteList.findOutOfRange
import mathtools.lists.NumberListConversion.toByte
import mathtools.lists.testdata.ListDataSource.uniform101
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

/** Testing [ByteList] FindOutOfRange function
 * @author DK96-OS : 2022 */
class ByteListFindOutOfRangeTest {

	private val u101 = toByte(uniform101)

	@Test
	fun testEmptyList() {
		assertEquals(
			0, findOutOfRange(
				emptyList(), 1, 20
			).size
		)
	}

	@Test
	fun testSingleItemList() {
		assertEquals(
			0, findOutOfRange(
				listOf(5), 0, 9
			).size
		)
		assertEquals(
			listOf(0), findOutOfRange(
				listOf(5), -6, 4
			)
		)
		assertEquals(
			listOf(0), findOutOfRange(
				listOf(6), 7, Byte.MAX_VALUE
			)
		)
	}

	@Test
	fun testBadArguments() {
		// Reversed Bounds
		assertEquals(
			0, findOutOfRange(
				u101, 60, 40
			).size
		)
	}

	@Test
	fun testEqualBounds() {
		// No elements equal to bounds
		assertEquals(
			u101.size,
			findOutOfRange(
				u101, 121, 121
			).size
		)
		// One Element is equal to bounds
		assertEquals(
			u101.size - 1,
			findOutOfRange(
				u101, 30, 30
			).size
		)
	}

	@Test
	fun testU101With20EachSide() {
		val result = findOutOfRange(
			u101, 0, 60,
		)
		assertEquals(
			40, result.size)
		//
		for (i in 0 until 20)
			assertEquals(
				(-20 + i).toByte(), u101[result[i]]
			)
		for (i in 61 .. 80)
			assertEquals(
				i.toByte(), u101[result[i - 41]]
			)
	}

	@Test
	fun testU101AllOutOfRange() {
		assertEquals(
			u101.indices.toList(),
			findOutOfRange(
				u101, -127, -100
			)
		)
	}

	@Test
	fun testStartArg() {
		val result1 = findOutOfRange(
			u101, -10, 80, 9
		)
		val result2 = findOutOfRange(
			u101, -10, 80, 10
		)
		val result3 = findOutOfRange(
			u101, -10, 79, 9
		)
		val result4 = findOutOfRange(
			u101, -19, 79, -1
		)
		assertEquals(
			listOf(9), result1)
		assertEquals(
			0, result2.size)
		assertEquals(
			listOf(9, 100), result3)
		assertEquals(
			listOf(0, 100), result4)
	}

	@Test
	fun testEndArg() {
		val result1 = findOutOfRange(
			u101, -20, 79, 0, 100
		)
		val result2 = findOutOfRange(
			u101, -20, 79, 0, 101
		)
		val result3 = findOutOfRange(
			u101, -19, 70, 0, 92
		)
		val result4 = findOutOfRange(
			u101, -19, 79, 0, 150
		)
		assertEquals(
			0, result1.size)
		assertEquals(
			100, result2[0])
		assertEquals(
			listOf(0, 91), result3)
		assertEquals(
			listOf(0, 100), result4)
		//
		assertEquals(
			2, findOutOfRange(
				u101, -19, 79, 0,  u101.size
			).size
		)
	}

	@Test
	fun testSublistSearch() {
		val result1 = findOutOfRange(
			u101, -10, 70, 9, 92
		)
		val result2 = findOutOfRange(
			u101, -10, 70, 10, 92
		)
		val result3 = findOutOfRange(
			u101, -10, 70, 9, 91
		)
		assertEquals(
			listOf(9, 91), result1)
		assertEquals(
			91, result2[0])
		assertEquals(
			9, result3[0])
	}

	@Test
	fun testMinAndMaxRange() {
		assertEquals(
			listOf(100),
			findOutOfRange(u101, Byte.MIN_VALUE, 79)
		)
		assertEquals(
			Collections.emptyList<Int>(),
			findOutOfRange(u101, Byte.MIN_VALUE, Byte.MAX_VALUE)
		)
	}

}
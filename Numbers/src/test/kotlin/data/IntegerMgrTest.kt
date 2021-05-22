package data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Test the Integer Mgr class */
class IntegerMgrTest {

	private lateinit var singleDigitInt: IntegerMgr

	@BeforeEach fun setup() {
		singleDigitInt = IntegerMgr(0, max = 9, min = -9)
	}

	@Test fun testIncrementLoop() {
		for (i in 1 .. 9) 
			assertEquals(i, singleDigitInt.increment())
		for (i in 8 downTo -9)
			assertEquals(i, singleDigitInt.increment())
		for (i in -8 .. 0)
			assertEquals(i, singleDigitInt.increment())
	}
	
	@Test fun testTrySetN() {
		singleDigitInt.trySetN(7)
		assertEquals(7, singleDigitInt.number)
		singleDigitInt.trySetN(-10)
		assertEquals(-9, singleDigitInt.number)
		singleDigitInt.trySetN(30)
		assertEquals(9, singleDigitInt.number)
	}
	
	@Test fun testAdditionOperation() {
		singleDigitInt.trySetN(7)
		assertEquals(12, singleDigitInt + 5)
		assertEquals(12.5f, singleDigitInt + 5.5f)
		assertEquals(14, singleDigitInt + singleDigitInt)
	}
	
	@Test fun testCompareOperation() {
		singleDigitInt.trySetN(5)
		assertEquals(true, singleDigitInt > 4)
		assertEquals(true, singleDigitInt <= 5)
		assertEquals(true, singleDigitInt >= 5)
		assertEquals(false, singleDigitInt > 5)
	}

}

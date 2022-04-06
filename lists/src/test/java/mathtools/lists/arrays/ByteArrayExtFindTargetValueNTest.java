package mathtools.lists.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.lists.arrays.ByteArrayExt.findTargetValueN;
import static mathtools.lists.arrays.ByteArrayExtTest.newArray;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing [ByteArrayExt] FindTargetValueN function
 * @author DK96-OS : 2022 */
public final class ByteArrayExtFindTargetValueNTest {

	private byte[] mArray;

	@BeforeEach
	public void testSetup() {
		mArray = newArray(8, (byte) 4);
		for (int i = 0; i < mArray.length; i += 2) {
			mArray[i] = 0;
		}
	}

	@Test
	public void testCountTargetNormal() {
		assertEquals(
			1, findTargetValueN(mArray, (byte) 4, 1));
		assertEquals(
			3, findTargetValueN(mArray, (byte) 4, 2));
		assertEquals(
			5, findTargetValueN(mArray, (byte) 4, 3));
		assertEquals(
			7, findTargetValueN(mArray, (byte) 4, 4));
	}

	@Test
	public void testCountTargetBeyond() {
		// 5th target value not found
		assertEquals(
			-1, findTargetValueN(mArray, (byte) 4, 5));
	}

	@Test
	void testTargetValueNotPresent() {
		assertEquals(
			-1, findTargetValueN(mArray, (byte) 5, 1));
	}

	@Test
	public void testCounterArgumentInvalid() {
		assertEquals(
			-1, findTargetValueN(mArray, (byte) 4, 0));
		assertEquals(
			-1, findTargetValueN(mArray, (byte) 4, -1));
	}

}
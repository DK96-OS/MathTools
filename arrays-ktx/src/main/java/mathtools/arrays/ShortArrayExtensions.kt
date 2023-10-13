package mathtools.arrays

import java.util.Arrays

/** Convenient ShortArray methods
 * @author DK96-OS : 2022 */
object ShortArrayExtensions {

	/** Clears any nonzero elements in the Array */
	fun ShortArray.clear() {
		Arrays.fill(this, 0)
	}

	/** Determines whether this array contains only non-zero elements */
	fun ShortArray.isNonZero()
		: Boolean = ShortArrayExt.allNonZero(this)

}
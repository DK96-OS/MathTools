package mathtools.arrays

import java.util.Arrays

/** Convenient IntArray methods
 * @author DK96-OS : 2022 */
object IntArrayExtensions {

	/** Clears any nonzero elements in the Array */
	fun IntArray.clear() {
		Arrays.fill(this, 0)
	}

	/** Determines whether this array contains only non-zero elements */
	fun IntArray.isNonZero()
		: Boolean = IntArrayExt.allNonZero(this)

}
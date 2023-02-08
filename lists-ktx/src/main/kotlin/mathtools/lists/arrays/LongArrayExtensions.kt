package mathtools.lists.arrays

import java.util.*

/** Convenient LongArray methods
 * @author DK96-OS : 2022 */
object LongArrayExtensions {

	/** Clears any nonzero elements in the Array */
	fun LongArray.clear() {
		Arrays.fill(this, 0)
	}

	/** Determines whether this array contains only non-zero elements */
	fun LongArray.isNonZero()
		: Boolean = LongArrayExt.allNonZero(this)

}
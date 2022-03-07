package mathtools.lists.arrays

/** Convenient ByteArray methods
 * @author DK96-OS : 2022 */
object ByteArrayExtensions {

	/** Clears any nonzero elements in the Array */
	fun ByteArray.clear() {
		ByteArrayExt.clear(this)
	}

	/** Determines whether this array contains only non-zero elements */
	fun ByteArray.isNonZero()
		: Boolean = ByteArrayExt.allNonZero(this)

}
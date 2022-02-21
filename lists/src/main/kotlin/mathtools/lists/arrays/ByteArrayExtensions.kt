package mathtools.lists.arrays

/** Convenient ByteArray methods
 * @author DK96-OS : 2022 */
object ByteArrayExtensions {

	/** Clears any nonzero elements in the Array */
	fun ByteArray.clear() {
		val zero: Byte = 0
		val first = indexOfFirst { it != zero }
		if (first > -1)
			for (i in first until size)
				if (this[i] != (0).toByte()) set(i, 0)
	}

}
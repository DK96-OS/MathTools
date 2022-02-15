package mathtools.lists

/** Converts Numbered List Types
 * @author DK96-OS : 2022 */
object NumberListConversion {

	fun toDouble(list: List<Number>)
		: MutableList<Double> = ArrayList<Double>(list.size).apply {
		for (i in list.indices) add(list[i].toDouble())
	}

	fun toFloat(list: List<Number>)
		: MutableList<Float> = ArrayList<Float>(list.size).apply {
		for (i in list.indices) add(list[i].toFloat())
	}

	fun toLong(list: List<Number>)
		: MutableList<Long> = ArrayList<Long>(list.size).apply {
		for (i in list.indices) add(list[i].toLong())
	}

	fun toInt(list: List<Number>)
		: MutableList<Int> = ArrayList<Int>(list.size).apply {
		for (i in list.indices) add(list[i].toInt())
	}

	fun toShort(list: List<Number>)
		: MutableList<Short> = ArrayList<Short>(list.size).apply {
		for (i in list.indices) add(list[i].toShort())
	}

	fun toByte(list: List<Number>)
		: MutableList<Byte> = ArrayList<Byte>(list.size).apply {
		for (i in list.indices) add(list[i].toByte())
	}

}
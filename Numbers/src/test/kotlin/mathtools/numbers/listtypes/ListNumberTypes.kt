package mathtools.numbers.listtypes

object ListNumberTypes {

	fun convertToDouble(list: List<Number>)
		: MutableList<Double> = ArrayList<Double>(list.size).apply {
		for (i in list.indices) add(list[i].toDouble())
	}

	fun convertToFloat(list: List<Number>)
		: MutableList<Float> = ArrayList<Float>(list.size).apply {
		for (i in list.indices) add(list[i].toFloat())
	}

	fun convertToLong(list: List<Number>)
		: MutableList<Long> = ArrayList<Long>(list.size).apply {
		for (i in list.indices) add(list[i].toLong())
	}

	fun convertToInt(list: List<Number>)
		: MutableList<Int> = ArrayList<Int>(list.size).apply {
		for (i in list.indices) add(list[i].toInt())
	}

	fun convertToShort(list: List<Number>)
		: MutableList<Short> = ArrayList<Short>(list.size).apply {
		for (i in list.indices) add(list[i].toShort())
	}

	fun convertToByte(list: List<Number>)
		: MutableList<Byte> = ArrayList<Byte>(list.size).apply {
		for (i in list.indices) add(list[i].toByte())
	}

}
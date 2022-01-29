package mathtools.numbers.listtypes

object ListNumberTypes {

	fun convertToDouble(list: List<Number>)
		: MutableList<Double> {
		return (list as List<Double>).toMutableList()
	}
	fun convertToFloat(list: List<Number>)
		: MutableList<Float> {
		return (list as List<Float>).toMutableList()
	}

	fun convertToLong(list: List<Number>)
		: MutableList<Long> {
		return (list as List<Long>).toMutableList()
	}
	fun convertToInt(list: List<Number>)
		: MutableList<Int> {
		return (list as List<Int>).toMutableList()
	}
	fun convertToShort(list: List<Number>)
		: MutableList<Short> {
		return (list as List<Short>).toMutableList()
	}
	fun convertToByte(list: List<Number>)
		: MutableList<Byte> {
		return (list as List<Byte>).toMutableList()
	}

}
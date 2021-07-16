package mathtools.numbers.formats


/** Data Container and Formatter
  * Developed by DK96-OS : 2021 */
class DataTable<Key, N : Number>(
    val keyLabels: Array<String>,
) {
    // Each column is indexed, these are the column labels
    internal val columns = ArrayList<String>()

    init { columns.addAll(keyLabels) }

    // The index of the key is it's row index
    internal val keys = ArrayList<Key>()

    // 2D Map of Table values
    private val dataMap = mutableMapOf<Int, MutableMap<Int, N>>()

    /** Convenience for ArrayList item indexing */
    private inline fun <T> ArrayList<T>.getIndexOrInsert(value: T): Int {
        val idx = indexOf(value)
        return if (idx >= 0) idx else {
            add(value)
            size - 1
        }
    }

    /** Insert a value into a specific location, defined by Key and Label */
    fun enter(key: Key, label: String, value: N) {
        val keyIdx = keys.getIndexOrInsert(key)
        val columnIdx = columns.getIndexOrInsert(label)
        val existingRow = dataMap[keyIdx]
        if (existingRow != null)
            existingRow[columnIdx] = value
        else
            dataMap[keyIdx] = mutableMapOf(columnIdx to value)
    }

    /** Insert data into a specific column, defined by Label */
    fun enterColumn(label: String, values: ArrayList<Pair<Key, N>>) {
        val columnIdx = columns.getIndexOrInsert(label)
        for (i in values.indices) {
            val (key, num) = values[i]
            val keyIdx = keys.getIndexOrInsert(key)
            val rowMap = dataMap[keyIdx]
            if (rowMap == null)
                dataMap[keyIdx] = mutableMapOf<Int, N>(columnIdx to num)
            else
                rowMap[columnIdx] = num
        }
    }

    /** Insert data into a specific row, defined by Key */
    fun enterRow(key: Key, values: ArrayList<Pair<String, N>>) {
        val keyIdx = keys.getIndexOrInsert(key)
        var rowMap = dataMap[keyIdx]
        if (rowMap == null) {
            rowMap = mutableMapOf<Int, N>()
            dataMap[keyIdx] = rowMap
        }
        for (i in values.indices) {
            val (label, num) = values[i]
            rowMap[columns.getIndexOrInsert(label)] = num
        }
    }

    /** Format the Data in Comma Separated Value Strings */
    fun getCSVOutput(): MutableList<String> {
        val outputList = ArrayList<String>()
        outputList.add("\n")
        outputList.add("${columns.joinToString(",")},\n")
        for (i in keys.indices) {
            val row = dataMap[i]
            outputList.add(if (row == null) "\n" else buildString {
                val maxColumnIdx = row.keys.maxOrNull() ?: -1
                for (c in 0 until keyLabels.size) {
                    append(keys[i].toString())
                    append(",")
                }
                for (c in keyLabels.size .. maxColumnIdx) {
                    val num = row[c]
                    if (num != null) append(num)
                    append(",")
                }
                append("\n")
            })
        }
        return outputList
    }

    /** Print a Comma Separated Value representation of this DataTable */
    fun printCSV() {
        for (i in getCSVOutput()) print(i)
    }
}

package mathtools.systems.groups

/** Sorting related Group operations */
object GroupSort {

    /** Remove and return the items in the list that match the selector.
      *  @param list A mutable list which will be modified if any items match
      *  @param presorted If the list is sorted according to selector, ie. the matches are all in one consecutive index range in the list.
      *  @param selector The condition to test for determining a match */
    inline fun <T> extractGroup(
        list:MutableList<T>, presorted:Boolean=false, selector: (T) -> Boolean
    ) : MutableList<T>? {
        var i = list.indexOfFirst { selector(it) }
        if (i < 0) return null
        val groupList: MutableList<T> = arrayListOf(list.removeAt(i))
        if (presorted) 
            while (i < list.size && selector(list[i]))
                groupList.add(list.removeAt(i))
        else
            while (i < list.size) {
                if (selector(list[i])) groupList.add(list.removeAt(i))
                else i++
            }
        return groupList
    }

}

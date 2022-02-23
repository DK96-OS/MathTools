package mathtools.lists;

import java.util.ArrayList;
import java.util.List;

/** Converts Numbered List Types
 * @author DK96-OS : 2022 */
public final class NumberListConversion {

    private NumberListConversion() {}

    /** Cast all elements in a List to Double
     * @param list The list of numbers to convert
     * @return A Collection of Double numbers */
    public static List<Double> toDouble(
            List<Number> list
    ) {
        ArrayList<Double> newList = new ArrayList<>();
        for (Number number : list) {
            newList.add(number.doubleValue());
        }
        return newList;
    }

    /** Cast all elements in a List to Float
     * @param list The list of numbers to convert
     * @return A Collection of Float numbers */
    public static List<Float> toFloat(
            List<Number> list
    ) {
        ArrayList<Float> newList = new ArrayList<>();
        for (Number number : list) {
            newList.add(number.floatValue());
        }
        return newList;
    }

    /** Cast all elements in a List to Long
     * @param list The list of numbers to convert
     * @return A Collection of Long numbers */
    public static List<Long> toLong(
            List<Number> list
    ) {
        ArrayList<Long> newList = new ArrayList<>();
        for (Number number : list) {
            newList.add(number.longValue());
        }
        return newList;
    }

    /** Cast all elements in a List to Integer
     * @param list The list of numbers to convert
     * @return A Collection of Integer numbers */
    public static List<Integer> toInt(
            List<Number> list
    ) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (Number number : list) {
            newList.add(number.intValue());
        }
        return newList;
    }

    /** Cast all elements in a List to Short
     * @param list The list of numbers to convert
     * @return A Collection of Short numbers */
    public static List<Short> toShort(
            List<Number> list
    ) {
        ArrayList<Short> newList = new ArrayList<>();
        for (Number number : list) {
            newList.add(number.shortValue());
        }
        return newList;
    }

    /** Cast all elements in a List to Byte
     * @param list The list of numbers to convert
     * @return A Collection of Byte numbers */
    public static List<Byte> toByte(
            List<Number> list
    ) {
        ArrayList<Byte> newList = new ArrayList<>();
        for (Number number : list) {
            newList.add(number.byteValue());
        }
        return newList;
    }

}
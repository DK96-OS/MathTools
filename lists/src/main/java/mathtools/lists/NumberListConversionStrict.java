package mathtools.lists;

import static mathtools.lists.NumberComparison.isEquivalent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Numbered List Conversion functions that are strict
 * @author DK96-OS : 2022 */
public final class NumberListConversionStrict {

    private NumberListConversionStrict() {}

    /** Attempts to convert to a list of bytes
     * @param list The input list to convert
     * @param ignoreInvalid Whether to ignore invalid conversions, or throw exception */
    public static List<Byte> toByteStrict(
            List<Number> list,
            boolean ignoreInvalid
    ) throws NumberFormatException {
        if (list.isEmpty())
            return Collections.emptyList();
        final ArrayList<Byte> newList = new ArrayList<>(list.size());
        for (Number number : list) {
            final byte b = number.byteValue();
            if (isEquivalent(number, b))
                newList.add(b);
            else if (!ignoreInvalid)
                throw new NumberFormatException(
                        "Cannot convert " + number + " to byte"
                );
        }
        return newList;
    }

}

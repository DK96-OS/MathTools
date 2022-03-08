package mathtools.lists;

import static mathtools.lists.NumberComparison.isEquivalent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/** Numbered List Conversion functions that are strict
 * @author DK96-OS : 2022 */
public final class NumberListConversionStrict {

    private NumberListConversionStrict() {}

    /** Attempts to convert to a list of bytes
     * @param list The input list to convert
     * @param ignoreInvalid Whether to ignore errors, or throw exception
     * @return A list of Bytes that were converted exactly */
    @Nonnull
    public static List<Byte> toByteStrict(
            @Nonnull final List<Number> list,
            final boolean ignoreInvalid
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

    /** Attempts to convert to a list of shorts
     * @param list The input list to convert
     * @param ignoreInvalid Whether to ignore errors, or throw exception
     * @return A list of Short that were converted exactly */
    @Nonnull
    public static List<Short> toShortStrict(
            @Nonnull final List<Number> list,
            final boolean ignoreInvalid
    ) throws NumberFormatException {
        if (list.isEmpty())
            return Collections.emptyList();
        final ArrayList<Short> newList = new ArrayList<>(list.size());
        for (Number number : list) {
            final short b = number.shortValue();
            if (isEquivalent(number, b))
                newList.add(b);
            else if (!ignoreInvalid)
                throw new NumberFormatException(
                        "Cannot convert " + number + " to short"
                );
        }
        return newList;
    }

}
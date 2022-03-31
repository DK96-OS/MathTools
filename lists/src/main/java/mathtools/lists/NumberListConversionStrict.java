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

    /** Attempts to convert to a list of Byte
     * @param list The input list to convert
     * @param ignoreInvalid Whether to ignore errors, or throw exception
     * @return A list of Bytes that were converted exactly */
    @Nonnull
    public static List<Byte> toByteStrict(
        @Nonnull final List<Number> list,
        final boolean ignoreInvalid
    ) throws NumberFormatException {
        // Check for empty list
        if (list.isEmpty()) return Collections.emptyList();
        // Allocate new List
        final ArrayList<Byte> newList = new ArrayList<>(list.size());
        for (final Number number : list) {
            final byte value = number.byteValue();
            // Determine if the conversion is lossless
            if (isEquivalent(number, value))
                newList.add(value);
            else if (!ignoreInvalid)
                throw new NumberFormatException(
                    "Cannot convert " + number + " to byte"
                );
        }
        return newList;
    }

    /** Attempts to convert to a list of Short
     * @param list The input list to convert
     * @param ignoreInvalid Whether to ignore errors, or throw exception
     * @return A list of Short that were converted exactly */
    @Nonnull
    public static List<Short> toShortStrict(
        @Nonnull final List<Number> list,
        final boolean ignoreInvalid
    ) throws NumberFormatException {
        // Check for empty list
        if (list.isEmpty()) return Collections.emptyList();
        // Allocate new List
        final ArrayList<Short> newList = new ArrayList<>(list.size());
        for (final Number number : list) {
            final short value = number.shortValue();
            // Determine if the conversion is lossless
            if (isEquivalent(number, value))
                newList.add(value);
            else if (!ignoreInvalid)
                throw new NumberFormatException(
                    "Cannot convert " + number + " to short"
                );
        }
        return newList;
    }

    /** Attempts to convert to a list of Integer
     * @param list The input list to convert
     * @param ignoreInvalid Whether to ignore errors, or throw exception
     * @return A list of Integer that were converted exactly */
    @Nonnull
    public static List<Integer> toIntStrict(
        @Nonnull final List<Number> list,
        final boolean ignoreInvalid
    ) throws NumberFormatException {
        // Check for empty list
        if (list.isEmpty()) return Collections.emptyList();
        // Allocate new List
        final ArrayList<Integer> newList = new ArrayList<>(list.size());
        for (final Number number : list) {
            final int value = number.intValue();
            // Determine if the conversion is lossless
            if (isEquivalent(number, value))
                newList.add(value);
            else if (!ignoreInvalid)
                throw new NumberFormatException(
                    "Cannot convert " + number + " to integer"
                );
        }
        return newList;
    }

    /** Attempts to convert to a list of Long
     * @param list The input list to convert
     * @param ignoreInvalid Whether to ignore errors, or throw exception
     * @return A list of Long that were converted exactly */
    @Nonnull
    public static List<Long> toLongStrict(
        @Nonnull final List<Number> list,
        final boolean ignoreInvalid
    ) throws NumberFormatException {
        // Check for empty list
        if (list.isEmpty()) return Collections.emptyList();
        // Allocate new List
        final ArrayList<Long> newList = new ArrayList<>(list.size());
        for (final Number number : list) {
            final long value = number.longValue();
            // Determine if the conversion is lossless
            if (isEquivalent(number, value))
                newList.add(value);
            else if (!ignoreInvalid)
                throw new NumberFormatException(
                    "Cannot convert " + number + " to long"
                );
        }
        return newList;
    }

}
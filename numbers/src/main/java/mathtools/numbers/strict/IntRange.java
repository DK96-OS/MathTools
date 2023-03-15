package mathtools.numbers.strict;

/** A selection of consecutive integer values.
 * @author DK96-OS : 2022 - 2023
 */
public interface IntRange {

    /** The lowest int in this range.
     */
    int getStartValue();

    /** The largest int in this range.
     */
    int getLastValue();

}
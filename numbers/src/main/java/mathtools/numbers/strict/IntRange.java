package mathtools.numbers.strict;

/** A group of consecutive integer values
 * @author DK96-OS : 2022 */
public interface IntRange {

    /** The lowest int that can be counted by this counter */
    int getStartValue();

    /** The largest int that can be counted by this counter */
    int getLastValue();

}
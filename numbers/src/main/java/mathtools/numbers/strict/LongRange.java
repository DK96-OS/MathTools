package mathtools.numbers.strict;

/** A selection of consecutive long values.
 * @author DK96-OS : 2023
 */
public interface LongRange {

    /** The lowest long in the range.
     */
    long getStartValue();

    /** The largest long in the range.
     */
    long getLastValue();

}
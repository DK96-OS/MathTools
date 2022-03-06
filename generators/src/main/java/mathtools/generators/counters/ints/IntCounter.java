package mathtools.generators.counters.ints;

import java.util.List;

/** Can Count 32-bit Integer values
 * @author DK96-OS : 2022 */
public interface IntCounter {

    /** Increase the count associated with this value
     * @param value The value that will be counted
     * @return True if the value was able to be counted */
    boolean count(
            final int value
    );

    /** Obtain a List of all counts, in ascending order of corresponding value
     * @return A List containing the counts of each value */
    List<Integer> toList();

}
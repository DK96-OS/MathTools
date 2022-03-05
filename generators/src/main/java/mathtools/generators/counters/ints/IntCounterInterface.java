package mathtools.generators.counters.ints;

/** Can Count 32-bit Integer values
 * @author DK96-OS : 2022 */
public interface IntCounterInterface {

    /** Increase the count associated with this value
     * @param value The value that will be counted
     * @return True if the value was able to be counted */
    boolean count(
            final int value
    );

}
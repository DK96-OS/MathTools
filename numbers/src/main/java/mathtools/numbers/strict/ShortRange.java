package mathtools.numbers.strict;

/** A selection of consecutive short values.
 * @author DK96-OS : 2023
 */
public interface ShortRange {

	/** The lowest short in this range.
	 * @return Start Value.
	 */
	short getStartValue();

	/** The largest short in this range.
	 * @return Last Value.
	 */
	short getLastValue();

	/** Determine whether this Range contains a value.
	 * @param value The value to check.
	 * @return True if value is in Range (endpoints included).
	 */
	default boolean contains(
		final short value
	) {
		return getStartValue() <= value
			&& value <= getLastValue();
	}

	/** Obtain the Size of the Range.
	 * @return The number of values in this Range.
	 */
	default int getSize() {
		return getLastValue() - getStartValue() + 1;
	}

}
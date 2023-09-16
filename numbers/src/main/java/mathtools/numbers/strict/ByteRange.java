package mathtools.numbers.strict;

/** A selection of consecutive byte values.
 * @author DK96-OS : 2023
 */
public interface ByteRange {

	/** The lowest byte in this range.
	 * @return Start Value.
	 */
	byte getStartValue();

	/** The largest byte in this range.
	 * @return Last Value.
	 */
	byte getLastValue();

	/** Determine whether this Range contains a value.
	 * @param value The value to check.
	 * @return True if value is in Range (endpoints included).
	 */
	default boolean contains(
		final byte value
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
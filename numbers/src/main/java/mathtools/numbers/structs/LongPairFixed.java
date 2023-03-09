package mathtools.numbers.structs;

/** A pair of primitive 64-bit integers that are immutable.
 * @author DK96-OS : 2023
 */
public class LongPairFixed {

	/** The first value in the Pair.
	 */
	public final long first;

	/** The second value in the Pair.
	 */
	public final long second;

	public LongPairFixed(
		final long first,
		final long second
	) {
		this.first = first;
		this.second = second;
	}

}
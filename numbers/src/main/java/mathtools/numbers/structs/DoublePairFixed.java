package mathtools.numbers.structs;

/** A pair of primitive 64 bit floating point numbers that are immutable.
 * @author DK96-OS : 2023
 */
public class DoublePairFixed {

	/** The first value in the Pair.
	 */
	public final double first;

	/** The second value in the Pair.
	 */
	public final double second;

	public DoublePairFixed(
		final double first,
		final double second
	) {
		this.first = first;
		this.second = second;
	}

}
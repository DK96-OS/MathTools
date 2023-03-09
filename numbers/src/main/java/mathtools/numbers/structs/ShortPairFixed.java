package mathtools.numbers.structs;

/** A pair of primitive 16 bit integers that are immutable.
 * @author DK96-OS : 2023
 */
public class ShortPairFixed {

	/** The first byte in the Pair.
	 */
	public final short first;

	/** The second byte in the Pair.
	 */
	public final short second;

	public ShortPairFixed(
		final short first,
		final short second
	) {
		this.first = first;
		this.second = second;
	}

}
package mathtools.numbers.structs;

/** A pair of primitive 8 bit integers that are immutable.
 * @author DK96-OS : 2023
 */
public class BytePairFixed {

	/** The first byte in the Pair.
	 */
	public final byte first;

	/** The second byte in the Pair.
	 */
	public final byte second;

	public BytePairFixed(
		final byte first,
		final byte second
	) {
		this.first = first;
		this.second = second;
	}

}
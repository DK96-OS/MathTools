package mathtools.numbers.structs;

/** A pair of primitive 32-bit integers that are immutable.
 * @author DK96-OS : 2023
 */
public class IntPairFixed {

	/** The first int in the Pair.
	 */
	public final int first;

	/** The second int in the Pair.
	 */
	public final int second;

	public IntPairFixed(
		final int first,
		final int second
	) {
		this.first = first;
		this.second = second;
	}

}
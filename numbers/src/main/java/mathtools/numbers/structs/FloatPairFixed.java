package mathtools.numbers.structs;

/** A pair of primitive 32 bit floating point numbers that are immutable.
 * @author DK96-OS : 2023
 */
public class FloatPairFixed {

	/** The first value in the Pair.
	 */
	public final float first;

	/** The second value in the Pair.
	 */
	public final float second;

	public FloatPairFixed(
		final float first,
		final float second
	) {
		this.first = first;
		this.second = second;
	}

}
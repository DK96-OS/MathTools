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

	public FloatPair toMutable() {
		return new FloatPair(first, second);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FloatPairFixed) {
			final FloatPairFixed other = (FloatPairFixed) obj;
			return first == other.first
					&& second == other.second;
		} else if (obj instanceof FloatPair) {
			final FloatPair other = (FloatPair) obj;
			return first == other.getFirst()
					&& second == other.getSecond();
		} else
			return false;
	}

}
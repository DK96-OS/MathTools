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

	public ShortPair toMutable() {
		return new ShortPair(first, second);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShortPairFixed) {
			final ShortPairFixed other = (ShortPairFixed) obj;
			return first == other.first
					&& second == other.second;
		} else if (obj instanceof ShortPair) {
			final ShortPair other = (ShortPair) obj;
			return first == other.getFirst()
					&& second == other.getSecond();
		} else
			return false;
	}

}
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

	public DoublePair toMutable() {
		return new DoublePair(first, second);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DoublePairFixed) {
			final DoublePairFixed other = (DoublePairFixed) obj;
			return first == other.first
					&& second == other.second;
		} else if (obj instanceof DoublePair) {
			final DoublePair other = (DoublePair) obj;
			return first == other.getFirst()
					&& second == other.getSecond();
		} else
			return false;
	}

}
package mathtools.pairs;

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
	
	/** Create a Fixed DoublePair.
	 * @param first The first double in the pair.
	 * @param second The second double in the pair.
	 */
	public DoublePairFixed(
		final double first,
		final double second
	) {
		this.first = first;
		this.second = second;
	}

	/** Create a Mutable DoublePair instance.
	 * @return A new DoublePair.
	 */
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

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

}
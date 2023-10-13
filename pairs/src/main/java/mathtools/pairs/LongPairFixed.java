package mathtools.pairs;

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

	/** Create a Fixed LongPair.
	 * @param first The first long in the pair.
	 * @param second The second long in the pair.
	 */
	public LongPairFixed(
		final long first,
		final long second
	) {
		this.first = first;
		this.second = second;
	}

	/** Create a Mutable LongPair instance.
	 * @return A new LongPair.
	 */
	public LongPair toMutable() {
		return new LongPair(first, second);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LongPairFixed) {
			final LongPairFixed other = (LongPairFixed) obj;
			return first == other.first
					&& second == other.second;
		} else if (obj instanceof LongPair) {
			final LongPair other = (LongPair) obj;
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
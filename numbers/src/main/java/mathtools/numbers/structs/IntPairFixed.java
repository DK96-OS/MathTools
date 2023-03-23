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

	/** Create a Fixed IntPair.
	 * @param first The first int in the pair.
	 * @param second The second int in the pair.
	 */
	public IntPairFixed(
		final int first,
		final int second
	) {
		this.first = first;
		this.second = second;
	}

	/** Create a Mutable IntPair instance.
	 * @return A new IntPair.
	 */
	public IntPair toMutable() {
		return new IntPair(first, second);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntPairFixed) {
			final IntPairFixed other = (IntPairFixed) obj;
			return first == other.first
					&& second == other.second;
		} else if (obj instanceof IntPair) {
			final IntPair other = (IntPair) obj;
			return first == other.getFirst()
					&& second == other.getSecond();
		} else
			return false;
	}
	
}
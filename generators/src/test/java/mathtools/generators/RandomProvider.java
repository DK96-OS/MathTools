package mathtools.generators;

import java.util.Random;

import javax.annotation.Nonnull;

/** Provides object satisfying [Random] interface for testing
 * @author DK96-OS : 2022 */
public final class RandomProvider {

	/** A Random that always returns the same value
	 * @param value The number that will always be returned
	 * @return An object implementing Random interface */
	@Nonnull
	public static Random fixedValue(
		final int value
	) {
		return new Random() {
			@Override
			public int nextInt(int bound) { return value; }

			@Override
			public int nextInt() { return value; }
		};
	}

	/** A Random that returns one value, then the other
	 * @param first The first value to return
	 * @param second The value to return afterwards
	 * @return An object implementing Random interface */
	@Nonnull
	public static Random firstThenSecondValue(
		final int first,
		final int second
	) {
		return new Random() {
			private boolean hasReturned = false;
			private final int mFirst = first;
			private final int mSecond = second;

			@Override
			public int nextInt(int bound) { return nextInt(); }

			@Override
			public int nextInt() {
				if (hasReturned) return mSecond;
				hasReturned = true;
				return mFirst;
			}
		};
	}

}
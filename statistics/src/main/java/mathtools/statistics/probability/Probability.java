package mathtools.statistics.probability;

import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.Nonnull;

/** Probability Operations.
 * @author DK96-OS : 2022
 */
public final class Probability {

	/** The Random Number Generator instance to use.
	 */
	private final Random mRNG;

	/** Construct Probability with default SecureRandom.
	 */
	public Probability() {
		mRNG = new SecureRandom();
	}

	/** Construct Probability with a given Random instance.
	 * @param rng The Random number generator to use for operations.
	 */
	public Probability(
		@Nonnull final Random rng
	) {
		mRNG = rng;
	}

	/** Return true once every total times this function is called.
	 * @param total The approximate ratio of false:true probabilities.
	 * @return True approximately one in total times.
	 */
	public boolean oneIn(
		final int total
	) {
		if (1 < total)
			return 0.9999999f > total * mRNG.nextFloat();
		else if (1 == total)
			return true;
		else
			throw new IllegalArgumentException(
				"Total must not be less than 1"
			);
	}

}
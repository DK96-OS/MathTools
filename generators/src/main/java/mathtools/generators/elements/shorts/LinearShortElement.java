package mathtools.generators.elements.shorts;

import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.Nonnull;

/** An element with a linearly changing probability.
 *  Max Length (Number of outcomes) = 180
 *  Max Rate of Change (Linear Slope) = 200
 * @author DK96-OS : 2022 */
public final class LinearShortElement
	implements ShortElementInterface {

	private short mLength;

	private short mRate;

	private int mLengthStates;

	private final Random mRNG;

	/** Create a new Linear Short Element with SecureRandom RNG
	 * @param length The number of outcomes
	 * @param rate The rate of change in probability between outcomes
	 * @throws IllegalArgumentException if length is negative or zero, or rate is negative or zero
	 */
	public LinearShortElement(
		final short length,
		final short rate
	) throws IllegalArgumentException {
		// Validate arguments
		if (1 > length || 180 < length)
			throw new IllegalArgumentException("Invalid Length");
		if (1 > rate || 200 < rate)
			throw new IllegalArgumentException("Invalid Rate");
		//
		mLength = length;
		mRate = rate;
		mLengthStates = countLinearStates(length);
		mRNG = new SecureRandom();
	}

	/** Create a new Linear Short Element with a given RNG instance
	 * @param length The number of outcomes
	 * @param rate The rate of change in probability between outcomes
	 * @param rng The Random number generator to use
	 * @throws IllegalArgumentException if length is negative or zero, or rate is negative or zero
	 */
	public LinearShortElement(
		final short length,
		final short rate,
		@Nonnull final Random rng
	) {    // Validate arguments
		if (1 > length || 180 < length)
			throw new IllegalArgumentException("Invalid Length");
		if (1 > rate || 200 < rate)
			throw new IllegalArgumentException("Invalid Rate");
		//
		mLength = length;
		mRate = rate;
		mLengthStates = countLinearStates(length);
		mRNG = rng;
	}

	/** Obtain the Range Length, or the number of outcomes */
	public short getLength() {
		return mLength;
	}

	/** Set the length of the Element, or the number of outcomes
	 * @param length The new number of outcomes
	 * @return Whether the new value is valid, and has been updated */
	public boolean setLength(
		final short length
	) {
		if (1 > length ||
			180 < length ||
			mLength == length
		) return false;
		//
		mLength = length;
		mLengthStates = countLinearStates(length);
		return true;
	}

	/** Obtain the rate of change in probability between outcomes */
	public short getRate() {
		return mRate;
	}

	/** Sets the rate of change in probability of the element
	 * @param rate The change in probability between outcomes. Max 200.
	 * @return Whether the new value is valid, and has been updated
	 */
	public boolean setRate(
		final short rate
	) {
		if (1 > rate ||
			200 < rate ||
			mRate == rate
		) return false;
		//
		mRate = rate;
		return true;
	}

	@Override
	public short generate() {
		// Select one of the Element micro-states
		final int selectedState = mRNG.nextInt(mLengthStates);
		//
		int sCounter = mLength - 1;
		short tCounter = 1;
		for (;
		     selectedState > sCounter && mLength > tCounter;
		     ++tCounter
		) {
			sCounter += mLength - tCounter;
		}
		return tCounter;
	}

	/** Calculate the minimum number of Microstates for a Linear element
	 * @param length The number of Macrostates. Must be less than 181.
	 * @return The minimum number of Microstates required */
	public static int countLinearStates(
		short length
	) {
		// Zero or negative lengths are invalid
		if (1 > length) return 0;
		// Max length is 180
		if (180 < length) return 16290;
		// Check for specific lengths
		switch (length) {
			case 1: return 1;
			case 6: return 21;
			case 20: return 210;
			case 40: return 820;
			case 80: return 3240;
			case 120: return 7260;
			case 150: return 11325;
			default: return length + countLinearStates(--length);
		}
	}

}
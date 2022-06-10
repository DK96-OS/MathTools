package mathtools.statistics.probability;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/** Testing Probability
 * @author DK96-OS : 2022
 */
public final class ProbabilityTest {

	private Random mRandom;
	private Probability mProbability;

	@BeforeEach
	public void testSetup() {
		mRandom = new Random(400L);
		mProbability = new Probability(mRandom);
	}

	@Test
	public void testOneInOne() {
		for (
			byte i = 0; 10 > i; ++i
		) assertTrue(
			mProbability.oneIn(1)
		);
	}

	@Test
	public void testInvalidInputs() {
		assertThrows(
			IllegalArgumentException.class,
			() -> mProbability.oneIn(0)
		);
		assertThrows(
			IllegalArgumentException.class,
			() -> mProbability.oneIn(-1)
		);
	}

}
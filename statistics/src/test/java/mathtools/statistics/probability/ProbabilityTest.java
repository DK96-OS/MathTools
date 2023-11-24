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
	private Probability mInstance;

	@BeforeEach
	public void testSetup() {
		mRandom = new Random(400L);
		mInstance = new Probability(mRandom);
	}

	@Test
	public void testOneIn_One_ReturnsTrue() {
		for (
			byte i = 0; 10 > i; ++i
		) assertTrue(
			mInstance.oneIn(1)
		);
	}

	@Test
	public void testOneIn_InvalidInput_Zero_ThrowsException() {
		assertThrows(
			IllegalArgumentException.class,
			() -> mInstance.oneIn(0)
		);
	}

	@Test
	public void testOneIn_InvalidInput_Negative1_ThrowsException() {
		assertThrows(
			IllegalArgumentException.class,
			() -> mInstance.oneIn(-1)
		);
	}

}
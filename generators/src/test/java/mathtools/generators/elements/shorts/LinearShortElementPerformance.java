package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.generators.elements.shorts.LinearShortElement.countLinearStates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import mathtools.generators.RandomProvider;

/** Compare the Performance of [LinearShortElement] scenarios */
public final class LinearShortElementPerformance {

	private static final short initialRate = 1;

	private LinearShortElement mElement1;
	private LinearShortElement mElementLarge;

	private static final short largeLength = 150;
	private static final int mStateCountLarge = countLinearStates(largeLength);

	private static final int trials = 1_000_000;

	private final Random mFixedRandom1 = RandomProvider.fixedValue(1);
	private final Random mFixedRandomLarge = RandomProvider.fixedValue(mStateCountLarge);

	@BeforeEach
	public void testSetup() {
		mElement1 = new LinearShortElement(
			largeLength, initialRate, mFixedRandom1
		);
		mElementLarge = new LinearShortElement(
			largeLength, initialRate, mFixedRandomLarge
		);
	}

	@Test
	public void compareStateSelection() {
		 for (int i = 0; trials > i; ++i) {
			 mElement1.generate();
			 mElementLarge.generate();
		 }
	}

	@Test
	public void compareStateSelection1() {
		for (int i = 0; trials > i; ++i) {
			assertEquals(
				1, mElement1.generate());
		}
	}

	@Test
	public void compareStateSelectionLarge() {
		for (int i = 0; trials > i; ++i) {
			assertEquals(
				150, mElementLarge.generate());
		}
	}

}
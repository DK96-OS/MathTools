package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import mathtools.generators.RandomProvider;

/** Compare the Performance of [LinearShortElement] scenarios */
public final class LinearShortElementPerformance {

	private LinearShortElement mElement1;
	private LinearShortElement mElement150;

	private final Random mFixedRandom1 = RandomProvider.fixedValue(1);
	private final Random mFixedRandom150 = RandomProvider.fixedValue(nStates);

	private static final int trials = 1_000_000;

	private static final int nStates
		= LinearShortElement.countLinearStates((short) 150);

	@BeforeEach
	public void testSetup() {
		mElement1 = new LinearShortElement(
			(short) 150,
			(short) 1,
			mFixedRandom1
		);
		mElement150 = new LinearShortElement(
			(short) 150,
			(short) 1,
			mFixedRandom150
		);
	}

	@Test
	public void compareStateSelection() {
		 for (int i = 0; trials > i; ++i) {
			 mElement1.generate();
			 mElement150.generate();
		 }
	}

	@Test
	public void compareStateSelection1() {
		for (int i = 0; trials > i; ++i) {
			assertEquals(
				1, mElement1.generate()
			);
		}
	}

	@Test
	public void compareStateSelection150() {
		for (int i = 0; trials > i; ++i) {
			assertEquals(
				150, mElement150.generate()
			);
		}
	}

}
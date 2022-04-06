package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;

import mathtools.generators.counters.ints.IntCounter32000;

/** Inspection of the generated values of [LinearShortElement]
 * @author DK96-OS : 2022 */
public final class LinearShortElementAnalysis {

	private LinearShortElement mElement;

	private IntCounter32000 mCounter;

	@Test
	public void testLength9() {
		mElement = new LinearShortElement((short) 9, (short) 1);
		mCounter = new IntCounter32000(1, 9);
		//
		for (int i = 0; 160_000 > i; ++i) {
			assertTrue(
				mCounter.count(mElement.generate()));
		}
		final List<Integer> results = mCounter.toList();
		for (int i = 0; i < results.size(); ++i) {
			final int outcome = i + 1;
			System.out.printf(
				"%d : %d\n", outcome, results.get(i)
			);
		}
		assert 0 < mCounter.getCountOf(1);
		assert 0 < mCounter.getCountOf(9);
	}

	@Test
	public void testLength20() {
		mElement = new LinearShortElement((short) 20, (short) 1);
		mCounter = new IntCounter32000(1, 20);
		//
		for (int i = 0; 300_000 > i; ++i) {
			assertTrue(
				mCounter.count(mElement.generate()));
		}
		final List<Integer> results = mCounter.toList();
		for (int i = 0; i < results.size(); ++i) {
			final int outcome = i + 1;
			System.out.printf(
				"%d : %d\n", outcome, results.get(i)
			);
		}
		assert 0 < mCounter.getCountOf(1);
		assert 0 < mCounter.getCountOf(20);
	}

	@Test
	public void testLength150() {
		mElement = new LinearShortElement((short) 150, (short) 1);
		mCounter = new IntCounter32000(1, 150);
		//
		for (int i = 0; 1_000_000 > i; ++i) {
			assertTrue(
				mCounter.count(mElement.generate()));
		}
		final List<Integer> results = mCounter.toList();
		for (int i = 0; i < results.size(); ++i) {
			final int outcome = i + 1;
			System.out.printf(
				"%d : %d\n", outcome, results.get(i)
			);
		}
		assert 0 < mCounter.getCountOf(1);
		assert 0 < mCounter.getCountOf(150);
	}

}
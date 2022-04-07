package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import mathtools.generators.counters.ints.IntCounter32000;

/** Inspection of the generated values of [LinearShortElement]
 * @author DK96-OS : 2022 */
public final class LinearShortElementAnalysis {

	private LinearShortElement mElement;
	private IntCounter32000 mCounter;

	@AfterEach
	public void testCleanup() {
		mElement = null;
		mCounter = null;
	}

	@ParameterizedTest
	@ValueSource(shorts = { 9, 25, 100, 150 })
	public void testLengths(
		final short length
	) {
		mElement = new LinearShortElement(length);
		mCounter = new IntCounter32000(1, length);
		//
		for (int i = 0; 160_000 > i; ++i) {
			assertTrue(
				mCounter.count(mElement.generate()));
		}
		final List<Integer> results = mCounter.toList();
		// Print the results
		for (int i = 0; i < results.size(); ++i) {
			final int outcome = i + 1;
			System.out.printf(
				"%d : %d\n", outcome, results.get(i)
			);
		}
		assert 0 < mCounter.getCountOf(1);
		assert 0 < mCounter.getCountOf(length);
		assertEquals(
			length, results.size());
	}

}
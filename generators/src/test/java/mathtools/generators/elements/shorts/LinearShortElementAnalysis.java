package mathtools.generators.elements.shorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import mathtools.generators.counters.ints.IntCounter32000;

/** Inspection of the generated values of [LinearShortElement]
 * @author DK96-OS : 2022 */
public final class LinearShortElementAnalysis {

	private LinearShortElement mElement;
	private IntCounter32000 mCounter;

	private static final short initialLength = 12;
	private static final short initialRate = 1;

	@ParameterizedTest
	@ValueSource(shorts = { 9, 25, 100, 150 })
	public void testLengths(
		final short length
	) {
		mElement = new LinearShortElement(length, initialRate);
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

	@ParameterizedTest
	@ValueSource(shorts = { 2, 3, 5, 7, 10 })
	public void testRates(
		final short rate
	) {
		mElement = new LinearShortElement(initialLength, rate);
		mCounter = new IntCounter32000(1, initialLength);
		//
		for (int i = 0; 160_000 > i; ++i) {
			final short value = mElement.generate();
			assertTrue(
				mCounter.count(value), "Cannot count: " + value);
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
		assert 0 < mCounter.getCountOf(initialLength);
		assertEquals(
			initialLength, results.size());
	}

}
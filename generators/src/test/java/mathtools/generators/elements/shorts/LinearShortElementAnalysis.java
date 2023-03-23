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
		// Format the largest number
		final byte largestNumberLength = (byte) String.valueOf(mCounter.getLastValue()).length();
		final String formatString;
		switch (largestNumberLength) {
			case 2: formatString = "%2d "; break;
			case 3: formatString = "%3d "; break;
			case 4: formatString = "%4d "; break;
			default: formatString = "%d ";
		}
		for (int i = 0; i < results.size(); ++i) {
			System.out.printf(
					formatString.concat(": %d\n"),
					i + 1,
					results.get(i)
			);
		}
		assert 0 < mCounter.getCountOf(1);
		assert 0 < mCounter.getCountOf(length);
		assertEquals(
			length, results.size());
	}

}
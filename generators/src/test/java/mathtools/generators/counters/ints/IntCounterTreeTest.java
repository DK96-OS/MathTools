package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Testing [IntTreeCounter]
 * @author DK96-OS : 2022 */
public final class IntCounterTreeTest {

    @Test
    void testInvalidConstructor() {
        final IntRangeCounter lower = new IntCounter127(2, 9);
        final IntRangeCounter higher = new IntCounter127(10, 17);
        // Reversed
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounterTree(higher, lower));
        // Same counter
        assertThrows(IllegalArgumentException.class,
                () -> new IntCounterTree(higher, higher));
    }

    @Test
    void testIntRangeMethods() {
        final IntRangeCounter lower = new IntCounter127(2, 9);
        final IntRangeCounter higher = new IntCounter127(10, 17);
        final IntCounterTree tree = new IntCounterTree(lower, higher);
        //
        assertEquals(
                2, tree.getStartValue());
        assertEquals(
                17, tree.getLastValue());
    }

    @Test
    void testIntRangeMethodsTwoLevels() {
        final IntRangeCounter range0 = new IntCounter127(2, 9);
        final IntRangeCounter range1 = new IntCounter127(10, 17);
        final IntRangeCounter range2 = new IntCounter127(18, 25);
        final IntRangeCounter range3 = new IntCounter127(26, 33);
        //
        final IntCounterTree root = new IntCounterTree(
                new IntCounterTree(range0, range1),
                new IntCounterTree(range2, range3)
        );
        assertEquals(
                2, root.getStartValue());
        assertEquals(
                33, root.getLastValue());
        //
        assertTrue(root.count(2));
        assertTrue(root.count(10));
        assertTrue(root.count(18));
        assertTrue(root.count(26));
        //
        assertFalse(root.count(1));
        assertFalse(root.count(34));
    }

}
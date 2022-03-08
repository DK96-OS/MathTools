package mathtools.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Testing [DoubleList.largeSum] method
 * @author DK96-OS : 2022 */
public final class DoubleListLargeSumTest {

    @Test
    void testEmptyList() {
        assertEquals(
                BigDecimal.ZERO,
                DoubleList.INSTANCE.largeSum(Collections.emptyList())
        );
    }

    @Test
    void testSingleValuedList() {
        assertEquals(
                BigDecimal.valueOf(1.0),
                DoubleList.INSTANCE.largeSum(List.of(1.0))
        );
        assertEquals(
                BigDecimal.valueOf(33.56),
                DoubleList.INSTANCE.largeSum(List.of(33.56))
        );
    }

    @Test
    void testMaxDouble() {
        final double max = Double.MAX_VALUE;
        final BigDecimal bigMax = BigDecimal.valueOf(max);
        assertEquals(
                0, bigMax.compareTo(
                        DoubleList.INSTANCE.largeSum(List.of(max))
                )
        );
        //
        final List<Double> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(Double.MAX_VALUE);
        assertEquals(
                0, bigMax.multiply(BigDecimal.TEN).compareTo(
                        DoubleList.INSTANCE.largeSum(list)
                )
        );
    }

    @Test
    void testNegativeValues() {
        final double max = -Double.MAX_VALUE;
        final BigDecimal bigMax = BigDecimal.valueOf(max);
        assertEquals(
                0, bigMax.compareTo(
                        DoubleList.INSTANCE.largeSum(List.of(max))
                )
        );
        final List<Double> list = new ArrayList<>();
        for (int i = 0; i< 10; i++) list.add(max);
        assertEquals(
                0, bigMax.multiply(BigDecimal.TEN).compareTo(
                        DoubleList.INSTANCE.largeSum(list)
                )
        );
    }

}
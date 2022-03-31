package mathtools.lists;

import java.util.ArrayList;
import java.util.List;

/** Testing Resources
 * @author DK96-OS : 2022 */
public final class NumberListTestResources {

    public static final List<Number> shortList = new ArrayList<>();
    public static final List<Number> intList = new ArrayList<>();
    public static final List<Number> longList = new ArrayList<>();

    static {
        shortList.add((short) 100);
        shortList.add((short) Short.MAX_VALUE);
        //
        intList.add((int) 100);
        intList.add((int) Short.MAX_VALUE);
        intList.add((int) Integer.MAX_VALUE);
        //
        longList.add(100L);
        longList.add((long) Short.MAX_VALUE);
        longList.add((long) Integer.MAX_VALUE);
        longList.add((long) Long.MAX_VALUE);
    }

}
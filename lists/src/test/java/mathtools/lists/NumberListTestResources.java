package mathtools.lists;

import java.util.ArrayList;
import java.util.List;

/** Testing Resources
 * @author DK96-OS : 2022 */
public final class NumberListTestResources {

    public static final List<Number> shortList = new ArrayList<>();
    public static final List<Number> intList = new ArrayList<>();

    static {
        shortList.add((short) 100);
        shortList.add((short) Short.MAX_VALUE);
        //
        intList.add((int) 100);
        intList.add((int) Short.MAX_VALUE);
        intList.add((int) Integer.MAX_VALUE);
    }

}
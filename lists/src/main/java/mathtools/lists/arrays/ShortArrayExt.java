package mathtools.lists.arrays;

public class ShortArrayExt {

    private ShortArrayExt() {}

    public static long sum(short[] array) {
        long sum = 0;
        for (short s : array) sum += s;
        return sum;
    }

}
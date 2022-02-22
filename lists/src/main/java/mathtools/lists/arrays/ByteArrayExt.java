package mathtools.lists.arrays;

public class ByteArrayExt {

    private ByteArrayExt() {}

    public static long sum(byte[] array) {
        long sum = 0;
        for (byte b : array) sum += b;
        return sum;
    }

}
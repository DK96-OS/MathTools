package mathtools.generators.elements.ints;

import java.security.SecureRandom;
import java.util.Random;

/** An Element that produces 32-bit Integers */
public interface IntElementInterface {

    /** Generate a new integer */
    int generate();

    static final Random random = new SecureRandom();

}

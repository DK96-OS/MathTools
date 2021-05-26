package mathtools.systems.parabolic

/** A solved Parabolic System's key functions
 * Developed by DK96-OS : 2019 */
object SimpleParabolicSystem {

    /** The parabolic function */
    fun parabola(a: Float, b: Float, c: Float, x: Float) = ((a * x) + b) * x + c

    /** Solving the A constant for the given system parameters */
    fun parabolicA(l1: Float, l2: Float, S: Float) = (S / 100f) - (l2 - l1) / 10000f

    /** Solving the B constant, given A and the system parameter S */
    fun parabolicB(a: Float, S: Float) = S - 200f * a

    /** Calculates the value at x for the given system parameters */
    fun calc(l1: Float, l2: Float, s: Float, x: Float): Float {
        val S = (l2 - l1) / s
        val a = parabolicA(l1, l2, S)
        return parabola(a, parabolicB(a, S), l1, x * 100f)
    }
}

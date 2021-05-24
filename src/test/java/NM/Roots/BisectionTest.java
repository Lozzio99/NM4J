package NM.Roots;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.*;

class BisectionTest {

    @Test
    @DisplayName("FindRoot")
    void FindRoot() {
        Root test = new Bisection(x -> pow(x, 2) - 2).setEpsilon(.1);
        assertEquals(1.4375, test.findRoot(1, 2));
        assertArrayEquals(new Double[]{1.5, 1.25, 1.375}, test.getP_stages().toArray(Double[]::new));
    }

    @Test
    @DisplayName("TestFindRoot")
    void TestFindRoot() {
        Root test = new Bisection(Double::valueOf);
        assertThrows(UnsupportedOperationException.class, () -> test.findRoot(0));
    }
}
package NM.Roots;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.*;

class SecantTest {

    @Test
    @DisplayName("FindRoot")
    void FindRoot() {
        Root secant = new Secant(x -> pow(x, 2) - 2).setEpsilon(1e-3);
        assertEquals(1.41421143847487, secant.findRoot(1, 2));
        assertArrayEquals(new Double[]{1.3333333333333335, 1.4000000000000001, 1.4146341463414633, 1.41421143847487}, secant.getP_stages().toArray(Double[]::new));
    }

    @Test
    @DisplayName("TestFindRoot")
    void TestFindRoot() {
        Root secant = new Secant(Double::valueOf);
        assertThrows(UnsupportedOperationException.class, () -> secant.findRoot(0));
    }
}
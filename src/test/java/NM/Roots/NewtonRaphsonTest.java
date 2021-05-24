package NM.Roots;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.*;

class NewtonRaphsonTest {

    @Test
    @DisplayName("FindRoot")
    void FindRoot() {
        Root newton = new NewtonRaphson(x -> pow(x, 2) - 2).setEpsilon(1e-4);
        assertEquals(1.4142135634427837, newton.findRoot(1));
        assertArrayEquals(new Double[]{1.4997501249376068, 1.416680497711029, 1.4142165798805022, 1.4142135634427837},
                newton.getP_stages().toArray(Double[]::new));
    }

    @Test
    @DisplayName("TestFindRoot")
    void TestFindRoot() {
        Root newton = new NewtonRaphson(Double::valueOf);
        assertThrows(UnsupportedOperationException.class, () -> newton.findRoot(0, 1));
    }
}
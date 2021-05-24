package NM.Roots;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RootTest {

    @Test
    @DisplayName("SetEpsilon")
    void SetEpsilon() {
        Root r = new Secant(x -> x).setEpsilon(1e-5);
        assertEquals(1e-5, r.epsilon);
    }

    @Test
    @DisplayName("GetP_stages")
    void GetP_stages() {
        Root r = new Secant(x -> x);
        assertEquals(new ArrayList<>(), r.getP_stages());
    }

    @Test
    @DisplayName("GetRoot")
    void GetRoot() {
        Root r = new Secant(x -> x).setEpsilon(.1);
        Root finalR = r;
        assertThrows(IllegalStateException.class, () -> finalR.getRoot(0));
        r = new Secant(x -> pow(x, 2) - 2);
        r.findRoot(1, 2);
        assertEquals(1.41, r.getRoot(2));
    }
}

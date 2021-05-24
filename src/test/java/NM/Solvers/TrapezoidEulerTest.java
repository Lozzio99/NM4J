package NM.Solvers;

import NM.Util.functions.Fty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrapezoidEulerTest {

    @Test
    @DisplayName("Step")
    void Step() {
        final Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver trapezoid = new TrapezoidEuler(f).setH(1).setInitialTime(0).setTimeFinal(1);

        double h = 1;
        trapezoid.setInitialTime(0).setH(h).setInitialValue(0);
        trapezoid.solve();
        assertEquals(.183939, trapezoid.getW(6));


        h = .5;
        trapezoid.setInitialTime(0).setH(h).setInitialValue(0);
        trapezoid.solve();
        assertEquals(.468457, trapezoid.getW(6));


        h = .2;
        trapezoid.setInitialTime(0).setH(h).setInitialValue(0);
        trapezoid.solve();
        assertEquals(.499971, trapezoid.getW(6));


        h = .1;
        trapezoid.setInitialTime(0).setH(h).setInitialValue(0);
        trapezoid.solve();
        assertEquals(.502638, trapezoid.getW(6));
    }
}
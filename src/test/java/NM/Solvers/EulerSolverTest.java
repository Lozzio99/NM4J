package NM.Solvers;

import NM.Util.functions.Fty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EulerSolverTest {
    @Test
    @DisplayName("Step")
    void Step() {
        final Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver euler = new EulerSolver(f).setH(1).setInitialTime(0).setTimeFinal(1);
        euler.step(0, euler.h);
        assertEquals(1, euler.getW());
        double h = .5;
        euler.setInitialTime(0).setH(h);
        euler.step(0, h);
        assertEquals(.5, euler.getW());
        euler.setInitialTime((float) (0 + h));
        euler.step(euler.getW(), h);
        assertEquals(.678, euler.getW(3));

        h = .2;
        euler.setInitialTime(0).setH(h).setInitialValue(0);
        euler.solve();
        assertEquals(.56455, euler.getW(5));


        h = .1;
        euler.setInitialTime(0).setH(h).setInitialValue(0);
        euler.solve();
        assertEquals(.5329, euler.getW(5));
    }
}
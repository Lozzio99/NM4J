package NM.Solvers.RungeKutta;

import NM.Solvers.Solver;
import NM.Util.functions.Fty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Ralston_s2ndTest {

    @Test
    @DisplayName("Step")
    void Step() {
        final Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver ralston = new Ralston_s2nd(f).setH(1).setInitialTime(0).setTimeFinal(1);

        double h = .1;
        ralston.setInitialTime(0).setH(h).setInitialValue(0);
        ralston.solve();
        assertEquals(.5026588, ralston.getW(7));


    }

    @Test
    @DisplayName("Ralston2ndOrderStep")
    void Ralston2ndOrderStep() {

        final Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver ralston = new Ralston_s2nd(f);
        double h = .1;
        ralston.setInitialTime(0).setH(h).setInitialValue(0);
        double staticStep = Ralston_s2nd.Ralston2ndOrderStep(f, 0, h, .1);
    }
}
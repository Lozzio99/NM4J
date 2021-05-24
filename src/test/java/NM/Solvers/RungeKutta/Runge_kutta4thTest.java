package NM.Solvers.RungeKutta;

import NM.Solvers.Solver;
import NM.Util.functions.Fty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Runge_kutta4thTest {

    @Test
    @DisplayName("Step")
    void Step() {
        Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver rk = new Runge_kutta4th(f).setInitialValue(0).setTimeFinal(1);
        double h = .5;
        rk.setH(h);
        rk.step(rk.getW(), h);
        assertEquals(.36609, rk.getW(5));
        rk.setInitialTime((float) (0 + h));
        rk.step(rk.getW(), h);
        assertEquals(.5025, rk.getW(6));
    }

    @Test
    @DisplayName("RungeKutta4thStep")
    void RungeKutta4thStep() {
        Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        assertEquals(.36609, Runge_kutta4th.RungeKutta4thStep(f, 0, 0, .5), 1e-3);
        assertEquals(.5025, Runge_kutta4th.RungeKutta4thStep(f, .5, 0.36609962087206827, .5), 1e-3);

    }
}
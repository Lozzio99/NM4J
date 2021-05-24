package NM.Solvers.MultiStep;

import NM.Solvers.Solver;
import NM.Util.functions.Fty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Adam_BashforthTest {

    @Test
    @DisplayName("AdamBashforth2ndStep")
    void AdamBashforth2ndStep() {
        Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver solver = new Adam_Bashforth(f)
                .setOrder(2)
                .setInitialValue(0)
                .setInitialTime(0)
                .setTimeFinal(1)
                .setH(.1);
        solver.solve();
        assertEquals(.46647, solver.getW(5));
    }

    @Test
    @DisplayName("AdamBashforth2ndStep")
    void Step() {
        Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        double w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;
        w1 = 0.094830;
        w2 = Adam_Bashforth.AdamBashforth2ndStep(f, w1, 0, .1, 0, .1);
        w3 = Adam_Bashforth.AdamBashforth2ndStep(f, w2, w1, .2, .1, .1);
        w4 = Adam_Bashforth.AdamBashforth2ndStep(f, w3, w2, .3, .2, .1);
        w5 = Adam_Bashforth.AdamBashforth2ndStep(f, w4, w3, .4, .3, .1);
        w6 = Adam_Bashforth.AdamBashforth2ndStep(f, w5, w4, .5, .4, .1);
        w7 = Adam_Bashforth.AdamBashforth2ndStep(f, w6, w5, .6, .5, .1);
        w8 = Adam_Bashforth.AdamBashforth2ndStep(f, w7, w6, .7, .6, .1);
        w9 = Adam_Bashforth.AdamBashforth2ndStep(f, w8, w7, .8, .7, .1);
        w10 = Adam_Bashforth.AdamBashforth2ndStep(f, w9, w8, .9, .8, .1);
        assertEquals(.1792, w2, 1e-3);
        assertEquals(.2524, w3, 1e-3);
        assertEquals(.3146, w4, 1e-3);
        assertEquals(.36648, w5, 1e-3);
        assertEquals(.40875, w6, 1e-3);
        assertEquals(.4424, w7, 1e-3);
        assertEquals(.46844, w8, 1e-3);
        assertEquals(.48788, w9, 1e-3);
        assertEquals(.50167, w10, 1e-3);
    }
}
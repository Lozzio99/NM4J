package NM.Solvers;

import NM.Util.functions.Fty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MidPointEulerTest {

    @Test
    @DisplayName("Step")
    void Step() {

        final Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver midpoint = new MidPointEuler(f).setH(1).setInitialTime(0).setTimeFinal(1);
        double h = .5;
        midpoint.setInitialTime(0).setH(h);
        midpoint.step(0, h);
        assertEquals(.3581, midpoint.getW(4));
        midpoint.setInitialTime((float) (0 + h));
        midpoint.step(midpoint.getW(), h);
        assertEquals(.4802, midpoint.getW(4));


        h = .2;
        midpoint.setInitialTime(0).setH(h).setInitialValue(0);
        midpoint.solve();
        assertEquals(.50041, midpoint.getW(5));


        h = .1;
        midpoint.setInitialTime(0).setH(h).setInitialValue(0);
        midpoint.solve();
        assertEquals(.50266, midpoint.getW(5));
    }
}
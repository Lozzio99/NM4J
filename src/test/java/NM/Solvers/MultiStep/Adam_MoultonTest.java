package NM.Solvers.MultiStep;

import NM.Solvers.Solver;
import NM.Util.functions.Fty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Adam_MoultonTest {

    @Test
    @DisplayName("Step")
    void Step() {
        Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        Solver am = new Adam_Moulton(f).setOrder(3).setH(.1).setInitialTime(0).setInitialValue(0).setTimeFinal(1);
        am.solve();
        assertEquals(0.50817, am.getW(), 1e-4);
        System.out.println(am.getW());
    }

    @Test
    @DisplayName("PredictorCorrector")
    void AdamMoulton3rdOrderCorrector() {
        Fty<Double> f = (t, y) -> exp(-t) - pow(y, 2);
        double w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;
        double y3, y4, y5, y6, y7, y8, y9, y10;
        w1 = 0.09485432;
        w2 = 0.17923033;
        w3 = 0.25222940;
        w4 = 0.31446243;
        w5 = 0.366457;
        w6 = 0.40897734;
        w7 = 0.44293043;
        w8 = 0.46928659;
        w9 = 0.48901809;
        w10 = 0.503055859;
        y3 = Adam_Moulton.AdamMoultoun3rdStep(f, w3, w2, w1, 0, .2, .1);
        y4 = Adam_Moulton.AdamMoultoun3rdStep(f, w4, w3, w2, w1, .2, .1);
        y5 = Adam_Moulton.AdamMoultoun3rdStep(f, w5, w4, w3, w2, .3, .1);
        y6 = Adam_Moulton.AdamMoultoun3rdStep(f, w6, w5, w4, w3, .4, .1);
        y7 = Adam_Moulton.AdamMoultoun3rdStep(f, w7, w6, w5, w4, .5, .1);
        y8 = Adam_Moulton.AdamMoultoun3rdStep(f, w8, w7, w6, w5, .6, .1);
        y9 = Adam_Moulton.AdamMoultoun3rdStep(f, w9, w8, w7, w6, .7, .1);
        y10 = Adam_Moulton.AdamMoultoun3rdStep(f, w10, w9, w8, w7, .8, .1);
        assertEquals(w3, y3, 1e-1);
        assertEquals(w4, y4, 1e-1);
        assertEquals(w5, y5, 1e-1);
        assertEquals(w6, y6, 1e-1);
        assertEquals(w7, y7, 1e-1);
        assertEquals(w8, y8, 1e-1);
        assertEquals(w9, y9, 1e-1);
        assertEquals(w10, y10, 1e-1);
    }
}
package NM.Integration;

import NM.Util.functions.Fx;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegratorTest {

    static Trapezoid t;
    static int a,b;
    static double []xs ;
    static double accuracy = 1e3;
    static {
        Fx<Double> f = (x) -> 1 / (1 + (x * x));
        a = 1; b = 4;
        xs = new double[]{1,1.5,2,3,3.5,4};
        t = new Trapezoid(f,xs);
    }


    @Test
    @DisplayName("Integrate")
    void Integrate() {
    }

    @Test
    @DisplayName("NRule")
    void NRule() {
    }


    @Test
    @DisplayName("F")
    void F() {
    }

    @Test
    @DisplayName("SetF")
    void SetF() {
    }



    @Test
    @DisplayName("Step")
    void Step() {
        assertTrue(abs(0.1951219512195122-t.step(xs[0],xs[1]))<accuracy);
        assertTrue(abs(0.3181988742964353-t.step(xs[1],xs[2]))<accuracy);
        assertTrue(abs(0.4561299087791939-t.step(xs[2],xs[3]))<accuracy);
        assertTrue(abs(0.49937315202243715-t.step(xs[3],xs[4]))<accuracy);
        assertTrue(abs(0.5325681727693251-t.step(xs[4],xs[5]))<accuracy);
    }

    @Test
    @DisplayName("Solve")
    void Solve() {
        //assertEquals(t.integrate(),t.nRule(xs[0],xs[xs.length-1]));
        System.out.println(t.integrate());
    }
}
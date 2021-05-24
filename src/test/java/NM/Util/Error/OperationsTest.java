package NM.Util.Error;

import NM.Util.functions.Fx;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationsTest {

    @Test
    @DisplayName("CubeSubtract")
    void CubeSubtract() {
        double x = 427;
        double y = 426;
        double exact = 545707;
        double cubeSum = pow(x, 3) + pow(y, 3);
        double optimized = Operations.cubeSubtract(x, y);
        double err1 = Error.relativeError(exact, cubeSum);
        double err2 = Error.relativeError(exact, optimized);
        assertTrue(err2 < err1);
    }

    @Test
    @DisplayName("Completing the square")
    void CompletingTheSquare() {
        double a = 0.5;
        double b = 2;
        double c = 0.005;
        double squareRoots = Operations.findRoot(a, b, c);
        double improved = Operations.completingTheSquare(a, b, c);
        double exact = 0.02484567;
        double err1 = Error.absoluteError(exact, squareRoots);
        double err2 = Error.absoluteError(exact, improved);
        double E1 = err1;
        double E2 = err2;
        assertTrue(() -> (E1 <= E2) || (abs(E1 - E2) < 1e-6));
        err1 = Error.relativeError(exact, squareRoots);
        err2 = Error.relativeError(exact, improved);
        double E3 = err2;
        double E4 = err1;
        assertTrue(() -> (E3 <= E4) || (abs(E3 - E4) < 1e-6));
    }

    @Test
    @DisplayName("HornersRule")
    void HornersRule() {
        double exact = 1.282355;
        double[] c = new double[]{1, -5.34, 1.52, 4.61};
        double x = 4.89;
        double p = Operations.polynomial(c, x);
        Fx<Double> h = Operations.hornersCube(c[0], c[1], c[2], c[3]);
        Fx<Double> hornerComplete = Operations.hornerFormula(c);
        double hornerP = h.f_x(x);
        double complete = hornerComplete.f_x(x);
        double e1 = Error.relativeError(exact, p);
        double e2 = Error.relativeError(exact, hornerP);
        assertTrue(e2 < e1);
        assertEquals(hornerP, complete);
    }
}
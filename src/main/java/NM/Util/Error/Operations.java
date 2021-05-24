package NM.Util.Error;

import NM.Util.functions.Fx;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Operations {

    public static double cubeSubtract(double aToCube, double bToCube) {
        return (bToCube - aToCube) * (pow(aToCube, 2) + (aToCube * bToCube) + pow(bToCube, 2));
    }


    public static double completingTheSquare(double a, double b, double c) {
        return (-2 * c) / (sqrt(pow(b, 2) - (4 * a * c)) + b);
    }


    public static double findRoot(double a, double b, double c) {
        return (sqrt(pow(b, 2) - (4 * a * c)) - b) / (2 * a);
    }

    public static Fx<Double> hornersCube(double c1, double c2, double c3, double c4) {
        return x -> (((c1 * x) + c2) * x + c3) * x + c4;
    }

    public static Fx<Double> hornerFormula(double... coeff) {
        if (coeff.length < 3) throw new RuntimeException("not ready");
        return x -> {
            double s = (coeff[0] * x) + coeff[1];
            for (int i = 2; i < coeff.length; i++) {
                s *= x;
                s += coeff[i];
            }
            return s;
        };
    }


    public static double polynomial(double[] coefficients, double x) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            int k = (coefficients.length - i) - 1;
            sum += pow(x, k) * coefficients[i];
        }
        return sum;
    }


}

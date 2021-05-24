package NM.Integration;

import NM.Util.functions.Fx;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.StrictMath.*;

public class AdaptiveSimpsons extends Simpsons {

    double epsilon = 0.00001;

    public AdaptiveSimpsons(Fx<Double> f, double... xs) {
        super(xs.length - 1, f, xs);
    }

    public AdaptiveSimpsons(int n, Fx<Double> f, double... xs) {
        super(n, f, xs);
    }

    public static void main(String[] args) {
        Fx<Double> f = (x) -> 1 / (1 + pow(x, 2));
        AdaptiveSimpsons a = new AdaptiveSimpsons(f, 0, 2);
        // System.out.println(a.nRule(0,2.));


        f = x -> cos(2 * x) / (3 + (2 * sin(x)));
        a = new AdaptiveSimpsons(f);
        System.out.println(a.nRule(-PI, PI));
    }

    public double nRule(double a, double b) {
        double t1 = step(a, b);

        double mid = (b + a) / 2;
        double t2 = step(a, mid) + step(mid, b);
        if (abs(t2 - t1) > ((mid - a) / (b - a)) * epsilon)
            return nRule(a, mid) + nRule(mid, b);
        return t2;
    }

    @Override
    public double step(double p, double q) {
        double step = ((q - p) / 12) * (f(p) + 4 * f((3 * p + q) / 4) + 2 * f((p + q) / 2) + 4 * f((p + 3 * q) / 4) + f(q));
        return step;
    }

}

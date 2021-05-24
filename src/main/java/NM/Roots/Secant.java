package NM.Roots;



import NM.Util.functions.Fx;

import static java.lang.Double.NaN;

public class Secant extends Root {
    public Secant(Fx<Double> f) {
        super(f);
    }

    @Override
    public double findRoot(double a, double b) {
        double r = NaN;
        while (Math.abs(b - a) > epsilon) {
            r = b - ((b - a) / (f.f_x(b) - f.f_x(a)) * f.f_x(b));
            this.p_stages.add(r);
            a = b;
            b = r;
        }
        return this.root = r;
    }

    @Override
    public double findRoot(double p) {
        throw new UnsupportedOperationException();
    }
}

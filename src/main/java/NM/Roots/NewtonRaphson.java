package NM.Roots;

import NM.Util.functions.Fx;

import static java.lang.StrictMath.abs;

public class NewtonRaphson extends Root {

    public NewtonRaphson(Fx<Double> f) {
        super(f);
    }

    @Override
    public double findRoot(double a, double b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double findRoot(double p) {
        double p1 = p - (f.f_x(p) / f.derivative(p, 1e-3));
        this.p_stages.add(p1);
        if (abs(p1 - p) > epsilon) return findRoot(p1);
        return this.root = p1;
    }
}

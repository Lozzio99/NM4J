package NM.Roots;

import NM.Util.functions.Fx;

import static java.lang.StrictMath.signum;

public class Bisection extends Root {

    public Bisection(Fx<Double> f) {
        super(f);
    }

    @Override
    public double findRoot(double a, double b) {
        double steps = 0;
        while ((b - a) / 2 > epsilon) {
            double c = (a + b) / 2;
            this.p_stages.add(c);
            if (signum(f.f_x(c)) == signum(f.f_x(a))) {
                a = c;
            } else {
                b = c;
            }
            steps++;
        }
        return this.root = ((a + b) / 2);
    }

    @Override
    public double findRoot(double p) {
        throw new UnsupportedOperationException();
    }


}

package NM.Solvers.RungeKutta;

import NM.Solvers.Solver;
import NM.Util.functions.Fty;

public class Ralston_s2nd extends Solver {
    public Ralston_s2nd(Fty<Double> f) {
        super(f);
    }

    public static double Ralston2ndOrderStep(Fty<Double> f, double w, double h, double t) {
        double dh = (2 * h / 3);
        double k = w + dh * f.f(t, w);
        double k2 = f.f(t, w) + (3 * f.f((t + dh), w + k));
        return w + ((h / 4) * k2);
    }

    @Override
    public void step(double w, double h) {
        double k1 = h * this.f.f(this.t, w);
        double k2 = h * this.f.f(this.t + ((2 / 3.) * h), w + ((2 / 3.) * k1));
        this.w = w + ((1 / 4.) * (k1 + 3 * k2));
    }


}

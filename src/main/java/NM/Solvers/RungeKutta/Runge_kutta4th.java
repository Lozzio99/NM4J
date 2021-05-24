package NM.Solvers.RungeKutta;

import NM.Solvers.Solver;
import NM.Util.functions.Fty;

public class Runge_kutta4th extends Solver {
    public Runge_kutta4th(Fty<Double> function) {
        super(function);
    }

    public static double RungeKutta4thStep(Fty<Double> f, double t, double w, double h) {
        double k1 = h * f.f(t, w);
        double k2 = h * f.f(t + (h / 2), w + (k1 / 2));
        double k3 = h * f.f(t + (h / 2), w + (k2 / 2));
        double k4 = h * f.f(t + h, w + k3);
        return w + ((1 / 6.) * (k1 + 2 * k2 + 2 * k3 + k4));
    }

    @Override
    public void step(double w, double h) {
        double k1 = h * this.f.f(this.t, w);
        double k2 = h * this.f.f(this.t + (h / 2), w + (k1 / 2));
        double k3 = h * this.f.f(this.t + (h / 2), w + (k2 / 2));
        double k4 = h * this.f.f(this.t + h, w + k3);
        this.w = w + ((1 / 6.) * (k1 + 2 * k2 + 2 * k3 + k4));
    }
}

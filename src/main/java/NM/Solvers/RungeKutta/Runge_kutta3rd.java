package NM.Solvers.RungeKutta;

import NM.Solvers.Solver;
import NM.Util.functions.Fty;

public class Runge_kutta3rd extends Solver {

    /**
     * Constructor,
     * set starting time t = 0;
     *
     * @param f
     */
    public Runge_kutta3rd(Fty<Double> f) {
        super(f);
    }

    public static double RungeKutta3rdStep(Fty<Double> f, double w, double h, double t) {

        if (PRINT_STEPS) {
            System.out.print("\nRungeKutta 3rd order : ");
            System.out.println(" k1 = hf(t,w);  k2 = hf(t+ h/2, w+ k1/2);  k2 = hf(t+h, w-k1 + 2k2); ");
        }
        double k1 = h * f.f(t, w);
        double k2 = h * f.f(t + (h / 2), w + (k1 / 2));
        double k3 = h * f.f(t + h, w - k1 + (2 * k2));
        if (PRINT_STEPS) {
            System.out.println("k1 : " + k1 + " k2 : " + k2 + " k3 : " + k3);
            System.out.println(" w1 = w + 1/6 (k1 + 4k2 + k3) ");
        }
        return w + (k1 + (4 * k2) + k3) / 6.;
    }

    @Override
    public void step(double w, double h) {
        double k1 = h * this.f.f(t, w);
        double k2 = h * this.f.f(t + (h / 2), w + (k1 / 2));
        double k3 = h * this.f.f(t + h, w - k1 + (2 * k2));
        this.w = w + (k1 + (4 * k2) + k3) / 6.;
    }
}

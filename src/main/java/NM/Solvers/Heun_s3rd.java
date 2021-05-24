package NM.Solvers;

import NM.Util.functions.Fty;

public class Heun_s3rd extends Solver {

    /**
     * Constructor,
     * set starting time t = 0;
     *
     * @param f
     */
    public Heun_s3rd(Fty<Double> f) {
        super(f);
    }

    @Override
    public void step(double w, double h) {
        double k1 = h * this.f.f(t, w);
        double k2 = h * this.f.f(t + (h / 3), w + (k1 / 3));
        double k3 = h * this.f.f(t + (h / 3 * 2), w + (k2 / 3 * 2));
        this.w = w + (k1 + (3 * k3)) / 4;
    }
}

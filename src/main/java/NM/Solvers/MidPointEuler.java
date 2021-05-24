package NM.Solvers;

import NM.Util.functions.Fty;

public class MidPointEuler extends Solver {

    /**
     * Constructor,
     * set starting time t = 0;
     *
     * @param f
     */
    public MidPointEuler(Fty<Double> f) {
        super(f);
    }

    @Override
    public void step(double w, double h) {
        this.w = w + h * this.f.f_y((this.t + h / 2), w + (h / 2) * f.f_y(t, w));
    }


}

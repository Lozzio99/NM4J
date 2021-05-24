package NM.Solvers;

import NM.Util.functions.Fty;

public class TrapezoidEuler extends Solver {


    /**
     * Constructor,
     * set starting time t = 0;
     *
     * @param f
     */
    public TrapezoidEuler(Fty<Double> f) {
        super(f);
    }

    @Override
    public void step(double y, double dt) {
        this.w = y + (dt / 2) * (this.f.f_y(t, y) + this.f.f_y(this.t + dt, y + dt * (this.f.f_y(t, y))));
    }
}

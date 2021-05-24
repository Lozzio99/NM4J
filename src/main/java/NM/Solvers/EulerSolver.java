package NM.Solvers;

import NM.Util.functions.Fty;

public class EulerSolver extends Solver {
    /**
     * Constructor,
     * set starting time t = 0;
     *
     * @param f
     */
    public EulerSolver(Fty<Double> f) {
        super(f);
    }

    @Override
    public void step(double y, double dt) {
        this.w = y + (this.f.f(this.t, y) * dt);
    }

}

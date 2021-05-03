package NM.Solvers;

public class EulerSolver extends Solver {


    @Override
    public void step(double y, double dt) {
        this.w = y + (this.f.f_y(this.t,y)*dt);
    }

}

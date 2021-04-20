package Ivp.Solvers;

public class TrapezoidEuler extends Solver {


    @Override
    public void step(double y, double dt) {
        this.w = y + (dt/2)*(this.f.f_y(t,y)+this.f.f_y(this.t+dt,y + dt*(this.f.f_y(t,y))));
    }
}

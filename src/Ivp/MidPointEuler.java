package Ivp;

public class MidPointEuler extends Solver
{

    @Override
    public void step(double w, double h) {
        this.w = w + h * this.f.f_y ((this.t + h/2),w + (h/2) * f.f_y(t,w));
    }


}

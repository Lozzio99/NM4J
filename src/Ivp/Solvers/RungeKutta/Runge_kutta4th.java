package Ivp.Solvers.RungeKutta;

import functions.fYT;
import Ivp.Solvers.Solver;

public class Runge_kutta4th extends Solver
{


    @Override
    public void step(double w, double h)
    {
        double k1 = h * this.f.f_y(this.t,w);
        double k2 = h * this.f.f_y(this.t + (h/2), w + (k1/2));
        double k3 = h * this.f.f_y(this.t + (h/2), w + (k2/2));
        double k4 = h * this.f.f_y(this.t+h,w + k3);
        this.w = w + ((1/6.) * (k1 + 2*k2 + 2*k3 + k4));
    }


    public static double RungeKutta4thStep(fYT f, double t , double w , double h)
    {
        double k1 = h * f.f_y(t,w);
        double k2 = h * f.f_y(t + (h/2), w + (k1/2));
        double k3 = h * f.f_y(t + (h/2), w + (k2/2));
        double k4 = h * f.f_y(t+h,w + k3);
        return  w + ((1/6.) * (k1 + 2*k2 + 2*k3 + k4));
    }
}

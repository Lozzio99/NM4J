package Ivp.RungeKutta;

import Ivp.Function;
import Ivp.Solver;

public class Runge_kutta3rd extends Solver
{

    @Override
    public void step(double w, double h)
    {
        double k1 = h * this.f.f_y(t,w);
        double k2 = h * this.f.f_y(t + (h/2), w + (k1/2));
        double k3 = h * this.f.f_y(t + h, w-k1 + (2*k2));
        this.w = w + ( k1 + (4*k2) + k3)/6.;
    }

    public static double RungeKutta3rdStep(Function f, double w, double h, double t){
        double k1 = h * f.f_y(t,w);
        double k2 = h * f.f_y(t + (h/2), w + (k1/2));
        double k3 = h * f.f_y(t + h, w-k1 + (2*k2));
        return  w + ( k1 + (4*k2) + k3)/6.;
    }
}

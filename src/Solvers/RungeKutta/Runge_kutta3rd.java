package Solvers.RungeKutta;

import Util.functions.fYT;
import Solvers.Solver;

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

    public static double RungeKutta3rdStep(fYT f, double w, double h, double t){

        System.out.print("\nRungeKutta 3rd order : ");
        System.out.println(" k1 = hf(t,w);  k2 = hf(t+ h/2, w+ k1/2);  k2 = hf(t+h, w-k1 + 2k2); ");
        double k1 = h * f.f_y(t,w);
        double k2 = h * f.f_y(t + (h/2), w + (k1/2));
        double k3 = h * f.f_y(t + h, w-k1 + (2*k2));
        System.out.println("k1 : "+k1+ " k2 : "+k2 + " k3 : "+k3);
        System.out.println(" w1 = w + 1/6 (k1 + 4k2 + k3) ");
        return  w + ( k1 + (4*k2) + k3)/6.;
    }
}

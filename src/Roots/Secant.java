package Roots;



import Ivp.Solvers.RungeKutta.Ralston_s2nd;
import Ivp.Solvers.RungeKutta.Runge_kutta4th;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

public class Secant extends Root
{
    @Override
    public double findRoot(double a, double b)
    {
        double r = NaN;
        while(Math.abs(b-a)>epsilon)
        {
            r = b - ((b-a)/(f.f_x(b)-f.f_x(a))*f.f_x(b));
            this.p_stages.add(r);
            a = b;
            b = r;
        }
        return r;
    }

    @Override
    public double findRoot(double p) {
        return 0;
    }



}

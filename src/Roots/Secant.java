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


    public static double findSpecialRoot(Ivp.Function f, double t1, double t2, double w1, double w2)
    {
        double root = NaN;
        double epsilon = 0.001;
        List<Double> p_stages = new ArrayList<>();
        System.out.println("between t [ "+ t1 + "  " + t2 + " ]");
        System.out.println(" f(a) = "+ w1 + "  , f(b) = "+ w2);
        double prevt = t1, prevw = w1;
        while(Math.abs(t2-t1)>epsilon)
        {
            root = t2 - ((t2-t1)/(w2-w1)*w2);
            p_stages.add(root);
            t1 = t2;
            t2 = root;
            w2 = Ralston_s2nd.Ralston2ndOrderStep(f,prevw,t2-prevt,t2);
            w1 = Ralston_s2nd.Ralston2ndOrderStep(f,prevw,t1-prevt,t1);
            System.out.println("between t [ "+ t1 + "  " + t2 + " ]");
            System.out.println(" f(a) = "+ w1 + "  , f(b) = "+ w2);
        }
        return root;
    }
}

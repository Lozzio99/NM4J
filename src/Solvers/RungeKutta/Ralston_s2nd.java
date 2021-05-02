package Solvers.RungeKutta;

import Util.functions.fYT;
import Solvers.Solver;

public class Ralston_s2nd extends Solver {
    @Override
    public void step(double w, double h)
    {
        double k1 = h * this.f.f_y(this.t,w);
        double k2 = h * this.f.f_y(this.t + ((2/3.) * h),w + ((2/3.) * k1));
        this.w = w + ((1/4.)*(k1 + 3*k2));
    }

    public static double Ralston2ndOrderStep(fYT f, double w, double h, double t)
    {
        double k1 = h * f.f_y(t,w);
        //System.out.print("k1\t: "+ k1+"\t");
        double k2 = h * f.f_y(t + ((2/3.) * h),w + ((2/3.) * k1));
        //System.out.print("\tk2\t: "+k2 + "\t\t");
        double next = w + ((1/4.)*h *(k1 + 3*k2));
        //System.out.println("w(i+1)\t: "+next);
        return next;
    }


}

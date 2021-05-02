package Fourier;

import Graph.Plot;
import Polynomials.Interpolation;
import Util.functions.fX;

import static java.lang.Math.*;

public class FourierSeries
{
    int n;
    fX s;

    public FourierSeries(int n)
    {
        this.n = n;

        this.s = (x)->{
          double sum = 0;
          for (int i = 1; i<=n; i++)
              sum+= pow(-1,i-1)* (2./i)*sin(i*x);
          return sum;
        };
    }

    public double s(double x){
        return this.s.f_x(x);
    }


    public static void main(String[] args) {
        FourierSeries f = new FourierSeries(6),f2 = new FourierSeries(104);
        double [] xs = Interpolation.linX(-5*PI,5*PI,300);
        double [] ys = new double [300],ys2 = new double[300];
        for (int i = 0; i< ys.length; i++)
        {
            ys[i] = f.s(xs[i]);
            ys2[i] = f2.s(xs[i]);
        }
        Plot p = new Plot();
        p.scale(40);
        p.plot(xs,ys);
        p.plot2(xs,ys2);
    }

}

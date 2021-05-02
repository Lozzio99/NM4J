package Integration;

import Polynomials.Lagrange;
import functions.fX;

import static java.lang.Math.pow;

public class Simpsons extends Integrator
{
    Lagrange l;

    public Simpsons(Lagrange g)
    {
        super((x)->x);
        this.l = g;
    }

    public Simpsons (fX f, double ... x){
        super(f,x);
    }

    @Override
    double integrate(int a, int b) {
        return 0;
    }

    public double step(double a,double b)
    {
        return ((b-a)/6) * (l.polynomial(a) + 4*l.polynomial((a+b)/2) + l.polynomial(b));
    }

    public double highStep(double a, double b)
    {
        double h =(b-a)/(xs.length-1);
        h /= 3.;
        double sum = f.f_x(xs[0]);
        for (int i = 1;i< xs.length-1; i++)
        {
            if (i%2 != 0)
                sum+= (4*f.f_x(xs[i]));
            else
                sum+= (2*f.f_x(xs[i]));
        }
        sum += f.f_x(xs[xs.length-1]);
        return h*sum;
    }


    public double threeEightStep(double a, double b)
    {
        double h =(b-a)/(xs.length-1);
        h *= 3/8.;
        double sum = f.f_x(xs[0]);
        for (int i = 1;i< xs.length-1; i++)
        {
            sum+= (3*f.f_x(xs[i]));
        }
        sum += f.f_x(xs[xs.length-1]);
        return h*sum;
    }

    public static void main(String[] args) {
        fX f = (x)-> 1/(1+pow(x,2));
        Simpsons s = new Simpsons(f,1,1.5,2,2.5,3,3.5,4.0);
        System.out.println(s.threeEightStep(1,4));
    }
}

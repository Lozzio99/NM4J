package NM.Integration;
import NM.Util.functions.Fx;

public class Trapezoid extends Integrator
{
    public Trapezoid(int nodes, Fx f, double... xs) {
        super(nodes, f, xs);
    }

    public Trapezoid(Fx f, double... xs) {
        super(xs.length - 1, f, xs);
    }

    public Trapezoid(int nodes, Fx f, double a, double b) {
        super(nodes, f, a, b);
    }


    /**
     *  Composite rule,
     *  * equally spaced nodes,
     *  Tn[a,b] =  h ( (fx0/2) +  fx1 +  fx2 +  fx3 +  fx4 +  fx5  +  (fx6/2))
     */
    @Override
    public double nRule(double a, double b) {
        double sum = f(xs[0])/2 + f(xs[xs.length-1])/2; // f0,fn
        if (DEBUG)
            System.out.println(xs[0] + " -> "+ f(xs[0]));
        for (int i = 1; i< xs.length-1; i++)
        {
            if (DEBUG)
                System.out.println(xs[i] + " -> "+ f(xs[i]));
            sum+=f(xs[i]);
        }
        if (DEBUG)
            System.out.println(xs[xs.length-1] + " -> "+ f(xs[xs.length-1]));
        return sum*((b-a)/nodes);  // /h
    }


    public double step(double a, double b)
    {
        return (b-a) * ((f.f_x(a) + f.f_x(b))/2);
    }


}

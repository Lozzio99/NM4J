package NM.Integration;

import NM.Polynomials.Lagrange;
import NM.Util.functions.Fx;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class Simpsons extends Integrator {
    private Lagrange l;

    public Simpsons(Lagrange g) {
        super((x) -> x);
        this.l = g;
    }

    public Simpsons(Fx<Double> fX, double... x) {
        super(x.length - 1, fX, x);
    }

    public Simpsons(int n, Fx<Double> f, double... xs) {
        super(n, f, xs);
    }

    public Simpsons(int nodes, Fx<Double> f, double a, double b) {
        super(nodes, f, a, b);
    }


    /**
     *  Composite rule,
     *  * equally spaced nodes,
     *  * even number of partitions
     *  Sn[a,b] =  h/3 ( fx0 +  4fx1 +  2fx2 +  4fx3 +  2fx4 +  4fx5  +  fx6)
     */
    @Override
    public double nRule(double a, double b) {
        double h = ((b-a)/(nodes))/3;
        double sum = f(xs[0]);
        if (DEBUG)
            System.out.println(xs[0] + " -> "+ f(xs[0]));
        for (int i = 1;i< nodes; i++)
        {
            if (DEBUG)
                System.out.println(xs[i] + " -> "+ f(xs[i]));
            if (i%2 != 0)
                sum+= (4*f(xs[i]));
            else
                sum+= (2*f(xs[i]));
        }
        if (DEBUG)
            System.out.println(xs[xs.length-1] + " -> "+ f(xs[xs.length-1]));
        sum += f(xs[xs.length-1]);
        return h*sum;
    }



    /**
     *  Step for non-equal subdivisions
     */
    public double step(double a,double b) {
        return ((b-a)/6) * (f(a) +  4* f((a+b)/2) +  f(b));
    }

    /**
     * Step with derived polynomial basis (non equal subdivisions)
     */
    public double lagrangeStep(double a, double b){
        return ((b-a)/6) * (l.polynomial(a) + 4*l.polynomial((a+b)/2) + l.polynomial(b));
    }

    /**
     *  Composite rule, three partitions step
     *  * equally spaced nodes,
     *  * odd number of partitions
     *  Sn[a,b] =  3h/8 ( fx0 +  3fx1 +  3fx2  +  fx4)
     */
    public double threeEightStep(double a, double b)
    {
        double h =(b-a)/(xs.length-1);
        h *= 3/8.;
        double sum = f(xs[0]);
        if (DEBUG)
            System.out.println(xs[0] + " -> "+ f(xs[0]));
        for (int i = 1;i< xs.length-1; i++)
        {
            if (DEBUG)
                System.out.println(xs[i] + " -> "+ f(xs[i]));
            sum+= (3*f(xs[i]));
        }
        if (DEBUG)
            System.out.println(xs[xs.length-1] + " -> "+ f(xs[xs.length-1]));
        sum += f(xs[xs.length-1]);
        return h*sum;
    }


    public static void main(String[] args) {
        Fx<Double> f = (x) -> 1 / (1 + pow(x, 2));
        Simpsons s = new Simpsons(f, 1, 1.5, 2, 2.5, 3, 3.5, 4.0);
        //System.out.println(s.threeEightStep(1,4));
        //System.out.println(s.integrateStep(1,4));
        System.out.println(s.integrate());
        System.out.println(s.nRule(1, 4));
        //System.out.println(s.integrate(1,4));

        double[] xs = new double[201];
        double h = (2 * PI) / 200;
        double a = -PI;
        for (int i = 0; i < 201; i++) {
            xs[i] = a;
            a += h;
        }
        System.out.println(xs[xs.length - 1]);
        System.out.println(new Simpsons(f, xs).nRule(-PI, PI));
    }
}

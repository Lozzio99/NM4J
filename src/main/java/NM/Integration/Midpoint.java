package NM.Integration;

import NM.Util.functions.fX;

public class Midpoint extends Integrator
{
    public Midpoint( fX f, double... xs)
    {
        super(xs.length-1,f,xs);
    }



    public Midpoint(int n, fX f, double...xs){
        super(n,f,xs);
    }

    public Midpoint( int nodes, fX f , double a , double b)
    {
        super(nodes,f,a,b);
    }

    /**
     *  Composite rule,
     *  * equally spaced nodes,
     *  Mn[a,b] =  h ( f[(x0 + x1)/2] +  f[(x1 + x2)/2] + ...  +  f[(xn-1 + xn)/2])
     */
    @Override
    public double nRule(double a, double b) {
        double sum =0; //h
        for (int i = 0; i< nodes; i++)
        {
            if (DEBUG)
            {
                System.out.println(xs[i] + " -> "+f(xs[i]));
                //System.out.println(" Mid : " +xk[i] + " fX  -> "+f(xk[i]));
                //System.out.println(" I"+i+" : " + sum);
            }
            sum+= f((xs[i] +xs[i+1])/2);
        }
        System.out.println(xs[xs.length-1] + " -> "+f(xs[xs.length-1]));
        return sum *  ((b-a)/nodes);   // h
    }

    @Override
    public double step(double a, double b) {
        return (b-a)*f((b+a)/2);
    }

    public static void main(String[] args)
    {

        fX f = (x)-> 1/(1+ (x*x));
        int a = 1,b = 4;
        Midpoint m = new Midpoint(f, 1,1.5,2,3,3.5,4);
        System.out.println(m.nRule(a,b));
         /*
        fX f = Math::cos;
        double [] xs = Interpolation.linX(-1,1,100);
        Midpoint m = new Midpoint(f,xs);
        System.out.println(m.integrate());
        System.out.println(m.integrate(-1,1));

         */
    }

}

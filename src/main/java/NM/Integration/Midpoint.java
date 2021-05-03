package NM.Integration;

import NM.Util.functions.fX;

public class Midpoint extends Integrator
{
    public Midpoint( fX f, double... xs)
    {
        super(f,xs);
        this.nodes = xs.length-1;
    }

    @Override
    double integrate(int a, int b) {
        return 0;
    }

    public double integrate()
    {
        double integral = 0;
        double [] xk = new double[nodes];
        for (int i = 0; i< nodes; i++) {
            xk[i] = (this.xs[i+1]+this.xs[i])/2 ;
            System.out.println(xk[i] + " -> " + f.f_x(xk[i]));
        }
        for (int i = 0; i< nodes; i++){
            integral+= (this.xs[i+1]-this.xs[i])* f.f_x(xk[i]);
        }
        return integral;
    }

    public static void main(String[] args)
    {
        fX f = (x)-> 1/(1+ (x*x));
        int a = 1,b = 4;
        Midpoint m = new Midpoint(f, 1,1.5,2,3,3.5,4);
        System.out.println(m.integrate(a,b));
    }

}

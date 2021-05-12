package NM.Integration;

import NM.Util.functions.fX;

public abstract class Integrator
{
    protected double [] xs,xk;
    public fX f;
    protected int nodes;

    protected final boolean DEBUG = false;

    public Integrator(int nStep, fX f, double... xs)
    {
        this.nodes = nStep;
        this.f = f;
        this.xs = xs;
        if (nodes>= 0)
            this.xk = new double[nodes];
    }
    public Integrator(fX f, double...xs)
    {
        this(xs.length-1,f,xs);
    }

    public Integrator( int nodes, fX f , double a , double b)
    {
        this(nodes,f);
        this.xs = new double[nodes+1];
        double h = (b-a)/nodes;
        double a1 = a;
        for (int i = 0; i< xs.length-1; i++) {
            xs[i] = a1;
            xk[i] = ((xs[i]+h)-xs[i])/2;
            a1+= h;
        }
        xs[xs.length-1] = b;
    }


    public double integrate()  {
        assert nodes != 0;
        double integral = 0;
        for (int i = 0; i< nodes; i++) {
            xk[i] = (this.xs[i+1]+this.xs[i])/2 ;
            integral+= step(xs[i],xs[i+1]);
            if (DEBUG)
            {
                System.out.println(xs[i] + " -> "+f(xs[i]));
                System.out.println(xs[i+1]+ " -> "+f(xs[i+1]));
                System.out.println(" Mid : " + xk[i]+ " fX  -> "+f(xk[i]));
                System.out.println(" I"+i+" : " + integral);
            }
        }
        return integral;
    }

    public abstract double nRule(double a, double b);

    abstract double step(double a, double b);

    public double f(double x){
        return f.f_x(x);
    }

    public void setF(fX f) {
        this.f = f;
    }
}

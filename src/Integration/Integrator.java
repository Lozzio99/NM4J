package Integration;

import functions.fX;

public abstract class Integrator
{
    protected double [] xs;
    protected fX f;
    protected int nodes;
    public Integrator(fX f, double...xs){
        this.f = f;
        this.xs = xs;
    }
    abstract double integrate(int a,int b);
}

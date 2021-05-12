package NM.Polynomials;

import NM.Integration.Simpsons;
import NM.Util.functions.fX;

public class Legendre extends AbstractLegendre
{

    private int degree = 1;
    public Legendre(fX f, double a, double b, int degree)
    {
        this.f = f;
        this.a = a;
        this.b = b;
        this.degree = degree;
        integrator= new Simpsons(20,this.f, a,b);
        fitPs();
        fitCs();
    }
    private void fitPs(){
        this.p = new fX[degree+1];
        this.p[0] = x->1;
        this.p[1] = x->x;
        for (int i = 2; i< p.length; i++) {
            double k = i;
            int j = i;
            p[i] = x-> (2-(1/k))*x*p[j-1].f_x(x) - (1-(1/k))*p[j-2].f_x(x);
        }
    }

    private void fitCs(){
        this.c = new double[this.p.length];
        this.c[0] = this.integrator.nRule(-1,1)/2;
        for (int i = 0; i< this.c.length; i++){
            int k = i;
            this.integrator.setF( x -> this.f.f_x(x)* p[k].f_x(x));
            this.c[i] = (k+(1/2.))* this.integrator.integrate();
        }
    }

    /**
     * p(x) = c0 p0(x) + c1 p1(x)... + cn pn(x)
     */
    public double g(double x){
        double sum = 0;
        for (int i =0; i< this.c.length; i++) sum += this.c[i] * this.p[i].f_x(x);
        return sum;
    }

    public fX g() {
        return this::g;
    }

    public double [] g(double []x ){
        double [] g = new double [x.length];
        for (int i = 0; i< g.length; i++)
            g[i] = g(x[i]);
        return g;
    }




}
